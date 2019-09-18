package com.example.bottomtesttwo.fragments.fragment2;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.Calendar;

public class AddTarget extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor = dbOperator.Query( "select * from user_info");
    private int id;
    EditText editText1;
    EditText editText2;

    TextView textView1;
    TextView textView2;

    private int dateType = 1;
    private int dateStart = 0;
    private int dateEnd = 0;

    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_target);
        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();

        editText1 = (EditText)findViewById(R.id.add_tar_name);
        editText2 = (EditText)findViewById(R.id.add_tar_money);
        textView1 = (TextView)findViewById(R.id.frag2_tar_date1);
        textView2 = (TextView)findViewById(R.id.frag2_tar_date2);
    }

    public void calculator2_tar_onClick1(View view){
        switch (view.getId()){
            case R.id.frag2_cal1_back_btn2://返回键
//                finish();
                break;
            case R.id.frag1_tar_button1://取消
                finish();
                break;
            case R.id.frag2_tar_button2://确定
                commit();
                break;
            case R.id.frag2_tar_date1://日历开始时间
                dateType = 1;
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.show();
                DatePicker dp1 = findDatePicker((ViewGroup) datePickerDialog1.getWindow().getDecorView());
                if (dp1 != null) {
                    ((ViewGroup)((ViewGroup) dp1.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
                }
                break;
            case R.id.frag2_tar_date2://日历结束时间
                dateType = 2;
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(this,this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog2.show();
                DatePicker dp2 = findDatePicker((ViewGroup) datePickerDialog2.getWindow().getDecorView());
                if (dp2 != null) {
                    ((ViewGroup)((ViewGroup) dp2.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
                }
                break;
        }
    }

    private void commit(){

        String name = editText1.getText().toString();
        String money = editText2.getText().toString();

        if(name.equals("")){
            Toast.makeText(this,"名称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if(dateStart == 0){
            Toast.makeText(this,"请选择开始日期",Toast.LENGTH_SHORT).show();
            return;
        }
        if(dateEnd == 0){
            Toast.makeText(this,"请选择结束日期",Toast.LENGTH_SHORT).show();
            return;
        }
        if(dateStart>dateEnd){
            Toast.makeText(this,"结束日期不能大于开始日期",Toast.LENGTH_SHORT).show();
            return;
        }
        if(money.equals("")){
            Toast.makeText(this,"钱数不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(money.matches("(^-?[1-9](\\d+)?(\\.\\d{1,2})?$)|(^-?0$)|(^-?\\d\\.\\d{1,2}$)")){
//            Calendar calendar = Calendar.getInstance();
            //获取系统时间
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            //小时
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            //分钟
            int minute = calendar.get(Calendar.MINUTE);
            //秒
            int second = calendar.get(Calendar.SECOND);
            String plan_info_id = String.format("1%02d%02d%02d",(month+1)%100,day,minute,second);
            //获取用户ID
            cursor = dbOperator.Query( "select * from user_info");
            cursor.moveToFirst();
            id = cursor.getInt(cursor.getColumnIndex("id"));
            cursor.close();

            /**
             *  plan_info_id -> id
             *  name -> title
             *  dateStart -> startTime
             *  dateEnd -> endTime
             *  money -> expectantAmount
             */
            dbOperator.Cud("insert into plan_info (id,expectantAmount,startTime,endTime,title,user_info_id) values ('"
                    +plan_info_id+"','"
                    +money+"','"
                    +dateStart+"','"
                    +dateEnd+"','"
                    +name+"','"
                    +id+"')");
            finish();

        }else {
            Toast.makeText(this,"钱数非法，最多精确至两位小数",Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(dateType == 1){
            dateStart = dayOfMonth + (month+1)*100 + year*100*100;
            String dateString = String.format("%d年%d月%d日",year%100,(month+1)%100,dayOfMonth%100);
            textView1.setText(dateString);
        }else if(dateType == 2){
            dateEnd = dayOfMonth + (month+1)*100 + year*100*100;
            String dateString = String.format("%d年%d月%d日",year%100,(month+1)%100,dayOfMonth%100);
            textView2.setText(dateString);
        }
    }

    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }

}
