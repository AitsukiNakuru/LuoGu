package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class luogu_P3183 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();   int m = sc.nextInt();
        int result=0;
        /*
        int[][] du = new int[100000][2];
        int[][] dp = new int[100001][20000+1];
        for (int i=1 ; i<=m ; i++) {
            int a = sc.nextInt();   int b = sc.nextInt();
            if (du[a][0]!=0) {
                du[b][0]+=du[a][0];
            }
            else {
                du[b][0]++;
            }
            du[a][1]++;
            dp[a][du[a][1]]=b;
        }
        for (int i=1 ; i<=n ; i++) {
            
        }
        for (int i=1 ; i<=n ; i++) {
            if (du[i][1]==0) {
                System.out.println(du[i][0] + " "+ i);
                result+=du[i][0];
            }
        }

         */

        for (int i=1 ; i<=m ; i++) {
            int a = sc.nextInt();   int b = sc.nextInt();

        }

        System.out.println(result);
    }
}
