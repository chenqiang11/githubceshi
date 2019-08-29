package com.example.educationmanage.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;




import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.kaopiz.kprogresshud.KProgressHUD;
public class Api {

 //  public static final String base_url = "http://192.168.1.150:8989/Interface/";
   public static final String base_url = "http://192.168.1.180:7777/";
   // public static final String base_url = "http://sf136540136.gnway.cc:8989/Interface/";
    //外网
  //  public static final String base_url = "http://116.62.204.239:60113/";

    /**
     *    登录
     * @param activity
     * @param username  账户名
     * @param password  密码
     * @param listener
     */

    public static void getPhlogin(final Activity activity, String username, String password, final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("name", username).add("pwd",password)
                .build();
        Request request = new Request.Builder()
                .url(base_url+"login.asmx/logins")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
               // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

              //  Log.d("1", response.body().string());
              String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
              listener.onResponse(newJson);
            }
        });




    }

    /**
     *     报警列表
     * @param activity
     * @param sql  关键字
     * @param listener
     */
    public static void getWarninglist(final Activity activity, String sql, final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("sql", sql)
                .build();
        Request request = new Request.Builder()
                .url(base_url+"index.asmx/baojing")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

                //  Log.d("1", response.body().string());
                String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
                listener.onResponse(newJson);
            }
        });

    }

    /**
     *    获取门店信息列表
     * @param activity
     * @param listener
     */
    public static void getstore(final Activity activity,  final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()

                .build();
        Request request = new Request.Builder()
                .url(base_url+"index.asmx/Store")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

                //  Log.d("1", response.body().string());
                String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
                listener.onResponse(newJson);
            }
        });

    }

    /**
     *  会员添加
     * @param activity
     * @param name  会员姓名
     * @param statime 出生日期
     * @param mdid 所属门店
     * @param comment 备注
     * @param listener
     */

    public static void getAdd(final Activity activity, String name,  String statime, String mdid, String comment,final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .add("statime", statime)
                .add("mdid", mdid)
                .add("comment", comment)
                .build();
        Request request = new Request.Builder()
                .url(base_url+"index.asmx/Add")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

                //  Log.d("1", response.body().string());
                String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
                listener.onResponse(newJson);
            }
        });

    }

    /**
     *      获取会员列表
     * @param activity
     * @param sql  关键字
     * @param listener
     */
    public static void getmemberslist(final Activity activity, String sql, final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("sql", sql)
                .build();
        Request request = new Request.Builder()
                .url(base_url+"index.asmx/student")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

                //  Log.d("1", response.body().string());
                String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
                listener.onResponse(newJson);
            }
        });

    }

    /**
     *     家长信息
     * @param activity
     * @param stuID  学生id
     * @param listener
     */
    public static void getparentlist(final Activity activity, String stuID, final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("stuID", stuID)
                .build();
        Request request = new Request.Builder()
                .url(base_url+"index.asmx/Parent")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

                //  Log.d("1", response.body().string());
                String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
                listener.onResponse(newJson);
            }
        });

    }

    /**
     *     获取课程信息
     * @param activity
     * @param listener
     */
    public static void getcourse(final Activity activity,  final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()

                .build();
        Request request = new Request.Builder()
                .url(base_url+"index.asmx/course")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

                //  Log.d("1", response.body().string());
                String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
                listener.onResponse(newJson);
            }
        });

    }

    /**
     *        会员缴费
     * @param activity
     * @param Stuid 会员ID
     * @param Couid 课程ID
     * @param Statime 缴费时间
     * @param Price 缴费金额
     * @param Couch 缴费课时
     * @param Beizhu 备注
     * @param listener
     */
    public static void getPayment(final Activity activity, String Stuid,String Couid,String Statime,String Price,String Couch,String Beizhu, final OnResponseListener listener){

        final KProgressHUD alertHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍等...").show();



        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("stuid", Stuid)
                .add("couid", Couid)
                .add("statime", Statime)
                .add("price", Price)
                .add("coukh", Couch)
                .add("beizhu", Beizhu)
                .build();
        Request request = new Request.Builder()
                .url(base_url+"index.asmx/Payment")

                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //   Log.d(TAG, "onFailure: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertHUD.dismiss();
                        DialogHelp.getMessageDialog(activity, e.getMessage() + "请联系管理员!").show();
                    }
                });


            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                alertHUD.dismiss();
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                   Log.d("11", headers.name(i) + ":" + headers.value(i));
//                }

                //  Log.d("1", response.body().string());
                String json = response.body().string();

                String newJson = xmlToString(json);
                newJson = htmlToString(newJson);
                listener.onResponse(newJson);
            }
        });

    }

    /**
     * 回调成功数据
     */
    public interface OnResponseListener {
        void onResponse(String json);
    }

    /**
     * @作者 zxy
     * xml 格式转换成tostring形式
     * @创建日期 2018-08-29
     */
    public static String xmlToString(String string) {
        String str = string.replace("<string xmlns=\"http://tempuri.org/\">", "");
        str = str.replace("</string>", "");
        str = str.replace("<string>", "");
        str = str.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
        str = str.replace("\r\n", "");
        return str.trim();
    }

    public static String htmlToString(String string){
        if(string.contains("<!DOCTYPE html PUBLIC")){
            string = string.substring(string.indexOf("<body>")+6,string.indexOf("</body>"));
            string = string.replaceAll("\r\n","");
            string = string.replaceAll("\t","");
        }
        return string;
    }
}
