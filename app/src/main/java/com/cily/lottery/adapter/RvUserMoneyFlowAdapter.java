package com.cily.lottery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cily.lottery.PayType;
import com.cily.lottery.R;
import com.cily.lottery.Utils;
import com.cily.lottery.ac.BaseAc;
import com.cily.lottery.bean.UserMoneyFlowBean;
import com.cily.utils.app.listener.SingleClickListener;

import java.math.BigDecimal;
import java.util.List;

public class RvUserMoneyFlowAdapter extends RecyclerView.Adapter<RvUserMoneyFlowAdapter.VH> {
    private List<UserMoneyFlowBean.ItemBean> datas;

    public RvUserMoneyFlowAdapter(List<UserMoneyFlowBean.ItemBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_rv_user_money_flow, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setMoney(datas.get(position).getMoney());
        holder.setText(holder.tv_payType, "支付方式：" +
                PayType.fomcatPayType(datas.get(position).getPayType()));
        holder.setText(holder.tv_payStatus, "资金状态：" +
                PayType.fomcatIsAddToUser(datas.get(position).getIsAddToUser()));
        holder.setText(holder.tv_schemeName, "方案名称：" + fomcat(datas.get(position).getSchemeName()));
        holder.setText(holder.tv_createTime, "交易时间：" + fomcat(datas.get(position).getCreateTime()));

        if (holder.rootView != null){
            holder.rootView.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(view, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder{
        private TextView tv_money, tv_payType, tv_payStatus, tv_schemeName, tv_createTime;
        private View rootView;

        public VH(@NonNull View v) {
            super(v);

            rootView = v;

            tv_money = (TextView)v.findViewById(R.id.tv_money);
            tv_payType = (TextView)v.findViewById(R.id.tv_payType);
            tv_payStatus = (TextView)v.findViewById(R.id.tv_payStatus);
            tv_schemeName = (TextView)v.findViewById(R.id.tv_schemeName);
            tv_createTime = (TextView)v.findViewById(R.id.tv_createTime);
        }

        private void setMoney(String money){
            BigDecimal m = Utils.toBigDecimal(money, BaseAc.ZERO);
            if (Utils.lessThan(m, BaseAc.ZERO)){
                setText(tv_money, "   " + m.toString());
            }else {
                setText(tv_money, " + " + m.toString());
            }
        }

        private void setText(TextView tv, String str) {
            if (tv != null){
                if (str != null){
                    tv.setText(str);
                }
            }
        }
    }

    protected String fomcat(String str){
        return str == null ? "" : str;
    }

    private Adapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(Adapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}