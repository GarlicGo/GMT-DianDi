package com.example.bottomtesttwo.util;

import java.util.Random;

public class TargetMoneyUtil {

    public static double getMoney(int day,double mon,int getDay){

        Random rand = new Random();
        /**
         * int day 目标天数
         * int mon 目标钱数
         */

        int day_copy;//存钱天数，存钱天数拷贝
        int base;//平均值
        int float1 = 4;//浮动；
        int base_float;//实际存钱，即平均值浮动后；
        int i;

        day_copy=day;

        int[] a = new int[day+5];//检查用；

        for(i=0; i<day_copy; i++)
        {
            if(day!=0)
            {
                if(day!=1)
                {
                    base=(int)mon/day;//平均值
                    base_float=(int)(base+((rand.nextInt(100000)%(float1*2))-float1/2));//平均值+浮动值的结果
                    a[i]=base_float;//
                    mon-=base_float;//还差多少钱
                }
                else if(day==1)
                {
                    a[i] = (int)mon;
                }
                day--;
            }
        }

        return a[getDay];
    }


}
