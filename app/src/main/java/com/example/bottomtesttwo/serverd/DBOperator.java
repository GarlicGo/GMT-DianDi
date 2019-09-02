package com.example.bottomtesttwo.serverd;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.fragments.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DBOperator {

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    private static DBOperator instance = new DBOperator();
    private DBOperator (){}
    public static DBOperator getOperator() {
        return instance;
    }

    private class UploadSQLTask extends AsyncTask<Void, Integer, Boolean> {

        private String sql;

        public UploadSQLTask(String sql) {
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
            try {
                client.newCall(request).execute();
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

    private class AddUserTask extends AsyncTask<Void, Integer, Integer> {

        private String email;
        private String pwd;

        public AddUserTask(String email, String pwd) {
            this.email = email;
            this.pwd = pwd;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("dbUser", "root")
                    .add("dbPwd", "GMT415263")
                    .add("dbName", "gmt_dads")
                    .add("email", email)
                    .add("pwd", pwd)
                    .build();
            Request request = new Request.Builder()
                    .url("http://114.55.252.35/signUp.php")
                    .post(requestBody)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                int status = Integer.parseInt(response.body().string());
                response.close();
                return status;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("MYX", e.getMessage());
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer status) {
            switch (status) {
                case -1:
                    Log.d("MYX", "This mail has been bound.");
                    break;
                case 0:
                    Log.d("MYX", "Fail to register.");
                    break;
                default:
                    DBSyncer dbSyncer = DBSyncer.getSyncer();
                    dbSyncer.start(status);
                    Log.d("ZXY", "Registered successfully, and id is: "+status);
                    break;
            }
        }
    }

    private class SeekUserTask extends AsyncTask<Void, Integer, Integer> {

        private String email;
        private String pwd;

        public SeekUserTask(String email, String pwd) {
            this.email = email;
            this.pwd = pwd;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("dbUser", "root")
                    .add("dbPwd", "GMT415263")
                    .add("dbName", "gmt_dads")
                    .add("email", email)
                    .add("pwd", pwd)
                    .build();
            Request request = new Request.Builder()
                    .url("http://114.55.252.35/signIn.php")
                    .post(requestBody)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                int status = Integer.parseInt(response.body().string());
                response.close();
                return status;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("MYX", e.getMessage());
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer status) {
            switch (status) {
                case 0:
                    Log.d("MYX", "Seek failed.");
                    Toast.makeText(LoginActivity.instance,"账号不存在或密码错误",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    DBSyncer dbSyncer = DBSyncer.getSyncer();
                    dbSyncer.start(status);

                    pref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.instance);
                    prefEditor = pref.edit();
                    prefEditor.putString("id",""+status);
                    prefEditor.apply();

                    Toast.makeText(LoginActivity.instance,"登录成功",Toast.LENGTH_SHORT).show();
                    Log.d("ZXY", "Registered successfully, and id is: "+status);
                    break;
            }
        }
    }

    public void Add(String email, String pwd) {
        AddUserTask addUserTask = new AddUserTask(email, pwd);
        addUserTask.execute();
    }

    public void Seek(String email, String pwd) {
        SeekUserTask seekUserTask = new SeekUserTask(email, pwd);
        seekUserTask.execute();
    }

    public void Cud(String sql) {
        File file = new File("data/data/com.example.bottomtesttwo/databases");
        SQLiteDatabase db;
        if (file.exists()) {
            db = SQLiteDatabase.openOrCreateDatabase(file.getPath()+"/gmt_dads.db", null);
        } else {
            file.mkdirs();
            db = SQLiteDatabase.openOrCreateDatabase(file.getPath()+"/gmt_dads.db", null);
        }
        db.execSQL(sql);
        UploadSQLTask uploadSQLTask = new UploadSQLTask(sql);
        uploadSQLTask.execute();
    }

    public Cursor Query(String sql) {
        File file = new File("data/data/com.example.bottomtesttwo/databases");
        SQLiteDatabase db;
        if (file.exists()) {
            db = SQLiteDatabase.openOrCreateDatabase(file.getPath()+"/gmt_dads.db", null);
        } else {
            file.mkdirs();
            db = SQLiteDatabase.openOrCreateDatabase(file.getPath()+"/gmt_dads.db", null);
        }
        return db.rawQuery(sql, null);
    }
}