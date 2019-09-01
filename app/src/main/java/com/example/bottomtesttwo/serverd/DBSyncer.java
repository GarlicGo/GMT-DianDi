package com.example.bottomtesttwo.serverd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.fragments.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DBSyncer {

    private static DBSyncer instance = new DBSyncer();
    private DBSyncer (){}
    public static DBSyncer getSyncer() {
        return instance;
    }

    public void start(int UserID) {
        DownloadDBTask downloadDBTask = new DownloadDBTask(UserID);
        downloadDBTask.execute();
    }

    private class DownloadDBTask extends AsyncTask<Void, Integer, Boolean> {
        private int UserID;
        public DownloadDBTask(int UserID) {
            this.UserID = UserID;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("dbUser", "root")
                    .add("dbPwd", "GMT415263")
                    .add("dbName", "gmt_dads")
                    .add("UserID", String.valueOf(UserID))
                    .build();

            Request request = new Request.Builder()
                    .url("http://114.55.252.35/syncData.php")
                    .post(requestBody)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String queryResult = response.body().string();
                Log.d("MYX", queryResult);
                response.close();
                parseData(queryResult);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean == true) {
                DBOperator dbOperator = DBOperator.getOperator();
                Cursor cursor = dbOperator.Query( "select * from user_info");
                cursor.moveToFirst();
                long status = cursor.getInt(cursor.getColumnIndex("id"));
                cursor.close();
                cursor = dbOperator.Query("select * from card_records where user_info_id='"+status+"'");
                if(cursor.moveToFirst()){

                }else {
                    for (int i = 1; i <= 6; i++) {
                        String title = "错误";
                        switch (i){
                            case 1:
                                title = "现金";
                                break;
                            case 2:
                                title = "储蓄卡";
                                break;
                            case 3:
                                title = "信用卡";
                                break;
                            case 4:
                                title = "支付宝";
                                break;
                            case 5:
                                title = "微信";
                                break;
                            case 6:
                                title = "饭卡";
                                break;
                        }
                        dbOperator.Cud("insert into card_records (id,icon,title,balance,user_info_id) values ('"
                                +status+""+i+"','"+i+"','" +title+"','0','" +status+"')");
                    }
                }

                Intent intent = new Intent(LoginActivity.instance, MainActivity.class);
                LoginActivity.instance.startActivity(intent);
                LoginActivity.instance.finish();
                Log.d("MYX", "DBSyncer: success");
                Log.d("ZXYY", "status:"+status);
            } else {
                Log.d("MYX", "DBSyncer: failed");
            }
        }

        private boolean parseData(String queryResult) {
            try {
                File file = new File("data/data/com.example.bottomtesttwo/databases");
                SQLiteDatabase db;
                if (file.exists()) {
                    db = SQLiteDatabase.openOrCreateDatabase(file.getPath()+"/gmt_dads.db", null);
                } else {
                    file.mkdirs();
                    db = SQLiteDatabase.openOrCreateDatabase(file.getPath()+"/gmt_dads.db", null);
                }
                JSONObject jsonObject = new JSONObject(queryResult);
                JSONObject User_Info;
                if (jsonObject.getString("User_Info".toLowerCase()).equals("null")) {
                    User_Info = null;
                } else {
                    User_Info = jsonObject.getJSONObject("User_Info".toLowerCase());
                }
                syncUserInfo(db, User_Info);
                JSONArray Account_Records = jsonObject.getJSONArray("Account_Records".toLowerCase());
                syncAccountRecords(db, Account_Records);
                JSONArray Card_Records = jsonObject.getJSONArray("Card_Records".toLowerCase());
                syncCardRecords(db, Card_Records);
                JSONArray Saving_Records = jsonObject.getJSONArray("Saving_Records".toLowerCase());
                syncSavingRecords(db, Saving_Records);
                JSONArray Amount_Changes = jsonObject.getJSONArray("Amount_Changes".toLowerCase());
                syncAmountChanges(db, Amount_Changes);
                JSONArray Plan_Info = jsonObject.getJSONArray("Plan_Info".toLowerCase());
                syncPlanInfo(db, Plan_Info);
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        private boolean syncUserInfo(SQLiteDatabase db, JSONObject User_Info) {
            db.execSQL("drop table if exists user_info");
            db.execSQL("create table user_info(" +
                    "id integer primary key autoincrement," +
                    "avatar blob," +
                    "username text," +
                    "password text," +
                    "gender integer," +
                    "personalSignature text," +
                    "birthday text," +
                    "phoneNumber text," +
                    "email text," +
                    "SSI text," +
                    "SSIAnswer text," +
                    "SSIHint text)");
            try {
                if (User_Info != null) {
                    ContentValues values = new ContentValues();
                    values.put("id", User_Info.getInt("id"));
                    values.put("avatar", User_Info.getString("avatar").getBytes());
                    values.put("username", User_Info.getString("username"));
                    values.put("password", User_Info.getString("password"));
                    values.put("gender", User_Info.getString("gender"));
                    values.put("personalSignature", User_Info.getString("personalSignature"));
                    values.put("birthday", User_Info.getString("birthday"));
                    values.put("phoneNumber", User_Info.getString("phoneNumber"));
                    values.put("email", User_Info.getString("email"));
                    values.put("SSI", User_Info.getString("SSI"));
                    values.put("SSIAnswer", User_Info.getString("SSIAnswer"));
                    values.put("SSIHint", User_Info.getString("SSIHint"));
                    db.insert("user_info", null, values);
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        private boolean syncAccountRecords(SQLiteDatabase db, JSONArray Account_Records) {
            db.execSQL("drop table if exists account_records");
            db.execSQL("create table account_records(" +
                    "id integer primary key autoincrement," +
                    "icon integer," +
                    "title text," +
                    "description text," +
                    "accountNumber text," +
                    "username text," +
                    "password text," +
                    "email text," +
                    "phoneNumber text," +
                    "IDNumber text," +
                    "user_info_id integer)");
            try {
                for (int i=0; i<Account_Records.length(); i++) {
                    JSONObject curRow = Account_Records.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("id", curRow.getLong("id"));
                    values.put("icon", curRow.getInt("icon"));
                    values.put("title", curRow.getString("title"));
                    values.put("description", curRow.getString("description"));
                    values.put("accountNumber", curRow.getString("accountNumber"));
                    values.put("username", curRow.getString("username"));
                    values.put("password", curRow.getString("password"));
                    values.put("email", curRow.getString("email"));
                    values.put("phoneNumber", curRow.getString("phoneNumber"));
                    values.put("IDNumber", curRow.getString("IDNumber"));
                    values.put("user_info_id", curRow.getInt("user_info_id"));
                    db.insert("account_records", null, values);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        private boolean syncCardRecords(SQLiteDatabase db, JSONArray Card_Records) {
            db.execSQL("drop table if exists card_records");
            db.execSQL("create table card_records(" +
                    "id integer primary key autoincrement," +
                    "icon integer," +
                    "title text," +
                    "description text," +
                    "balance real," +
                    "user_info_id integer)");
            try {
                for (int i=0; i<Card_Records.length(); i++) {
                    JSONObject curRow = Card_Records.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("id", curRow.getLong("id"));
                    values.put("icon", curRow.getString("icon"));
                    values.put("title", curRow.getString("title"));
                    values.put("description", curRow.getString("description"));
                    values.put("balance", curRow.getDouble("balance"));
                    values.put("user_info_id", curRow.getInt("user_info_id"));
                    db.insert("card_records", null, values);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        private boolean syncSavingRecords(SQLiteDatabase db, JSONArray Saving_Records) {
            db.execSQL("drop table if exists saving_records");
            db.execSQL("create table saving_records(" +
                    "id integer primary key autoincrement," +
                    "savingAmount real," +
                    "remarks text," +
                    "date integer," +
                    "time integer," +
                    "originCard integer," +
                    "targetCard integer," +
                    "plan_info_id integer," +
                    "user_info_id integer)");
            try {
                for (int i=0; i<Saving_Records.length(); i++) {
                    JSONObject curRow = Saving_Records.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("id", curRow.getLong("id"));
                    values.put("savingAmount", curRow.getDouble("savingAmount"));
                    values.put("remarks", curRow.getInt("remarks"));
                    values.put("date", curRow.getInt("date"));
                    values.put("time", curRow.getInt("time"));
                    values.put("originCard", curRow.getLong("originCard"));
                    values.put("targetCard", curRow.getLong("targetCard"));
                    values.put("plan_info_id", curRow.getLong("plan_info_id"));
                    values.put("user_info_id", curRow.getInt("user_info_id"));
                    db.insert("saving_records", null, values);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        private boolean syncAmountChanges(SQLiteDatabase db, JSONArray Amount_Changes) {
            db.execSQL("drop table if exists amount_changes");
            db.execSQL("create table amount_changes(" +
                    "id integer primary key autoincrement," +
                    "icon integer," +
                    "changeAmount real," +
                    "changeType integer," +
                    "sourceType integer," +
                    "remarks text," +
                    "date integer," +
                    "time integer," +
                    "operatedCard integer," +
                    "user_info_id integer)");
            try {
                for (int i=0; i<Amount_Changes.length(); i++) {
                    JSONObject curRow = Amount_Changes.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("id", curRow.getLong("id"));
                    values.put("icon", curRow.getString("icon"));
                    values.put("changeAmount", curRow.getDouble("changeAmount"));
                    values.put("changeType", curRow.getInt("changeType"));
                    values.put("sourceType", curRow.getInt("sourceType"));
                    values.put("remarks", curRow.getString("remarks"));
                    values.put("date", curRow.getInt("date"));
                    values.put("time", curRow.getInt("time"));
                    values.put("operatedCard", curRow.getLong("operatedCard"));
                    values.put("user_info_id", curRow.getInt("user_info_id"));
                    db.insert("amount_changes", null, values);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        private boolean syncPlanInfo(SQLiteDatabase db, JSONArray Plan_Info) {
            db.execSQL("drop table if exists plan_info");
            db.execSQL("create table plan_info(" +
                    "id integer primary key autoincrement," +
                    "expectantAmount real," +
                    "startTime integer," +
                    "endTime integer," +
                    "originCard integer," +
                    "title text," +
                    "user_info_id integer)");
            try {
                for (int i=0; i<Plan_Info.length(); i++) {
                    JSONObject curRow = Plan_Info.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("id", curRow.getLong("id"));
                    values.put("expectantAmount", curRow.getDouble("expectantAmount"));
                    values.put("startTime", curRow.getInt("startTime"));
                    values.put("duration", curRow.getInt("duration"));
                    values.put("originCard", curRow.getLong("originCard"));
                    values.put("targetCard", curRow.getLong("targetCard"));
                    values.put("user_info_id", curRow.getInt("user_info_id"));
                    db.insert("plan_info", null, values);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

}