package com.cily.lottery.ac;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.cily.lottery.R;
import com.cily.lottery.SexUtils;
import com.cily.lottery.dialog.InputDialog;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.base.StrUtils;

public class RegisterAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_register;
    }

    private String userName, realName, sex,
            phone, idCard, address, bankName, bankCard, pwd; ;
    @Override
    protected void initUI() {
        super.initUI();

        final TextView tv_userName = findView(R.id.tv_userName);
        tv_userName.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("用户账号：", userName, tv_userName);
            }
        });

        final TextView tv_pwd = findView(R.id.tv_pwd);
        tv_pwd.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("登录密码：", pwd, tv_pwd);
            }
        });

        final TextView tv_realName = findView(R.id.tv_realName);
        tv_realName.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("真实姓名：", realName, tv_realName);
            }
        });

        final TextView tv_sex = findView(R.id.tv_sex);
        tv_sex.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showSexDialog(tv_sex);
            }
        });

        final TextView tv_phone = findView(R.id.tv_phone);
        tv_phone.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("手机号码：", phone, tv_phone);
            }
        });

        final TextView tv_idCard = findView(R.id.tv_idCard);
        tv_idCard.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("身份证号：", idCard, tv_idCard);
            }
        });

        final TextView tv_address = findView(R.id.tv_address);
        tv_address.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("家庭住址：", address, tv_address);
            }
        });

        final TextView tv_bankName = findView(R.id.tv_bankName);
        tv_bankName.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("开户银行：", bankName, tv_bankName);
            }
        });

        final TextView tv_bankCard = findView(R.id.tv_bankCard);
        tv_bankCard.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("银行卡号：", bankCard, tv_bankCard);
            }
        });

        findView(R.id.btn_commit).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                regist();
            }
        });
    }

    private void showSexDialog(final TextView tv_sex){
        new AlertDialog.Builder(this).setTitle("请选择性别：")
                .setItems(SexUtils.SEX, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sex = SexUtils.strToCode(SexUtils.SEX[which]);
                        tv_sex.setText("用户性别：" + SexUtils.SEX[which]);
                    }
                }).create().show();
    }

    private void regist(){
        if (StrUtils.isEmpty(userName)){
            showToast("账号为空");
            return;
        }
        if (!userName.matches(LoginAc.REGX_USER_NAME)){
            showToast("用户名需是1至16位的字母、数字");
            return;
        }

        if (StrUtils.isEmpty(pwd)){
            showToast("密码为空");
            return;
        }
        if (StrUtils.isEmpty(realName)){
            showToast("真实姓名为空");
            return;
        }

        if (!loading("注册中..", true)){
            return;
        }
        NetWork.regist(this, userName, pwd, realName, sex, phone,
                idCard, address, bankName, bankCard, new ResultSubscriber() {
                    @Override
                    public void onSuccess(Object o) {
                        showToast("注册成功，请耐心等待审核..");
                        finish();
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        disLoading();
                        showToast(s1);
                    }
                });
    }

    private InputDialog inputDialog;
    private void showInputDialog(final String title, CharSequence defValue, final TextView tv){
        InputMethodManager inputManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        inputDialog = new InputDialog(this);
        inputDialog.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        if (tv != null) {
            inputDialog.setMsg(defValue);
        }
        inputDialog.setTitle(title)
                .setCanceledOnTouchOutside(false)
                .setCancelBtn(new InputDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, View view, CharSequence input) {
                        dismissInputDialog();
                    }
                }).setCommitBtn(new InputDialog.OnClickListener() {
            @Override
            public void onClick(Dialog dialog, View view, CharSequence in) {
                if (in != null) {
                    String input = in.toString();
                    if (tv != null) {
                        tv.setText(title + input);

                        if (tv.getId() == R.id.tv_userName){
                            userName = input;
                        } else if (tv.getId() == R.id.tv_pwd){
                            pwd = input;
                        } else if (tv.getId() == R.id.tv_realName){
                            realName = input;
                        } else if (tv.getId() == R.id.tv_phone){
                            phone = input;
                        } else if (tv.getId() == R.id.tv_idCard){
                            idCard = input;
                        } else if (tv.getId() == R.id.tv_address){
                            address = input;
                        } else if (tv.getId() == R.id.tv_bankName){
                            bankName = input;
                        } else if (tv.getId() == R.id.tv_bankCard){
                            bankCard = input;
                        }
                    }
                }
                dismissInputDialog();
            }
        }).show();
    }
    private void dismissInputDialog(){
        if (inputDialog != null){
            inputDialog.dismiss();
        }
        inputDialog = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dismissInputDialog();
    }
}
