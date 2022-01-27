package com.mediksystem.okhttptest.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.mediksystem.okhttptest.R;
import com.mediksystem.okhttptest.adapter.NoticeAdapter;
import com.mediksystem.okhttptest.adapter.SimpleTextAdapter;
import com.mediksystem.okhttptest.databinding.ActivityCalendar2Binding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Calendar2Activity extends AppCompatActivity {
    private ActivityCalendar2Binding binding;
    ArrayList<String> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar2);

        for (int i=0; i<20; i++) {
            list.add(String.format("할일 %d번 입니다.", i)) ;
        }

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.e("선택한 날짜", year +"년 "+ (month+1)+"월 "+dayOfMonth+"일");
                binding.diaryTextView.setText(String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)+"의 할 일");

                binding.todoRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                binding.todoRecyclerView.addItemDecoration(new DividerItemDecoration(binding.todoRecyclerView.getContext(), 1));

                SimpleTextAdapter adapter = new SimpleTextAdapter(list);
                binding.todoRecyclerView.setAdapter(adapter);
            }
        });


    }






}
