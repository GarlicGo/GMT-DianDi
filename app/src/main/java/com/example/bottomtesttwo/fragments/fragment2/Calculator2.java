package com.example.bottomtesttwo.fragments.fragment2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.TestLooperManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment1.Calculator11Update;
import com.example.bottomtesttwo.fragments.fragment3.Calculator;
import com.example.bottomtesttwo.fragments.fragment3.MoneyTypeListActivity;
import com.example.bottomtesttwo.serverd.DBOperator;

import org.w3c.dom.Text;

import java.util.Calendar;

import static com.example.bottomtesttwo.activity.MainActivity.setStatusBar;

public class Calculator2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private long card_records_id1;
    private long card_records_id2;
    private TextView date;
    private TextView textView1;
    private TextView textView2;
    private TextView tb_note_money;
    private TextView calculator2_date;
    private LinearLayout tb_moneyType1;
    private LinearLayout tb_moneyType2;
    private boolean haveFloat = false;
    private String display = "0.00";
    private EditText editText;


    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor = dbOperator.Query( "select * from user_info");
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);

        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();

        tb_moneyType1 = (LinearLayout) findViewById(R.id.frag2_money_type1);
        tb_moneyType2 = (LinearLayout) findViewById(R.id.frag2_money_type2);
        textView1 = (TextView)findViewById(R.id.frag2_text1);
        textView2 = (TextView)findViewById(R.id.frag2_text2);
        tb_note_money = (TextView)findViewById(R.id.tb_note_money);
        calculator2_date = (TextView)findViewById(R.id.calculator2_date);
        editText = (EditText)findViewById(R.id.calculator2_ediTtext);

        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor = dbOperator.Query( "select * from card_records where user_info_id='"+id+"'");

        if(cursor.moveToFirst()){
            textView1.setText(cursor.getString(cursor.getColumnIndex("title")));
            textView2.setText(cursor.getString(cursor.getColumnIndex("title")));
            card_records_id1 = cursor.getLong(cursor.getColumnIndex("id"));
            card_records_id2 = cursor.getLong(cursor.getColumnIndex("id"));
        }
        cursor.close();

    }

    public void calculator2_onClick(View view) {
//        Toast.makeText(this,"you clicked ",Toast.LENGTH_SHORT).;
        switch (view.getId()) {
            case R.id.calculator2_num_0:
                addDisplay("0");
                break;
            case R.id.calculator2_num_1:
                addDisplay("1");
                break;
            case R.id.calculator2_num_2:
                addDisplay("2");
                break;
            case R.id.calculator2_num_3:
                addDisplay("3");
                break;
            case R.id.calculator2_num_4:
                addDisplay("4");
                break;
            case R.id.calculator2_num_5:
                addDisplay("5");
                break;
            case R.id.calculator2_num_6:
                addDisplay("6");
                break;
            case R.id.calculator2_num_7:
                addDisplay("7");
                break;
            case R.id.calculator2_num_8:
                addDisplay("8");
                break;
            case R.id.calculator2_num_9:
                addDisplay("9");
                break;
            case R.id.calculator2_num_dot:
                addDisplay(".");
                break;
            case R.id.calculator2_num_del://退格
                deleteBackDisplay();
                break;
            case R.id.calculator2_num_done://确定
                if(card_records_id1==card_records_id2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setIcon(null);//设置图标, 这里设为空值
                    builder.setTitle("卡户冲突");
                    builder.setMessage("您选择的转入和转出的卡户相同，是否确定执行该操作？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface arg0, int arg1){
                            commit();
                            finish();
//                            Toast.makeText(Calculator2.this,"删除成功！",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface arg0,int arg1){

                        }
                    });
                    AlertDialog b = builder.create();
                    b.show();//显示对话框
                }else {
                    commit();
                    finish();
                }

                break;
            case R.id.calculator2_clear://清空
                deleteEmptyDisplay();
                break;
            case R.id.frag2_money_type1:
                Intent intentMoneyType1 = new Intent(Calculator2.this, MoneyTypeListActivity.class);
                startActivityForResult(intentMoneyType1,1);
                break;
            case R.id.frag2_money_type2:
                Intent intentMoneyType2 = new Intent(Calculator2.this, MoneyTypeListActivity.class);
                startActivityForResult(intentMoneyType2,2);
                break;
            case R.id.back_btn:
                finish();
                break;
            case R.id.calculator2_date:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    textView1.setText(data.getStringExtra("name"));
                    card_records_id1 = data.getLongExtra("card_id",0);
//                    Toast.makeText(this,""+data.getLongExtra("card_id",0),Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    textView2.setText(data.getStringExtra("name"));
                    card_records_id2 = data.getLongExtra("card_id",0);
//                    Toast.makeText(this,""+data.getLongExtra("card_id",0),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void addDisplay(String i){
        //在0.00时操作
        if (display.equals("0.00")){
            //第一次就输入小数点
            if(i.equals(".")){
                display = "0.";
                haveFloat = true;
                tb_note_money.setText(display);
                //第一次输入数字
            }else {
                display = i;
                tb_note_money.setText(display);
            }
            //在不为0.00时操作
        }else {
            if(display.length()<=10) {
                if(i.equals(".")){
                    if(haveFloat == false){
                        display = display + i;
                        haveFloat = true;
                        tb_note_money.setText(display);
                    }
                }else {
                    display = display + i;
                    tb_note_money.setText(display);
                }
            }else {
                Toast.makeText(Calculator2.this,"数额不合理",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //退格删除
    public void deleteBackDisplay(){
        if(display.equals("")){
            tb_note_money.setText(display);
        }else {
            if(display.equals("0.00")){
                tb_note_money.setText(display);
            }else{
                display = display.substring(0,display.length()-1);
                tb_note_money.setText(display);
            }
        }
    }

    public void deleteEmptyDisplay(){
        display = "0.00";
        tb_note_money.setText(display);
    }

    private void commit(){

        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();

        Calendar calendar = Calendar.getInstance();
        //获取系统时间
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String saving_records_id = String.format("1%02d%02d%02d",(month+1)%100,day,minute,second);
        String dateDate = String.format("%02d%02d%02d",year%100,(month+1)%100,day%100);

        cursor = dbOperator.Query("select * from card_records where id='"+card_records_id1+"'");
        if(cursor.moveToFirst()) {
            double tempMoney = cursor.getDouble(cursor.getColumnIndex("balance"));
            tempMoney = tempMoney - Double.valueOf(display);
            cursor.close();
            dbOperator.Cud("update card_records set balance='"+tempMoney+"' where id='"+card_records_id1+"'");
        }

        cursor = dbOperator.Query("select * from card_records where id='"+card_records_id2+"'");
        if(cursor.moveToFirst()) {
            double tempMoney = cursor.getDouble(cursor.getColumnIndex("balance"));
            tempMoney = tempMoney + Double.valueOf(display);
            cursor.close();
            dbOperator.Cud("update card_records set balance='"+tempMoney+"' where id='"+card_records_id2+"'");
        }

        String plan_info_id = "";
        cursor = dbOperator.Query("select * from plan_info where user_info_id='"+id+"' and originCard='1'");
        if(cursor.moveToFirst()){
            plan_info_id = cursor.getString(cursor.getColumnIndex("id"));
        }else {

        }
        cursor.close();

        String remarks = editText.getText().toString();
        if(remarks.equals("")){
            remarks = "存钱记录";
        }
        /**
         *  saving_records_id -> id 时间戳
         *  display -> savingAmount 数额
         * 	remarks -> remarks 备注
         *  dateDate -> date 日期（精确到日）
         *  card_records_id1 -> originCard （转出卡片）
         *  card_records_id2 -> targetCard （转入卡片）
         *  plan_info_id -> plan_info_id （归属目标）
         *  id -> user_info_id （归属用户）
         */

        if(plan_info_id.equals("")){
            Toast.makeText(this,"目标异常",Toast.LENGTH_SHORT).show();
            return;
        }

        dbOperator.Cud("insert into saving_records(id,savingAmount,remarks,date,originCard,targetCard,plan_info_id,user_info_id) values ('"
                +saving_records_id+"','"
                +display+"','"
                +remarks+"','"
                +dateDate+"','"
                +card_records_id1+"','"
                +card_records_id2+"','"
                +plan_info_id+"','"
                +id+"')");
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
