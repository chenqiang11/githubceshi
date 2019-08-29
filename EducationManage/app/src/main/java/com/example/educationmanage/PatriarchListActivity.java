package com.example.educationmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationmanage.adapter.PatriarchlistAdapter;
import com.example.educationmanage.bean.PatriarchlistBean;
import com.example.educationmanage.util.Api;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PatriarchListActivity extends BaseActivity {
    PatriarchlistAdapter adapter;
    List<PatriarchlistBean.content.table> list =new ArrayList<>();//数据源
    String ID ="";//id
    String name="";//姓名


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patriarchlist);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        Intent intent = getIntent();
        ID=  (String) intent.getStringExtra("id");
         name =  (String) intent.getStringExtra("name");
        TextView patriarchtitle = findViewById(R.id.patriarchtitle);
        patriarchtitle.setText(name);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());


        recyclerView.setLayoutManager(layoutManager);
        adapter = new  PatriarchlistAdapter( list);
        recyclerView.setAdapter(adapter);
        adapter.setseletedSuccessful(new  PatriarchlistAdapter.seletedsuccessful() {
            @Override
            public void onItemClick(Object tag) {

            }
        });
        getdata();
    }

    void getdata(){

        Api.getparentlist(PatriarchListActivity.this,  ID, new Api.OnResponseListener() {
            @Override
            public void onResponse(String json) {
                final String contextjson1 = json;
                PatriarchListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        PatriarchlistBean patriarchlistBean = gson.fromJson(contextjson1, PatriarchlistBean.class);
                        if (patriarchlistBean.getData().getDs().equals("1")){
                            list.clear();
                            list.addAll(patriarchlistBean.getData().getTable1());
                            adapter.notifyDataSetChanged();


                        }
                        else if (patriarchlistBean.getData().getDs().equals("0")){
                            Toast.makeText(getBaseContext(),"暂无数据", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getBaseContext(),"数据异常", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}
