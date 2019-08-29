package com.example.educationmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.educationmanage.bean.MembersListBean;

public class MemberInformationActivity extends BaseActivity {
    MembersListBean.content.table item;
    TextView memberinformation_title;//会员信息
    TextView  memberinformation_tuifei;//退费
    TextView    memberinformation_jiaofei;//缴费
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberinformation);
        Intent intent = getIntent();
        item = (MembersListBean.content.table) intent.getSerializableExtra("memberinformation");
        memberinformation_title = findViewById(R.id.memberinformation_title);
        memberinformation_title.setText(item.getName());
        memberinformation_tuifei = findViewById(R.id.memberinformation_tuifei);
        memberinformation_jiaofei = findViewById(R.id.memberinformation_jiaofei);
        //缴费
        memberinformation_jiaofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent intent = new Intent(MemberInformationActivity.this,MembershipFeesActivity_.class);
                intent.putExtra("memberinformation",item);
                startActivity(intent);
            }
        });
      //退费
        memberinformation_tuifei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
