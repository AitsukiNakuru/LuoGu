package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P8771 {
    static int sizeA, sizeB, n;
    static Long ans = 0L;
    static int[] numberA, numberB;

    static int mod(int n) {
        return n % 1000000007;
    }

    static Long mod(Long n) {
        return n % 1000000007;
    }

    static int findMax(int a, int b) {
        int max = Math.max(a, b);
        return Math.min(max + 1, n);
    }

    static int findMin(int a, int b) {
        int min = Math.max(a, b);
        return Math.max(min + 1, 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        str = br.readLine().split(" ");
        sizeA = Integer.parseInt(str[0]);
        str = br.readLine().split(" ");
        numberA = new int[10000000];
        for (int i = 0; i < sizeA; i++) {
            numberA[sizeA - i - 1] = Integer.parseInt(str[i]);
        }
        str = br.readLine().split(" ");
        sizeB = Integer.parseInt(str[0]);
        numberB = new int[10000000];
        str = br.readLine().split(" ");
        for (int i = 0; i < sizeB; i++) {
            numberB[sizeB - i - 1] = Integer.parseInt(str[i]);
        }
        int maxIndex = Math.max(sizeA, sizeB);
        int[] x = new int[maxIndex];
        for (int i = 0; i < maxIndex; i++) {
            x[i] = Math.max(2, Math.max(numberA[i],numberB[i])+1);
        }
        long A=0L, B=0L;
        for (int i = sizeA-1; i >=0; i--) {
            A = (A *x[i] + numberA[i]) %1000000007;
        }
        for (int i = sizeB-1; i >=0 ; i--) {
            B = (B *x[i] + numberB[i]) %1000000007;
        }
        System.out.println((A - B +  1000000007) %1000000007);
    }
}
