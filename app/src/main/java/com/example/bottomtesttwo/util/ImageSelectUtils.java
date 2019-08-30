package com.example.bottomtesttwo.util;

import com.example.bottomtesttwo.R;

public class ImageSelectUtils {

    //1-50（29）为支出图片
    //51-100（6）为收入图片
    //100-150（）为账号本、卡包图片

    static public int getImageAddress(int imageAddress){
        int image = 0;
        switch (imageAddress){
            case R.mipmap.sort_1_canyin:
                image = 1;
                break;
            case R.mipmap.sort_1_gouwu:
                image = 2;
                break;
            case R.mipmap.sort_1_jiaotong:
                image = 3;
                break;
            case R.mipmap.sort_1_lingshi:
                image = 4;
                break;
            case R.mipmap.sort_1_meirong:
                image = 5;
                break;
            case R.mipmap.sort_1_shuma:
                image = 6;
                break;
            case R.mipmap.sort_1_tongxun:
                image = 7;
                break;
            case R.mipmap.sort_1_xuexi:
                image = 8;
                break;
            case R.mipmap.sort_1_yanjiu:
                image = 9;
                break;
            case R.mipmap.sort_1_yule:
                image = 10;
                break;
            case R.mipmap.sort_bangong:
                image = 11;
                break;
            case R.mipmap.sort_chongwu:
                image = 12;
                break;
            case R.mipmap.sort_haizi:
                image = 13;
                break;
            case R.mipmap.sort_huankuan:
                image = 14;
                break;
            case R.mipmap.sort_jianzhi:
                image = 15;
                break;
            case R.mipmap.sort_juanzeng:
                image = 16;
                break;
            case R.mipmap.sort_jujia:
                image = 17;
                break;
            case R.mipmap.sort_lingqian:
                image = 18;
                break;
            case R.mipmap.sort_liwu:
                image = 19;
                break;
            case R.mipmap.sort_lvxing:
                image = 20;
                break;
            case R.mipmap.sort_shouxufei:
                image = 21;
                break;
            case R.mipmap.sort_shuiguo:
                image = 22;
                break;
            case R.mipmap.sort_weixiu:
                image = 23;
                break;
            case R.mipmap.sort_weiyuejin:
                image = 24;
                break;
            case R.mipmap.sort_yiban:
                image = 25;
                break;
            case R.mipmap.sort_yiliao:
                image = 26;
                break;
            case R.mipmap.sort_yongjin:
                image = 27;
                break;
            case R.mipmap.sort_yundong:
                image = 28;
                break;
            case R.mipmap.sort_zhufang:
                image = 29;
                break;

            //收入图片
            case R.mipmap.income_fanxian:
                image = 51;
                break;
            case R.mipmap.income_fenhong:
                image = 52;
                break;
            case R.mipmap.income_gongzi:
                image = 53;
                break;
            case R.mipmap.income_jiangjin:
                image = 54;
                break;
            case R.mipmap.income_lijin:
                image = 55;
                break;
            case R.mipmap.income_lixi:
                image = 56;
                break;
            default:
                break;
        }
        return image;
    }


    static public int getImageId(int imageId){
        int image = 0;
        switch (imageId){
            //支出图片
            case 1:
                image = R.mipmap.sort_1_canyin;
                break;
            case 2:
                image = R.mipmap.sort_1_gouwu;
                break;
            case 3:
                image = R.mipmap.sort_1_jiaotong;
                break;
            case 4:
                image = R.mipmap.sort_1_lingshi;
                break;
            case 5:
                image = R.mipmap.sort_1_meirong;
                break;
            case 6:
                image = R.mipmap.sort_1_shuma;
                break;
            case 7:
                image = R.mipmap.sort_1_tongxun;
                break;
            case 8:
                image = R.mipmap.sort_1_xuexi;
                break;
            case 9:
                image = R.mipmap.sort_1_yanjiu;
                break;
            case 10:
                image = R.mipmap.sort_1_yule;
                break;
            case 11:
                image = R.mipmap.sort_bangong;
                break;
            case 12:
                image = R.mipmap.sort_chongwu;
                break;
            case 13:
                image = R.mipmap.sort_haizi;
                break;
            case 14:
                image = R.mipmap.sort_huankuan;
                break;
            case 15:
                image = R.mipmap.sort_jianzhi;
                break;
            case 16:
                image = R.mipmap.sort_juanzeng;
                break;
            case 17:
                image = R.mipmap.sort_jujia;
                break;
            case 18:
                image = R.mipmap.sort_lingqian;
                break;
            case 19:
                image = R.mipmap.sort_liwu;
                break;
            case 20:
                image = R.mipmap.sort_lvxing;
                break;
            case 21:
                image = R.mipmap.sort_shouxufei;
                break;
            case 22:
                image = R.mipmap.sort_shuiguo;
                break;
            case 23:
                image = R.mipmap.sort_weixiu;
                break;
            case 24:
                image = R.mipmap.sort_weiyuejin;
                break;
            case 25:
                image = R.mipmap.sort_yiban;
                break;
            case 26:
                image = R.mipmap.sort_yiliao;
                break;
            case 27:
                image = R.mipmap.sort_yongjin;
                break;
            case 28:
                image = R.mipmap.sort_yundong;
                break;
            case 29:
                image = R.mipmap.sort_zhufang;
                break;

                //收入图片
            case 51:
                image = R.mipmap.income_fanxian;
                break;
            case 52:
                image = R.mipmap.income_fenhong;
                break;
            case 53:
                image = R.mipmap.income_gongzi;
                break;
            case 54:
                image = R.mipmap.income_jiangjin;
                break;
            case 55:
                image = R.mipmap.income_lijin;
                break;
            case 56:
                image = R.mipmap.income_lixi;
                break;
                default:
                    break;

        }
        return image;
    }

}
