package com.cily.lottery;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.Logs;

import java.math.BigDecimal;

public class Utils {

    public static String fomcatSex(String sex){
        if ("1".equals(sex)){
            return "男";
        }else if ("2".equals(sex)){
            return "女";
        }
        return "保密";
    }

    public static String fomcatStar(String idCard){
        if (!StrUtils.isEmpty(idCard)){
            char[] strs = idCard.toCharArray();
            for (int i = 0; i < strs.length; i++){
                if (i > 1 && i < strs.length - 2){
                    strs[i] = '*';
                }
            }
            return String.valueOf(strs);
        }
        return "";
    }

    public static BigDecimal toBigDecimal(String str){
        try{
            BigDecimal b = new BigDecimal(str);
            b = b.setScale(2, BigDecimal.ROUND_DOWN);
            return b;
        }catch (Exception e){
            Logs.printException(e);
            return null;
        }
    }
}
