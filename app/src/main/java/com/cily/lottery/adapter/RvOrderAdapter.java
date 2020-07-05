package com.cily.lottery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cily.lottery.R;
import com.cily.lottery.bean.OrderBean;

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
