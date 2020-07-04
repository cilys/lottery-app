package com.cily.lottery.fg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cily.lottery.R;
import com.cily.lottery.Utils;
import com.cily.lottery.ac.CashAc;
import com.cily.lottery.ac.ChangePwdAc;
import com.cily.lottery.ac.UserInfoAc;
import com.cily.lottery.ac.UserMoneyFlowAc;
import com.cily.lottery.bean.UserBean;
import com.cily.utils.app.listener.SingleClickListener;

public class MeFg extends BaseFg {
    @Override
    protected View initView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View v = layoutInflater.inflate(R.layout.fg_me, viewGroup, false);
        initUI(v);
        return v;
    }

    private TextView tv_realName, tv_sex, tv_idCard, tv_address, tv_leftMoney, tv_bankCard;
    private void initUI(View v) {
        v.findViewById(R.id.tv_change_pwd).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Intent i = new Intent(getActivity(), ChangePwdAc.class);
                startActivity(i);
            }
        });

        v.findViewById(R.id.tv_cash).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toCash();
            }
        });

        tv_sex = (TextView)v.findViewById(R.id.tv_sex);
        tv_sex.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toUserInfo();
            }
        });

        tv_realName = (TextView)v.findViewById(R.id.tv_realName);
        tv_realName.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toUserInfo();
            }
        });

        tv_idCard = (TextView)v.findViewById(R.id.tv_idCard);
        tv_idCard.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toUserInfo();
            }
        });

        tv_address = (TextView)v.findViewById(R.id.tv_address);
        tv_address.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toUserInfo();
            }
        });

        tv_leftMoney = (TextView)v.findViewById(R.id.tv_leftMoney);

        findView(R.id.tv_userMomey_flow).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Intent i = new Intent(getActivity(), UserMoneyFlowAc.class);
                startActivity(i);
            }
        });

        tv_bankCard = (TextView)v.findViewById(R.id.tv_bankCard);
        tv_bankCard.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toUserInfo();
            }
        });
        getUserInfo();
    }

    private void toCash(){
        if (userBean == null){
            showToast("暂未获取到账户信息，请稍等..");
            return;
        }
        Intent i = new Intent(getActivity(), CashAc.class);
        i.putExtra("leftMoney", userBean.getLeftMoney());
        i.putExtra("bankName", userBean.getBankName());
        i.putExtra("bankCard", userBean.getBankCard());
        startActivity(i);
    }

    private void toUserInfo(){
        if (userBean == null){
            showToast("暂未获取到账户信息，请稍等..");
            return;
        }
        Intent i = new Intent(getActivity(), UserInfoAc.class);
        i.putExtra("leftMoney", userBean.getLeftMoney());
        i.putExtra("bankName", userBean.getBankName());
        i.putExtra("bankCard", userBean.getBankCard());
        i.putExtra("address", userBean.getAddress());
        i.putExtra("idCard", userBean.getIdCard());
        i.putExtra("phone", userBean.getPhone());
        i.putExtra("sex", userBean.getSex());
        startActivity(i);
    }

    private UserBean userBean;
    @Override
    protected void getUserInfoSuccess(UserBean userBean) {
        super.getUserInfoSuccess(userBean);
        this.userBean = userBean;

        if (userBean != null){
            setText(tv_realName, "姓名：" + userBean.getRealName());
            setText(tv_sex, "性别：" + Utils.fomcatSex(userBean.getSex()));
            setText(tv_idCard, "身份证：" + Utils.fomcatStar(userBean.getIdCard()));
            setText(tv_address, "住址：" + userBean.getAddress());
            setText(tv_leftMoney, "余额：" + userBean.getLeftMoney());
            setText(tv_bankCard, "卡号：" + Utils.fomcatStar(userBean.getBankCard()));

//            Sp.putStr(Conf.SP_REAL_NAME, userBean.getRealName());
//            Sp.putStr(Conf.SP_SEX, userBean.getSex());
//            Sp.putStr(Conf.SP_PHONE, userBean.getPhone());
//            Sp.putStr(Conf.SP_ADDRESS, userBean.getAddress());
//            Sp.putStr(Conf.SP_IDCARD, userBean.getIdCard());
//            Sp.putStr(Conf.SP_LEFT_MONEY, userBean.getLeftMoney());
//            Sp.putStr(Conf.SP_BANK_NAME, userBean.getBankName());
//            Sp.putStr(Conf.SP_BANK_CARD, userBean.getBankCard());
        }
    }

    @Override
    protected void getUserInfoFailed(String s, String s1) {
        super.getUserInfoFailed(s, s1);
        showToast(s1);
    }
}