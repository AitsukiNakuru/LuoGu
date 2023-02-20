package MinimumSpanningTree.Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P3366 {
    static int MAX_INT = 2147483647;
    static int MIN_INT = -2147483648;

    static int parseInt(String str) {
        return Integer.parseInt(str);
    }

    static int[] fillArrayAsc(int[] array, int n) {
        array = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            array[i] = i;
        }
        return array;
    }

    static int[] fillArrayDec(int[] array, int n) {
        array = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            array[i] = n - i;
        }
        return array;
    }

    static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static int n, m;
    static PriorityQueue<Edge> edgeQueue;
    static int[] nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = parseInt(str[0]);
        m = parseInt(str[1]);
        edgeQueue = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight;
        });
        for (int i = 0; i < m; i++) {
            str = bf.readLine().split(" ");
            int u = parseInt(str[0]);
            int v = parseInt(str[1]);
            int w = parseInt(str[2]);
            edgeQueue.add(new Edge(u, v, w));
        }
        int result = kruskal();
        if (result == -1) {
            System.out.println("orz");
        } else {
            System.out.println(result);
        }
        return;
    }

    static void union(int parent, int child) {
        nodeList[find(child)] = find(nodeList[parent]);
    }

    static int find(int node) {
        if (nodeList[node] == node) {
            return nodeList[node];
        }
        nodeList[node] = find(nodeList[node]);
        return nodeList[node];
    }

    static int kruskal() {
        nodeList = fillArrayAsc(nodeList, n);
        int count = 0;
        int result = 0;
        while (!edgeQueue.isEmpty()) {
            Edge edge = edgeQueue.poll();
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                result += edge.weight;
                count++;
            }
            if (count == n - 1) {
                return result;
            }
        }
        return -1;
    }

}
