package com.predictorsd.geodesycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArgumentActivity extends AppCompatActivity {

    List<Actions> selist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argument);

        //控件绑定
        initlist();
        RecyclerView menu1=findViewById(R.id.menu_1);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        Menu1Adopter menu1Adopter=new Menu1Adopter(selist);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        menu1.setAdapter(menu1Adopter);
        menu1.setLayoutManager(linearLayoutManager);
    }

    void initlist(){
        Actions error=new Actions("高斯坐标正算",R.mipmap.ic_launcher,"error");
        Actions argument=new Actions("高斯坐标反算",R.mipmap.ic_launcher,"argument");
        Actions location=new Actions("测区定位功能",R.mipmap.ic_launcher,"location");

        selist.add(error);
        selist.add(argument);
        selist.add(location);
    }
}
