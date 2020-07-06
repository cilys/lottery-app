package com.cily.lottery.ac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cily.lottery.R;
import com.cily.utils.app.listener.SingleClickListener;

public class SchemeInfoAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_scheme_info;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("方案详情");

        String totalBonus = getIntent().getStringExtra("totalBonus");
        String canUseBonus = getIntent().getStringExtra("canUseBonus");
        String totalMoney = getIntent().getStringExtra("totalMoney");
        String outOfTime = getIntent().getStringExtra("outOfTime");
        String selledMoney = getIntent().getStringExtra("selledMoney");
        String payedMoney = getIntent().getStringExtra("payedMoney");
        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        String bonusRate = getIntent().getStringExtra("bonusRate");
        String descption = getIntent().getStringExtra("descption");
        String status = getIntent().getStringExtra("status");
        boolean isHistory = getIntent().getBooleanExtra("isHistory", false);

        TextView tv_name = findView(R.id.tv_name);
        setText(tv_name, "方案名称：" + fomcat(name));

        TextView tv_totalMoney = findView(R.id.tv_totalMoney);
        setText(tv_totalMoney, "方案总额：" + fomcat(totalMoney));

        TextView tv_selledMoney = findView(R.id.tv_selledMoney);
        setText(tv_selledMoney, "已售金额：" + fomcat(selledMoney));

        TextView tv_payedMoney = findView(R.id.tv_payedMoney);
        setText(tv_payedMoney, "成交金额：" + fomcat(payedMoney));

        TextView tv_outOfTime = findView(R.id.tv_outOfTime);
        setText(tv_outOfTime, "过期时间：" + fomcat(outOfTime));

        TextView tv_status = findView(R.id.tv_status);
        setText(tv_status, "方案状态：" + fomcat(status));

        TextView tv_totalBonus = findView(R.id.tv_totalBonus);
        setText(tv_totalBonus, "奖金总额：" + fomcat(totalBonus));

        TextView tv_bonusRate = findView(R.id.tv_bonusRate);
        setText(tv_bonusRate, "税率（%）：" + fomcat(bonusRate));

        TextView tv_canUseBonus = findView(R.id.tv_canUseBonus);
        setText(tv_canUseBonus, "可用奖金：" + fomcat(canUseBonus));

        TextView tv_descption = findView(R.id.tv_descption);
        setText(tv_descption, "方案详情：" + fomcat(descption));

        Button btn_commit = findView(R.id.btn_commit);
        btn_commit.setVisibility(isHistory ? View.GONE : View.VISIBLE);
        btn_commit.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("totalMoney", totalMoney);
                bundle.putString("selledMoney", selledMoney);
                bundle.putString("name", name);
                bundle.putString("id", id);
                toAc(OrderAddAc.class, bundle);
            }
        });
    }
}