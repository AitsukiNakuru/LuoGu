package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.PriorityQueue;

public class P1194 {
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

    static int a, b, count = 0;
    static int[] parentList;
    static boolean[] buy;

    static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static PriorityQueue<Edge> edgeList;

    static int find(int node) {
        if (parentList[node] == node) return parentList[node];
        parentList[node] = find(parentList[node]);
        return parentList[node];
    }

    static void union(int parent, int child) {
        parentList[find(child)] = find(parent);
    }

    static int kruskal() {
        int result = 0;
        while (!edgeList.isEmpty()) {
            if (count == b - 1) break;
            Edge edge = edgeList.poll();
            int u = edge.u;
            int v = edge.v;
            int weight = edge.weight;
            if (find(u) != find(v)) {
                union(u, v);
                result += weight;
                count++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        a = Integer.parseInt(str[0]);
        b = Integer.parseInt(str[1]);
        edgeList = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight;
        });
        buy = new boolean[b + 1];
        parentList = new int[b + 1];
        for (int i = 0; i < b; i++) {
            str = bf.readLine().split(" ");
            for (int j = 0; j < b; j++) {
                int num = Integer.parseInt(str[j]);
                if (num == 0 || num > a) {
                    edgeList.offer(new Edge(i, j, a));
                } else {
                    edgeList.offer(new Edge(i, j, num));
                }
            }
        }
        for (int i = 0; i <= b; i++) {
            parentList[i] = i;
        }

        System.out.println(kruskal()+a);
    }
}
