package com.cily.lottery.fg;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cily.lottery.R;
import com.cily.lottery.ac.SchemeInfoAc;
import com.cily.lottery.adapter.RvSchemeAdapter;
import com.cily.lottery.bean.SchemeBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;

import java.util.ArrayList;
import java.util.List;

public class SchemeListFg extends BaseFg {
    private String type = "0";

    @Override
    protected View initView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View v = layoutInflater.inflate(R.layout.fg_scheme_list, viewGroup, false);

        type = getArguments().getString("type", "0");

        initUI(v);

        return v;
    }

    private RvSchemeAdapter adapter;
    private List<SchemeBean.ItemBean> datas;
    private void initUI(View v) {
        srl = (SwipeRefreshLayout)v.findViewById(R.id.srl);
        RecyclerView rv = (RecyclerView)v.findViewById(R.id.rv);
        datas = new ArrayList<>();
        adapter = new RvSchemeAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView rv, int newState) {
                super.onScrollStateChanged(rv, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    boolean canScroll = ViewCompat.canScrollVertically(rv, 1);
                    if (!canScroll) {
                        if (lastPage) {
                            showToast("没有更多数据");
                            return;
                        }
                        getData(false);
                    }
                }
            }
        });
        adapter.setOnItemClickListener(new RvSchemeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("totalBonus", datas.get(position).getTotalBonus());
                bundle.putString("canUseBonus", datas.get(position).getCanUseBonus());
                bundle.putString("totalMoney", datas.get(position).getTotalMoney());
                bundle.putString("outOfTime", datas.get(position).getOutOfTime());
                bundle.putString("selledMoney", datas.get(position).getSelledMoney());
                bundle.putString("payedMoney", datas.get(position).getPayedMoney());
                bundle.putString("name", datas.get(position).getName());
                bundle.putString("id", datas.get(position).getId());
                bundle.putString("bonusRate", datas.get(position).getBonusRate());
                bundle.putString("descption", datas.get(position).getDescption());
                bundle.putString("status", datas.get(position).getStatus());
                bundle.putBoolean("isHistory", "1".equals(type));
                toAc(SchemeInfoAc.class, bundle);
            }
        });
    }

    @Override
    protected void loadOnly() {
        super.loadOnly();
        srlRefresh(true);
        getData(true);
    }

    private int pageNumber;
    private boolean lastPage = false;
    private void getData(boolean refresh) {
        if (pageNumber < 1){
            pageNumber = 1;
        }
        if (refresh){
            pageNumber = 1;
        } else {
            pageNumber ++;
        }

        NetWork.schemeList(this, pageNumber, type, new ResultSubscriber<SchemeBean>() {
            @Override
            public void onSuccess(SchemeBean schemeBean) {
                srlRefresh(false);
                if (schemeBean != null){
                    pageNumber = schemeBean.getPageNumber();
                    lastPage = schemeBean.isLastPage();

                    if (datas == null){
                        datas = new ArrayList<>();
                    }
                    if (refresh){
                        datas.clear();
                    }
                    if (schemeBean.getList() != null){
                        datas.addAll(schemeBean.getList());
                    }

                    if (adapter != null){
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(String s, String s1) {
                srlRefresh(false);
                showToast(s1);

                pageNumber --;
            }
        });
    }
}