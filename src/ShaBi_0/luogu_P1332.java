package ShaBi_0;
import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class luogu_P1332 {
    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n, m, a, b;
        n = sc.nextInt();   m = sc.nextInt();   a = sc.nextInt();   b = sc.nextInt();
        int[][] lord = new int[b+1][2];   int[][] visit = new int[n+1][m+1];
        int[] move_x = new int[]{1, -1, 0, 0};      int[] move_y = new int[]{0, 0, 1, -1};
        int temp_x, temp_y;
        LinkedList<Node> que = new LinkedList<>();
        for (int i=1 ; i<=a ; i++) {
            int c = sc.nextInt();    int d = sc.nextInt();
            que.offer(new Node(c, d, 1));
            visit[c][d]=1;
        }


        for (int i=1 ; i<=b ; i++) {
            lord[i][0] = sc.nextInt();  lord[i][1] = sc.nextInt();
        }

        while (!que.isEmpty()) {
            for (int i=0 ; i<4 ; i++) {
                temp_x = que.peek().x+move_x[i];    temp_y = que.peek().y+move_y[i];
                if (temp_x>=0&&temp_x<=n&&temp_y>=0&&temp_y<=m&&visit[temp_x][temp_y]==0){
                    que.offer(new Node(temp_x, temp_y, que.peek().time+1));

                    visit[temp_x][temp_y]=que.peek().time+1;
                }
            }
            que.removeFirst();
        }
        for (int i=1 ; i<=b ; i++) {
            System.out.println(visit[lord[i][0]][lord[i][1]]-1);
        }
    }
}
