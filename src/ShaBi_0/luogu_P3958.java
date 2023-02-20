package ShaBi_0;
import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class luogu_P3958 {
    static class Node {
        int x, y, z;
        int num;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Node(int x, int y, int z, int num) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.num = num;
        }
    }
    public static double Distance (Node n1, Node n2 ) {
        return sqrt(pow((n1.x-n2.x), 2)+pow((n1.y-n2.y), 2)+pow((n1.z-n2.z), 2));
    }
    public static void ShaBi (Scanner sc) {
        int[][] dong = new int[3][1005];
        LinkedList<Node> que = new LinkedList<>();
        int[][] reach = new int[1005][1005];
        int[] visit = new int[1005];
        int n, h, r;
        n = sc.nextInt();   h = sc.nextInt();   r = sc.nextInt();
        int flag=0;
        for (int i=1 ; i<=n ; i++) {
            dong[0][i] = sc.nextInt();  dong[1][i] = sc.nextInt();  dong[2][i] = sc.nextInt();
        }

        for (int l=1 ; l<=n ; l++) {
            if (visit[l]==0&&dong[2][l]-r<=0) {
                que.clear();
                que.offer(new Node(dong[0][l], dong[1][l], dong[2][l], l));
                visit[l]=1;
                if (dong[2][l]+r>=h) {
                    flag++; break;
                }
                while (!que.isEmpty()&&flag==0) {
                    for (int i=1 ; i<=n ; i++) {
                        if (visit[i]==0&&Distance(que.peek(), new Node(dong[0][i], dong[1][i], dong[2][i]))<=2*r) {
                            if (dong[2][i]+r>=h) {
                                flag++; break;
                            }
                            que.offer(new Node(dong[0][i], dong[1][i], dong[2][i], i));
                            visit[i]=1;
                        }
                    }
                    que.removeFirst();
                }
            }
            if (flag>=1) {
                break;
            }
        }


        if (flag>=1) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }




    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();

        for (int i=1 ; i<=t ; i++) {
            ShaBi(sc);
        }
    }
}