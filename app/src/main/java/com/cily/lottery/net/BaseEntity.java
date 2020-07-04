package com.cily.lottery.net;

import com.cily.lottery.Conf;
import com.cily.lottery.MyApp;
import com.cily.lottery.Sp;
import com.cily.utils.app.event.Event;
import com.cily.utils.app.rx.RxBus;
import com.cily.utils.app.rx.okhttp.ResponseException;
import com.cily.utils.app.utils.SpUtils;
import com.cily.utils.base.StrUtils;


import rx.functions.Func1;

/**
 * Created by 123 on 2018/4/15.
 */

public class BaseEntity<T> implements Func1<BaseResBean<T>, T> {
    public T call(BaseResBean<T> b) {
        if(!"0".equals(b.getCode())) {
            if ("1002".equals(b.getCode()) || "1003".equals(b.getCode())) {
                Event e = Event.obtain();
                e.what = Conf.RX_LOGIN_OTHER;
                RxBus.getInstance().post(e);
                Sp.logout();
                return null;
            }
            throw new ResponseException(b.getCode(), b.getMsg() == null?"未知异常":b.getMsg());
        } else {
            if (!StrUtils.isEmpty(b.getToken())){
                SpUtils.putStr(MyApp.getApp(), Conf.SP_TOKEN, b.getToken());
            }
            return b.getData();
        }
    }
}