package ShaBi_0;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class luogu_P1141 {

    static int n, m, temp[][];
    static int[] xx = { 0, 0, 1, -1 };
    static int[] yy = { 1, -1, 0, 0 };
    static int[][] vis;
    static char[][] arr;
    static int[] resutl;
    static class Node {
        int x, y, number;

        public Node(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }
    public static void bfs(int x, int y, int num, int f) {
        LinkedList<Node> que = new LinkedList<>();
        que.offer(new Node(x, y, num));
        resutl[f]++;
        vis[x][y]=f;
        while (!que.isEmpty()) {

            int temp_x, temp_y;
            for (int i=0 ; i<4 ; i++) {
                temp_x = que.peek().x+xx[i];   temp_y = que.peek().y+yy[i];
                if (temp_x>=0&&temp_x<n&&temp_y>=0&&temp_y<n&&vis[temp_x][temp_y]==0&&arr[temp_x][temp_y]!=arr[que.peek().x][que.peek().y]) {
                    que.offer(new Node(temp_x, temp_y, arr[temp_x][temp_y]));
                    vis[temp_x][temp_y]=f;
                    resutl[f]++;
                }
            }
            que.removeFirst();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        PrintWriter pw = new PrintWriter(System.out);
        st.nextToken();
        n = (int)st.nval;
        st.nextToken();
        m = (int)st.nval;
        arr = new char[n][n];
        temp = new int[n][n];
        vis = new int[n][n];
        resutl = new int[m+1];
        br.readLine();
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        for (int i = 1; i <= m; i++) {
            st.nextToken();
            int x = (int)st.nval;
            st.nextToken();
            int y = (int)st.nval;
            if (vis[x - 1][y - 1]==0) {
                bfs(x - 1, y - 1, arr[x-1][y-1], i);
            }
            else {
                resutl[i]=resutl[vis[x-1][y-1]];
            }
            pw.println(resutl[i]);
        }
        pw.flush();
    }


}
