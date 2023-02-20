package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P1387 {
    public static int Min (int a, int b, int c) {
        int result;
        result = Math.min(a, b);
        result = Math.min(result, c);
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();   int m = sc.nextInt();   int result = 0;
        int[][] dp = new int[n+1][m+1];
        for (int i=1 ; i<=n ; i++) {
            for (int j=1 ; j<=m ; j++) {
                if (sc.nextInt() == 1) {
                    dp[i][j] = Min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1;
                    result = Math.max(dp[i][j], result);
                }
            }
        }
        System.out.println(result);
    }
}
