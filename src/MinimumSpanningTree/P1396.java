package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P1396 {
    static int MAX_INT = 2147483647;
    static int MIN_INT = -2147483648;
    static int parseInt(String str) {
        return Integer.parseInt(str);
    }
    static double parseDouble(String str) {
        return Double.parseDouble(str);
    }
    static double getDouble(double d,int b) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(b);
        return Double.parseDouble(nf.format(d));
    }
    static int n, m, s, t, index = 0;
    static int[] endList, lastEdgeList, finalEdgeList, weightList, dist;
    static boolean[] visitedList;
    static void add(int u, int v, int weight) {
        endList[index] = v;
        lastEdgeList[index] = finalEdgeList[u];
        weightList[index] = weight;
        finalEdgeList[u] = index++;
    }
    static class Node {
        int index, distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
    static int dijkstra() {
        PriorityQueue<Node> accessibleQueue = new PriorityQueue<Node>((o1, o2) -> {
            return o1.distance - o2.distance;
        });
        Arrays.fill(visitedList, false);
        Arrays.fill(dist, MAX_INT);
        dist[s] = 0;
        accessibleQueue.offer(new Node(s, 0));
        while (!accessibleQueue.isEmpty()) {
            Node node = accessibleQueue.poll();
            int nodeIndex = node.index;
            int nodeDistance = node.distance;
            for (int i = finalEdgeList[nodeIndex]; i != -1; i = lastEdgeList[i]) {
                int endNode = endList[i];
                if (Math.max(dist[nodeIndex], weightList[i]) < dist[endNode]) {
                    dist[endNode] = Math.max(dist[nodeIndex], weightList[i]);
                    accessibleQueue.add(new Node(endNode, dist[endNode]));
                }
            }
        }
        return dist[t];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        s = Integer.parseInt(str[2]);
        t = Integer.parseInt(str[3]);
        weightList = new int[2*m+1];
        endList = new int[2*m+1];
        lastEdgeList = new int[2*m+1];
        finalEdgeList = new int[n+1];
        visitedList = new boolean[n+1];
        dist = new int[n+1];
        Arrays.fill(finalEdgeList, -1);
        for (int i = 0; i < m; i++) {
            str = bf.readLine().split(" ");
            int u = Integer.parseInt(str[0]);
            int v = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            add(u, v, w);
            add(v, u, w);
        }

        System.out.println(dijkstra());
    }
}
