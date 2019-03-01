package com.predictorsd.geodesycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Actions> selist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //控件绑定
        initlist();
        RecyclerView mainmenu=findViewById(R.id.main_menu);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        Action2Adopter action2Adapter=new Action2Adopter(selist);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainmenu.setAdapter(action2Adapter);
        mainmenu.setLayoutManager(linearLayoutManager);
    }

    void initlist(){
        Actions error=new Actions("坐标转换计算",R.mipmap.ic_launcher,"error");
        Actions argument=new Actions("常用参数查看",R.mipmap.earthicon,"argument");
        //Actions location=new Actions("测区定位功能",R.mipmap.ic_launcher,"location");

        selist.add(error);
        selist.add(argument);
        //selist.add(location);
    }
}
