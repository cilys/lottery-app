package com.cily.lottery;

import com.cily.lottery.ac.BaseAc;
import com.cily.lottery.ac.LoginAc;
import com.cily.lottery.ac.MainAc;
import com.cily.utils.base.StrUtils;

public class SplashAc extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_splash;
    }

    @Override
    protected void initUI() {
        super.initUI();

        if (Sp.isLogout()){
            toAcWithFinish(LoginAc.class, null);
        } else {
            toAcWithFinish(MainAc.class, null);
        }
    }
}
