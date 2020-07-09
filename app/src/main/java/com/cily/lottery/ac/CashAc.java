package com.cily.lottery.ac;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.Sp;
import com.cily.lottery.Utils;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;
import com.cily.utils.base.StrUtils;

public class CashAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_cash;
    }

    @Override
    protected void initUI() {
        super.initUI();


        setTitle("申请提现");

        String leftMoney = getIntent().getStringExtra("leftMoney");
        leftMoney = leftMoney == null ? "0.00" : leftMoney;
        String bankName = getIntent().getStringExtra("bankName");
        bankName = bankName == null ? "" : bankName;
        String bankCard = getIntent().getStringExtra("bankCard");
        bankCard = bankCard == null ? "" : bankCard;

        String coldMoney = fomcat(getIntent().getStringExtra("coldMoney"));

        TextView tv_leftMoney = findView(R.id.tv_leftMoney);
        setText(tv_leftMoney, "账户余额：" + leftMoney + fomcatColdMoney(coldMoney));
        TextView tv_bankName = findView(R.id.tv_bankName);
        setText(tv_bankName, "开户银行：" + bankName);
        TextView tv_bankCard = findView(R.id.tv_bankCard);
        setText(tv_bankCard, "银行卡号：" + Utils.fomcatStar(bankCard));

        final EditText ed_money = findView(R.id.ed_money);
        final EditText ed_msg = findView(R.id.ed_msg);

        findView(R.id.btn_commit).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                addCash(ed_money.getText().toString().trim(), ed_msg.getText().toString().trim());
            }
        });

        findView(R.id.btn_list).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toAc(CashListAc.class, null);
            }
        });
    }

    private String fomcatColdMoney(String coldMoney){
        if (StrUtils.isEmpty(coldMoney)){
            return "";
        }
        return " + " + coldMoney + "(冻结中)";
    }

    private void addCash(String money, String msg){
        if (StrUtils.isEmpty(money)){
            showToast("请输入提现金额");
            return;
        }
        if (!isNumber(money)){
            showToast("提现金额不合法");
            return;
        }
        if (!loading("请求中..", true)){
            return;
        }
        NetWork.cashAdd(this, Sp.getStr(Conf.SP_USER_ID, null), money, msg, new ResultSubscriber() {
            @Override
            public void onSuccess(Object o) {
                showToast("操作成功");
                disLoading();

                getUserInfo();
            }

            @Override
            public void onFailure(String s, String s1) {
                showToast(s1);
                disLoading();
            }
        });
    }

    public static boolean isNumber(String str) {
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        java.util.regex.Matcher match=pattern.matcher(str);
        if(match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }
}