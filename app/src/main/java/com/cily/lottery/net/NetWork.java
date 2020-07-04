package com.cily.lottery.net;

import com.cily.lottery.BuildConfig;
import com.cily.lottery.Conf;
import com.cily.lottery.Sp;
import com.cily.lottery.bean.SchemeBean;
import com.cily.lottery.bean.UserBean;
import com.cily.lottery.bean.UserMoneyFlowBean;
import com.cily.utils.app.rx.okhttp.HeaderInterceptor;
import com.cily.utils.app.rx.okhttp.OkHttpUtils;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.app.rx.okhttp.RetrofitUtils;
import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.LogType;

import com.trello.rxlifecycle.LifecycleProvider;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 123 on 2018/4/15.
 */

public class NetWork {
    private static NetService getService(){
        OkHttpUtils.addInterceptor(OkHttpUtils.getLogInterceptor(
                LogType.INFO, HttpLoggingInterceptor.Level.BODY), true);
        Map<String, String>map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("osType", "2");
        map.put("osVersion", BuildConfig.VERSION_NAME);
        HeaderInterceptor it = new HeaderInterceptor(map);
        OkHttpUtils.addInterceptor(it, false);

        return RetrofitUtils.getInstance(false).getRetrofit().create(NetService.class);
    }

    public final static void login(LifecycleProvider lp, String user,
                                   String pwd, ResultSubscriber<UserBean> rs){
        if (lp == null){
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("userName", user);
        map.put("pwd", pwd);

        Observable ob = getService().login(headers(), map)
                .map(new BaseEntity<UserBean>()).compose(lp.bindToLifecycle());

        toSub(ob, rs);
    }

    public final static void regist(LifecycleProvider lp, String userName,
                                    String pwd, String realName, String idCard,
                                    String phone, String sex,
                                    ResultSubscriber rs){
        if (lp == null){
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);
        map.put("pwd", pwd);
        map.put("realName", realName);
        map.put("idCard", idCard);
        if (!StrUtils.isEmpty(phone)){
            map.put("phone", phone);
        }
        if (!StrUtils.isEmpty(sex)){
            map.put("sex", sex);
        }

        Observable ob = getService().regist(headers(), map)
                .map(new BaseEntity()).compose(lp.bindToLifecycle());

        toSub(ob, rs);
    }


    public final static void changePwd(LifecycleProvider lp, String oldPwd, String newPwd, ResultSubscriber rs){
        if (lp == null){
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("pwd", oldPwd);
        map.put("newPwd", newPwd);
        Observable ob = getService().changePwd(headers(), map)
                .map(new BaseEntity()).compose(lp.bindToLifecycle());

        toSub(ob, rs);
    }

    private static Map<String, String> headers(){
        Map<String, String> headers = new HashMap<>();
        String userId = Sp.getStr(Conf.SP_USER_ID, "");
        if (!StrUtils.isEmpty(userId)) {
            headers.put("userId", userId);
        }
        String token = Sp.getStr(Conf.SP_TOKEN, null);
        if (!StrUtils.isEmpty(token)){
            headers.put("token", token);
        }
        headers.put("deviceImei", Sp.getSN());
        return headers;
    }

    public final static void userInfo(LifecycleProvider lp, String userId,
                                      ResultSubscriber<UserBean> rs){
        if (lp == null){
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        Observable ob = getService().userInfo(headers(), map)
                .map(new BaseEntity<UserBean>()).compose(lp.bindToLifecycle());

        toSub(ob, rs);
    }

    public final static void userMoneyFlow(LifecycleProvider lp,
                                      int pageNumber,
                                      ResultSubscriber<UserMoneyFlowBean> rs){
        if (lp == null){
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("pageNumber", String.valueOf(pageNumber));
        map.put("pageSize", String.valueOf(Conf.PAGE_SIZE));

        Observable ob = getService().userMoneyFlow(headers(), map)
                .map(new BaseEntity<UserMoneyFlowBean>()).compose(lp.bindToLifecycle());

        toSub(ob, rs);
    }

    public final static void schemeList(LifecycleProvider lp, int pageNumber,
                                           ResultSubscriber<SchemeBean> rs){
        if (lp == null){
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("pageNumber", String.valueOf(pageNumber));
        map.put("pageSize", String.valueOf(Conf.PAGE_SIZE));

        Observable ob = getService().schemeList(headers(), map)
                .map(new BaseEntity<SchemeBean>()).compose(lp.bindToLifecycle());

        toSub(ob, rs);
    }


    private static <T> void toSub(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}