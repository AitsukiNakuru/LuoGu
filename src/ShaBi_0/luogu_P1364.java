package ShaBi_0;
import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
    public class luogu_P1364 {
        /*
        public static void main(String[] args) {
            Scanner sc = new Scanner(new BufferedInputStream(System.in));

            int[] w, u, v;
            w = new int[105];   u = new int[105];   v = new int[105];
            int result = 0;     int n = sc.nextInt();
            int[][] map  = new int[105][105];
            for (int i=1 ; i<=n ; i++) {
                w[i] = sc.nextInt();    u[i] = sc.nextInt();    v[i] = sc.nextInt();
            }
            for (int i=1 ; i<=n ; i++) {
                for (int j=1 ; j<=n ; j++) {
                    map[i][j]=999999999;
                }
            }
            for (int i=1 ; i<=n ; i++) {
                map[i][i]=0;
                if (u[i]!=0) {
                    map[i][u[i]]=1;
                    map[u[i]][i]=1;
                }
                if (v[i]!=0) {
                    map[i][v[i]]=1;
                    map[v[i]][i]=1;
                }

            }
            for (int k=1 ; k<=n ; k++) {
                for (int i=1 ; i<=n ; i++) {
                    for (int j=1 ; j<=n ; j++) {
                        if (map[i][j]>map[i][k]+map[k][j]&&i!=k&&k!=j&&i!=j) {
                            map[i][j]=map[i][k]+map[k][j];
                        }
                    }
                }
            }
            int min=999999999;
            for (int i=1 ; i<=n ; i++) {
                result=0;
                for (int j=1 ; j<=n ; j++) {
                    result+=w[j]*map[j][i];
                }
                //System.out.println(result);
                if (result<min) {
                    min=result;
                }
            }
            System.out.println(min);
        }
         */



        static class Node {
            int weight, step, num;

            public Node(int weight, int step, int num) {
                this.weight = weight;
                this.step = step;
                this.num = num;
            }
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(new BufferedInputStream(System.in));

            int[] w, u, v;
            w = new int[105];   u = new int[105];   v = new int[105];
            int result = 0;     int n = sc.nextInt();
            int[][] map  = new int[105][105];
            int[] visit = new int[105];
            for (int i=1 ; i<=n ; i++) {
                w[i] = sc.nextInt();    u[i] = sc.nextInt();    v[i] = sc.nextInt();
            }
            for (int i=1 ; i<=n ; i++) {
                for (int j=1 ; j<=n ; j++) {
                    map[i][j]=999999999;
                }
            }
            for (int i=1 ; i<=n ; i++) {
                map[i][i]=0;
                if (u[i]!=0) {
                    map[i][u[i]]=1;
                    map[u[i]][i]=1;
                }
                if (v[i]!=0) {
                    map[i][v[i]]=1;
                    map[v[i]][i]=1;
                }
            }
            int min=999999999;
            LinkedList<Node> que = new LinkedList<>();
            for (int i=1 ; i<=n ; i++) {
                que.clear();
                Arrays.fill(visit, 0);
                result=0;
                que.offer(new Node(w[i], 0, i));
                visit[i]=1;
                while (!que.isEmpty()) {
                    for (int j=1 ; j<=n ; j++) {
                        if (map[que.peek().num][j]==1&&que.peek().num!=j&&visit[j]==0) {
                            que.offer(new Node(w[j], que.peek().step+1, j));
                            result+=w[j]*(que.peek().step+1);
                            visit[j]=1;
                        }
                    }
                    que.removeFirst();
                }
                if (min>result) {
                    min=result;
                }
            }
            System.out.println(min);
        }
    }
