package com.cily.lottery.ac;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.Sp;
import com.cily.lottery.Utils;
import com.cily.lottery.dialog.InputDialog;
import com.cily.utils.app.listener.SingleClickListener;

public class UserInfoAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_user_info;
    }

    private String leftMoney, bankName, bankCard, address, idCard, phone, sex;
    @Override
    protected void initUI() {
        super.initUI();

        leftMoney = getIntent().getStringExtra("leftMoney");
        leftMoney = leftMoney == null ? "" : leftMoney;

        bankName = getIntent().getStringExtra("bankName");
        bankName = bankName == null ? "" : bankName;

        bankCard = getIntent().getStringExtra("bankCard");
        bankCard = bankCard == null ? "" : bankCard;

        address = getIntent().getStringExtra("address");
        address = address == null ? "" : address;

        idCard = getIntent().getStringExtra("idCard");
        idCard = idCard == null ? "" : idCard;

        phone = getIntent().getStringExtra("phone");
        phone = phone == null ? "" : phone;

        sex = getIntent().getStringExtra("sex");
        sex = sex == null ? "" : sex;

        TextView tv_userName = findView(R.id.tv_userName);
        setText(tv_userName, "用户账号：" + Sp.getStr(Conf.SP_USER_NAME, ""));

        TextView tv_realName = findView(R.id.tv_realName);
        setText(tv_realName, "真实姓名：" + Sp.getStr(Conf.SP_REAL_NAME, ""));

        TextView tv_sex = findView(R.id.tv_sex);
        setText(tv_sex, "用户性别：" + Utils.fomcatSex(sex));
        tv_sex.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {

            }
        });

        TextView tv_phone = findView(R.id.tv_phone);
        setText(tv_phone, "手机号码：" + phone);
        tv_phone.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("手机号码", phone, tv_phone);
            }
        });

        TextView tv_idCard = findView(R.id.tv_idCard);
        setText(tv_idCard, "身份证号：" + Utils.fomcatStar(idCard));

        TextView tv_address = findView(R.id.tv_address);
        setText(tv_address, "家庭住址：" + address);
        tv_address.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("家庭住址", address, tv_address);
            }
        });

        TextView tv_leftMoney = findView(R.id.tv_leftMoney);
        setText(tv_leftMoney, "账户余额：" + leftMoney);

        TextView tv_bankName = findView(R.id.tv_bankName);
        setText(tv_bankName, "开户银行：" + bankName);
        tv_bankName.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("开户银行", bankName, tv_bankName);
            }
        });

        TextView tv_bankCard = findView(R.id.tv_bankCard);
        setText(tv_bankCard, "银行卡号：" + bankCard);
        tv_bankCard.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showInputDialog("银行卡号", bankCard, tv_bankCard);
            }
        });
    }

    private InputDialog inputDialog;
    private void showInputDialog(String title, String defValue, final TextView tv){
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
                            tv.setText(input);
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
}
