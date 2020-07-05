package com.cily.lottery;

public class PayType {
    //1余额支付，2微信支付，3支付宝支付，4银联支付，5现金支付
    public final static String YU_E = "1";
    public final static String WECHAT = "2";
    public final static String ALI = "3";
    public final static String BANK = "4";
    public final static String XIAN_JIN = "5";

    public final static String STR_YU_E = "余额支付";
    public final static String STR_WECHAT = "微信支付";
    public final static String STR_ALI = "支付宝支付";
    public final static String STR_BANK = "银联支付";
    public final static String STR_XIAN_JIN = "现金支付";

    public final static String[] PAY_TYPES = {STR_YU_E, STR_WECHAT, STR_ALI, STR_BANK, STR_XIAN_JIN};

    public final static String[] MONEY = {"2.00", "5.00", "10.00", "20.00",
            "50.00", "100.00", "200.00", "500.00", "1000.00"};

    public static String codeToStr(String code){
        if (WECHAT.equals(code)){
            return STR_WECHAT;
        }else if (ALI.equals(code)){
            return STR_ALI;
        }else if (BANK.equals(code)){
            return STR_BANK;
        }else if (XIAN_JIN.equals(code)){
            return STR_XIAN_JIN;
        }else {
            return STR_YU_E;
        }
    }

    public static String strToCode(String str){
        if (STR_WECHAT.equals(str)){
            return WECHAT;
        }else if (STR_ALI.equals(str)){
            return ALI;
        }else if (STR_BANK.equals(str)){
            return BANK;
        }else if (STR_XIAN_JIN.equals(str)){
            return XIAN_JIN;
        }else {
            return YU_E;
        }
    }
}
