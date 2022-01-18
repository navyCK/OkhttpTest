package com.mediksystem.okhttptest;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediksystem.okhttptest.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags;


public class MainActivity extends JsonUtil {
    private static final String TAG = "라이프사이클";

    private ActivityMainBinding binding;

    String POST_URL = "https://jsonplaceholder.typicode.com/posts";

    JSONObject noticeObject;
    String str_response;

    Map<String, Object> map = null;

    ProgressDialog customProgressDialog;

    RecyclerView recyclerView = null;
    PostAdapter adapter = null;
    ArrayList<NoticeListViewItem> mList = new ArrayList<>();

    int scrollPosition = 0;

    Dialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


    }

    // json -> map
    public static Map<String, Object> getMapFromJsonObject(JSONObject jsonObj){
        Map<String, Object> objectMap = null;

        try {
            objectMap = new ObjectMapper().readValue(jsonObj.toString(), Map.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return objectMap;
    }

    // json -> map -> String -> addList
    private void convertJson(OkHttpClient client, Request request) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                str_response = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(str_response);

                    for (int i=0; i<jsonArray.length(); i++) {
                        noticeObject = jsonArray.getJSONObject(i);
                        map = getMapFromJsonObject(noticeObject);

                        String id = map.put("id", map).toString();
                        String userId = map.put("userId", map).toString();
                        String title = map.put("title", map).toString();
                        String body = map.put("body", map).toString();

                        addItem(id, userId, title, body);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addItem(String id, String userId, String title, String body) {
        NoticeListViewItem item = new NoticeListViewItem();

        item.setId(id);
        item.setUserId(userId);
        item.setTitle(title);
        item.setBody(body);

        mList.add(item);

    }

    private void setTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView = binding.recyclerview;
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                manager.setReverseLayout(true);
                manager.setStackFromEnd(true);
                recyclerView.setLayoutManager(manager);

                adapter = new PostAdapter(mList);
                recyclerView.setAdapter(adapter);

                customProgressDialog.dismiss();
            }
        }, 1000);
    }

    private void setProgressDialog() {
        customProgressDialog = new ProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();
    }





    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");

        mList.clear();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(POST_URL).build();
        convertJson(client, request);
        setProgressDialog();
        setTimer();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart()");
        recyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
        scrollPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        Log.e("스크롤 포지션 : ", String.valueOf(scrollPosition));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
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