package com.cily.lottery.net;

import com.cily.lottery.bean.CashBean;
import com.cily.lottery.bean.OrderBean;
import com.cily.lottery.bean.SchemeBean;
import com.cily.lottery.bean.UserBean;
import com.cily.lottery.bean.UserMoneyFlowBean;

import java.util.Map;

import retrofit2.http.Body;
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

    String URL_ORDER_ADD = "order/add";

    String URL_ORDER_LIST = "order/query";

    String URL_UPDATE_USER = "user/updateInfo";
    String URL_CASH_ADD = "cash/add";
    String URL_CASH_LIST = "cash/query";

    @POST(NetService.URL_LOGIN)
    Observable<BaseResBean<UserBean>> login(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_PWD_CHANGE)
    Observable<BaseResBean> changePwd(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_USER_INFO)
    Observable<BaseResBean<UserBean>>userInfo(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_USER_MONEY_FLOW)
    Observable<BaseResBean<UserMoneyFlowBean>> userMoneyFlow(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_SCHEME_LIST)
    Observable<BaseResBean<SchemeBean>> schemeList(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_ORDER_ADD)
    Observable<BaseResBean> orderAdd(@HeaderMap Map<String, String> headers, @Body Map<String, String> map);

    @POST(NetService.URL_ORDER_LIST)
    Observable<BaseResBean<OrderBean>> orderList(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @POST(NetService.URL_UPDATE_USER)
    Observable<BaseResBean> updateUserInfo(@HeaderMap Map<String, String> headers, @Body Map<String, String> map);

    @POST(NetService.URL_REGIST)
    Observable<BaseResBean> regist(@HeaderMap Map<String, String> headers, @Body Map<String, String> map);

    @POST(NetService.URL_CASH_ADD)
    Observable<BaseResBean> cashAdd(@HeaderMap Map<String, String> headers, @Body Map<String, String> map);

    @POST(NetService.URL_CASH_LIST)
    Observable<BaseResBean<CashBean>> cashList(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);
}