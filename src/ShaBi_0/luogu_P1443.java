package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Scanner;

    public class luogu_P1443 {
    static class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    public static void main(String[] args) {
        int n, m, x, y;
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        n = sc.nextInt();   m = sc.nextInt();   x = sc.nextInt();   y = sc.nextInt();
        int[][] visit = new int[n][m];
        x=x-1;   y=y-1;
        LinkedList<Node> que = new LinkedList<>();
        que.offer(new Node(x, y, 0));
        visit[x][y]=1;
        int[] move_x = {1, 1, -1, -1, 2, 2, -2, -2};    int[] move_y = {2, -2, 2, -2, 1, -1, 1, -1};
        while (!que.isEmpty()) {
            for (int i=0 ; i<8 ; i++) {
                int temp_x = que.peek().x+move_x[i];    int temp_y = que.peek().y+move_y[i];
                if (temp_x>=0&&temp_x<n&&temp_y>=0&&temp_y<m&&visit[temp_x][temp_y]==0) {
                    que.offer(new Node(temp_x, temp_y, que.peek().step+1));
                    visit[temp_x][temp_y] = que.peek().step+1;
                }
            }
            que.removeFirst();
        }

        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<m ; j++) {
                if (i==x&&j==y) {
                    System.out.printf("%-5d", 0);
                    continue;
                }
                if (visit[i][j]==0) {
                    System.out.printf("%-5d", -1);
                }
                else {
                    System.out.printf("%-5d", visit[i][j]);
                }
           }
            System.out.println();
        }
    }
}
