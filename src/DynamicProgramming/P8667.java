package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P8667 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        String[][] arrayStr = new String[3][];
        arrayStr[0] = br.readLine().split(" ");
        arrayStr[1] = br.readLine().split(" ");
        arrayStr[2] = br.readLine().split(" ");
        long[][] dp = new long[3][100000 + 2];

        for (int i = 0; i < n; i++) {
            dp[0][Integer.parseInt(arrayStr[0][i])]++;
            dp[1][Integer.parseInt(arrayStr[1][i])]++;
            dp[2][Integer.parseInt(arrayStr[2][i])]++;
        }

        for (int i = 100000; i >= 0; i--) {
            // dp[0][i] = 数组A中大小为i的元素的个数 * 数组B与数组C中符合条件的组合数 + 数组A中比i大的组合数
//            dp[0][i] = dp[1][i + 1] * dp[0][i] + dp[0][i + 1];
//            dp[1][i] = dp[2][i + 1] * dp[1][i] + dp[1][i + 1];
//            dp[2][i] += dp[2][i + 1];

            dp[2][i] += dp[2][i + 1];
            dp[1][i] = dp[2][i + 1] * dp[1][i] + dp[1][i + 1];
            dp[0][i] = dp[1][i + 1] * dp[0][i] + dp[0][i + 1];
        }
        System.out.println(dp[0][0]);
    }
}
