package com.example.educationmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.educationmanage.adapter.ChargeAdapter;
import com.example.educationmanage.bean.ChargeBean;
import com.example.educationmanage.util.Api;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.educationmanage.util.MyApplication.getContext;

public class ClassActivity extends BaseActivity {
    ChargeAdapter adapter;
    List<ChargeBean.content.table> list =new ArrayList<>();//数据源
    RefreshLayout refreshLayout;//刷新
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());


        recyclerView.setLayoutManager(layoutManager);
        adapter = new ChargeAdapter( list);
        adapter.setseletedSuccessful(new ChargeAdapter.seletedsuccessful() {
            @Override
            public void onItemClick(Object tag) {
                ChargeBean.content.table table =   list.get((int)tag);
//                Intent intent = new Intent(ClassActivity.this,MembershipFeesActivity_.class);
//
//                intent.putExtra("class",table);
//
//                startActivityForResult(intent,1);

                Intent intent=new Intent();
                //将求和的结果放进intent中
                intent.putExtra("result", table);
                //返回结果
                setResult(RESULT_OK,intent);
                //关闭当前界面
                finish();
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        //上拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

                getdata();
                refreshLayout.finishRefresh(2000/*,false*/);
                //传入false表示刷新失败
            }


        });
        getdata();
    }
    void getdata(){

        Api.getcourse(ClassActivity.this,  new Api.OnResponseListener() {
            @Override
            public void onResponse(String json) {
                final String contextjson1 = json;
                ClassActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ChargeBean warningBean = gson.fromJson(contextjson1,ChargeBean.class);
                        if (warningBean.getData().getDs().equals("1")){
                            list.clear();
                            list.addAll(warningBean.getData().getTable1());
                            adapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh(0000/*,false*/);

                        }else {
                            Toast.makeText(getContext(),"数据异常", Toast.LENGTH_SHORT).show();
                            refreshLayout.finishRefresh(2000/*,false*/);
                        }
                    }
                });
            }
        });

    }
}
