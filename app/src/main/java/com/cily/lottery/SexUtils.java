package com.cily.lottery;

public class SexUtils {

    public final static String[] SEX = {"男", "女"};
    public final static String MALE = "1";
    public final static String FEMALE = "2";

    public static String codeToStr(String code){
        if (MALE.equals(code)){
            return SEX[0];
        }else if (FEMALE.equals(code)){
            return SEX[1];
        }
        return "未知";
    }

    public static String strToCode(String str){
        if (SEX[1].equals(str)){
            return FEMALE;
        }else {
            return MALE;
        }
    }
}
