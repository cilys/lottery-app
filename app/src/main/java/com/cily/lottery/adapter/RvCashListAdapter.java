package com.cily.lottery.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cily.lottery.PayType;
import com.cily.lottery.R;
import com.cily.lottery.Utils;
import com.cily.lottery.ac.BaseAc;
import com.cily.lottery.bean.CashBean;
import com.cily.lottery.bean.OrderBean;
import com.cily.utils.base.StrUtils;

import java.math.BigDecimal;
import java.util.List;

public class RvCashListAdapter extends RecyclerView.Adapter<RvCashListAdapter.VH> {
    private List<CashBean.ItemBean> datas;

    public RvCashListAdapter(List<CashBean.ItemBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_cash_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setText(holder.tv_money, "提现金额："
                + RvSchemeAdapter.fomcat(datas.get(position).getMoney()));

        holder.setText(holder.tv_applyTime, "申请时间："
                + RvSchemeAdapter.fomcat(datas.get(position).getApplyTime()));

        holder.setText(holder.tv_status, "提现进度："
                + fomcatCashStatus(datas.get(position).getStatus()));

        holder.setTextColor(holder.tv_status, datas.get(position).getStatus());

        holder.setText(holder.tv_msg, "备注信息："
                + RvSchemeAdapter.fomcat(datas.get(position).getMsg()));

        holder.setText(holder.tv_operatorName, "操作人员："
                + RvSchemeAdapter.fomcat(datas.get(position).getOperatorName()));

        holder.setText(holder.tv_opreatorResult, "操作备注："
                + RvSchemeAdapter.fomcat(datas.get(position).getOperatorResult()));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder{
        private TextView tv_money, tv_applyTime, tv_status,
                tv_msg, tv_operatorName, tv_opreatorResult;

        public VH(@NonNull View v) {
            super(v);

            tv_money = (TextView)v.findViewById(R.id.tv_money);
            tv_applyTime = (TextView)v.findViewById(R.id.tv_applyTime);
            tv_status = (TextView)v.findViewById(R.id.tv_status);
            tv_msg = (TextView)v.findViewById(R.id.tv_msg);
            tv_operatorName = (TextView)v.findViewById(R.id.tv_operatorName);
            tv_opreatorResult = (TextView)v.findViewById(R.id.tv_opreatorResult);
        }

        private void setText(TextView tv, String str){
            if (tv != null) {
                if (str != null){
                    tv.setText(str);
                }
            }
        }

        private void setTextColor(TextView tv, String status){
            if (tv != null){
                if ("0".equals(status)){
                    tv.setTextColor(0xFF00FF00);
                } else if ("2".equals(status)){
                    tv.setTextColor(0xFFFF0000);
                } else {
                    tv.setTextColor(0xFF777777);
                }
            }
        }
    }

    private String fomcatCashStatus(String status){
        if (status == null){
            return "";
        }
        if ("0".equals(status)){
            return "已同意";
        } else if ("1".equals(status)){
            return "待审批";
        } else if ("2".equals(status)){
            return "被驳回";
        }
        return status;
    }
}
