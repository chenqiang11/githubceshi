package com.example.educationmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.educationmanage.adapter.MembersListAdapter;
import com.example.educationmanage.bean.MembersListBean;
import com.example.educationmanage.bean.WarningBean;
import com.example.educationmanage.util.Api;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MembersListActivity extends BaseActivity {
    MembersListAdapter adapter;
    List<MembersListBean.content.table> list =new ArrayList<>();//数据源
    RefreshLayout refreshLayout;//刷新
    EditText member_text;//搜索内容
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberslist);
        member_text = findViewById(R.id.member_text);
        ImageView search = findViewById(R.id.member_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             getdata();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());


        recyclerView.setLayoutManager(layoutManager);
        adapter = new MembersListAdapter( list);
        adapter.setseletedSuccessful(new MembersListAdapter.seletedsuccessful() {
            @Override
            public void onItemClick(Object tag) {
                MembersListBean.content.table item =    list.get((int)tag);
                Intent intent = new Intent(MembersListActivity.this,PatriarchListActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra("name",item.getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        //上拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

                getdata();
                //传入false表示刷新失败
            }


        });
        getdata();
    }

    void getdata(){

        Api.getmemberslist(MembersListActivity.this,   member_text.getText().toString().trim(), new Api.OnResponseListener() {
            @Override
            public void onResponse(String json) {
                final String contextjson1 = json;
                MembersListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        MembersListBean membersListBean = gson.fromJson(contextjson1,MembersListBean.class);
                        if (membersListBean.getData().getDs().equals("1")){
                            list.clear();
                            list.addAll(membersListBean.getData().getTable1());
                            adapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh(0000/*,false*/);
                            member_text.setText("");
                        }else {
                            Toast.makeText(getBaseContext(),"数据异常", Toast.LENGTH_SHORT).show();
                            refreshLayout.finishRefresh(2000/*,false*/);
                        }
                    }
                });
            }
        });

    }
}
