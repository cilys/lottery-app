package com.cily.lottery.net;

import com.cily.lottery.bean.SchemeBean;
import com.cily.lottery.bean.UserBean;
import com.cily.lottery.bean.UserMoneyFlowBean;

import java.util.Map;

import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 123 on 2018/4/15.
 */

public interface NetService {
    String URL_LOGIN = "user/login";
    String URL_REGIST = "user/regist";
    String URL_PWD_CHANGE = "user/changePwd";

    String URL_USER_INFO = "user/userInfo";

    String URL_USER_MONEY_FLOW = "userMoneyFlow/query";

    String URL_SCHEME_LIST = "scheme/queryAll";

    @POST(NetService.URL_LOGIN)
    Observable<BaseResBean<UserBean>> login(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_REGIST)
    Observable<BaseResBean> regist(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_PWD_CHANGE)
    Observable<BaseResBean> changePwd(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_USER_INFO)
    Observable<BaseResBean<UserBean>>userInfo(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_USER_MONEY_FLOW)
    Observable<BaseResBean<UserMoneyFlowBean>> userMoneyFlow(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_SCHEME_LIST)
    Observable<BaseResBean<SchemeBean>> schemeList(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

}
