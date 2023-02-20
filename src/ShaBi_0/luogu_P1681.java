package ShaBi_0;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class luogu_P1681 {
    static class Read{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        public int nextInt() throws Exception{
            st.nextToken();
            return (int)st.nval;
        }
    }

    public static int Min (int a, int b, int c) {
        int result;
        result = Math.min(a, b);
        result = Math.min(result, c);
        return result;
    }
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(new BufferedInputStream(System.in));
        Read sc = new Read();
        int n = sc.nextInt();   int m = sc.nextInt();   int result=0;
        int[][] map = new int[2][m+1];    int[][] dp = new int[2][m+1];
        int a=0 , b=1 ;
        for (int i=1 ; i<=n ; i++) {
            for (int j=1 ; j<=m ; j++) {
                if (i%2==1) {
                    map[a][j] = sc.nextInt();
                    dp[a][j] = 1;
                    if (map[a][j]!=map[a][j-1] && map[a][j]!=map[b][j]) {
                        dp[a][j] = Min(dp[b][j-1], dp[b][j], dp[a][j-1])+1;
                        result = Math.max(dp[a][j], result);
                    }
                }
                else {
                    map[b][j] = sc.nextInt();
                    dp[b][j] = 1;
                    if (map[b][j]!=map[b][j-1] && map[b][j]!=map[a][j]) {
                        dp[b][j] = Min(dp[b][j-1], dp[a][j], dp[a][j-1])+1;
                        result = Math.max(dp[b][j], result);
                    }
                }
            }
        }
        System.out.println(result);
    }
}
