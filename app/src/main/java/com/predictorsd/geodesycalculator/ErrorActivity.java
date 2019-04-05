package com.predictorsd.geodesycalculator;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ErrorActivity extends AppCompatActivity {

    List<Actions> selist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_error);

        //控件绑定
        initlist();
        RecyclerView menu1=findViewById(R.id.error_menu);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        Menu1Adopter menu1Adopter=new Menu1Adopter(selist);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        menu1.setAdapter(menu1Adopter);
        menu1.setLayoutManager(linearLayoutManager);
    }

    void initlist(){

        //菜单列表
        Actions act1=new Actions("空间直角坐标转大地坐标",R.mipmap.ic_launcher,"e_ACT1");
        Actions act2=new Actions("大地坐标转空间直角坐标",R.mipmap.ic_launcher,"e_ACT2");
        Actions act3=new Actions("高斯坐标正算",R.mipmap.ic_launcher,"e_ACT3");
        Actions act4=new Actions("高斯坐标反算",R.mipmap.ic_launcher,"e_ACT4");
        Actions act5=new Actions("二维坐标四参数转换模型",R.mipmap.ic_launcher,"e_ACT5");
        Actions act6=new Actions("空间坐标布尔莎七参数转换模型",R.mipmap.ic_launcher,"e_ACT6");

        selist.add(act1);
        selist.add(act2);
        selist.add(act3);
        selist.add(act4);
        selist.add(act5);
        selist.add(act6);

    }
}
