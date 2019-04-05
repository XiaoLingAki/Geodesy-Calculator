package com.predictorsd.geodesycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.predictorsd.geodesycalculator.CoordinateConvert.CoordinateConvert;

import Jama.Matrix;

public class ErrorAct5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_act5);

        final EditText editText1=findViewById(R.id.act5edit1);
        final EditText editText2=findViewById(R.id.act5edit2);
        final EditText editText3=findViewById(R.id.act5edit3);
        final EditText editText4=findViewById(R.id.act5edit4);
        final EditText editText5=findViewById(R.id.act5edit5);
        final EditText editText6=findViewById(R.id.act5edit6);

        final EditText editText7=findViewById(R.id.act5result1);
        final EditText editText8=findViewById(R.id.act5result2);

        Button button1=findViewById(R.id.act5button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double dX=Double.parseDouble(editText1.getText().toString());
                    double dY=Double.parseDouble(editText2.getText().toString());
                    double alpha=Double.parseDouble(editText3.getText().toString());
                    double m=Double.parseDouble(editText4.getText().toString());
                    double x=Double.parseDouble(editText5.getText().toString());
                    double y=Double.parseDouble(editText6.getText().toString());

                    Matrix re=CoordinateConvert.CBP_Calculate(dX,dY,alpha,m,x,y);

                    editText7.setText(String.valueOf(re.get(0,0)));
                    editText8.setText(String.valueOf(re.get(1,0)));
                }catch (Exception e){
                    Toast.makeText(v.getContext(),"错误的输入数据！",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}
