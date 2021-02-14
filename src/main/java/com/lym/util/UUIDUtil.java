package com.lym.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liuyanmin on 2019/9/29.
 */
public class UUIDUtil {

    public static String gen32UUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public static String genUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    /**
     * 32位数字字符串：日期yyyyMMddHHmmss + 18位随机数字
     * **/
    public static String get32UUID(){
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss");
        String settData = simp.format(new Date());
        String num = get18Random();
        String settNum = settData + num;
        return settNum;
    }

    /**
     * 32位数字字符串：日期MMddHHmmssSSS + 19位随机数字
     * **/
    public static String get32CollectionId(){
        SimpleDateFormat simp = new SimpleDateFormat("MMddHHmmssSSS");
        String settData = simp.format(new Date());
        String num = get19Random();
        String settNum = settData + num;
        return settNum;
    }

    /**
     * 返回18位随机数
     * **/
    private static String get18Random(){
        int num1 = (int) Math.round(Math.random()*99+1);
        int num2 = (int) Math.round(Math.random()*999+1);
        int num3 = (int) Math.round(Math.random()*9999+1);
        int num4 = (int) Math.round(Math.random()*99999+1);
        int num5 = (int) Math.round(Math.random()*9999+1);
        String num = formatNum(num1,2) + formatNum(num2,3) + formatNum(num3,4) + formatNum(num4,5) + formatNum(num5,4);
        return num;
    }
    
    /**
     * 返回6位随机数
     * **/
    public static String get6Code(){
        int num1 = (int) Math.round(Math.random()*99+1);
        int num2 = (int) Math.round(Math.random()*99+1);
        int num3 = (int) Math.round(Math.random()*99+1);
        String num = formatNum(num1,2) + formatNum(num2,2) + formatNum(num3,2);
        return num;
    }
    
    /**
     * 返回19位随机数
     * **/
    private static String get19Random(){
        int num1 = (int) Math.round(Math.random()*99+1);
        int num2 = (int) Math.round(Math.random()*999+1);
        int num3 = (int) Math.round(Math.random()*9999+1);
        int num4 = (int) Math.round(Math.random()*99999+1);
        int num5 = (int) Math.round(Math.random()*99999+1);
        String num = formatNum(num1,2) + formatNum(num2,3) + formatNum(num3,4) + formatNum(num4,5) + formatNum(num5,5);
        return num;
    }

    /**
     * 输入一个数字,输入一个长度,返回前补零的字符串，若数字本身超过输入长度则将数字末端进行截取
     * **/
    public static String formatNum(int num,int length){
        String str = String.format("%0"+ length + "d",num);
        if(str.length()>length){
            str = str.substring(0, length);
        }
        return str;
    }

    /**
     * 生成16位yyMMdd+10位随机数
     * **/
    public static String get16UUID(){
        SimpleDateFormat simp = new SimpleDateFormat("yyMMdd");
        String dateStr = simp.format(new Date());
        String num = gen10RandomNum();
        String uuid = dateStr + num;
        return uuid;
    }
    
    /**
     * 生成18位yyyyMMddHHmmss+4位随机数
     * **/
    public static String get18UUID(){
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = simp.format(new Date());
        int num = (int) Math.round(Math.random()*9999+1);
        String numStr = formatNum(num,4);
        String uuid = dateStr + numStr;
        return uuid;
    }

    /**
     * 生成10位随机数
     * **/
    private static String gen10RandomNum(){
        int num1 = (int) Math.round(Math.random()*99+1);
        int num2 = (int) Math.round(Math.random()*999+1);
        int num3 = (int) Math.round(Math.random()*99999+1);
        String num = formatNum(num1,2) + formatNum(num2,3) + formatNum(num3,5);
        return num;
    }
}
