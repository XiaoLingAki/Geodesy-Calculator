package com.predictorsd.geodesycalculator.CoordinateConvert;


import Jama.Matrix;

public class CoordinateConvert {

    private double a, b, e;                 //椭圆参数

    public double X, Y, Z;                 //空间直角坐标
    public double L, B, H;                 //大地坐标
    public CoordinateConvert()             //构造函数
    {
        a = 6378137.0;
        b = a - (a / 298.257223563);
        e = Math.sqrt(a * a - b * b) / a;
    }

    private void setSpatialC(double x, double y, double z)
    {
        X = x;Y = y;Z = z;
    }
    private void setLandC(double l,double b,double h)
    {
        L = l;B = b;H = h;
    }

    /// <summary>
    /// 空间-大地坐标转换
    /// </summary>
    /// <param name="x">X坐标</param>
    /// <param name="y">Y坐标</param>
    /// <param name="z">Z坐标</param>
    /// <returns></returns>
    public double[] ConvertStoL(double x, double y, double z)
    {
        setSpatialC(x, y, z);
        //计算L、B、H
        L = Math.atan(Y / X);
        double tanb0 = Z / (Math.sqrt(X * X + Y * Y));
        B = CalB(tanb0);
        double N = a / (Math.sqrt(1 - (e * e * Math.pow(Math.sin(B), 2))));
        H = Z / (Math.sin(B)) - N * (1 - e * e);
        return new double[] { L, B, H };
    }

    /// <summary>
    /// 迭代函数
    /// </summary>
    /// <param name="B0">迭代初始值</param>
    /// <returns></returns>
    private double CalB(double B0)
    {
        double re = Z / (Math.sqrt(X * X + Y * Y)) + (a * e * e * B0)/
                ((Math.sqrt(X * X + Y * Y))*(Math.sqrt((1+(1-e*e)*Math.pow(B0,2)))));

        if (Math.abs(re - B0) < 5)
        {
            return re;
        }
        else
        {
            return CalB(re);
        }
    }

    /// <summary>
    /// 大地-空间坐标转换
    /// </summary>
    /// <param name="l">大地经度L</param>
    /// <param name="b">大地纬度B</param>
    /// <param name="h">大地高H</param>
    /// <returns></returns>
    public double[] ConvertLtoS(double l, double b, double h)
    {
        l = l * Math.PI / 180;
        b = b * Math.PI / 180;
        h = h * Math.PI / 180;

        setLandC(l, b, h);
        double N = a / (Math.sqrt(1 - (e * e * Math.pow(Math.sin(B), 2))));
        //计算X、Y、Z
        X = (N + H) * Math.cos(B) * Math.cos(L);
        Y = (N + H) * Math.cos(B) * Math.sin(L);
        Z = (N * (1 - e * e) + H) * Math.sin(B);

        return new double[] { X, Y, Z };
    }

    /// <summary>
    /// 空间坐标系布尔莎七参数转换函数
    /// </summary>
    /// <param name="X"></param>
    /// <param name="Y"></param>
    /// <param name="Z"></param>
    /// <param name="TX"></param>
    /// <param name="TY"></param>
    /// <param name="TZ"></param>
    /// <param name="D"></param>
    /// <param name="RX"></param>
    /// <param name="RY"></param>
    /// <param name="RZ"></param>
    /// <returns></returns>
    public static Matrix SC_Calculate(double X, double Y, double Z, double TX, double TY, double TZ, double D, double RX, double RY, double RZ)
    {
        Matrix re;
        double[][] a = { { X }, { Y }, { Z } };
        Matrix aa = new Matrix(a);

        double[][] b = { { TX }, { TY }, { TZ } };
        Matrix bb = new Matrix(b);

        double[][] c = { { D, -RZ, RY }, { RZ, D, -RX }, { -RY, RX, D } };
        Matrix cc = new Matrix(c);

        re = aa.plus( bb .plus (cc .times(aa)));
        return re;
    }

    /// <summary>
    /// 二维平面坐标四参数转换模型
    /// </summary>
    /// <param name="X"></param>
    /// <param name="Y"></param>
    /// <param name="dX"></param>
    /// <param name="dY"></param>
    /// <param name="m"></param>
    /// <param name="alpha"></param>
    /// <returns></returns>
    public static Matrix CBP_Calculate(double X,double Y,double dX,double dY,double m,double alpha)
    {
        Matrix re;
        double[][] a = { { dX }, { dY } };
        Matrix aa = new Matrix(a);

        double[][] b = { { Math.cos(alpha), -Math.sin(alpha) }, { Math.sin(alpha), Math.cos(alpha) } };
        Matrix bb = new Matrix(b);

        double[][] c = { { X }, { Y } };
        Matrix cc = new Matrix(c);

        Matrix dd = bb .times(m) ;
        re = aa .plus( dd .times(cc) );

        return re;
    }

    /// <summary>
    /// 高斯投影变换正算函数
    /// </summary>
    /// <param name="L"></param>
    /// <param name="B"></param>
    /// <returns></returns>
    public static double[] GCP_Calculate(double L, double B)
    {
        //角度化弧度
        double Bp = B * Math.PI / 180;
        L = L * Math.PI / 180;

        double a = 6378137;                 //椭球长轴
        double f = 1 / 298.257223563;       //椭球扁率
        double b = a - a * f;               //椭球短轴
        double e = Math.sqrt(a * a - b * b) / a;        //第一偏心率
        double e2 = Math.sqrt(a * a - b * b) / b;       //第二偏心率
        //m系列
        double m0 = a * (1 - e * e);
        double m2 = 3.0 / 2.0 * e * e * m0;
        double m4 = 5.0 / 4.0 * e * e * m2;
        double m6 = 7.0 / 6.0 * e * e * m4;
        double m8 = 9.0 / 8.0 * e * e * m6;
        //a系列
        double a0 = m0 + 1.0 / 2.0 * m2 + 3.0 / 8.0 * m4 + 5.0 / 16.0 * m6 + 35.0 / 128.0 * m8;
        double a2 = 1.0 / 2.0 * m2 + 1.0 / 2.0 * m4 + 15.0 / 32.0 * m6 + 7.0 / 16.0 * m8;
        double a4 = 1.0 / 8.0 * m4 + 3.0 / 16.0 * m6 + 7.0 / 32.0 * m8;
        double a6 = 1.0 / 32.0 * m6 + 1.0 / 16.0 * m8;
        double a8 = 1.0 / 128.0 * m8;

        double L0 = Math.round(L / 6) * 6 - 3;
        double l = L - L0;
        double η = e2 * e2 * Math.pow(Math.cos(Bp), 2);

        double W = Math.sqrt(1 - e * e * Math.pow(Math.sin(Bp), 2));
        double V = Math.sqrt(1 + e2 * e2 * Math.pow(Math.cos(Bp), 2));

        double M = a * (1 - e * e) / Math.pow(W, 3);        //子午圈曲率半径
        double N = a / W;           //卯酉圈曲率半径
        double R = N / V;           //平均曲率半径

        double X = a0 * Bp - a2 / 2 * Math.sin(2 * Bp) + a4 / 4 * Math.sin(4 * Bp) - a6 / 6 * Math.sin(6 * Bp) + a8 / 8 * Math.sin(8 * Bp);
        double t = Math.tan(Bp);

        double x = X + N / 2 * t * Math.pow(Math.cos(Bp), 2) * l * l +
                N / 24 * t * (5 - t * t + 9 * η * η + 4 * η * η) * Math.pow(Math.cos(Bp), 4) * Math.pow(l, 4) +
                N / 720 * t * (61 - 58 * t * t + Math.pow(t, 4)) * Math.pow(Math.cos(Bp), 6) * Math.pow(l, 6);
        double y = N * Math.cos(Bp) * l +
                N / 6 * Math.pow(Math.cos(Bp), 3) * (1 - t * t + Math.pow(η, 2)) * Math.pow(l, 3) +
                N / 120 * Math.pow(Math.cos(Bp), 5) * (5 - 18 * t * t + Math.pow(t, 4) + 14 * Math.pow(η, 2) - 58 * Math.pow(η, 2) * Math.pow(t, 2)) * Math.pow(l, 5);
        return new double[] { x, y };
    }

    /// <summary>
    /// 高斯投影变换反算函数
    /// </summary>
    /// <param name="x"></param>
    /// <param name="y"></param>
    /// <returns></returns>
    public static double[] GCB_Calculate(double x,double y)
    {
        double a = 6378137;                 //椭球长轴
        double f = 1 / 298.257223563;       //椭球扁率
        double b = a - a * f;               //椭球短轴
        double e = Math.sqrt(a * a - b * b) / a;        //第一偏心率
        double e2 = Math.sqrt(a * a - b * b) / b;       //第二偏心率
        //m系列
        double m0 = a * (1 - e * e);
        double m2 = 3.0 / 2.0 * e * e * m0;
        double m4 = 5.0 / 4.0 * e * e * m2;
        double m6 = 7.0 / 6.0 * e * e * m4;
        double m8 = 9.0 / 8.0 * e * e * m6;
        //a系列
        double a0 = m0 + 1.0 / 2.0 * m2 + 3.0 / 8.0 * m4 + 5.0 / 16.0 * m6 + 35.0 / 128.0 * m8;
        double a2 = 1.0 / 2.0 * m2 + 1.0 / 2.0 * m4 + 15.0 / 32.0 * m6 + 7.0 / 16.0 * m8;
        double a4 = 1.0 / 8.0 * m4 + 3.0 / 16.0 * m6 + 7.0 / 32.0 * m8;
        double a6 = 1.0 / 32.0 * m6 + 1.0 / 16.0 * m8;
        double a8 = 1.0 / 128.0 * m8;

        double Bf = x / a0;
        for(int i = 0; i < 5; i++)
        {
            Bf = 1 / a0 * (x + a2 / 2 * Math.sin(2 * Bf) - a4 / 4 * Math.sin(4 * Bf)
                    + a6 / 6 * Math.sin(6 * Bf) - a8 / 8 * (Math.sin(8 * Bf)));
        }

        double tf = Math.tan(Bf);
        double ηf = e2 * Math.cos(Bf);

        double Nf = a * Math.pow((1 - e * e * Math.pow(Math.sin(Bf), 2)), -1 / 2);
        double Mf = a * (1 - e * e) * Math.pow((1 - e * e * Math.pow(Math.sin(Bf), 2)), -3 / 2);

        double L0 = 114.33 * Math.PI / 180;
        double B = Bf - (tf * y * y / 2 * Mf * Nf)
                + (tf * (5 + 3 * tf * tf + ηf * ηf - 9 * tf * tf * ηf * ηf) * Math.pow(y, 4)) / 24 * Mf * Math.pow(Nf, 3)
                - (tf * (61 + 90 * tf * tf + 45 * Math.pow(tf, 4)) * Math.pow(y, 6)) / 720 * Mf * Math.pow(Nf, 5);
        double L = y / Nf * Math.cos(Bf)
                - Math.pow(y, 3) * (1 + 2 * tf * tf + ηf * ηf) / 6 * Math.pow(Nf, 3) * Math.cos(Bf)
                + Math.pow(y, 5) * (5 + 28 * tf * tf + 24 * tf * tf * tf * tf + 6 * ηf * ηf + 8 * tf * tf * ηf * ηf) / 120 * Math.pow(Nf, 5) * Math.cos(Bf)
                + L0;

        B = B * 180 / Math.PI;
        L = L * 180 / Math.PI;

        return new double[] { L, B };
    }

    /// <summary>
    /// 最小二乘法求二维坐标转换四参数
    /// </summary>
    /// <param name="strdata2"></param>
    /// <param name="row2"></param>
    /// <returns></returns>
    public static double[] CA_Calculate(String[][] strdata2, int row2)
    {
        double detaX = Double.parseDouble(strdata2[1][3]) - Double.parseDouble(strdata2[0][3]);
        double detaY = Double.parseDouble(strdata2[1][4]) - Double.parseDouble(strdata2[0][4]);
        double detax = Double.parseDouble(strdata2[1][1]) - Double.parseDouble(strdata2[0][1]);
        double detay = Double.parseDouble(strdata2[1][2]) - Double.parseDouble(strdata2[0][2]);
        double S = Math.sqrt(Math.pow(detaX, 2) + Math.pow(detaY, 2));
        double s = Math.sqrt(Math.pow(detax, 2) + Math.pow(detay, 2));
        double A = Math.atan(detaY / detaX);
        double a = Math.atan(detaY / detaX);
        double[][] Dxy = new double[2] [1];//存储平移参数
        Dxy[0][0] = Double.parseDouble(strdata2[0][3]) - Double.parseDouble(strdata2[0][1]);
        Dxy[1][0] = Double.parseDouble(strdata2[0][4]) - Double.parseDouble(strdata2[0][2]);
        double m = (S - s) / S;//尺度因子
        double theda = A - a;
        double[][] XpYp = new double[2 * row2][1];
        double[][] B = new double[2 * row2][4];
        double[][] L = new double[2 * row2][1];
        double[][] fourPara = new double[4][1];
        Matrix FourPara = new Matrix(fourPara);
        do
        {
            for (int i = 0; i < row2; i++)
            {
                XpYp[2 * i][0] = Dxy[0][0] + (1 + m) * (Math.cos(theda) * Double.parseDouble(strdata2[i][1]) + Math.sin(theda) * Double.parseDouble(strdata2[i][2]));
                XpYp[2 * i + 1][0] = Dxy[1][0] + (1 + m) * (-Math.sin(theda) * Double.parseDouble(strdata2[i][1]) + Math.cos(theda) * Double.parseDouble(strdata2[i][2]));
            }
            for (int i = 0; i < row2; i++)
            {
                L[2 * i][0] = Double.parseDouble(strdata2[i][3]) - XpYp[2 * i][0];
                L[2 * i + 1][0] = Double.parseDouble(strdata2[i][4]) - XpYp[2 * i + 1][0];
            }
            for (int i = 0; i < row2; i++)
            {
                B[2 * i][0] = 1;
                B[2 * i][1] = 0;
                B[2 * i][2] = Math.cos(theda) * Double.parseDouble(strdata2[i][1]) + Math.sin(theda) * Double.parseDouble(strdata2[i][2]);
                B[2 * i][3] = (1 + m) * (-Math.sin(theda) * Double.parseDouble(strdata2[i][1]) + Math.cos(theda) * Double.parseDouble(strdata2[i][2]));
                B[2 * i + 1][0] = 0;
                B[2 * i + 1][1] = 1;
                B[2 * i + 1][2] = -Math.sin(theda) * Double.parseDouble(strdata2[i][1]) + Math.cos(theda) * Double.parseDouble(strdata2[i][2]);
                B[2 * i + 1][3] = (1 + m) * (-Math.cos(theda) * Double.parseDouble(strdata2[i][1]) - Math.sin(theda) * Double.parseDouble(strdata2[i][2]));
            }
            Matrix matrixB = new Matrix(B);
            Matrix matrixL = new Matrix(L);
            //计算BT*P*B和BT*P*L
            Matrix BTB = matrixB.transpose() .times(matrixB);
            Matrix BTL = matrixB.transpose() .times(matrixL);
            FourPara = BTB.inverse() .times(BTL);
            Dxy[0][0] += FourPara.get(0,0);
            Dxy[1][0] += FourPara.get(1,0);
            m += FourPara.get(2,0);
            theda += FourPara.get(3,0);
        } while ((Math.abs(FourPara.get(0,0)) > 5.0E-10) && (Math.abs(FourPara.get(1,0))
                > 5.0E-10) && (Math.abs(FourPara.get(2,0)) > 5.0E-10) && (Math.abs(FourPara.get(3,0)) > 5.0E-10));

        return new double[] { Dxy[0][0], Dxy[1][0], theda, (1 + m) };
    }
}
