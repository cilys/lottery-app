package com.cily.lottery.ac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cily.lottery.R;
import com.cily.lottery.Utils;
import com.cily.lottery.bean.SchemeBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;

import java.math.BigDecimal;

public class SchemeInfoAc extends BaseAc {
    private String schemeId;
    private TextView tv_name, tv_totalMoney, tv_selledMoney,
            tv_leftMoney, tv_payedMoney, tv_outOfTime, tv_status,
            tv_totalBonus, tv_bonusRate, tv_canUseBonus, tv_descption;

    private String totalMoney, selledMoney;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_scheme_info;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("方案详情");

        srl = findView(R.id.srl);

        String totalBonus = getIntent().getStringExtra("totalBonus");
        String canUseBonus = getIntent().getStringExtra("canUseBonus");
        totalMoney = getIntent().getStringExtra("totalMoney");
        String outOfTime = getIntent().getStringExtra("outOfTime");
        selledMoney = getIntent().getStringExtra("selledMoney");
        String payedMoney = getIntent().getStringExtra("payedMoney");
        String name = getIntent().getStringExtra("name");
        schemeId = getIntent().getStringExtra("id");
        String bonusRate = getIntent().getStringExtra("bonusRate");
        String descption = getIntent().getStringExtra("descption");
        String status = getIntent().getStringExtra("status");
        boolean isHistory = getIntent().getBooleanExtra("isHistory", false);

        tv_name = findView(R.id.tv_name);
        setText(tv_name, "方案名称：" + fomcat(name));

        tv_totalMoney = findView(R.id.tv_totalMoney);
        setText(tv_totalMoney, "方案总额：" + fomcat(totalMoney));

        tv_selledMoney = findView(R.id.tv_selledMoney);
        setText(tv_selledMoney, "已售金额：" + fomcat(selledMoney));

        BigDecimal TOTAL_MONEY = Utils.toBigDecimal(totalMoney);
        if (TOTAL_MONEY == null){
            TOTAL_MONEY = ZERO;
        }
        BigDecimal SELLED_MONEY = Utils.toBigDecimal(selledMoney);
        if (SELLED_MONEY == null){
            SELLED_MONEY = ZERO;
        }
        BigDecimal LEFT_MONEY = Utils.subtract(TOTAL_MONEY, SELLED_MONEY);

        tv_leftMoney = findView(R.id.tv_leftMoney);
        setText(tv_leftMoney, "剩余额度：" + fomcat(LEFT_MONEY.toString()));

        tv_payedMoney = findView(R.id.tv_payedMoney);
        setText(tv_payedMoney, "成交金额：" + fomcat(payedMoney));

        tv_outOfTime = findView(R.id.tv_outOfTime);
        setText(tv_outOfTime, "过期时间：" + fomcat(outOfTime));

        tv_status = findView(R.id.tv_status);
        setText(tv_status, "方案状态：" + Utils.fomcatStatus(status));

        tv_totalBonus = findView(R.id.tv_totalBonus);
        setText(tv_totalBonus, "奖金总额：" + fomcat(totalBonus));

        tv_bonusRate = findView(R.id.tv_bonusRate);
        setText(tv_bonusRate, "奖金税率：" + Utils.fomcatRate(bonusRate));

        tv_canUseBonus = findView(R.id.tv_canUseBonus);
        setText(tv_canUseBonus, "可用奖金：" + fomcat(canUseBonus));

        tv_descption = findView(R.id.tv_descption);
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
                bundle.putString("id", schemeId);
                toAc(OrderAddAc.class, bundle);
            }
        });

        findView(R.id.btn_list).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("schemeId", schemeId);
                toAc(OrderListAc.class, bundle);
            }
        });

        getSchemeDetail(schemeId);
    }

    @Override
    protected void srlRefreshListener() {
        super.srlRefreshListener();
        getSchemeDetail(schemeId);
    }

    private void getSchemeDetail(String id){
        NetWork.schemeDetail(this, id, new ResultSubscriber<SchemeBean.ItemBean>() {
            @Override
            public void onSuccess(SchemeBean.ItemBean itemBean) {
                srlRefresh(false);
                if (itemBean != null){
                    totalMoney = itemBean.getTotalMoney();
                    BigDecimal tm = Utils.toBigDecimal(totalMoney);
                    if (tm == null){
                        tm = ZERO;
                    }
                    selledMoney = itemBean.getSelledMoney();
                    BigDecimal sm = Utils.toBigDecimal(selledMoney);
                    if (sm == null){
                        sm = ZERO;
                    }
                    BigDecimal lm = Utils.subtract(tm, sm);

                    setText(tv_name, "方案名称：" + fomcat(itemBean.getName()));
                    setText(tv_totalMoney, "方案总额：" + fomcat(totalMoney));
                    setText(tv_selledMoney, "已售金额：" + fomcat(selledMoney));
                    setText(tv_leftMoney, "剩余额度：" + fomcat(lm.toString()));
                    setText(tv_payedMoney, "成交金额：" + fomcat(itemBean.getPayedMoney()));
                    setText(tv_outOfTime, "过期时间：" + fomcat(itemBean.getOutOfTime()));
                    setText(tv_status, "方案状态：" + Utils.fomcatStatus(itemBean.getStatus()));
                    setText(tv_totalBonus, "奖金总额：" + fomcat(itemBean.getTotalBonus()));
                    setText(tv_bonusRate, "奖金税率：" + Utils.fomcatRate(itemBean.getBonusRate()));
                    setText(tv_canUseBonus, "可用奖金：" + fomcat(itemBean.getCanUseBonus()));
                    setText(tv_descption, "方案详情：" + fomcat(itemBean.getDescption()));
                }
            }

            @Override
            public void onFailure(String s, String s1) {
                srlRefresh(false);
            }
        });
    }
}