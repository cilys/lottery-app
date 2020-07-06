package com.cily.lottery.adapter;

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
import com.cily.lottery.bean.OrderBean;
import com.cily.utils.base.StrUtils;

import java.math.BigDecimal;
import java.util.List;

public class RvOrderAdapter extends RecyclerView.Adapter<RvOrderAdapter.VH> {
    private List<OrderBean.ItemBean> datas;

    public RvOrderAdapter(List<OrderBean.ItemBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_order_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setText(holder.tv_schemeName,
                "方案名称：" + RvSchemeAdapter.fomcat(datas.get(position).getSchemeName()));

        holder.setText(holder.tv_cusertomerName,
                "客户姓名：" + RvSchemeAdapter.fomcat(datas.get(position).getCusertomerName()));

        holder.setText(holder.tv_money,
                "购买份额：" + RvSchemeAdapter.fomcat(datas.get(position).getMoney()));

        holder.setText(holder.tv_payType,
                "支付方式：" + PayType.fomcatPayType(datas.get(position).getPayType()));

        holder.setText(holder.tv_orderStatus,
                "支付状态：" + PayType.fomcatPayStatus(datas.get(position).getOrderStatus()));

        holder.setText(holder.tv_createTime,
                "创建时间：" + RvSchemeAdapter.fomcat(datas.get(position).getCreateTime()));

        if (StrUtils.isEmpty(datas.get(position).getBonusMoney())){
            holder.showBonusIcon(false);
        }else {
            BigDecimal bonus = Utils.toBigDecimal(datas.get(position).getBonusMoney(), BaseAc.ZERO);
            if (Utils.moreThan(bonus, BaseAc.ZERO)){
                holder.showBonusIcon(true);
            }else {
                holder.showBonusIcon(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder{
        private TextView tv_schemeName, tv_cusertomerName, tv_money,
                tv_payType, tv_orderStatus, tv_createTime;
        private ImageView img_bonus;

        public VH(@NonNull View v) {
            super(v);

            img_bonus = (ImageView)v.findViewById(R.id.img_bonus);

            tv_schemeName = (TextView)v.findViewById(R.id.tv_schemeName);
            tv_cusertomerName = (TextView)v.findViewById(R.id.tv_cusertomerName);
            tv_money = (TextView)v.findViewById(R.id.tv_money);
            tv_payType = (TextView)v.findViewById(R.id.tv_payType);
            tv_orderStatus = (TextView)v.findViewById(R.id.tv_orderStatus);
            tv_createTime = (TextView)v.findViewById(R.id.tv_createTime);
        }

        private void setText(TextView tv, String str){
            if (tv != null) {
                if (str != null){
                    tv.setText(str);
                }
            }
        }

        private void showBonusIcon(boolean show){
            if (img_bonus != null){
                img_bonus.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }
}
