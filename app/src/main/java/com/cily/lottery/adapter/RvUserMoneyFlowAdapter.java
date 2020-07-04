package com.cily.lottery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cily.lottery.R;
import com.cily.lottery.bean.UserMoneyFlowBean;

import java.util.List;

public class RvUserMoneyFlowAdapter extends RecyclerView.Adapter<RvUserMoneyFlowAdapter.VH> {
    private List<UserMoneyFlowBean.ItemBean> datas;

    public RvUserMoneyFlowAdapter(List<UserMoneyFlowBean.ItemBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_user_money_flow, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder{
        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}