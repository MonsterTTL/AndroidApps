package com.example.generateprojection;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class TopApplication extends Application {

    final String APPID = "3e984bed" ;//科大讯飞语音识别

    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
        context = getApplicationContext();

        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=" +APPID);
    }

    public Context getContext(){
        return context;
    }
}
