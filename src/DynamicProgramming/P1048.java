package DynamicProgramming;

import java.util.*;

public class P1048 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int m = sc.nextInt();
        int[][] herbal = new int[2][m+1];
        for (int i = 1; i <= m; i++) {
            herbal[0][i] = sc.nextInt();
            herbal[1][i] = sc.nextInt();
        }
        int[] dp = new int[t+1];
        dp[0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = t; j >= 1; j--) {
                if (j-herbal[0][i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j-herbal[0][i]] + herbal[1][i]);
                }
            }
        }
        System.out.println(dp[t]);
    }
}
