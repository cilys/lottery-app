package com.cily.lottery;

public class PayType {
    // 1余额支付，2微信支付，3支付宝支付，4银联支付，5现金支付，
    // 6系统退款，7系统充值，8更新余额，9所得奖金
    public final static String YU_E = "1";
    public final static String WECHAT = "2";
    public final static String ALI = "3";
    public final static String BANK = "4";
    public final static String XIAN_JIN = "5";
    public final static String SYS_BACK = "6";
    public final static String SYS_ECHARGE = "7";
    public final static String SYS_UPDATE_YU_E = "8";
    public final static String BONUS = "9";

    public final static String STR_YU_E = "余额支付";
    public final static String STR_WECHAT = "微信支付";
    public final static String STR_ALI = "支付宝支付";
    public final static String STR_BANK = "银联支付";
    public final static String STR_XIAN_JIN = "现金支付";
    public final static String STR_SYS_BACK = "系统退款";
    public final static String STR_SYS_ECHARGE = "系统充值";
    public final static String STR_SYS_UPDATE_YU_E = "更新余额";
    public final static String STR_BONUS = "所得奖金";

    public final static String[] PAY_TYPES = {STR_YU_E, STR_WECHAT, STR_ALI, STR_BANK, STR_XIAN_JIN};

    public final static String[] MONEY = {"2.00", "5.00", "10.00", "20.00",
            "50.00", "100.00", "200.00", "500.00", "1000.00"};

    public final static String fomcatPayType(String code){
        if (WECHAT.equals(code)){
            return STR_WECHAT;
        } else if (ALI.equals(code)){
            return STR_ALI;
        } else if (BANK.equals(code)){
            return STR_BANK;
        } else if (XIAN_JIN.equals(code)){
            return STR_XIAN_JIN;
        } else if (SYS_BACK.equals(code)){
            return STR_SYS_BACK;
        } else if (SYS_ECHARGE.equals(code)){
            return STR_SYS_ECHARGE;
        } else if (SYS_UPDATE_YU_E.equals(code)){
            return STR_SYS_UPDATE_YU_E;
        } else if (BONUS.equals(code)){
            return STR_BONUS;
        } else {
            return STR_YU_E;
        }
    }

    public final static String strToCode(String str){
        if (STR_WECHAT.equals(str)){
            return WECHAT;
        } else if (STR_ALI.equals(str)){
            return ALI;
        } else if (STR_BANK.equals(str)){
            return BANK;
        } else if (STR_XIAN_JIN.equals(str)){
            return XIAN_JIN;
        } else if (STR_SYS_BACK.equals(str)){
            return SYS_BACK;
        } else if (STR_SYS_ECHARGE.equals(str)){
            return SYS_ECHARGE;
        } else if (STR_SYS_UPDATE_YU_E.equals(str)){
            return SYS_UPDATE_YU_E;
        } else if (STR_BONUS.equals(str)){
            return BONUS;
        } else {
            return YU_E;
        }
    }

    public final static String fomcatIsAddToUser(String isAddToUser){
        if ("0".equals(isAddToUser)){
            return "同步成功";
        } else if ("1".equals(isAddToUser)){
            return "已计算";
        } else if ("3".equals(isAddToUser)){
            return "未同步";
        }
        return isAddToUser;
    }

    public final static String fomcatPayStatus(String payStatus){
        if (payStatus == null){
            return "";
        }
        if ("0".equals(payStatus)) {
            return "已支付";
        }else if ("1".equals(payStatus)){
            return "待支付";
        }else if ("2".equals(payStatus)){
            return "已退款";
        }
        return payStatus;
    }

}
