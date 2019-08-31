package com.example.bottomtesttwo.fragments.fragment1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.serverd.DBOperator;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class Calculator12Update extends AppCompatActivity {

    EditText editText1;
    EditText editText2;

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    private long card_records_id;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator12_update);

        getSupportActionBar().hide();

        editText1 = (EditText)findViewById(R.id.frag1_calculator2_up1);
        editText2 = (EditText)findViewById(R.id.frag1_calculator2_up2);

        //获取编辑子项ID
        intent = getIntent();
        initText();
    }

    public void calculator1_onClick2_up(View view) {
        switch (view.getId()) {
            case R.id.frag1_cal1_back_up_btn2://退格
                finish();
                break;
            case R.id.frag1_cal2_up_button1://取消
                finish();
                break;
            case R.id.frag1_cal2_up_button2://编辑、确定
                updateItem();
                break;
            case R.id.frag1_cal1_back_btn33333://删除
                AlertDialog.Builder builder = new Builder(this);
                builder.setIcon(null);//设置图标, 这里设为空值
                builder.setTitle("删除确认");
                builder.setMessage("确认删除此条信息吗？不可恢复。");
                builder.setPositiveButton("确定", new OnClickListener(){
                    public void onClick(DialogInterface arg0, int arg1){
                        delete();
                        finish();
                        Toast.makeText(Calculator12Update.this,"删除成功！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new OnClickListener(){
                    public void onClick(DialogInterface arg0,int arg1){

                    }
                });
                AlertDialog b = builder.create();
                b.show();//显示对话框
                break;
        }
    }

//    string1.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")

    private void initText(){
        //获取用户ID
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();
        //获取实例ID
        card_records_id = intent.getLongExtra("card_records_id",0);
        //获取子项数据
        cursor = dbOperator.Query("select * from card_records where id='"+card_records_id+"'");
        if(cursor.moveToFirst()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String balance = cursor.getString(cursor.getColumnIndex("balance"));

            editText1.setText(title);
            editText2.setText(balance);

        }
    }

    private void updateItem(){
        String string1 = editText1.getText().toString();
        String string2 = editText2.getText().toString();
        if(string1.equals("")){
            Toast.makeText(this,"名称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else if(string2.equals("")){
            //余额输入为空默认为0
            string2 = "0";
        }else {
            if(string2.matches("(^-?[1-9](\\d+)?(\\.\\d{1,2})?$)|(^-?0$)|(^-?\\d\\.\\d{1,2}$)")){
//                Toast.makeText(this,"正确",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"余额格式错误(精确之小数点后两位)",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //获取用户ID
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();
        //获取实例ID
        card_records_id = intent.getLongExtra("card_records_id",0);

        dbOperator.Cud("update card_records set title='"+string1+"' where id='"+card_records_id+"'");
        dbOperator.Cud("update card_records set balance='"+string2+"' where id='"+card_records_id+"'");
        Intent intentBack = new Intent();
        setResult(RESULT_OK,intentBack);
        finish();
    }

    private void delete(){
        //获取子项ID
        card_records_id = intent.getLongExtra("card_records_id",0);
        dbOperator.Cud("delete from card_records where id='"+card_records_id+"'");
        Intent intentBack = new Intent();
        setResult(RESULT_OK,intentBack);
    }
}
