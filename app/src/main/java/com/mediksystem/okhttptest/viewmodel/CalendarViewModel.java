package com.mediksystem.okhttptest.viewmodel;


import com.mediksystem.okhttptest.data.TSLiveData;

import java.util.Calendar;

import androidx.lifecycle.ViewModel;

public class CalendarViewModel extends ViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();

    public void setCalendar(Calendar calendar) {
        this.mCalendar.setValue(calendar);
    }


}
