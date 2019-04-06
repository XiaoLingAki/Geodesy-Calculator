package com.predictorsd.geodesycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.predictorsd.geodesycalculator.CoordinateConvert.CoordinateConvert;

import Jama.Matrix;

public class ErrorAct6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_act6);
        //七参数
        final EditText editText1=findViewById(R.id.act6edit1);
        final EditText editText2=findViewById(R.id.act6edit2);
        final EditText editText3=findViewById(R.id.act6edit3);
        final EditText editText4=findViewById(R.id.act6edit4);
        final EditText editText9=findViewById(R.id.act6edit7);
        final EditText editText10=findViewById(R.id.act6edit8);
        final EditText editText11=findViewById(R.id.act6edit9);
        //输入X、Y、Z
        final EditText editText5=findViewById(R.id.act6edit5);
        final EditText editText6=findViewById(R.id.act6edit6);
        final EditText editText12=findViewById(R.id.act6edit12);
        //结果
        final EditText editText7=findViewById(R.id.act6result1);
        final EditText editText8=findViewById(R.id.act6result2);
        final EditText editText13=findViewById(R.id.act6edit13);

        Button button1=findViewById(R.id.act6button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double tX=Double.parseDouble(editText1.getText().toString());
                    double tY=Double.parseDouble(editText2.getText().toString());
                    double tZ=Double.parseDouble(editText3.getText().toString());
                    double rX=Double.parseDouble(editText4.getText().toString());
                    double rY=Double.parseDouble(editText9.getText().toString());
                    double rZ=Double.parseDouble(editText10.getText().toString());
                    double D=Double.parseDouble(editText11.getText().toString());

                    double x=Double.parseDouble(editText5.getText().toString());
                    double y=Double.parseDouble(editText6.getText().toString());
                    double z=Double.parseDouble(editText12.getText().toString());

                    Matrix re= CoordinateConvert.SC_Calculate(x,y,z,tX,tY,tZ,D,rX,rY,rZ);

                    editText7.setText(String.valueOf(re.get(0,0)));
                    editText8.setText(String.valueOf(re.get(1,0)));
                    editText13.setText(String.valueOf(re.get(2,0)));
                }catch (Exception e){
                    Toast.makeText(v.getContext(),"错误的输入数据！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
