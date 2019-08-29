package com.example.educationmanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.educationmanage.bean.UserBean;
import com.example.educationmanage.util.Api;
import com.example.educationmanage.util.UserInformationBean;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    UserInformationBean  user= new UserInformationBean();;
    @ViewById(R.id.tabbar_btn)
    public ImageView tabbar_btn;//登录按钮
    @ViewById(R.id.account)
    public EditText account_text;              //账号
    @ViewById(R.id.pwd)
    public EditText pwd_text;                   //密码

    String username;                      //账号
    String password;                       //密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
    }
    @AfterViews
     public   void  initdata(){


        if (user.getremember_password()){
            String useraccount = user.gettuseraccount();
            String pwd = user.getpwd();
            account_text.setText(useraccount);
            pwd_text.setText(pwd);
        }
    }
    @Click(R.id.tabbar_btn)
   public void  login(){
        username =  account_text.getText().toString().trim();
        password = pwd_text.getText().toString().trim();
        //判断账号和密码不能为空
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {


            Api.getPhlogin(MainActivity.this, username,  password, new Api.OnResponseListener() {
                @Override
                public void onResponse( String json) {
                    //  Toast.makeText(getContext(),json, Toast.LENGTH_SHORT).show();
                    // Log.d(00",json);
                    final String contextjson1 = json;
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getContext(),contextjson, Toast.LENGTH_SHORT).show();
                           Gson gson = new Gson();
                            UserBean userBean = gson.fromJson(contextjson1, UserBean.class);
                            //       Log.d("00", userBean.getData().getDs());
                            if (userBean.getData().getDs().equals("1")){

                                user.setremember_password(true);
                                user.setuseraccount(username);
                                user.setpwdt(password);
                                user.setID(userBean.getData().getUserid());


                                Toast.makeText(getBaseContext(),"登录成功", Toast.LENGTH_SHORT).show();
                                // 跳转页面
                                  Intent intent = new Intent(MainActivity.this, ShowTabbarActivity.class);
                              //  Intent intent = new Intent(MainActivity.this, ListActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getBaseContext(),"登录失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
            });





        }else {
            Toast.makeText(MainActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
        }


    }
}
