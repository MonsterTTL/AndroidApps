package com.example.generateprojection.helper;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;


public class AudioHelper {
    private static final String TAG="AudioHelper";

    public interface SpeechCallBack{
        public void onListen(String result);
    };//内部的一个接口，用于处理之后的回调
    public SpeechCallBack myCallBack;
    public AudioHelper(SpeechCallBack myCallBack){
        this.myCallBack = myCallBack;
    }
    public void useSpeech(final Context context){
        RecognizerDialog mDialog = new RecognizerDialog(context,null);
        mDialog.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT,"mandarin");
        mDialog.setParameter(SpeechConstant.ASR_PTT,"0");
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
               String result = recognizerResult.getResultString();
                Log.d(TAG, result);
                String ans = parseVoice(result);
                Log.d(TAG,ans);
                myCallBack.onListen(ans);
            }

            @Override
            public void onError(SpeechError speechError) {
            }
        });
        mDialog.show();
    }

    private  String parseVoice(String resultString){
        Gson gson = new Gson();
        AudioHelperGson data = gson.fromJson(resultString,AudioHelperGson.class);
        StringBuffer buffer = new StringBuffer();
        for(AudioHelperGson.wsBean wsBeans: data.ws){
            buffer.append(wsBeans.cw.get(0).w);
        }
        return buffer.toString();
    }

}
