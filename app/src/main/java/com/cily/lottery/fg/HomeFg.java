package com.cily.lottery.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cily.lottery.R;
import com.cily.utils.app.listener.SingleClickListener;

public class HomeFg extends BaseFg {

    @Override
    protected View initView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View v = layoutInflater.inflate(R.layout.fg_home, viewGroup, false);
        initUI(v);
        return v;
    }

    private void initUI(View v){
        initTitle(v);
        showTitleLeftImg(false);
        setTitle("彩票爱好者");
    }
}
