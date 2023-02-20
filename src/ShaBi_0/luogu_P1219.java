package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P1219 {
    public static int[] d0 = new int [30];
    public static int[] d1 = new int [30];
    public static int[] d2 = new int[30];
    public  static int n;
    public static int flag = 0;
    public static int count = 0;
    public static int[] ans = new int [30];
    static void print() {
        System.out.print(ans[0]);
        for (int i=1 ; i<n ; i++) {
            System.out.printf(" "+ans[i]);
        }
        System.out.println();
    }
    static void dfs (int a) {
        if (flag>=3&&a==n) {
            count++;
            return;
        }
        if (flag<3&&a==n) {
            print();
            flag++;
            count++;
            return;
        }
        int i, j, k, l, b;
        for (b=0 ; b<n ; b++) {
            if (d0[b]==0&&d1[a+b]==0&&d2[a-b+n]==0) {
                d0[b]=1; d1[a+b]=1; d2[a-b+n]=1; ans[a]=b+1;
                dfs(a+1);
                d0[b]=0; d1[a+b]=0; d2[a-b+n]=0; ans[a]=0;
            }
        }
        return;
    }

    public static void main (String args[]) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        n = sc.nextInt();
        dfs(0);
        System.out.print(count);
    }
}
