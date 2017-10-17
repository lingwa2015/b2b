package com.sxc.natasha.common;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Description: TimeCountUtil 倒计时Util
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    TimeCountUtil.java
 * Create at:   2015-03-19
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-19      zhengshutian    1.0         1.0 Version
 */
public class TimeCountUtil extends CountDownTimer {

    TextView textView;

    public TimeCountUtil(long millisInFuture, long countDownInterval,TextView textView) {

        super(millisInFuture, countDownInterval);

        this.textView=textView;
    }

    //计时完毕时触发
    @Override
    public void onFinish() {
        textView.setText("重新获取验证码");
        textView.setClickable(true);
    }

    //计时过程显示
    @Override
    public void onTick(long millisUntilFinished){
        textView.setClickable(false);
        textView.setText("("+millisUntilFinished /1000+"秒)");
    }
}
