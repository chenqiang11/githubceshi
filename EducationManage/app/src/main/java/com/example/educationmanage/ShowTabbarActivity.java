package com.example.educationmanage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationmanage.util.ActivityCollector;

public class ShowTabbarActivity extends FragmentActivity implements View.OnClickListener {
    FragmentManager fragmentManager;
    private  AddmumberFragment addmumberFragment;//添加会员
    private ChargeFragment chargeFragment;//收费
    private WarningFragment warningFragment;//预警
    private LinearLayout add_layout;
    private ImageView add_iv;
    private TextView add_tv;
    private LinearLayout charge_layout;
    private ImageView charge_iv;
    private TextView charge_tv;

    private LinearLayout waring_layout;
    private ImageView warning_iv;
    private TextView warning_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtabbar);
        ActivityCollector.addActivity(this);//将活动添加到活动收集器
        fragmentManager = getSupportFragmentManager();
        // 绑定页面
        initView();

        warningFragment = new  WarningFragment  ();
        // 启动Activity时使第一个按钮的图标为选中状态（投机取巧）
       // dinghuo_iv.setImageResource(R.drawable.dinghuoimage1);

//        hideFragments(getSupportFragmentManager().beginTransaction());
////        getSupportFragmentManager().beginTransaction().commit();

//        getSupportFragmentManager().beginTransaction().hide(addmumberFragment);

//        if (savedInstanceState != null) {
//            // mTransaction = mManager.beginTransaction();
//            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(Constant.SOURCE_HEADLINES_TAG));
//            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(Constant.BUILDING_ONLINE_TAG));
//            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(Constant.HOME_TAG));
//
//            getSupportFragmentManager().beginTransaction().commitAllowingStateLoss();
//        }


        getSupportFragmentManager().beginTransaction().add(R.id.top_layout,  warningFragment).commit();
    }
   //暂时解决重影问题
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
      //  Toast.makeText(getBaseContext(),"进到这里面了", Toast.LENGTH_SHORT).show();
        if(addmumberFragment==null && fragment instanceof AddmumberFragment){
            addmumberFragment=(AddmumberFragment)fragment;
            getSupportFragmentManager().beginTransaction().hide(addmumberFragment).commit();
        }else if(chargeFragment==null && fragment instanceof ChargeFragment){
            chargeFragment=(ChargeFragment)fragment;
            getSupportFragmentManager().beginTransaction().hide( chargeFragment).commit();
        }else if(warningFragment==null && fragment instanceof WarningFragment) {
            warningFragment = (WarningFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide( warningFragment).commit();
        }
    }

    //初始化界面
    void initView(){
        add_layout = (LinearLayout) findViewById(R.id.add_layout);
        add_iv = (ImageView) findViewById(R.id.add_iv);
        add_tv = (TextView) findViewById(R.id.add_tv);
        charge_layout = (LinearLayout) findViewById(R.id.charge_layout);
        charge_iv = (ImageView) findViewById(R.id.charge_iv);
        charge_tv = (TextView) findViewById(R.id.charge_tv);

        waring_layout = (LinearLayout) findViewById(R.id.waring_layout);
        warning_iv = (ImageView) findViewById(R.id.warning_iv);
        warning_tv = (TextView) findViewById(R.id.warning_tv);

        add_layout.setOnClickListener(this);
        charge_layout.setOnClickListener(this);
        waring_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_layout: setChioceItem(0);
                break;
            case R.id.charge_layout: setChioceItem(1);
                break;
            case R.id.waring_layout: setChioceItem(2);
                break;

            default:
                break;
        }
    }
    /***
     * 定义选中后的控制器
     * @param index index
     */
    public void setChioceItem(int index) {

        clearChioce(); // 既然是点击选择，那么在点的时候就应该清除一下上一个索引
        // 重置选项+隐藏所有的Fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
//
//
//
        switch (index) {
            case 0:
//                add_iv.setImageResource(R.drawable.tabbar_dinghuo_selector);
//                dinghuo_tv.setTextColor(getResources().getColor(R.color.tabbars));
                if (addmumberFragment == null) {
                    addmumberFragment = new AddmumberFragment();
                    transaction.add(R.id.top_layout, addmumberFragment);
                } else {
                    transaction.show(addmumberFragment);
                }

                break;


            case 1:
//                yanhuo_iv.setImageResource(R.drawable.tabbar_yanhuo_selector);
//                yanhuo_tv.setTextColor(getResources().getColor(R.color.tabbars));
                if (chargeFragment == null) {
                    chargeFragment = new ChargeFragment();
                    transaction.add(R.id.top_layout, chargeFragment);
                } else {
                    transaction.show(chargeFragment);
                }
                break;

            case 2:
//                yanhuo_iv.setImageResource(R.drawable.tabbar_yanhuo_selector);
//                yanhuo_tv.setTextColor(getResources().getColor(R.color.tabbars));
                if (warningFragment== null) {
                    warningFragment = new WarningFragment();
                    transaction.add(R.id.top_layout, warningFragment);
                } else {
                    transaction.show(warningFragment);
                }
                break;


        }


        transaction.commit();

    }

    /***
     * * 定义一个重置所有选项的方法 * /
     *
     */
    public void clearChioce() {
//        yanhuo_iv.setImageResource( R.drawable.yanhuoimage2);
//        yanhuo_layout.setBackgroundColor(Color.WHITE);
//        yanhuo_tv.setTextColor(getResources().getColor(R.color.tabbarn));
//        dinghuo_iv.setImageResource(R.drawable.dinghuoimage2);
//        dinghuo_layout.setBackgroundColor(Color.WHITE) ;
//        dinghuo_tv.setTextColor(getResources().getColor(R.color.tabbarn));

    }

    /***
     * * 将所有的Fragment都设置为隐藏状态 * @param transaction 事物 * /
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
//        private  AddmumberFragment addmumberFragment;//添加会员
//        private ChargeFragment chargeFragment;//收费
//        private WarningFragment warningFragment;//预警
        if (addmumberFragment != null) {
            transaction.hide(addmumberFragment);
        }
        if (chargeFragment != null)
        {
            transaction.hide(chargeFragment);
        }
        if (warningFragment != null)
        {
            transaction.hide(warningFragment);
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);//将活动移除活动收集器
    }
    /**
     * 点击空白区域隐藏键盘.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {  //把操作放在用户点击的时候
            View v = getCurrentFocus();      //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, me)) { //判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());   //收起键盘
            }

        }
        return super.dispatchTouchEvent(me);
    }
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {  //判断得到的焦点控件是否包含EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }
    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
