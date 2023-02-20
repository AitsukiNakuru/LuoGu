package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P1048 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int T = sc.nextInt();   int M = sc.nextInt();
        int[] dp = new int[T+1];
        int[][] arr = new int[M][2];
        for (int i=0 ; i<M ; i++) {
            arr[i][0] = sc.nextInt();   arr[i][1] = sc.nextInt();
        }
        for (int i=0 ; i<M ; i++) {
            for (int j=T ; j>=arr[i][0] ; j--) {
                dp[j] = Math.max(dp[j], dp[j-arr[i][0]]+arr[i][1]);
            }
        }
        System.out.println(dp[T]);
    }
}
