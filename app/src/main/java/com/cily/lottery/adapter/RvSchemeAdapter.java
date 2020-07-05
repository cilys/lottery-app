package com.cily.lottery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cily.lottery.R;
import com.cily.lottery.Utils;
import com.cily.lottery.ac.BaseAc;
import com.cily.lottery.bean.SchemeBean;
import com.cily.utils.app.listener.SingleClickListener;

import java.math.BigDecimal;
import java.util.List;

public class RvSchemeAdapter extends RecyclerView.Adapter<RvSchemeAdapter.VH> {
    private List<SchemeBean.ItemBean> datas;

    public RvSchemeAdapter(List<SchemeBean.ItemBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_scheme, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setText(holder.tv_name, "方案名称：" + fomcat(datas.get(position).getName()));
        holder.setText(holder.tv_totalMoney, "方案总额：" + fomcat(datas.get(position).getTotalMoney()));
        holder.setText(holder.tv_selledMoney, "已售金额：" + fomcat(datas.get(position).getSelledMoney()));
        holder.setText(holder.tv_status, "方案状态：" + Utils.fomcatStatus(datas.get(position).getStatus()));
        holder.setText(holder.tv_outOfTime, "最后期限：" + fomcat(datas.get(position).getOutOfTime()));

        BigDecimal bonusMoney = Utils.toBigDecimal(datas.get(position).getTotalBonus(), BaseAc.ZERO);

        holder.showBonusIcon (Utils.moreThan(bonusMoney, BaseAc.ZERO));

        if (holder.rootView != null) {
            holder.rootView.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    if (onItemClickListener != null) {
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
        private TextView tv_name, tv_totalMoney, tv_selledMoney, tv_status, tv_outOfTime;
        private ImageView img_bonus;
        private View rootView;

        public VH(@NonNull View v) {
            super(v);

            rootView = v;

            tv_name = (TextView)v.findViewById(R.id.tv_name);
            tv_totalMoney = (TextView)v.findViewById(R.id.tv_totalMoney);
            tv_selledMoney = (TextView)v.findViewById(R.id.tv_selledMoney);
            tv_status = (TextView)v.findViewById(R.id.tv_status);
            tv_outOfTime = (TextView)v.findViewById(R.id.tv_outOfTime);

            img_bonus = (ImageView)v.findViewById(R.id.img_bonus);
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

    public static String fomcat(String str){
        if (str == null){
            return "";
        }
        return str;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
