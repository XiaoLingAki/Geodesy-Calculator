package com.predictorsd.geodesycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.predictorsd.geodesycalculator.CoordinateConvert.RadiusCalculate;

import static com.predictorsd.geodesycalculator.CoordinateConvert.RadiusCalculate.calculate;

public class ArgACT1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arg_act1);

        TextView textView=findViewById(R.id.arg_act1_content);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(calstring());
    }

    private String calstring(){
        StringBuilder re;

        re = new StringBuilder("1975国际椭球体：" + "\n");
        double a = 6378140;
        double b = 6356755.288157528;
        double f = (a - b) / a;
        re.append("椭圆长轴为：").append(a).append("\n");
        re.append("椭圆短轴为：").append(b).append("\n");
        re.append("\n");
        re.append("曲率半径计算：").append("\n");
        for (double jj = 0; jj <= 90; jj += 10) {
            double[] r1 = calculate(a, f, jj, 30);
            re.append(String.valueOf(jj)).append("°对应的").append("子午圈曲率半径:").
                    append(String.valueOf(r1[0])).append("\n").append("卯酉圈曲率半径:").
                    append(String.valueOf(r1[1])).append("\n").append("平均曲率半径:").
                    append(String.valueOf(r1[2])).append("\n").append("30°对应").
                    append(String.valueOf(jj)).append("°的曲率半径：").append(r1[3]).append("\n");

            double[] r2 = calculate(a, f, jj, 60);
            re.append("60°对应").append(String.valueOf(jj)).append("°的曲率半径：").append(r2[3]).append("\n");
        }
        return re.toString();
    }
}
