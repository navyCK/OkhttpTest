package com.mediksystem.okhttptest.viewmodel;

import android.util.Log;

import com.mediksystem.okhttptest.data.TSLiveData;
import com.mediksystem.okhttptest.utils.DateFormat;
import com.mediksystem.okhttptest.utils.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;

public class CalendarListViewModel extends ViewModel {
    private long mCurrentTime;

    public TSLiveData<String> mTitle = new TSLiveData<>();
    public TSLiveData<ArrayList<Object>> mCalendarList = new TSLiveData<>(new ArrayList<>());

    public int mCenterPosition;

    public void setTitle(int position) {
        try {
            Object item = mCalendarList.getValue().get(position);
            if (item instanceof Long) {
                setTitle((Long) item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(long time) {
        mCurrentTime = time;
        mTitle.setValue(DateFormat.getDate(time, DateFormat.CALENDAR_HEADER_FORMAT));
    }


    public void initCalendarList() {
        GregorianCalendar cal = new GregorianCalendar();
        setCalendarList(cal);
    }

    public void setCalendarList(GregorianCalendar cal) {

        setTitle(cal.getTimeInMillis());
        Log.e("getTimeInMillis->title", DateFormat.getDate(cal.getTimeInMillis(), DateFormat.CALENDAR_HEADER_FORMAT)); // 밀리초로 나온 값을 년,월로 변환 표시
        Log.e("cal.getTime()", String.valueOf(cal.getTime()));

        ArrayList<Object> calendarList = new ArrayList<>();

        // i=1이면 현재날짜월,
        // i=2이면 현재월+1
        for (int i = -1; i < 1; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);

                if (i == 0) {
                    mCenterPosition = calendarList.size();
                    Log.e("mCenterPosition", String.valueOf(mCenterPosition));
                }
                calendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월에 마지막 요일
                Log.e("dayOfWeek", i+String.valueOf(dayOfWeek));
                Log.e("max", i+String.valueOf(max));


                for (int j = 0; j < dayOfWeek; j++) {
                    calendarList.add(Keys.EMPTY);

                }
                for (int j = 1; j <= max; j++) {
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mCalendarList.setValue(calendarList);

    }

}
