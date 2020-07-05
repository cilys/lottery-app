package com.cily.lottery.ac;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cily.lottery.R;
import com.cily.lottery.adapter.RvOrderAdapter;
import com.cily.lottery.bean.OrderBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;

import java.util.ArrayList;
import java.util.List;

public class OrderListAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_order_list;
    }

    private RvOrderAdapter adapter;
    private List<OrderBean.ItemBean> datas;
    @Override
    protected void initUI() {
        super.initUI();

        setTitle("订单记录");

        RecyclerView rv = findView(R.id.rv);
        datas = new ArrayList<>();
        adapter = new RvOrderAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(this));
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
    }

    private int pageNumber;
    private boolean lastPage = false;
    private void getData(boolean refresh) {
        if (pageNumber < 1){
            pageNumber = 1;
        }
        if (refresh){
            pageNumber = 1;
        }else {
            pageNumber ++;
        }

        NetWork.orderList(this, pageNumber, new ResultSubscriber<OrderBean>() {
            @Override
            public void onSuccess(OrderBean schemeBean) {
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