package com.predictorsd.geodesycalculator.CoordinateConvert;

public class RadiusCalculate {

    public RadiusCalculate(){}

    public static double[] calculate(double a, double f, double B, double A)
    {
        //角度化弧度
        B = B * Math.PI / 180;
        A = A * Math.PI / 180;

        double b = a - a * f;       //椭圆短轴
        double e = Math.sqrt(a * a - b * b) / a;    //第一偏心率
        double e2 = Math.sqrt(a * a - b * b) / b;    //第二偏心率

        double c = a * a / b;       //极曲率半径
        double W = Math.sqrt(1 - e * e * Math.pow(Math.sin(B), 2));
        double V = Math.sqrt(1 + e2 * e2 * Math.pow(Math.cos(B), 2));

        double M = a * (1 - e * e) / Math.pow(W, 3);        //子午圈曲率半径
        double N = a / W;           //卯酉圈曲率半径
        double R = N / V;           //平均曲率半径

        double RA = N / (1 + Math.pow(e2, 2) * Math.pow(Math.cos(B), 2) * Math.pow(Math.cos(A), 2));

        double[] re = { M, N, R, RA };
        return re;
    }
}
