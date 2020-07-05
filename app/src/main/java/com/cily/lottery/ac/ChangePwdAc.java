package com.cily.lottery.ac;

import android.view.View;
import android.widget.EditText;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.Sp;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.event.Event;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.RxBus;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.base.StrUtils;

public class ChangePwdAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_change_pwd;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setTitle("修改密码");

        final EditText ed_old = findView(R.id.ed_old_pwd);
        final EditText ed_new = findView(R.id.ed_new_pwd);
        final EditText ed_renew = findView(R.id.ed_renew_pwd);

        findView(R.id.btn_change_pwd).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                changePwd(ed_old.getText().toString().trim(),
                        ed_new.getText().toString().trim(),
                        ed_renew.getText().toString().trim());
            }
        });
    }
    private void changePwd(String oldPwd, String newPwd, String renewPwd){
        if (StrUtils.isEmpty(oldPwd)){
            showToast("请输入原密码");
            return;
        }
        if (StrUtils.isEmpty(newPwd)){
            showToast("请输入新密码");
            return;
        }

        if (StrUtils.isEmpty(renewPwd)){
            showToast("请重复新密码");
            return;
        }

        String pwd = Sp.getStr(Conf.SP_PWD, "");
        if (!oldPwd.equals(pwd)){
            showToast("原密码不正确");
            return;
        }
        if (!newPwd.equals(renewPwd)){
            showToast("新密码不一致");
            return;
        }
        if (oldPwd.equals(newPwd)){
            showToast("新密码与原密码相同");
            return;
        }
        if (!oldPwd.matches(LoginAc.REGX_PWD) || !newPwd.matches(LoginAc.REGX_PWD)){
            showToast("密码需是6至16位的字母、数字");
            return;
        }

        if (!loading("修改密码...", true)){
            return;
        }
        NetWork.changePwd(this, oldPwd, newPwd, new ResultSubscriber() {
            @Override
            public void onSuccess(Object o) {
                disLoading();
                showToast("修改密码成功，请重新登录");
                Event e = Event.obtain();
                e.what = Conf.RX_LOGIN_OTHER;
                RxBus.getInstance().post(e);
                finish();
            }

            @Override
            public void onFailure(String s, String s1) {
                showToast(s1);
                disLoading();
            }
        });
    }
}