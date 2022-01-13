package com.mediksystem.okhttptest;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends JsonUtil {

    ArrayList<String> allList = new ArrayList<>();
    ArrayList<String> idList = new ArrayList<>();
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> userIdList = new ArrayList<>();
    ArrayList<String> bodyList = new ArrayList<>();

    String TEST_URL = "https://jsonplaceholder.typicode.com/posts";

    TextView textView;
    Button button, button2, button3, button4;

    JSONObject noticeObject;
    String str_response;

    Map<String, Object> map = null;

    ProgressDialog customProgressDialog;

    RecyclerView recyclerView = null;
    SimpleTextAdapter adapter = null;
    ArrayList<NoticeListViewItem> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(TEST_URL).build();
        convertJson(client, request);
        setProgressDialog();
        setTimer();

        button.setOnClickListener(view -> textView.setText(str_response));
        button2.setOnClickListener(view -> textView.setText(allList.toString()));
        button3.setOnClickListener(view -> textView.setText(idList.toString()));
        button4.setOnClickListener(view -> textView.setText(titleList.toString()));

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

                        // 여기부터
                        idList.add(id);
                        userIdList.add(userId);
                        titleList.add(title);
                        bodyList.add(body);
                        // 여기까지 필요없음

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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView = findViewById(R.id.recyclerview);
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adapter = new SimpleTextAdapter(mList);
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
}