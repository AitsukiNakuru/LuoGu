package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P8786 {
    static void functionA() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n, m;
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        int[][][] dp = new int[n + m + 2][n + 2][102];
        dp[0][0][2] = 1;
        for (int i = 1; i <= n + m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= 100; k++) {
                    if (k > n + m - i) continue;
                    dp[i][j][k] += dp[i - 1][j][k + 1] % 1000000007;
                    if (j >= 1 && k % 2 == 0) {
                        dp[i][j][k] += dp[i - 1][j - 1][k / 2] % 1000000007;
                    }
                    dp[i][j][k] %= 1000000007;
                }
            }
        }
        System.out.println(dp[n + m - 1][n][1]);
    }

    public static void main(String[] args) throws IOException {
        functionA();
    }
}
