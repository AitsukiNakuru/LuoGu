package ShaBi_0;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class luogu_P1162 {
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt(), flag=0;
        int start_x=0, start_y=0, temp_x=0, temp_y=0;
        int[][] map = new int[n][n];
        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<n ; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j]==1&&flag==0) {
                    start_x = i;   start_y = j;     flag=1;
                }
            }
        }
        LinkedList<Node> que = new LinkedList<>();
        que.offer(new Node(start_x+1, start_y+1));
        map[start_x+1][start_y+1]=2;
        int[] move_x={1, -1, 0, 0};     int[] move_y={0, 0, 1, -1};

        while (!que.isEmpty()) {
            for (int i=0 ; i<4 ; i++) {
                temp_x = que.peek().x+move_x[i];      temp_y = que.peek().y+move_y[i];
                if (map[temp_x][temp_y]!=2&&temp_x>=0&&temp_x<n&&temp_y>=0&&temp_y<n&&map[temp_x][temp_y]!=1) {
                    que.offer(new Node(temp_x, temp_y));
                    map[temp_x][temp_y]=2;
                }
            }
            que.removeFirst();
        }
        for (int i=0 ; i<n ; i++) {
            System.out.print(map[i][0]);
            for (int j=1 ; j<n ; j++) {
                System.out.print(" "+map[i][j]);
            }
            System.out.println();
        }
    }
}
