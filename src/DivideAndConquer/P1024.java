package DivideAndConquer;

import java.util.Scanner;

public class P1024 {
    static double a;
    static double b;
    static double c;
    static double d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();
        d = sc.nextDouble();
        double derivativeDelta = b * b - 3 * a * c;
        double v1 = (-b + Math.sqrt(derivativeDelta)) / (3 * a);
        double v2 = (-b - Math.sqrt(derivativeDelta)) / (3 * a);
        double derivativeLeft = Math.min(v1, v2);
        double derivativeRight = Math.max(v1, v2);
        System.out.printf("%.2f ", divideAndConquer(-100, derivativeLeft));
        System.out.printf("%.2f ", divideAndConquer(derivativeLeft, derivativeRight));
        System.out.printf("%.2f ", divideAndConquer(derivativeRight, 100));
    }

    static double divideAndConquer(double left, double right) {
        if (left + 1 >= right) {
            for (double i = left; i <= right; i += 0.01) {
                if (judZero(fx(i))) {
                    return i;
                }
            }
        }
        double mid = (left + right) / 2;
        double fxLeft = fx(left);
        double fxMid = fx(mid);
        double fxRight = fx(right);
        if (judZero(fxLeft)) {
            return left;
        }
        if (judZero(fxMid)) {
            return mid;
        }
        if (judZero(fxRight)) {
            return right;
        }
        if (fxLeft * fxMid <= 0) {
            return divideAndConquer(left, mid);
        }
        if (fxMid * fxRight <= 0) {
            return divideAndConquer(mid, right);
        }
        return 0;
    }

    static double fx(double num) {
        return a * Math.pow(num, 3) + b * Math.pow(num, 2) + c * num + d;
    }

    static boolean judZero(double num) {
        return Math.abs(num - 0) < 0.01;
    }
}
