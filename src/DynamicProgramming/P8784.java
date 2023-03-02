package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P8784 {
    static void functionA() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long[][] dp = new long[3][4];
        dp[1][3] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[2][3] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i % 3][3] = dp[(i - 1) % 3][1] % 1000000007 + dp[(i - 1) % 3][2] % 1000000007 + dp[(i - 1) % 3][3] % 1000000007 + dp[(i - 2) % 3][3] % 1000000007;


            dp[i % 3][2] = dp[(i - 1) % 3][1] % 1000000007 + dp[(i - 2) % 3][3] % 1000000007;


            dp[i % 3][1] = dp[(i - 1) % 3][2] % 1000000007 + dp[(i - 2) % 3][3] % 1000000007;



            dp[i % 3][3] %= 1000000007;
            dp[i % 3][2] %= 1000000007;
            dp[i % 3][1] %= 1000000007;
        }
        System.out.println(dp[n % 3][3]);
    }
    static void functionB() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long[][] dp = new long[3][3];
        dp[1][2] = 1;
        dp[2][1] = 2;
        dp[2][2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i % 3][2] = dp[(i - 1) % 3][1] % 1000000007 + dp[(i - 1) % 3][2] % 1000000007 + dp[(i - 2) % 3][2] % 1000000007;


            dp[i % 3][1] = dp[(i - 1) % 3][1] % 1000000007 + dp[(i - 2) % 3][2]*2 % 1000000007;


            dp[i % 3][2] %= 1000000007;
            dp[i % 3][1] %= 1000000007;
        }
        System.out.println(dp[n % 3][2]);
    }
    public static void main(String[] args) throws IOException {
        functionB();
    }
}
