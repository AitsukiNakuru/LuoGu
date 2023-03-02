package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P8707 {
    public static void main(String[] args) throws IOException {
        int n, m;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        int[][] dp = new int[n+1][m+1];
        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i%2!=0 || j%2!=0) {
                    dp[i][j] += dp[i][j-1];
                    dp[i][j] += dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
