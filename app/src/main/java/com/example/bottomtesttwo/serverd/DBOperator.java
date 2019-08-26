package com.example.bottomtesttwo.serverd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DBOperator {

    private static DBOperator instance = new DBOperator();
    private DBOperator (){}
    public static DBOperator getOperator() {
        return instance;
    }

    //增删改
    public void Cud(int UserID, String sql) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.bottomtesttwo/databases/gmt_dads.db", null);
        db.execSQL(sql);
        UploadSQLTask uploadSQLTask = new UploadSQLTask(UserID, sql);
        uploadSQLTask.execute();
    }

    //查询，返回一个Cursor指针
    public Cursor Query(String sql) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.bottomtesttwo/databases/gmt_dads.db", null);
        return db.rawQuery(sql, null);
    }

    private class UploadSQLTask extends AsyncTask<Void, Integer, Boolean> {

        private int UserID;
        private String sql;

        public UploadSQLTask(int UserID, String sql) {
            this.UserID = UserID;
            this.sql = sql;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("dbUser", "root")
                    .add("dbPwd", "GMT415263")
                    .add("dbName", "gmt_dads")
                    .add("sql", sql)
                    .build();
            Request request = new Request.Builder()
                    .url("http://114.55.252.35/queryData.php")
                    .post(requestBody)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                response.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("MYX", e.getMessage());
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean == true) {
                DBSyncer dbSyncer = DBSyncer.getSyncer();
                dbSyncer.start(UserID);
                if (sql.toLowerCase().contains("insert")) {
                    Log.d("MYX", "DBOperator: insert success");
                } else if (sql.toLowerCase().contains("delete")) {
                    Log.d("MYX", "DBOperator: delete success");
                } else if (sql.toLowerCase().contains("update")) {
                    Log.d("MYX", "DBOperator: update success");
                }
            } else {
                Log.d("MYX", "DBOperator: failed");
            }
        }
    }
}
