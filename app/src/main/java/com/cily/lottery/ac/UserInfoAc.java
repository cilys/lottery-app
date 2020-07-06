package com.cily.lottery.ac;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.SexUtils;
import com.cily.lottery.Sp;
import com.cily.lottery.Utils;
import com.cily.lottery.dialog.InputDialog;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.base.StrUtils;

public class UserInfoAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_user_info;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setTitle("个人信息");

        String leftMoney = fomcat(getIntent().getStringExtra("leftMoney"));
        String bankName = fomcat(getIntent().getStringExtra("bankName"));
        String bankCard = fomcat(getIntent().getStringExtra("bankCard"));
        String address = fomcat(getIntent().getStringExtra("address"));
        String idCard = Utils.fomcatStar(getIntent().getStringExtra("idCard"));
        String phone = fomcat(getIntent().getStringExtra("phone"));
        String sex = fomcat(getIntent().getStringExtra("sex"));

        final TextView tv_userName = findView(R.id.tv_userName);
        setText(tv_userName, "用户账号：" + Sp.getStr(Conf.SP_USER_NAME, ""));

        final TextView tv_realName = findView(R.id.tv_realName);
        setText(tv_realName, "真实姓名：" + Sp.getStr(Conf.SP_REAL_NAME, ""));

        final TextView tv_sex = findView(R.id.tv_sex);
        setText(tv_sex, "用户性别：" + Utils.fomcatSex(sex));
        setTag(tv_sex, sex);
        tv_sex.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showSexDialog(tv_sex);
            }
        });

        final TextView tv_phone = findView(R.id.tv_phone);
        setText(tv_phone, "手机号码：" + phone);
        setTag(tv_phone, phone);
        tv_phone.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("手机号码：", phone, tv_phone);
            }
        });

        final TextView tv_idCard = findView(R.id.tv_idCard);
        setText(tv_idCard, "身份证号：" + idCard);
        setTag(tv_idCard, idCard);
        tv_idCard.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("身份证号：", phone, tv_phone);
            }
        });

        final TextView tv_address = findView(R.id.tv_address);
        setText(tv_address, "家庭住址：" + address);
        setTag(tv_address, address);
        tv_address.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("家庭住址：", address, tv_address);
            }
        });

        final TextView tv_leftMoney = findView(R.id.tv_leftMoney);
        setText(tv_leftMoney, "账户余额：" + leftMoney);

        final TextView tv_bankName = findView(R.id.tv_bankName);
        setText(tv_bankName, "开户银行：" + bankName);
        setTag(tv_bankName, bankName);
        tv_bankName.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("开户银行：", bankName, tv_bankName);
            }
        });

        final TextView tv_bankCard = findView(R.id.tv_bankCard);
        setText(tv_bankCard, "银行卡号：" + bankCard);
        setTag(tv_bankCard, bankCard);
        tv_bankCard.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("银行卡号：", bankCard, tv_bankCard);
            }
        });

        findView(R.id.btn_commit).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                String sex = (String)tv_sex.getTag();
                String phone = (String)tv_phone.getTag();
                String idCard = (String)tv_idCard.getTag();
                String address = (String)tv_address.getTag();
                String bankName = (String)tv_bankName.getTag();
                String bankCard = (String)tv_bankCard.getTag();

                if (StrUtils.isEmpty(sex)
                        && StrUtils.isEmpty(phone)
                        && StrUtils.isEmpty(idCard)
                        && StrUtils.isEmpty(address)
                        && StrUtils.isEmpty(bankName)
                        && StrUtils.isEmpty(bankCard)){
                    showToast("请输入更新信息");
                    return;
                }

                updateUserInfo(sex, phone, idCard, address, bankName, bankCard);
            }
        });
    }

    private InputDialog inputDialog;
    private void showInputDialog(final String title, final String defValue, final TextView tv){
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
                public void onClick(Dialog dialog, View view, CharSequence input) {
                    if (input != null) {
                        if (tv != null) {
                            tv.setText(title + input);
                            tv.setTag(input.toString());
                        }
                    }
                }
        }).show();
    }
    private void dismissInputDialog(){
        if (inputDialog != null){
            inputDialog.dismiss();
        }
        inputDialog = null;
    }

    private void updateUserInfo(String sex, String phone, String idCard,
                                String address, String bankName, String bankCard){
        NetWork.updateUserInfo(this, sex, phone, idCard, address, bankName, bankCard, new ResultSubscriber() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    private void showSexDialog(final TextView tv_sex){
        new AlertDialog.Builder(this).setTitle("请选择性别：")
                .setItems(SexUtils.SEX, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_sex.setText("用户性别：" + SexUtils.SEX[which]);
                        tv_sex.setTag(SexUtils.strToCode(SexUtils.SEX[which]));
                    }
                }).create().show();
    }

    private void setTag(TextView tv, Object tag){
        if (tv != null){
            if (tag != null){
                tv.setTag(tag);
            }
        }
    }
}