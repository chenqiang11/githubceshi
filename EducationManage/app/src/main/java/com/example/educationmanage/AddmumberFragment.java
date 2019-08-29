package com.example.educationmanage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.educationmanage.bean.AddBean;
import com.example.educationmanage.bean.ShopNameBean;
import com.example.educationmanage.bean.WarningBean;
import com.example.educationmanage.util.Api;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddmumberFragment extends Fragment {
   TextView add_addbtn;//添加
  EditText add_remark;//备注
  Button add_shop;//所属门店
  Button  add_birthday;//出生日期
   EditText add_mumbername;//会员名称
    TextView   mumberlist;//会员列表
    List<String> shopList = new ArrayList<>();//店面显示集合
    List<ShopNameBean.content.table> shopcontentList = new ArrayList();
    int shopindex = -1;//店面索引
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addmumber, container,false);
        add_addbtn = view.findViewById(R.id.add_addbtn);
        add_remark = view.findViewById(R.id.add_remark);
        add_birthday = view.findViewById(R.id.add_birthday);
        add_shop =view.findViewById(R.id.add_shop);
        add_mumbername = view.findViewById(R.id.add_mumbername);
        mumberlist = view.findViewById(R.id.mumberlist);
        //生日
        add_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        // Toast.makeText(AddActivity.this,sdf.format(date) , Toast.LENGTH_SHORT).show();
                        add_birthday.setText(sdf.format(date));

                    }
                }).build();
                pvTime.show();
            }
        });
        //所属部门
        add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdepartment();
            }
        });
        //添加
        add_addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果会员名字为空
                if (add_mumbername.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(),"会员名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (add_birthday.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(),"出生日期不能为空", Toast.LENGTH_SHORT).show();
                }
                if (add_shop.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(),"所属门店不能为空", Toast.LENGTH_SHORT).show();
                }
              String name = add_mumbername.getText().toString().trim();
                String statime =   add_birthday.getText().toString().trim();
                String mdid =  shopcontentList.get(shopindex).getId();
                String comment = add_remark.getText().toString().trim();

                Api.getAdd(getActivity(), name, statime, mdid, comment, new Api.OnResponseListener() {
                    @Override
                    public void onResponse(String json) {
                        final String contextjson1 = json;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson = new Gson();
                                AddBean addBean = gson.fromJson(contextjson1,AddBean.class);
                                Toast.makeText(getContext(),addBean.getData().getSm(), Toast.LENGTH_SHORT).show();
                                if (addBean.getData().getDs().equals("1")){
                                    add_mumbername.setText("");
                                    add_birthday.setText("");
                                    add_shop.setText("");
                                    add_remark.setText("");
                                }
                            }
                        });
                    }
                });


            }
        });
        //成员列表
        mumberlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MembersListActivity.class);


                startActivityForResult(intent,1);
            }
        });

        return view;
    }
    //获取部门信息
   void getdepartment(){
       Api.getstore(getActivity(), new Api.OnResponseListener() {
           @Override
           public void onResponse(String json) {
               final String contextjson1 = json;
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Gson gson = new Gson();
                       ShopNameBean shopNameBean = gson.fromJson(contextjson1,ShopNameBean.class);
                       if (shopNameBean.getData().getDs().equals("1")){

                           shopcontentList.clear();
                           shopList.clear();
                           shopcontentList.addAll(shopNameBean.getData().getTable1());
                           for (int i =0 ;i<shopcontentList.size();i++){
                               //  homemakeList[i]{(queryBean.getData().table1.get(i).getName())};
                               shopList.add(shopcontentList.get(i).getName());
                           }
                           showshop();

                       }else {
                           Toast.makeText(getContext(),"数据异常", Toast.LENGTH_SHORT).show();

                       }
                   }
               });
           }
       });
   }
   void  showshop(){

       final    String[] items4 = shopList.toArray(new String[shopList.size()]);
       AlertDialog alertDialog4 = new AlertDialog.Builder(getActivity())
               .setTitle("请选择")
               .setIcon(R.mipmap.ic_launcher)
               .setSingleChoiceItems(items4, shopindex, new DialogInterface.OnClickListener() {//添加单选框
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       shopindex = i;
                   }
               })
               .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       if (shopindex==-1){
                           return;
                       }
                       add_shop.setText(items4[shopindex]);

                       //Toast.makeText(AlertDialogActivity.this, "这是确定按钮" + "点的是：" + items4[index], Toast.LENGTH_SHORT).show();
                   }
               })

               .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       //  Toast.makeText(AlertDialogActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                   }
               })
               .create();
       alertDialog4.show();
   }
}
