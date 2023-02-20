

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;


public class Test {
    static int MAX_INT = 2147483647;
    static int n, m, s, t, u, v, index = 0;
    static long ans, w;
    static long[] dist, weightList;

    static int[] destinationList, lastEdgeList, finalEdgeList, pathList;
    static boolean[] visitedList;
    static int[][] flag;

    static void addEdge(int start, int end, long weight) {
        destinationList[index] = end;
        weightList[index] = weight;
        lastEdgeList[index] = finalEdgeList[start];
        finalEdgeList[start] = index++;

        destinationList[index] = start;
        weightList[index] = 0;
        lastEdgeList[index] = finalEdgeList[end];
        finalEdgeList[end] = index++;
    }

    static boolean bfs() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(s);
        Arrays.fill(visitedList, false);
        visitedList[s] = true;
        dist[s] = MAX_INT;
        while(!queue.isEmpty()) {
            int nowNode = queue.poll();

            for (int i = finalEdgeList[nowNode]; i != -1; i = lastEdgeList[i]) {
                if (weightList[i] == 0) continue;
                int destination = destinationList[i];
                if (visitedList[destination]) continue;
                dist[destination] = Math.min(dist[nowNode], weightList[i]);
                pathList[destination] = i;
                queue.add(destination);
                visitedList[destination] = true;
                if (destination == t) return true;
            }
        }
        return false;
    }

    static void update() {
        int nowNode = t;
        while(nowNode != s) {
            int lastEdge = pathList[nowNode];
            weightList[lastEdge] -= dist[t];
            weightList[lastEdge ^ 1] += dist[t];
            nowNode = destinationList[lastEdge ^ 1];
        }
        ans += dist[t];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        s = Integer.parseInt(str[2]);
        t = Integer.parseInt(str[3]);

        destinationList = new int[m * 2];
        lastEdgeList = new int[m * 2];
        finalEdgeList = new int[n+1];
        weightList = new long[m * 2];
        dist = new long[n+1];
        visitedList = new boolean[n+1];
        flag = new int[n+1][n+1];
        pathList = new int[n+1];
        Arrays.fill(finalEdgeList, -1);
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            u = Integer.parseInt(str[0]);
            v = Integer.parseInt(str[1]);
            w = Integer.parseInt(str[2]);
            if (flag[u][v] != 0) {
                weightList[flag[u][v] - 2] += w;

            } else {
                addEdge(u, v, w);
                flag[u][v] = index;
            }
        }

        while(bfs()) {
            update();
        }
        System.out.println(ans);
    }

}
