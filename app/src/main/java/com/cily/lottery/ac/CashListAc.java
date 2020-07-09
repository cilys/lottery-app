package com.cily.lottery.ac;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.lottery.Sp;
import com.cily.lottery.adapter.RvCashListAdapter;
import com.cily.lottery.bean.CashBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;

import java.util.ArrayList;
import java.util.List;

public class CashListAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_cash_list;
    }

    private RvCashListAdapter adapter;
    private List<CashBean.ItemBean> datas;
    @Override
    protected void initUI() {
        super.initUI();

        setTitle("提现记录");

        RecyclerView rv = findView(R.id.rv);
        datas = new ArrayList<>();
        adapter = new RvCashListAdapter(datas);
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

        getData(true);
        srlRefresh(true);
    }

    @Override
    protected void srlRefreshListener() {
        super.srlRefreshListener();

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
            srlRefresh(true);
        } else {
            pageNumber ++;
        }

        NetWork.cashList(this, pageNumber, Sp.getStr(Conf.SP_USER_ID, null), new ResultSubscriber<CashBean>() {
            @Override
            public void onSuccess(CashBean schemeBean) {
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
            }
        });
    }
}