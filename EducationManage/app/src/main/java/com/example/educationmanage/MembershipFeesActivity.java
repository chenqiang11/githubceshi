package com.example.educationmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.educationmanage.bean.ChargeBean;
import com.example.educationmanage.bean.MembersListBean;
import com.example.educationmanage.util.Api;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.educationmanage.util.MyApplication.getContext;

@EActivity(R.layout.activity_membershipfees)
public class MembershipFeesActivity extends BaseActivity {

    @Extra("memberinformation")
    MembersListBean.content.table table;//会员信息
    @ViewById(R.id.membersshipfees_name)
    Button membersshipfees_name;//会员名字
    @ViewById(R.id.membersshipfees_time)
    Button   membersshipfees_time;//缴费时间
    @ViewById(R.id.membersshipfees_money)
  EditText membersshipfees_money;//缴费金额
    @ViewById()
    EditText membersshipfees_hour;//缴费课时
    @ViewById()
    EditText membersshipfees_remark;//备注
    @ViewById()
    Button membersshipfees_classname;//课程名字
    ChargeBean.content.table JSON;//课程信息
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @AfterViews
    public void  settextview(){
        membersshipfees_name.setText(table.getName());
     //   membersshipfees_money.setText(table.getPrice());
      //  membersshipfees_hour.setText(table.getCou_Per());
      //  textview1.setText("这是初始化之后得text");
    }
    //会员名字
    @Click(R.id.membersshipfees_name)
    public void setmembersname(){
        Log.d("00","sfs");
    }
//缴费时间
    @Click(R.id.membersshipfees_time)
    public void setmembersshipfees_time(){
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(MembershipFeesActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // Toast.makeText(AddActivity.this,sdf.format(date) , Toast.LENGTH_SHORT).show();
                membersshipfees_time.setText(sdf.format(date));

            }
        }).build();
        pvTime.show();
    }
    //添加
    @Click(R.id. membershipfees_addbtn)
    public void setmembershipfees_addbtn(){
     //如果课程名为空提示
    if (membersshipfees_classname.getText().toString().trim().equals("")){
        Toast.makeText(MembershipFeesActivity.this,"请选择课程" , Toast.LENGTH_SHORT).show();
        return;
    }
        if (membersshipfees_time.getText().toString().trim().equals("")){
            Toast.makeText(MembershipFeesActivity.this,"请选择缴费时间" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (membersshipfees_money.getText().toString().trim().equals("")){
            Toast.makeText(MembershipFeesActivity.this,"请输入缴费金额" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (membersshipfees_hour.getText().toString().trim().equals("")){
            Toast.makeText(MembershipFeesActivity.this,"请输入缴费课时" , Toast.LENGTH_SHORT).show();
            return;
        }
         String Stuid = table.getId();
        String couid = JSON.getId();
        String statime = membersshipfees_time.getText().toString().trim();
        String price = membersshipfees_money.getText().toString().trim();
        String couch = membersshipfees_hour.getText().toString().trim();
        String beizhu = membersshipfees_remark.getText().toString().trim();
        Api.getPayment(MembershipFeesActivity.this, Stuid, couid, statime, price, couch, beizhu, new Api.OnResponseListener() {
            @Override
            public void onResponse(String json) {
                final String contextjson1 = json;
                MembershipFeesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ChargeBean warningBean = gson.fromJson(contextjson1,ChargeBean.class);
                        Toast.makeText(getContext(),warningBean.getData().getSm(), Toast.LENGTH_SHORT).show();
//                        if (warningBean.getData().getDs().equals("1")){
//                            Toast.makeText(getContext(),"", Toast.LENGTH_SHORT).show();
//
//                        }else {
//                            Toast.makeText(getContext(),"数据异常", Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });
            }
        });

    }
    //课程名字
    @Click(R.id. membersshipfees_classname)
    public void setmembersshipfees_classname(){
        Intent intent = new Intent(MembershipFeesActivity.this,ClassActivity.class);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:

                if (resultCode ==RESULT_OK) {
                    // YanHuoOrderDetailsBean.YanHuoOrderDetailsdata yanHuoOrderDetailsdata = (YanHuoOrderDetailsBean.YanHuoOrderDetailsdata) data.getSerializableExtra("yanHuoOrderDetailsdata");
//                    ChargeBean.content.table JSON = (ChargeBean.content.table)data.getStringExtra("result");
                     JSON = (ChargeBean.content.table)data.getSerializableExtra("result");
                    membersshipfees_classname.setText(JSON.getName());


                }
                break;

            default:
                break;
        }
    }
}
