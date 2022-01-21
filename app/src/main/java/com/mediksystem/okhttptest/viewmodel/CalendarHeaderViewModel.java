package com.mediksystem.okhttptest.viewmodel;

import com.mediksystem.okhttptest.data.TSLiveData;

import androidx.lifecycle.ViewModel;

public class CalendarHeaderViewModel extends ViewModel {
    public TSLiveData<Long> mHeaderDate = new TSLiveData<>();

    public void setHeaderDate(long headerDate) {
        this.mHeaderDate.setValue(headerDate);
    }
}
