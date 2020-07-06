package com.cily.lottery.ac;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.cily.lottery.R;
import com.cily.lottery.fg.BaseFg;
import com.cily.lottery.fg.SchemeListFg;
import com.cily.utils.app.adapter.BaseViewPagerFragmentAdapter;
import com.cily.utils.app.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class SchemeListAc extends BaseAc {
    @Override
    protected int getLayoutId() {
        return R.layout.ac_scheme_list;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setTitle("方案列表");

        List<BaseFg> fgs = new ArrayList<>();
        SchemeListFg normalFg = new SchemeListFg();
        Bundle bundle = new Bundle();
        bundle.putString("type", "0");
        normalFg.setArguments(bundle);
        fgs.add(normalFg);

        SchemeListFg historyFg = new SchemeListFg();
        Bundle historyBundle = new Bundle();
        historyBundle.putString("type", "1");
        historyFg.setArguments(historyBundle);
        fgs.add(historyFg);

        BaseViewPagerFragmentAdapter<BaseFg> adapter = new BaseViewPagerFragmentAdapter<>(getSupportFragmentManager(), fgs);

        final NoScrollViewPager noVp = findView(R.id.nvp);
        noVp.setAdapter(adapter);
        noVp.setScrollable(true);
        noVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 1){
                    setTitle("历史记录");
                }else {
                    setTitle("方案列表");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}