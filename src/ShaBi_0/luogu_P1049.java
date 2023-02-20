package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P1049 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int total = sc.nextInt();   int n = sc.nextInt();
        int[] arrary = new int[n+1];
        int[] dp = new int[total+1];
        for (int i=1 ; i<=n ; i++) {
            arrary[i] = sc.nextInt();
        }
        for (int i=1 ; i<=n ; i++) {
            for (int j=total ; j>=arrary[i] ; j--) {
                dp[j] = Math.max(dp[j], dp[j-arrary[i]]+arrary[i]);
            }
        }
        System.out.println(total-dp[total]);
    }
}
