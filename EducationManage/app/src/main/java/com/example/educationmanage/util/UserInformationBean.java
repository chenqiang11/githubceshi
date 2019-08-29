package  com.example.educationmanage.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.example.educationmanage.util.MyApplication;


import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;

 public  class UserInformationBean  implements Serializable {
        //存储账号
    public void setuseraccount(String useraccount){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();//获取编辑器

        editor.putString("useraccount", useraccount);



        editor.commit();//提交修改

    }
    //提取账号
public String gettuseraccount(){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);


     String useraccount = sharedPreferences.getString("useraccount","");
       return useraccount;


    }
    //存储密码
    public void setpwdt(String pwd){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();//获取编辑器

        editor.putString("pwd", pwd);



        editor.commit();//提交修改

    }
    //提取密码
    public String getpwd(){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);


        String pwd = sharedPreferences.getString("pwd","");
        return pwd;


    }

    //存储账号id
    public void setID(String ID){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();//获取编辑器

        editor.putString("ID", ID);



        editor.commit();//提交修改

    }
    //提取账号ID
    public String getID(){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);


        String ID = sharedPreferences.getString("ID","");
        return ID;


    }
    //记住密码
    public void setremember_password(boolean remember_password){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();//获取编辑器

        editor.putBoolean("remember_password",remember_password);



        editor.commit();//提交修改

    }
    //记住密码
    public boolean getremember_password(){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data",MyApplication.getContext().MODE_PRIVATE);


        boolean remember_password = sharedPreferences.getBoolean("remember_password",false);
        return remember_password;


    }
//清空缓存
    public static void clear() {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        editor.commit();
    }
}
