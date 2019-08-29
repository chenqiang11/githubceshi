package com.example.educationmanage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationmanage.adapter.WarningAdapter;
import com.example.educationmanage.bean.WarningBean;
import com.example.educationmanage.util.Api;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.Click;

import java.util.ArrayList;
import java.util.List;

public class WarningFragment extends Fragment {
    WarningAdapter adapter;
    List<WarningBean.content.table> list =new ArrayList<>();//数据源
    RefreshLayout refreshLayout;//刷新
    EditText waring_text;//搜索内容
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warning, container,false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());


        recyclerView.setLayoutManager(layoutManager);
        adapter = new  WarningAdapter( list);
        adapter.setseletedSuccessful(new WarningAdapter.seletedsuccessful() {
            @Override
            public void onItemClick(Object tag) {

            }
        });
         waring_text = view.findViewById(R.id.waring_text);
        ImageView search = view.findViewById(R.id.waring_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

//        adapter.setseletedSuccess(new InventoryAdapter.seletedsuccess() {
//            @Override
//            public void onItemClick(Object tag) {
//                InventoryBean.dataclass.item item =     inventoryBeans.get((int)tag);
////
////                    //刷新数据
////                    getdata();
//
//
//            }
//        });
//        adapter.adapeterContext(this);
        recyclerView.setAdapter(adapter);
        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        //上拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

                getdata();
             //传入false表示刷新失败
            }


        });
        getdata();

        return view;
    }

   void getdata(){

       Api.getWarninglist(getActivity(),   waring_text.getText().toString().trim(), new Api.OnResponseListener() {
           @Override
           public void onResponse(String json) {
               final String contextjson1 = json;
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Gson gson = new Gson();
                       WarningBean warningBean = gson.fromJson(contextjson1,WarningBean.class);
                       if (warningBean.getData().getDs().equals("1")){
                           list.clear();
                           list.addAll(warningBean.getData().getTable1());
                           adapter.notifyDataSetChanged();
                           refreshLayout.finishRefresh(0000/*,false*/);
                           waring_text.setText("");
                       }else {
                           Toast.makeText(getContext(),"数据异常", Toast.LENGTH_SHORT).show();
                           refreshLayout.finishRefresh(2000/*,false*/);
                       }
                   }
               });
           }
       });

   }

   public void search(){

        getdata();
   }


}
