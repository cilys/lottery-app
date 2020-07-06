package com.cily.lottery.ac;

import android.widget.TextView;

import com.cily.lottery.PayType;
import com.cily.lottery.R;
import com.cily.lottery.Utils;

import java.math.BigDecimal;

public class UserMoneyFlowInfoAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_user_money_flow_info;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setTitle("资金详情");
        String sourceUserName = getIntent().getStringExtra("sourceUserName");
        String schemeName = getIntent().getStringExtra("schemeName");
        String payType = getIntent().getStringExtra("payType");
        String money = getIntent().getStringExtra("money");
        String createTime = getIntent().getStringExtra("createTime");
        String isAddToUser = getIntent().getStringExtra("isAddToUser");

        TextView tv_money = findView(R.id.tv_money);
        setText(tv_money, "资金金额：" + fomcatMoney(money));

        TextView tv_payType = findView(R.id.tv_payType);
        setText(tv_payType, "支付方式：" + PayType.fomcatPayType(payType));

        TextView tv_isAddToUser = findView(R.id.tv_isAddToUser);
        setText(tv_isAddToUser, "资金状态：" + PayType.fomcatIsAddToUser(isAddToUser));

        TextView tv_sourceUserName = findView(R.id.tv_sourceUserName);
        setText(tv_sourceUserName, "资金来源：" + fomcat(sourceUserName));

        TextView tv_schemeName = findView(R.id.tv_schemeName);
        setText(tv_schemeName, "方案名称：" + fomcat(schemeName));

        TextView tv_createTime = findView(R.id.tv_createTime);
        setText(tv_createTime, "创建时间：" + fomcat(createTime));
    }

    private String fomcatMoney(String money){
        BigDecimal m = Utils.toBigDecimal(money, BaseAc.ZERO);
        if (Utils.lessThan(m, BaseAc.ZERO)){
            return "   " +m.toString();
        }else {
            return  " + " + m.toString();
        }
    }
}