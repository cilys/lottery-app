package com.cily.lottery.ac;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cily.lottery.R;
import com.cily.lottery.adapter.RvUserMoneyFlowAdapter;
import com.cily.lottery.bean.UserMoneyFlowBean;
import com.cily.lottery.net.NetWork;
import com.cily.utils.app.rx.okhttp.ResultSubscriber;

import java.util.ArrayList;
import java.util.List;

public class UserMoneyFlowAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_user_money_flow;
    }

    @Override
    protected void initUI() {
        super.initUI();

        datas = new ArrayList<>();
        RecyclerView rv = findView(R.id.rv);
        adapter = new RvUserMoneyFlowAdapter(datas);
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
    }
    private RvUserMoneyFlowAdapter adapter;
    private List<UserMoneyFlowBean.ItemBean> datas;

    private int pageNumber = 1;
    private boolean lastPage = false;
    private void getData(final boolean refresh){
        if (pageNumber < 1){
            pageNumber = 1;
        }
        if (refresh){
            pageNumber = 1;
        }else {
            pageNumber ++;
        }
        NetWork.userMoneyFlow(this, pageNumber, new ResultSubscriber<UserMoneyFlowBean>() {
            @Override
            public void onSuccess(UserMoneyFlowBean userMoneyFlowBean) {
                srlRefresh(false);
                if (userMoneyFlowBean != null){
                    pageNumber = userMoneyFlowBean.getPageNumber();
                    lastPage = userMoneyFlowBean.isLastPage();

                    if (datas == null){
                        datas = new ArrayList<>();
                    }
                    if (refresh){
                        datas.clear();
                    }
                    if (userMoneyFlowBean.getList() != null){
                        datas.addAll(userMoneyFlowBean.getList());
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

    @Override
    protected void srlRefreshListener() {
        super.srlRefreshListener();
        srlRefresh(true);

        getData(true);
    }
}