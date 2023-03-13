package com.example.quickswitch;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SimpleViewModel extends ViewModel {

    public MutableLiveData<Boolean> isWifiOpen = new MutableLiveData<>();
    public MutableLiveData<Boolean> isBlueOpen = new MutableLiveData<>();
    //初始化
    public SimpleViewModel(){
        isWifiOpen.setValue(true);
        isBlueOpen.setValue(true);
    }
    public Boolean getWifiStatue(){
        return isWifiOpen.getValue();
    }

    public Boolean getBlueStatue(){
        return isBlueOpen.getValue();
    }
    public void OpenWifi(){
        isWifiOpen.postValue(true);
    }

    public void CloseWifi(){
        isWifiOpen.postValue(false);
    }

    public void OpenBlue(){
        isBlueOpen.postValue(true);
    }

    public void CloseBlue(){
        isBlueOpen.postValue(false);
    }
}
