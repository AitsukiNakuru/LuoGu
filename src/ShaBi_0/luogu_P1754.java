package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P1754 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        long [][][] dp = new long[41][21][21];
        dp[1][1][0]=1;
        for (int i=1 ; i<=2*n ; i++) {
            for (int j=0 ; j<=n ; j++) {
                for (int k=0 ; k<=n ; k++) {
                    if (k+j!=i || k>j) {
                        dp[i][j][k]=0;
                        continue;
                    }
                    if (j==0) {
                        dp[i][j][k]=0;
                        continue;
                    }
                    if (k==0) {
                        dp[i][j][k]=1;
                        continue;
                    }
                    dp[i][j][k] = dp[i-1][j-1][k]+dp[i-1][j][k-1];
                }
            }
        }
        System.out.println(dp[2*n][n][n]);
    }
}
