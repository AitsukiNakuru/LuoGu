package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1119 {
    static int n, m, q, index = 0, nowTime = 0;
    static int[] villageList;
    static int[][] distanceMatrix;
    static void addEdge(int start, int end, int weight) {
        distanceMatrix[start][end] = weight;
        distanceMatrix[end][start] = weight;
    }
    static void floyd(int k) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distanceMatrix[i][k] != Integer.MAX_VALUE && distanceMatrix[j][k] != Integer.MAX_VALUE) {
                    distanceMatrix[i][j] = Math.min(distanceMatrix[i][j], distanceMatrix[i][k] + distanceMatrix[k][j]);
                    distanceMatrix[j][i] = Math.min(distanceMatrix[j][i], distanceMatrix[j][k] + distanceMatrix[k][i]);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        villageList = new int[n+1];
        distanceMatrix = new int[n][n];
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            villageList[i] = Integer.parseInt(str[i]);
            for (int j = 0; j < n; j++) {
                distanceMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }
        str = br.readLine().split(" ");
        q = Integer.parseInt(str[0]);

        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            int time = Integer.parseInt(str[2]);
            while (nowTime < n && villageList[nowTime] <= time) {
                floyd(nowTime++);
            }
            if (time < villageList[start] || time < villageList[end] || distanceMatrix[start][end] == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(distanceMatrix[start][end]);
            }
        }
    }
}
