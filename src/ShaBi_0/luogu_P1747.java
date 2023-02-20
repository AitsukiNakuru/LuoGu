package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class luogu_P1747 {
    static class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int x1 = sc.nextInt();  int y1 = sc.nextInt();  int x2 = sc.nextInt();  int y2 = sc.nextInt();
        int[][] visit1 = new int[24][24];   int[][] visit2 = new int[24][24];
        int[] move_x = new int[]{2,2,2,2,1,1,-1,-1,-2,-2,-2,-2};
        int[] move_y = new int[]{-2,-1,1,2,-2,2,-2,2,-2,-1,1,2};
        LinkedList<Node> que1 = new LinkedList<>();
        que1.offer(new Node(x1, y1, 0));
        visit1[x1][y1] = 1;
        int flag1=1, flag2=1;
        while (!que1.isEmpty()&&flag1==1) {
            int temp_x, temp_y;
            for (int i=0 ; i<12 ; i++) {
                temp_x = que1.peek().x+move_x[i];   temp_y = que1.peek().y+move_y[i];
                if (temp_x>0&&temp_x<24&&temp_y>0&&temp_y<24&&visit1[temp_x][temp_y]==0) {
                    que1.offer(new Node(temp_x, temp_y, que1.peek().step+1));
                    visit1[temp_x][temp_y]=1;
                }
                if (temp_x==1&&temp_y==1) {
                    flag1=0;
                    break;
                }
            }
            if (flag1==0) break;
            que1.removeFirst();
        }
        System.out.println(que1.peek().step+1);
        que1.clear();
        LinkedList<Node> que2 = new LinkedList<>();
        que2.offer(new Node(x2, y2, 0));
        visit2[x2][y2] = 1;
        while (!que2.isEmpty()&&flag2==1) {
            int temp_x, temp_y;
            for (int i=0 ; i<12 ; i++) {
                temp_x = que2.peek().x+move_x[i];   temp_y = que2.peek().y+move_y[i];
                if (temp_x>0&&temp_x<24&&temp_y>0&&temp_y<24&&visit2[temp_x][temp_y]==0) {
                    que2.offer(new Node(temp_x, temp_y, que2.peek().step+1));
                    visit2[temp_x][temp_y]=1;
                }
                if (temp_x==1&&temp_y==1) {
                    flag2=0;
                    break;
                }
            }
            if (flag2==0) break;
            que2.removeFirst();
        }
        System.out.println(que2.peek().step+1);
    }
}
