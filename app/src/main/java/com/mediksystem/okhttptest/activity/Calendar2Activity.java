package com.mediksystem.okhttptest.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.mediksystem.okhttptest.R;
import com.mediksystem.okhttptest.databinding.ActivityCalendar2Binding;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class Calendar2Activity extends AppCompatActivity {
    private ActivityCalendar2Binding binding;
    public String readDay = null;
    public String str = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar2);

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                binding.diaryTextView.setVisibility(View.VISIBLE);
                binding.saveBtn.setVisibility(View.VISIBLE);
                binding.contextEditText.setVisibility(View.VISIBLE);

                binding.textView2.setVisibility(View.INVISIBLE);
                binding.chaBtn.setVisibility(View.INVISIBLE);
                binding.delBtn.setVisibility(View.INVISIBLE);

                binding.diaryTextView.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
                binding.contextEditText.setText("");
                checkDay(year, month, dayOfMonth);
            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (binding.contextEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "일정을 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    saveDiary(readDay);
                    str = binding.contextEditText.getText().toString();
                    binding.textView2.setText(str);
                    binding.saveBtn.setVisibility(View.INVISIBLE);
                    binding.chaBtn.setVisibility(View.VISIBLE);
                    binding.delBtn.setVisibility(View.VISIBLE);
                    binding.contextEditText.setVisibility(View.INVISIBLE);
                    binding.textView2.setVisibility(View.VISIBLE);
                }
            }
        });
    }













    public void checkDay(int cYear, int cMonth, int cDay)
    {
        readDay = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt";
        FileInputStream fis;

        try
        {
            fis = openFileInput(readDay);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);

            binding.contextEditText.setVisibility(View.INVISIBLE);
            binding.textView2.setVisibility(View.VISIBLE);
            binding.textView2.setText(str);

            binding.saveBtn.setVisibility(View.INVISIBLE);
            binding.chaBtn.setVisibility(View.VISIBLE);
            binding.delBtn.setVisibility(View.VISIBLE);

            binding.chaBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    binding.contextEditText.setVisibility(View.VISIBLE);
                    binding.textView2.setVisibility(View.INVISIBLE);
                    binding.contextEditText.setText(str);

                    binding.saveBtn.setVisibility(View.VISIBLE);
                    binding.chaBtn.setVisibility(View.INVISIBLE);
                    binding.delBtn.setVisibility(View.INVISIBLE);
                    binding.textView2.setText(binding.contextEditText.getText());
                }

            });
            binding.delBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    binding.textView2.setVisibility(View.INVISIBLE);
                    binding.contextEditText.setText("");
                    binding.contextEditText.setVisibility(View.VISIBLE);
                    binding.saveBtn.setVisibility(View.VISIBLE);
                    binding.chaBtn.setVisibility(View.INVISIBLE);
                    binding.delBtn.setVisibility(View.INVISIBLE);
                    removeDiary(readDay);
                }
            });
            if (binding.textView2.getText() == null)
            {
                binding.textView2.setVisibility(View.INVISIBLE);
                binding.diaryTextView.setVisibility(View.VISIBLE);
                binding.saveBtn.setVisibility(View.VISIBLE);
                binding.chaBtn.setVisibility(View.INVISIBLE);
                binding.delBtn.setVisibility(View.INVISIBLE);
                binding.contextEditText.setVisibility(View.VISIBLE);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay)
    {
        FileOutputStream fos;
        try
        {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = "";
            fos.write((content).getBytes());
            fos.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay)
    {
        FileOutputStream fos;
        try
        {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = binding.contextEditText.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
