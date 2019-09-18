package com.example.bottomtesttwo.fragments.fragment3.charts;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item2;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import static com.example.bottomtesttwo.activity.MainActivity.setStatusBar;

public class AnalyzeAll extends AppCompatActivity {

    DateIntent dateIntent;
    NumberIntent numberIntent;
    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    //一个是横坐标，一个是数据点数组。
    private LineChartView lineChart;
//    String[] date = {"10-22", "11-22", "12-22", "1-22", "6-22", "5-23", "5-22", "6-22", "5-23", "5-22"};//X轴的标注

    List<String> date = new ArrayList<>();
    List<Integer> score = new ArrayList<>();//图表的数据点
    List<Analyze1> itemData = new ArrayList<>();

    public void addDate(String dateString){
        date.add(dateString);
    }

    public void initData(){

        date.clear();
        score.clear();
        itemData.clear();
        Log.d("ZXYFrag1","成功调用initItemList");
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        Log.d("ZXYFrag1","user_info_id :"+id);
        cursor = dbOperator.Query( "select * from amount_changes where user_info_id='"+id+"'");

        Intent intent = getIntent();
        String intentDate = intent.getStringExtra("date");
        long intentDateLong = Long.valueOf(intentDate);
        Log.d("ANA1","!!!:"+intentDate);
        if(cursor.moveToFirst()){
            Log.d("ANA1","成功进入movetoFirst");
            do {
                Log.d("ANA1","do");
                double dataDouble = cursor.getDouble(cursor.getColumnIndex("changeAmount"));
                    long Datelong1 = cursor.getLong(cursor.getColumnIndex("date"));
                    if(intentDateLong < Datelong1 && Datelong1 < (intentDateLong+100)){
                        String string1 = ""+Datelong1;
                        String string11 = string1.substring(2,4)+"月"+string1.substring(4)+"日";
                        Analyze1 analyze1 = new Analyze1(string11,(int)dataDouble);
                        analyze1.setDataTime(Datelong1);
                        itemData.add(analyze1);
                    }
            }while (cursor.moveToNext());

            cursor.close();

            Collections.sort(itemData, new Comparator<Analyze1>() {
                @Override
                public int compare(Analyze1 o1, Analyze1 o2) {
                    if (o1.getDataTime() - o2.getDataTime() < 0) { //变成 < 可以变成递减排序
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });

        }
    }
//    int[] score = {50, 42, 90, 33, 10, 74, 22, 18, 79, 20};//图表的数据点



    //图表数据点的添加方法
    public void addScore(int number){
        score.add(number);
    }

    public void initScore(){
        for (int i = 10;i<=20;i++){
            addScore(i);
        }
    }



    //    int[] score = {};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    private double all;
    private double average;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();
//        //顶部状态栏设置属性（具体细节见MainActivity中的静态实现方法）
//        //isPadding-是否预留出状态栏高度:true=是、false=fou;
//        //dark:true=黑色字体  false=白色
//        setStatusBar(this, false, true);
        setContentView(R.layout.activity_analyze_all);

        lineChart = (LineChartView) findViewById(R.id.line_chart);

        Intent intent = getIntent();

        if("action".equals(intent.getAction())){
            dateIntent = (DateIntent)intent.getSerializableExtra("dateIntent") ;
            numberIntent = (NumberIntent)intent.getSerializableExtra("numberIntent");
            String temp = intent.getStringExtra("1");
            Toast.makeText(AnalyzeAll.this,"1111",Toast.LENGTH_SHORT).show();
        }
        initData();
        Log.d("ANA1","inData.size():"+itemData.size());

        int all = 0;
        int average = 0;
        int count = 0;

        int moneyMan = 0;
        Analyze1 analyze = null;

        boolean nbman = false;

        if(itemData.size() ==  0){

        }

        for(Analyze1 analyze1:itemData){
            Log.d("ANA1","for data score");
            int posotion = itemData.indexOf(analyze1);
            count++;
            all = all + analyze1.getMoney();
            average = average + analyze1.getMoney();
            if(posotion == (itemData.size()-1) && analyze==null){
                date.add(analyze1.getDate());
                score.add(analyze1.getMoney());
            }else if(analyze==null){
//                date.add(analyze1.getDate());
//                score.add(analyze1.getMoney());
                moneyMan = analyze1.getMoney();
            }else if(posotion == (itemData.size()-1)){
                if(true){
                    date.add(analyze.getDate());
                    score.add(moneyMan);
                }else {
                    date.add(analyze1.getDate());
                    score.add(analyze1.getMoney());
                }
            }
            else if(analyze.getDataTime() != analyze1.getDataTime()){
                score.add(moneyMan);
                date.add(analyze.getDate());
                moneyMan = analyze1.getMoney();
            }
            else {
                moneyMan = moneyMan + analyze1.getMoney();
            }
            analyze = analyze1;
        }

        int average2;
        if(count==0){
            all = 0;
            average2 = 0;
        }else {
            average2 = average/count;
        }
        TextView textView1 = (TextView)findViewById(R.id.ana3_1);
        TextView textView2 = (TextView)findViewById(R.id.ana3_2);
        textView1.setText("总结余："+all);
        textView2.setText("平均值："+average2);
//        for(String item:dateIntent.getStringListIntent()){
//            Toast.makeText(AnalyzeSpend.this,"dateIntent",Toast.LENGTH_SHORT).show();
//            break;
//        }
        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {

        int i = 0;
        for (String item:date){
            mAxisXValues.add(new AxisValue(i).setLabel(item));
            i++;
        }

//        for (int i = 0; i < date.size(); i++) {
//            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
//        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        int i = 0;
        for(Integer item:score){
            mPointValues.add(new PointValue(i,item));
            i++;
        }
//        for (int i = 0; i < score.length; i++) {
//            mPointValues.add(new PointValue(i, score[i]));
//        }
    }

    private void initLineChart(){

        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();

        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        axisX.setName("日期");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(31); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
//        axisY.setName("金额");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        lineChart.setCurrentViewport(v);
    }
}
