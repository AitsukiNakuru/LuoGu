package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class luogu_P2360 {
    static class Node {
        int z, x, y, step;

        public Node(int z, int x, int y, int step) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int l, r, c, start_x = 0, start_y = 0, start_z = 0, end_x = 0, end_y = 0, end_z = 0;
        l = sc.nextInt();   r = sc.nextInt();   c = sc.nextInt();
        char[][][] map = new char[l][r][c];     char[][][] visit = new char[l][r][c];
        for (int i=0 ; i<l ; i++) {
            for (int j=0 ; j<r ; j++) {
                map[i][j] = sc.next().toCharArray();
                for (int k=0 ; k<c ; k++) {
                    if (map[i][j][k]=='S') {
                        start_z = i;    start_x = j;    start_y = k;
                    }
                    if (map[i][j][k]=='E') {
                        end_z = i;      end_x = j;      end_y = k;
                    }
                }
            }
        }
        int[] move_x = new int[]{1, -1, 0, 0, 0, 0};    int[] move_y = new int[]{0, 0, 1, -1, 0, 0};    int[] move_z = new int[]{0, 0, 0, 0, 1, -1};
        int temp_x = 0, temp_y = 0, temp_z = 0;
        LinkedList<Node> que = new LinkedList<>();
        que.offer(new Node(start_z, start_x, start_y, 0));
        visit[start_z][start_x][start_y]=1;
        while (!que.isEmpty()) {
            for (int i = 0 ; i<6 ; i++) {
                temp_z = que.peek().z+move_z[i];    temp_x = que.peek().x+move_x[i];    temp_y = que.peek().y+move_y[i];
                if (temp_z>=0 && temp_z<l && temp_x>=0 && temp_x<r && temp_y>=0 && temp_y<c && map[temp_z][temp_x][temp_y]=='.' && visit[temp_z][temp_x][temp_y]==0) {
                    que.offer(new Node(temp_z, temp_x, temp_y, que.peek().step+1));
                    visit[temp_z][temp_x][temp_y]=1;
                }
                if (temp_z==end_z && temp_x==end_x && temp_y==end_y) {
                    System.out.println("Escaped in "+(que.peek().step+1)+" minute(s).");     return;
                }
            }
            que.removeFirst();
        }
        System.out.println("Trapped!");
    }
}
