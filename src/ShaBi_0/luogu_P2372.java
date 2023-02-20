package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P2372 {
    static char[][] map;
    static int m, n, x, y, count;
    static int[][] flag;
    public static void find (int x, int y) {
        if (x<0 || y<0 || x>=m || y>=n) {
            count++;
            return;
        }
        if (map[x][y]=='.') {
            count++;
        }
    }
    public static void dfs (int x, int y) {
        if (x<0 || y<0 || x>=m || y>=n || map[x][y]=='.') {
            return;
        }
        if (flag[x][y]==1) {
            return;
        }
        flag[x][y]=1;
        find(x+1, y);
        find(x-1, y);
        find(x, y+1);
        find(x, y-1);

        dfs (x+1, y);
        dfs(x-1, y);
        dfs(x, y+1);
        dfs(x, y-1);
        dfs(x+1, y+1);
        dfs(x+1, y-1);
        dfs(x-1, y+1);
        dfs(x-1, y-1);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        m = sc.nextInt();
        n = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();

        map = new char[m][n];
        flag = new int[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = sc.next().toCharArray();
        }

        count = 0;
        dfs(x-1, y-1);
        System.out.println(count);
    }
}
