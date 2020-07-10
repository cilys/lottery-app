package com.cily.lottery.ac;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cily.lottery.Conf;
import com.cily.lottery.R;
import com.cily.utils.base.Sys;

public class UseRoleAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_use_role;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("合买协议");

        WebView wv = findView(R.id.wv);
        wv.getSettings().setDefaultTextEncodingName("UTF-8");
        wv.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        wv.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        wv.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持内容重新布局
        wv.getSettings().setLoadWithOverviewMode(true);

        wv.loadUrl(Conf.URL_BASE + "lottery/useRole");
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Sys.exit();
    }

    @Override
    protected void onClickTitleLeftImg() {
//        super.onClickTitleLeftImg();
        Sys.exit();
    }
}
