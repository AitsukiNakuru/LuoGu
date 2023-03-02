package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class P4047 {
    static int n, k, index = 0, count;
    static class Node {
        int x, y, parent;

        public Node(int x, int y, int parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
    }
    static class Edge {
        int start, end;
        double length;
        public Edge(int start, int end, double length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
    static Node[] nodeList;
    static Queue<Edge> edgeList = new PriorityQueue<>(Comparator.comparingDouble(o -> o.length));
    static double calculateDistance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    static void union(int parent, int child) {
        nodeList[find(child)].parent = find(nodeList[parent].parent);
    }
    static int find(int nodeIndex) {
        if (nodeList[nodeIndex].parent == nodeIndex) {
            return nodeList[nodeIndex].parent;
        }
        nodeList[nodeIndex].parent = find(nodeList[nodeIndex].parent);
        return nodeList[nodeIndex].parent;
    }
    static double kruskal() {
        while (!edgeList.isEmpty()) {
            Edge edge = edgeList.poll();
            if(find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                count+=1;
            }
            if (count == n - k +1) {
                return edge.length;
            }
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);
        nodeList = new Node[n];
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            nodeList[i] = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), i);
        }
        index=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edgeList.add(new Edge(i, j, calculateDistance(nodeList[i], nodeList[j])));
            }
        }
        System.out.printf("%.2f",kruskal());
    }
}
