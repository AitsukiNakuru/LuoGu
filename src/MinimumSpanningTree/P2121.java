package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P2121 {
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
    static int n, m, k;
    static int[] parentList;
    static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
    static int find(int node) {
        if (parentList[node] == node) return parentList[node];
        parentList[node] = find(parentList[node]);
        return parentList[node];
    }
    static void union(int parent, int child) {
        parentList[find(parent)] = find(child);
    }
    static PriorityQueue<Edge> edgeList;
    static int kruskal() {
        int count = 0;
        int result = 0;
        while (!edgeList.isEmpty()) {
            if (count == k) return result;
            Edge edge = edgeList.poll();
            int u = edge.u;
            int v = edge.v;
            int weight = edge.weight;
            if (find(u) != find(v)) {
                union(u, v);
                result+= weight;
                count++;
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);
        parentList = new int[n+1];
        edgeList = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        for (int i = 0; i < m; i++) {
            str = bf.readLine().split(" ");
            int u = Integer.parseInt(str[0]);
            int v = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            edgeList.offer(new Edge(u, v, w));
        }
        for (int i = 1; i <= n; i++) {
            parentList[i] = i;
        }
        System.out.println(kruskal());
    }
}
