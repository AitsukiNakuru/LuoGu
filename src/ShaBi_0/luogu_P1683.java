package ShaBi_0;

import javax.imageio.IIOException;
import java.io.*;
import java.util.Scanner;

public class luogu_P1683 {
    static char[][] map;
    static int[][] flag;
    static int w, h, count;

    public static void dfs (int x, int y) {
        if (x<0 || y<0 || x>=h || y>=w || flag[x][y]!=0 || map[x][y]=='#') {
            return;
        }
        flag[x][y]=1;
        count++;
        dfs(x+1, y);
        dfs(x-1, y);
        dfs(x, y+1);
        dfs(x, y-1);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
      /*
        st.nextToken();
        w = (int)st.nval;
        //st.nextToken();
        h = (int)st.nval;

     */

        w = sc.nextInt();
        h = sc.nextInt();

        int start_x=0, start_y=0;
        count = 0;
        map = new char[h][w];
        flag = new int[h][w];



        for (int i=0 ; i<h ; i++) {
            map[i] = sc.next().toCharArray();
            for (int j=0 ; j<w ; j++) {
                if (map[i][j]=='@') {
                    start_x = i;
                    start_y = j;
                }
            }
        }

        dfs(start_x, start_y);
        System.out.println(count);
    }

}
