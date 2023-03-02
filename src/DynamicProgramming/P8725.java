package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 此题可以使用二维数组，与P8786不同的是，在此题中可以根据当前时间和花费体力算出是当前与峡谷的距离
// P8786中无法更具时间和遇到的店数得出当前的剩余酒数，所以需要使用三维数组，增加一个维度来记录当前的酒数
public class P8725 {
    static void functionA() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int d = Integer.parseInt(str[0]);
        int t = Integer.parseInt(str[1]);
        int m = Integer.parseInt(str[2]);
        int[][][] dp = new int[t+1][m+2][d+m+2];
        // dp[i][j][k] = 过了i秒，剩余j点体力，距离峡谷k米
        dp[0][m][d] = 1;
        for (int i = 1; i <= t; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 1; k <= d + (m); k++) {
                    dp[i][j][k] += dp[i-1][j+1][k-1]%1000000007;
                    dp[i][j][k] += dp[i-1][j][k+1]%1000000007;
                    dp[i][j][k] %=1000000007;
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= d + m; i++) {
            count+= dp[t][0][i];
            count%=1000000007;
        }
        System.out.println(count);
    }
    static void functionB() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int d = Integer.parseInt(str[0]);
        int t = Integer.parseInt(str[1]);
        int m = Integer.parseInt(str[2]);
        // dp[i][j] = 第i秒剩余j点体力
        int[][] dp = new int[t+2][m+2];
        dp[0][m] = 1;
        for (int i = 1; i <= t; i++) {
            for (int j = 0; j <= m; j++) {
                if (d + (m - j) - (i - (m - j)) <= 0) continue;
                dp[i][j] += dp[i-1][j+1]%1000000007;
                dp[i][j] += dp[i-1][j]%1000000007;
                dp[i][j] %=1000000007;
            }
        }
        System.out.println(dp[t][0]);
    }
    public static void main(String[] args) throws IOException {
        functionB();
    }
}
