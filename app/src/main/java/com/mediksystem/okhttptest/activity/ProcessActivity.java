package com.mediksystem.okhttptest.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediksystem.okhttptest.R;
import com.mediksystem.okhttptest.adapter.PostAdapter;
import com.mediksystem.okhttptest.adapter.ProcessAdapter;
import com.mediksystem.okhttptest.databinding.ActivityProcessBinding;
import com.mediksystem.okhttptest.dialog.ProgressDialog;
import com.mediksystem.okhttptest.item.PostItem;
import com.mediksystem.okhttptest.item.ProcessItem;
import com.mediksystem.okhttptest.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProcessActivity extends JsonUtil {
    private ActivityProcessBinding binding;

    private String POST_URL = "https://v2.haniclinic.com/Process/Sub/ListData.json";
    JSONObject processObject;
    String str_response;

    Map<String, Object> map = null;
    ProgressDialog customProgressDialog;

    RecyclerView recyclerView = null;
    ProcessAdapter adapter = null;
    ArrayList<ProcessItem> mList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binding = DataBindingUtil.setContentView(ProcessActivity.this, R.layout.activity_process);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mList.clear();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(POST_URL).build();
        convertJson(client, request);
        setProgressDialog();
        setTimer();
    }


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




    private void convertJson(OkHttpClient client, Request request) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                str_response = response.body().string();
                Log.e("res", str_response);
                try {
                    JSONArray jsonArray = new JSONArray(str_response);

                    for (int i=0; i<jsonArray.length(); i++) {
                        processObject = jsonArray.getJSONObject(i);
                        map = getMapFromJsonObject(processObject);


                        String SubUniquenumber = map.put("SubUniquenumber", map).toString();
                        String ProcessSubType = map.put("ProcessSubType", map).toString();
                        String BeginTime = map.put("BeginTime", map).toString();
                        String FinishTime = map.put("FinishTime", map).toString();
                        String RegistrationTime = map.put("RegistrationTime", map).toString();
                        String ModificationTime = map.put("ModificationTime", map).toString();

                        if (map.put("FinishTime", map) == null) {
                            FinishTime = "-";
                        } else if (map.put("ModificationTime", map) == null) {
                            ModificationTime = "-";
                        } else {
                            FinishTime = "-";
                            ModificationTime = "-";
                        }

                        addItem(Integer.parseInt(SubUniquenumber), ProcessSubType, BeginTime, FinishTime, RegistrationTime, ModificationTime);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addItem(int SubUniquenumber, String ProcessSubType, String BeginTime, String FinishTime, String RegistrationTime, String ModificationTime) {
        ProcessItem item = new ProcessItem();

        item.setSubUniquenumber(SubUniquenumber);
        item.setProcessSubType(ProcessSubType);
        item.setBeginTime(BeginTime);
        item.setFinishTime(FinishTime);
        item.setRegistrationTime(RegistrationTime);
        item.setModificationTime(ModificationTime);

        mList.add(item);

    }

    private void setTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView = binding.processRecyclerView;
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                manager.setReverseLayout(true);
                manager.setStackFromEnd(true);
                recyclerView.setLayoutManager(manager);

                adapter = new ProcessAdapter(mList);
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
