package com.predictorsd.geodesycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.predictorsd.geodesycalculator.CoordinateConvert.CoordinateConvert;

public class ErrorAct1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_act1);

        final EditText editText1=findViewById(R.id.act1edit1);
        final EditText editText2=findViewById(R.id.act1edit2);
        final EditText editText3=findViewById(R.id.act1edit3);
        final EditText editText4=findViewById(R.id.act1result1);
        final EditText editText5=findViewById(R.id.act1result2);
        final EditText editText6=findViewById(R.id.act1result3);

        Button button=findViewById(R.id.act1button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double X=Double.parseDouble(editText1.getText().toString());
                    double Y=Double.parseDouble(editText2.getText().toString());
                    double Z=Double.parseDouble(editText3.getText().toString());

                    CoordinateConvert coordinateConvert=new CoordinateConvert();
                    double[] result=coordinateConvert.ConvertStoL(X,Y,Z);

                    editText4.setText(String.valueOf(result[0]));
                    editText5.setText(String.valueOf(result[1]));
                    editText6.setText(String.valueOf(result[2]));
                }catch (Exception e){
                    Toast.makeText(v.getContext(),"错误的输入数据！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
