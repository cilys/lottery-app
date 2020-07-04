package com.cily.lottery;

import com.cily.utils.app.utils.SpUtils;
import com.cily.utils.base.StrUtils;
import com.cily.utils.base.UUIDUtils;

/**
 * Created by 123 on 2018/4/22.
 */

public class Sp {
    public final static void putStr(String key, String value){
        SpUtils.putStr(MyApp.getApp(), key, value);
    }

    public final static String getStr(String key, String defValue){
        return SpUtils.getStr(MyApp.getApp(), key, defValue);
    }

    public final static String getSN(){
        String sn = Sp.getStr("SP_SN", null);
        if (StrUtils.isEmpty(sn)){
            sn = UUIDUtils.getUUID();
            putStr("SP_SN", sn);
        }
        return sn;
    }

    public final static long getLong(String key, long defValue){
        return SpUtils.getLong(MyApp.getApp(), key, defValue);
    }
    public final static void putLong(String key, long value){
        SpUtils.putLong(MyApp.getApp(), key, value);
    }

    public final static void logout(){
        SpUtils.remove(MyApp.getApp(),
                Conf.SP_TOKEN,
                Conf.SP_REAL_NAME,
                Conf.SP_USER_ID);
    }

    public final static boolean isLogout(){
        return StrUtils.isEmpty(Sp.getStr(Conf.SP_USER_ID, null))
                || StrUtils.isEmpty(Sp.getStr(Conf.SP_TOKEN, null));
    }
}
