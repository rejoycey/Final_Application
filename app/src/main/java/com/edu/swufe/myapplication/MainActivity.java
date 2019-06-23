package com.edu.swufe.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.edu.swufe.myapplication.CircleMenuLayout.OnMenuItemClickListener;

public class MainActivity extends AppCompatActivity {

    private long mExitTime;
    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts = new String[] { "登录&注册", "收入&支出", "统计" };
    private int[] mItemImgs = new int[] { R.mipmap.home_mbank_1_normal,
            R.mipmap.home_mbank_5_normal,
            R.mipmap.home_mbank_6_normal };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public void itemClick(View view, int pos) {
                    if (mItemTexts[pos] == "收入&支出") {
                    openSpendingWind(view);
                } else if (mItemTexts[pos] == "登录&注册") {
                    openLoginWind(view);
                } else if (mItemTexts[pos] == "统计") {
                    openCountWind(view);
                }
            }
            public void itemCenterClick(View view) {
                Toast.makeText(MainActivity.this, "请先登录或注册", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //跳转至登录&注册界面
        private void openLoginWind(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LoginActivity.class);
        this.startActivity(intent);
    }

     //跳转收入&支出界面
    private void openSpendingWind(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SpendingActivity.class);
        this.startActivity(intent);
    }

    //跳转至统计界面
    private void openCountWind(View v){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, CountActivity.class);
        this.startActivity(intent);
    }


     //点击两次返回退出app

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                //System.currentTimeMillis()系统当前时间
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
        }

