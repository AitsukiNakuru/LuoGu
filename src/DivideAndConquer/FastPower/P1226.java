package DivideAndConquer.FastPower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class P1226 {

    static long fastPower(long base, long power, long mod) {
        if (base == 0) {
            return 0;
        }
        if (power == 0) {
            return 1;
        }
        long result = 1;
        while (power > 0) {
            if ((power & 1) != 0) {
                result = result * base % mod; // 防止溢出
            }
            power >>= 1; // 去掉最低位
            base = base * base % mod;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        long a, b, p;
        a = Long.parseLong(str[0]);
        b = Long.parseLong(str[1]);
        p = Long.parseLong(str[2]);
        System.out.println(a + "^" + b + " mod " + p + "=" + fastPower(a, b, p));
    }
}
