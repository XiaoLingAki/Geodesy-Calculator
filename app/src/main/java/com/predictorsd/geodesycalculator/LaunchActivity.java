package com.predictorsd.geodesycalculator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/* 启动界面
   在此添加启动时执行的逻辑
   耗时不应过长，否则影响启动速度
*/
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LaunchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },2000);
        //假装自己在初始化什么东西
        //给钱优化
    }
}
