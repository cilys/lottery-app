package com.cily.lottery.ac;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.cily.lottery.PayType;
import com.cily.lottery.R;
import com.cily.lottery.Utils;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.listener.SingleClickListener;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;

import java.math.BigDecimal;

public class OrderAddAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_order_add;
    }

    private String buyMoney = "10.00";
    private String payType = PayType.YU_E;   //1余额支付，2微信支付，3支付宝支付，4银联支付，5现金支付
    private BigDecimal TOTAL_MONEY, LEFT_MONEY;

    @Override
    protected void initUI() {
        super.initUI();

        setTitle("购买");

        String totalMoney = getIntent().getStringExtra("totalMoney");
        TOTAL_MONEY = Utils.toBigDecimal(totalMoney);
        if (TOTAL_MONEY == null){
            TOTAL_MONEY = ZERO;
        }

        String selledMoney = getIntent().getStringExtra("selledMoney");
        BigDecimal SELLED_MONEY = Utils.toBigDecimal(selledMoney);
        if (SELLED_MONEY == null){
            SELLED_MONEY = ZERO;
        }

        LEFT_MONEY = Utils.subtract(TOTAL_MONEY, SELLED_MONEY);

        String name = getIntent().getStringExtra("name");
        final String id = getIntent().getStringExtra("id");

        TextView tv_name = findView(R.id.tv_name);
        setText(tv_name, "方案名称：" + fomcat(name));

        TextView tv_totalMoney = findView(R.id.tv_totalMoney);
        setText(tv_totalMoney, "总金额：" + fomcat(totalMoney));

        TextView tv_leftMoney = findView(R.id.tv_leftMoney);
        setText(tv_leftMoney, "剩余额度：" + fomcat(LEFT_MONEY.toString()));

        final TextView tv_order = findView(R.id.tv_order);
        setText(tv_order, "购买额度：" + fomcat(buyMoney));
        tv_order.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showOrderMoneyDialog(tv_order);
            }
        });

        final TextView tv_payType = findView(R.id.tv_payType);
        setText(tv_payType, "支付方式：" + PayType.fomcatPayType(payType));

        tv_payType.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                showPayTypeDialog(tv_payType);
            }
        });

        findView(R.id.btn_commit).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                orderAdd(id);
            }
        });
    }

    private void showOrderMoneyDialog(TextView tv){
        new AlertDialog.Builder(this).setTitle("请选择购买额度")
                .setItems(PayType.MONEY, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMoney = PayType.MONEY[which];
                        BigDecimal selected = Utils.toBigDecimal(selectedMoney);

                        if (Utils.noLessThan(LEFT_MONEY, selected)){
                            buyMoney = selectedMoney;
                            setText(tv, buyMoney);
                        } else {
                            showToast("剩余额度不足");
                        }
                    }
                }).create().show();
    }

    private void showPayTypeDialog(final TextView tv){
        new AlertDialog.Builder(this).setTitle("请选择支付方式")
                .setItems(PayType.PAY_TYPES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payType = PayType.PAY_TYPES[which];
                        if (tv != null){
                            setText(tv, PayType.fomcatPayType(payType));
                        }
                    }
                }).create().show();
    }

    private void orderAdd(String schemeId){
        BigDecimal buy = Utils.toBigDecimal(buyMoney);
        if (buy == null || Utils.noMoreThan(buy, ZERO)){
            showToast("请选择合适的购买额度");
            return;
        }
        if (!loading("请求中..", true)){
            return;
        }
        NetWork.orderAdd(this, schemeId, buyMoney, payType, new ResultSubscriber() {
            @Override
            public void onSuccess(Object o) {
                disLoading();
                showResult();
            }

            @Override
            public void onFailure(String s, String s1) {
                showToast(s1);
                disLoading();
            }
        });
    }

    private void showResult(){
        new AlertDialog.Builder(this)
                .setTitle("购买成功")
                .setMessage("购买成功，是否再购买一份？")
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).setPositiveButton("再次购买", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }
}