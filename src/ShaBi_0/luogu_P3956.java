package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class luogu_P3956 {
    static class Node {
        int x, y, color, cost, used;

        public Node(int x, int y, int color, int cost, int used) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.cost = cost;
            this.used = used;
        }
    }
    static int[][] map, visit;
    static int m, n;
    public static int jud (int color_1, int color_2) {
        if (color_1==color_2) {
            return 1;
        }
        if (color_1==1) {
            if (color_2==0) {
                return 10;// color_1=red        color_2=null
            }
            if (color_2==2) {
                return 12;// color_1=red        color_2=yellow
            }
        }
        if (color_1==2) {
            if (color_2==0) {
                return 20;// color_1=yellow     color_2=null
            }
            if (color_2==1) {
                return 21;// color_1=yellow     color_2=red;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        m = sc.nextInt();   n = sc.nextInt();
        map = new int[m+1][m+1];        visit = new int[m+1][m+1];
        for (int i=0 ; i<n ; i++) {
            map[sc.nextInt()][sc.nextInt()]=sc.nextInt()+1;//2=yellow       1=red       0=null
        }
        LinkedList<Node> que = new LinkedList<>();
        que.offer(new Node(1, 1, map[1][1], 0, 0));
        visit[1][1]=1;
        int temp_x, temp_y, result=999999999;
        int[] move_x = new int[]{1, -1, 0, 0};      int[] move_y = new int[]{0, 0, 1, -1};
        while (!que.isEmpty()) {
            for (int i=0 ; i<4 ; i++) {
                temp_x = que.peek().x+move_x[i];        temp_y = que.peek().y+move_y[i];
                if (temp_x>=1 && temp_x<=m && temp_y>=1 && temp_y<=m && visit[temp_x][temp_y]==0) {
                    if ((jud(que.peek().color, map[temp_x][temp_y])==12||jud(que.peek().color, map[temp_x][temp_y])==21)) {
                        que.offer(new Node(temp_x, temp_y, map[temp_x][temp_y], que.peek().cost+1, 0));
                        visit[temp_x][temp_y]++;
                    }
                    if ((jud(que.peek().color, map[temp_x][temp_y])==10||jud(que.peek().color, map[temp_x][temp_y])==20)&&que.peek().used==0) {
                        que.offer(new Node(temp_x, temp_y, que.peek().color, que.peek().cost+2, 1));
                        visit[temp_x][temp_y]++;
                    }

                    if (jud(que.peek().color, map[temp_x][temp_y])==1) {
                        que.offer(new Node(temp_x, temp_y, map[temp_x][temp_y], que.peek().cost, 0));
                        visit[temp_x][temp_y]++;
                    }
                    System.out.println(temp_x+"  "+temp_y);
                }
                if (que.getLast().x==m&&que.getLast().y==m&&result>que.getLast().cost) {
                    result=que.getLast().cost;
                }
            }
            que.removeFirst();
        }
        if (visit[m][m]==0) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
        }
    }
}
