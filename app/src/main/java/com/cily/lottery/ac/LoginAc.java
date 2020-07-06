package com.cily.lottery.ac;

import android.view.View;
import android.widget.EditText;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.Sp;
import com.cily.lottery.bean.UserBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.base.StrUtils;


public class LoginAc extends BaseAc {
    public final static String REGX_USER_NAME = "[0-9a-zA-Z]{1,16}";
    public final static String REGX_PWD = "[0-9a-zA-Z]{6,16}";

    @Override
    protected int getLayoutId() {
        return R.layout.ac_login;
    }

    @Override
    protected void initUI() {
        super.initUI();

        final EditText ed_userName = findView(R.id.ed_user_id);
        final EditText ed_pwd = findView(R.id.ed_pwd_id);
        String userName = Sp.getStr(Conf.SP_USER_NAME, "");
        ed_userName.setText(userName);
        ed_userName.setSelection(ed_userName.getText().toString().length());
        String pwd = Sp.getStr(Conf.SP_PWD, "");
        ed_pwd.setText(pwd);

        findView(R.id.btn_login_id).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                login(ed_userName.getText().toString().trim(), ed_pwd.getText().toString().trim());
            }
        });

        findView(R.id.btn_regist_id).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toAc(RegisterAc.class, null);
            }
        });

        findView(R.id.tv_phone_to_reset_pwd).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
//                toAc(ForgetAc.class, null);
                showToast("请联系店主，重置登录密码");
            }
        });
    }

    private void login(final String userName, final String pwd){
        if (StrUtils.isEmpty(userName)){
            showToast("账号为空");
            return;
        }
        if (!userName.matches(REGX_USER_NAME)){
            showToast("用户名需是1至16位的字母、数字");
            return;
        }

        if (StrUtils.isEmpty(pwd)){
            showToast("密码为空");
            return;
        }
        if (!pwd.matches(REGX_PWD)){
            showToast("密码需是6至16位的字母、数字");
            return;
        }
        if (!loading("登录中...", true)){
            return;
        }
        NetWork.login(this, userName, pwd, new ResultSubscriber<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                disLoading();
                if (userBean != null) {
                    showToast("登录成功...");
                    Sp.putStr(Conf.SP_USER_NAME, userName);
                    Sp.putStr(Conf.SP_PWD, pwd);
                    Sp.putStr(Conf.SP_USER_ID, userBean.getUserId());
                    Sp.putStr(Conf.SP_REAL_NAME, userBean.getRealName());
                    Sp.putStr(Conf.SP_LEFT_MONEY, userBean.getLeftMoney());
                    toAcWithFinish(MainAc.class, null);
                }else {
                    showToast("登录失败，请重试...");
                }
            }

            @Override
            public void onFailure(String s, String s1) {
                disLoading();
                showToast(s1);
            }
        });
    }
}