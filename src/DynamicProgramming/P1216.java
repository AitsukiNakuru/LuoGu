package DynamicProgramming;

import java.util.*;

public class P1216 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int[][] dp = new int[2][r+1];
        for (int i = 0; i <= r; i++) {
            dp[0][i] = 0;
            dp[1][i] = 0;
        }

        for (int i = 1 ; i <= r ; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i%2][j] = Math.max(dp[(i+1)%2][j-1], dp[(i+1)%2][j]) + sc.nextInt();
            }
        }
        int max = 0;
        for (int i = 0; i <= r; i++) {
            max = Math.max(dp[r%2][i], max);
        }
        System.out.println(max);
    }
}
