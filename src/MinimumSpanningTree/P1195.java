package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.PriorityQueue;

public class P1195 {
    static int MAX_INT = 2147483647;
    static int MIN_INT = -2147483648;

    static int parseInt(String str) {
        return Integer.parseInt(str);
    }

    static double parseDouble(String str) {
        return Double.parseDouble(str);
    }

    static double getDouble(double d, int b) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(b);
        return Double.parseDouble(nf.format(d));
    }

    static int n, m, k;

    static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static PriorityQueue<Edge> edgeList = new PriorityQueue<>((o1, o2) -> {
        return o1.weight - o2.weight;
    });
    static int[] parentList;

    static int find(int node) {
        if (parentList[node] == node) return parentList[node];
        parentList[node] = find(parentList[node]);
        return parentList[node];
    }

    static void union(int parent, int child) {
        parentList[find(child)] = find(parent);
    }
    static int kruskal() {
        int count = 0;
        int result = 0;
        while (!edgeList.isEmpty()) {
            if (count >= n - k) break;
            Edge edge = edgeList.poll();
            int u = edge.u;
            int v = edge.v;
            int weight = edge.weight;
            if (find(u) != find(v)) {
                union(u, v);
                count++;
                result+=weight;
            }
        }
        if (count >= n - k) {
            return result;
        } else {
            return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);
        parentList = new int[n + 1];
        for (int i = 0; i < m; i++) {
            str = bf.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            int l = Integer.parseInt(str[2]);
            edgeList.offer(new Edge(x, y, l));
        }
        for (int i = 0; i <= n; i++) {
            parentList[i] = i;
        }
        int result = kruskal();
        if (result == -1) {
            System.out.println("No Answer");
        } else {
            System.out.println(result);
        }
    }
}
