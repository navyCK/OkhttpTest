package com.mediksystem.okhttptest.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.mediksystem.okhttptest.R;
import com.mediksystem.okhttptest.adapter.NoticeAdapter;
import com.mediksystem.okhttptest.databinding.ActivityHomeBinding;
import com.mediksystem.okhttptest.item.NoticeItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        NoticeAdapter adapter = new NoticeAdapter();
        binding.noticeListView.setAdapter(adapter);

        for (int i=0; i<20; i++) {
            adapter.addItem(
                    ContextCompat.getDrawable(HomeActivity.this, R.drawable.icon_herbal),
                    "공지사항 " + String.valueOf(i),
                    "공지사항 " + String.valueOf(i) + "\t" + getString(R.string.desc_test1),
                    "2022-01-02");

        }

        binding.noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoticeItem noticeListViewItem = (NoticeItem) adapterView.getItemAtPosition(i);

                String s = String.valueOf(i);
                Log.d("터치된 위치 : ", s);
                showToast("터치된 위치 : " + i);

                binding.noticeTitle.setText(noticeListViewItem.getTitle());
                binding.noticeDescription.setText(noticeListViewItem.getDesc());
                binding.noticeDate.setText(noticeListViewItem.getDate());
            }
        });




        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TodoActivity.class);
                startActivity(intent);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, ProcessActivity.class);
//                startActivity(intent);
                showToast("HTTP 상태 401 – 인가 안됨\n대상 리소스에 접근하기 위한 유효한 인증 credentials가 없기 때문에, 요청에 적용되지 않았습니다.");
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NfcActivity.class);
                startActivity(intent);
            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Calendar2Activity.class);
                startActivity(intent);
            }
        });


    }


    void showToast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button noBtn = dialog.findViewById(R.id.noBtn);
        Button yesBtn = dialog.findViewById(R.id.yesBtn);

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
