package com.cily.lottery.ac;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cily.lottery.R;
import com.cily.lottery.Utils;
import com.cily.utils.app.listener.SingleClickListener;

public class CashAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_cash;
    }

    @Override
    protected void initUI() {
        super.initUI();

        showToast("申请提现");

        String leftMoney = getIntent().getStringExtra("leftMoney");
        leftMoney = leftMoney == null ? "0.00" : leftMoney;
        String bankName = getIntent().getStringExtra("bankName");
        bankName = bankName == null ? "" : bankName;
        String bankCard = getIntent().getStringExtra("bankCard");
        bankCard = bankCard == null ? "" : bankCard;

        TextView tv_leftMoney = findView(R.id.tv_leftMoney);
        setText(tv_leftMoney, "账户余额：" + leftMoney);
        TextView tv_bankName = findView(R.id.tv_bankName);
        setText(tv_bankName, "开户银行：" + bankName);
        TextView tv_bankCard = findView(R.id.tv_bankCard);
        setText(tv_bankCard, "银行卡号：" + Utils.fomcatStar(bankCard));

        EditText ed_money = findView(R.id.ed_money);

        findView(R.id.btn_commit).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {

            }
        });
    }
}