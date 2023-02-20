package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class luogu_P1746 {
    static class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    public static void main(String[] args) {
        int n, x1, y1, x2, y2;
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        n = sc.nextInt();
        char[][] map = new char[n+1][n+1];
        int[][] visit = new int[n+1][n+1];
        for (int i=0 ; i<n ; i++) {
           map[i] = sc.next().toCharArray();
        }
        x1 = sc.nextInt()-1;      y1 = sc.nextInt()-1;      x2 = sc.nextInt()-1;      y2 = sc.nextInt()-1;
        LinkedList<Node> que = new LinkedList<>();
        que.offer(new Node(x1, y1, 0));
        visit[x1][y1] = 0;
        int[] move_x = new int[]{1, -1, 0, 0};      int[] move_y = new int[]{0, 0, 1, -1};
        int temp_x = 0, temp_y = 0;
        while (!que.isEmpty()) {
            for (int i=0 ; i<4 ; i++) {
                temp_x = que.peek().x+move_x[i];        temp_y = que.peek().y+move_y[i];
                if (temp_x>=0 && temp_x<n && temp_y>=0 && temp_y<n && map[temp_x][temp_y]=='0' && visit[temp_x][temp_y]==0) {
                    que.offer(new Node(temp_x, temp_y, que.peek().step+1));
                    visit[temp_x][temp_y] = 1;
                }
                if (temp_x==x2 && temp_y==y2) {
                    System.out.println(que.peek().step+1);
                    return;
                }
            }
            que.removeFirst();
        }
    }
}
