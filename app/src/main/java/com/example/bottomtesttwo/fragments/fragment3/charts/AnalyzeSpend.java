package com.example.bottomtesttwo.fragments.fragment3.charts;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item2;

import java.util.ArrayList;
import java.util.Arrays;
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

public class AnalyzeSpend extends AppCompatActivity {

    DateIntent dateIntent;
    NumberIntent numberIntent;

    //一个是横坐标，一个是数据点数组。
    private LineChartView lineChart;
//    String[] date = {"10-22", "11-22", "12-22", "1-22", "6-22", "5-23", "5-22", "6-22", "5-23", "5-22"};//X轴的标注
    List<String> date = new ArrayList<>();

    public void addDate(String dateString){
        date.add(dateString);
    }

    public void initDate(){
        for(int i = 10;i<=12;i++){
            addDate(i+"");
        }
    }
//    int[] score = {50, 42, 90, 33, 10, 74, 22, 18, 79, 20};//图表的数据点

    List<Integer> score = new ArrayList<>();//图表的数据点

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
        setContentView(R.layout.activity_analyze_spend);

        lineChart = (LineChartView) findViewById(R.id.line_chart);

        Intent intent = getIntent();

        if("action".equals(intent.getAction())){
            dateIntent = (DateIntent)intent.getSerializableExtra("dateIntent") ;
            numberIntent = (NumberIntent)intent.getSerializableExtra("numberIntent");
            String temp = intent.getStringExtra("1");
            Toast.makeText(AnalyzeSpend.this,"1111",Toast.LENGTH_SHORT).show();
        }


//        for(String item:dateIntent.getStringListIntent()){
//            Toast.makeText(AnalyzeSpend.this,"dateIntent",Toast.LENGTH_SHORT).show();
//            break;
//        }
//
//        date = dateIntent.getStringListIntent();
//        score = numberIntent.getIntListIntent();

//        initScore();
//        initDate();
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
