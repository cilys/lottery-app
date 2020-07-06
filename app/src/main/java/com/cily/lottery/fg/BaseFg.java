package com.cily.lottery.fg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.Sp;
import com.cily.lottery.bean.UserBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.fg.BaseOkHttpRxBusLazyFragment;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.app.utils.AcUtils;
import com.cily.utils.app.utils.NetUtils;
import com.cily.utils.base.StrUtils;

/**
 * Created by 123 on 2018/4/21.
 */

public abstract class BaseFg extends BaseOkHttpRxBusLazyFragment {
    private ImageView img_back;
    private TextView tv_title;
    private ImageView img_right;

    protected void initTitle(View v){
        img_back = (ImageView)v.findViewById(R.id.img_back);
        if (img_back != null){
            img_back.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    onClickTitleLeftImg();
                }
            });
        }
        tv_title = (TextView)v.findViewById(R.id.tv_title);
        img_right = (ImageView)v.findViewById(R.id.img_right);
        if (img_right != null){
            img_right.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    onClickTitleRightImg();
                }
            });
        }
    }
    @SuppressLint("ResourceType")
    protected void showTitleRightImg(boolean show, @DrawableRes int icon){
        if (img_right != null){
            img_right.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            if (icon < 0){

            }else {
                img_right.setImageResource(icon);
            }
        }
    }
    protected void onClickTitleRightImg(){

    }
    protected void showTitleLeftImg(boolean show){
        if (img_back != null){
            img_back.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        }
    }

    protected void setTitle(String title){
        if (tv_title != null){
            tv_title.setText(title == null ? "" : title);
        }
    }

    protected void onClickTitleLeftImg(){
        if (!AcUtils.finishing(getActivity())){
            getActivity().finish();
        }
    }

    protected void getUserInfo(boolean showLoading){
        if (showLoading) {
            if (!loading("加载中...", true)) {
                return;
            }
        } else {

        }
        NetWork.userInfo(this, Sp.getStr(Conf.SP_USER_ID, null), new ResultSubscriber<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                getUserInfoSuccess(userBean);
                disLoading();
            }

            @Override
            public void onFailure(String s, String s1) {
                getUserInfoFailed(s, s1);
                disLoading();
            }
        });
    }

    protected void getUserInfoSuccess(UserBean userBean){}
    protected void getUserInfoFailed(String s, String s1){}

    protected boolean loading(String loadingText, boolean showToastNoNet){
        if (!NetUtils.isNetAvailable(getActivity())){
            if (showToastNoNet){
                showToast(getString(R.string.str_no_net));
            }
            return false;
        }
        showLoading(loadingText);
        startTimer();
        return true;
    }

    @Override
    protected void disLoading() {
        super.disLoading();
        stopTimer();
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
        if (refresh){
            startTimer();
        }else {
            disLoading();
        }
    }

    private CountDownTimer cdt;
    protected void startTimer(){
        stopTimer();
        if (cdt == null) {
            cdt = new CountDownTimer(45000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    srlRefresh(false);
                    disLoading();
                }
            };
        }
        cdt.start();
    }
    protected void stopTimer(){
        if (cdt != null){
            cdt.cancel();
        }
    }

    protected void toAc(Class<? extends Activity> cls, Bundle bundle){
        if (getActivity() != null) {
            Intent i = new Intent(getActivity(), cls);
            if (bundle != null) {
                i.putExtras(bundle);
            }
            startActivity(i);
        }
    }

    protected void toAcWithFinish(Class<? extends Activity> cls, Bundle bundle){
        toAc(cls, bundle);
        if (getActivity() != null){
            getActivity().finish();
        }
    }
}