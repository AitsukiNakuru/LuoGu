package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P1796 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int N = sc.nextInt();   int J=0;
        int[][] dp = new int[N+1][101];
        for (int i=1 ; i<=N ; i++) {
            for (int j=0 ; j<=100 ; j++) {
                dp[i][j]=999999999;
            }
        }
        for (int i=1 ; i<=N ; i++) {
            J = sc.nextInt();
            for (int j = 1 ; j<=J ; j++) {
                while (true) {
                    int a = sc.nextInt();
                    if (a==0) break;
                    int b = sc.nextInt();
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][a]+b);
                }
            }
        }
        int result = 99999999;
        for (int i=1 ; i<=J ; i++) {
            result = Math.min(result, dp[N][i]);
        }
        System.out.println(result);
    }
}
