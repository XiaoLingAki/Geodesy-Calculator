package com.predictorsd.geodesycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.predictorsd.geodesycalculator.CoordinateConvert.CoordinateConvert;

public class ErrorAct4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_act4);

        final EditText editText1=findViewById(R.id.act4edit1);
        final EditText editText2=findViewById(R.id.act4edit2);

        final EditText editText3=findViewById(R.id.act4result1);
        final EditText editText4=findViewById(R.id.act4result2);

        Button button=findViewById(R.id.act4button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double L=Double.parseDouble(editText1.getText().toString());
                double B=Double.parseDouble(editText2.getText().toString());

                double[] result= CoordinateConvert.GCB_Calculate(L,B);

                editText3.setText(String.valueOf(result[0]));
                editText4.setText(String.valueOf(result[1]));

            }
        });
    }
}
