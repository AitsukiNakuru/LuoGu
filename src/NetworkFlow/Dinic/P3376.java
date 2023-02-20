package NetworkFlow.Dinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class P3376 {
    static int n, m, s, t, u, v, w, index = 0;
    static long ans;
    static int[] destinationList, finalEdgeList, lastEdgeList, pathList;
    static long[] weightList, dist;
    static boolean[] visitedList;
    static int[][] flagList;
    static void addEdge(int start, int end, int weight) {
        weightList[index] = weight;
        destinationList[index] = end;
        lastEdgeList[index] = finalEdgeList[start];
        finalEdgeList[start] = index++;
        weightList[index] = 0;
        destinationList[index] = start;
        lastEdgeList[index] = finalEdgeList[end];
        finalEdgeList[end] = index++;
    }
    static boolean bfs() {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(s);
        dist[s] = 0;


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        s = Integer.parseInt(str[2]);
        t = Integer.parseInt(str[3]);
        destinationList = new int[m*2];
        finalEdgeList = new int[n+1];
        lastEdgeList = new int[m*2];
        weightList = new long[m*2];
        pathList = new int[n+1];
        dist = new long[n+1];
        visitedList = new boolean[n+1];
        flagList = new int[n+1][n+1];
        Arrays.fill(finalEdgeList, -1);
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            u = Integer.parseInt(str[0]);
            v = Integer.parseInt(str[1]);
            w = Integer.parseInt(str[2]);
            if (flagList[u][v]!=0) {
                weightList[flagList[u][v]] += w;
            } else {
                addEdge(u, v, w);
                flagList[u][v] = index - 2;
            }
        }

        System.out.println(ans);
    }
}
