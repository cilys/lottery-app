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
        return toBigDecimal(str, null);
    }

    public static BigDecimal toBigDecimal(String str, BigDecimal defValue){
        try{
            BigDecimal b = new BigDecimal(str);
            b = b.setScale(2, BigDecimal.ROUND_DOWN);
            return b;
        }catch (Exception e){
            Logs.printException(e);
            return defValue;
        }
    }

    public static BigDecimal add(BigDecimal b1, BigDecimal b2){
        BigDecimal b = b1.add(b2);
        b = b.setScale(2, BigDecimal.ROUND_DOWN);
        return b;
    }

    public static boolean equal(BigDecimal b1, BigDecimal b2){
        if (b1 == null || b2 == null){
            return false;
        }
        return b1.compareTo(b2) == 0;
    }

    public static boolean moreThan(BigDecimal b1, BigDecimal b2){
        if (b1 == null || b2 == null){
            return false;
        }
        return b1.compareTo(b2) == 1;
    }
    public static boolean lessThan(BigDecimal b1, BigDecimal b2){
        if (b1 == null || b2 == null){
            return false;
        }
        return b1.compareTo(b2) == -1;
    }

    public static boolean noMoreThan(BigDecimal b1, BigDecimal b2){
        return lessThan(b1, b2) || equal(b1, b2);
    }

    public static boolean noLessThan(BigDecimal b1, BigDecimal b2){
        return moreThan(b1, b2) || equal(b1, b2);
    }
    public static BigDecimal subtract(BigDecimal b1, BigDecimal b2){
        BigDecimal b = b1.subtract(b2);

        b = b.setScale(2, BigDecimal.ROUND_DOWN);

        return b;
    }

    public static String fomcatStatus(String status){
        if (StrUtils.isEmpty(status)){
            return "正常";
        }
        if ("0".equals(status)){
            return "正常";
        } else if ("1".equals(status)){
            return "禁用";
        }
        return status;
    }
}
