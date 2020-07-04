package com.cily.lottery.ac;

import android.widget.RadioGroup;

import com.cily.lottery.R;
import com.cily.lottery.fg.BaseFg;
import com.cily.lottery.fg.HomeFg;
import com.cily.lottery.fg.MeFg;
import com.cily.lottery.fg.SchemeFg;
import com.cily.utils.app.adapter.BaseViewPagerFragmentAdapter;
import com.cily.utils.app.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        super.initUI();

        List<BaseFg> fgs = new ArrayList<>();
        fgs.add(new HomeFg());
        fgs.add(new SchemeFg());
        fgs.add(new MeFg());
        BaseViewPagerFragmentAdapter<BaseFg> adapter = new BaseViewPagerFragmentAdapter<>(getSupportFragmentManager(), fgs);

        final NoScrollViewPager noVp = findView(R.id.noVp_id);
        noVp.setAdapter(adapter);
        noVp.setScrollable(false);

        RadioGroup rg = findView(R.id.rg_id);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbt_search_id){
                    noVp.setCurrentItem(1);
                }else if (checkedId == R.id.rbt_me_id){
                    noVp.setCurrentItem(2);
                }else {
                    noVp.setCurrentItem(0);
                }
            }
        });
    }
}