package com.mediksystem.okhttptest.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.mediksystem.okhttptest.R;
import com.mediksystem.okhttptest.utils.DateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {


    @BindingAdapter({"setCalendarHeaderText"})
    public static void setCalendarHeaderText(TextView view, Long date) {
        try {
            if (date != null) {
                view.setText(DateFormat.getDate(date, DateFormat.CALENDAR_HEADER_FORMAT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setDayText"})
    public static void setDayText(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                view.setText(DateFormat.getDate(gregorianCalendar.getTimeInMillis(), DateFormat.DAY_FORMAT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setStartTime"})
    public static void setStartTime(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

                setTextView(view, calendar, gregorianCalendar, "09:00");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setFinishTime"})
    public static void setFinishTime(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

                setTextView(view, calendar, gregorianCalendar, "18:00");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setIcon"})
    public static void setIcon(ImageView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

                setImageView(view, calendar, gregorianCalendar);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @BindingAdapter({"setState"})
    public static void setState(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

                setTextView(view, calendar, gregorianCalendar, "??????");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private static void setTextView(TextView view, Calendar calendar, GregorianCalendar gregorianCalendar, String text) {
        String day = DateFormat.getDate(gregorianCalendar.getTimeInMillis(), DateFormat.DAY_FORMAT);
        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayNum) {
            case 1:
                day = "???";
                break;
            case 2:
                day = "???";
                break;
            case 3:
                day = "???";
                break;
            case 4:
                day = "???";
                break;
            case 5:
                day = "???";
                break;
            case 6:
                day = "???";
                break;
            case 7:
                day = "???";
                break;
        }
        if (day == "???" || day == "???" || day == "???" || day == "???" || day == "???") {
            view.setText(text);
        } else {
            view.setText("");
        }
    }

    private static void setImageView(ImageView view, Calendar calendar, GregorianCalendar gregorianCalendar) {
        String day = DateFormat.getDate(gregorianCalendar.getTimeInMillis(), DateFormat.DAY_FORMAT);
        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayNum) {
            case 1:
                day = "???";
                break;
            case 2:
                day = "???";
                break;
            case 3:
                day = "???";
                break;
            case 4:
                day = "???";
                break;
            case 5:
                day = "???";
                break;
            case 6:
                day = "???";
                break;
            case 7:
                day = "???";
                break;
        }
        if (day == "???" || day == "???" || day == "???" || day == "???" || day == "???") {
            view.setImageResource(R.drawable.icon_work);
        } else {
            view.setImageResource(0);
        }
    }






}
