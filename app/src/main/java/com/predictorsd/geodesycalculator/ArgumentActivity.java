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
        Actions act1=new Actions("1975国际椭球参数",R.mipmap.ic_launcher,"1975");
        Actions act2=new Actions("WGS84椭球参数",R.mipmap.ic_launcher,"WGS");
        Actions act3=new Actions("CGCS2000椭球参数",R.mipmap.ic_launcher,"CGCS");

        selist.add(act1);
        selist.add(act2);
        selist.add(act3);
    }
}
