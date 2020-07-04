package com.cily.lottery.ac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.Sp;
import com.cily.lottery.ac.LoginAc;
import com.cily.lottery.bean.UserBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.event.Event;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.ac.BaseOkHttpRxBusActivity;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.app.utils.NetUtils;
import com.cily.utils.base.StrUtils;


/**
 * Created by admin on 2018/4/12.
 */

public abstract class BaseAc extends BaseOkHttpRxBusActivity {

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getIntentData();
        initTitle();
        initUI();
    }

    private ImageView img_back;
    private TextView tv_title;
    protected void initTitle(){
        img_back = findView(R.id.img_back_id);
        if (img_back != null){
            img_back.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    onClickTitleLeftImg();
                }
            });
        }
        tv_title = findView(R.id.tv_title_id);
    }

    protected void setTitle(String title){
        if (tv_title != null){
            tv_title.setText(title == null ? "" : title);
        }
    }

    protected void onClickTitleLeftImg(){
        finish();
    }

    protected abstract int getLayoutId();
    protected void getIntentData(){}
    protected void initUI(){
        srl = findView(R.id.srl);
        if (srl != null){
            srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    srlRefreshListener();
                }
            });
        }
    }

    protected void srlRefreshListener(){}

    protected boolean loading(String loadingText, boolean showToastNoNet){
        if (!NetUtils.isNetAvailable(this)){
            if (showToastNoNet){
                showToast(getString(R.string.str_no_net));
            }
            return false;
        }
        showLoading(loadingText);
        return true;
    }

    @Override
    protected void doRxbus(Event e) {
        super.doRxbus(e);
        if (e != null && e.what == Conf.RX_LOGIN_OTHER){
            if (this instanceof LoginAc){
                return;
            }
            toAcWithFinish(LoginAc.class, null);
        }
    }

    protected String formcatStr(String str, int keepLength){
        if (!StrUtils.isEmpty(str)){
            int length = str.length();
            if (keepLength <= 0){
                StringBuilder su = StrUtils.getStringBuilder();
                for (int i = 0; i < length; i++){
                    su.append("*");
                }
                return su.toString();
            }else if (keepLength >= length){
                return str;
            }else {
                StringBuilder su = StrUtils.getStringBuilder();
                for (int i = 0; i < length - keepLength; i++){
                    su.append("*");
                }
                str = str.substring(0, keepLength) + su.toString();
                return str;
            }
        }
        return "";
    }

    protected void getUserInfo(){
        NetWork.userInfo(this, Sp.getStr(Conf.SP_USER_ID, null), new ResultSubscriber<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {

            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    protected void setText(TextView tv, String value){
        if (tv == null){
            return;
        }
        if (StrUtils.isEmpty(value)){
            return;
        }
        tv.setText(value);
    }

    protected SwipeRefreshLayout srl;
    protected void srlRefresh(boolean refresh) {
        if (srl != null) {
            srl.setRefreshing(refresh);
        }
    }
}
