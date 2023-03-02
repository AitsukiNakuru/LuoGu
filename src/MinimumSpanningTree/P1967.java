package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1967 {
    static int n, m, q, count=0;
    static class Edge {
        int x, y, weight;

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
    static Queue<Edge> edgeList = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight * -1));
    static class Node {
        int index, parent;
        ArrayList<Integer> unionList;
        int[] weightList;

        public Node(int index, int parent, int n) {
            this.index = index;
            this.parent = parent;
            this.unionList = new ArrayList<>();
            unionList.add(index);
            weightList = new int[n+1];
            Arrays.fill(weightList, Integer.MAX_VALUE);
        }
    }
    static Node[] nodeList;

    static int find(int nodeIndex) {
        if (nodeList[nodeIndex].parent == nodeIndex) {
            return nodeList[nodeIndex].parent;
        }
        nodeList[nodeIndex].parent = find(nodeList[nodeIndex].parent);
        return nodeList[nodeIndex].parent;
    }
    static void union(int parent, int child) {
        nodeList[find(child)].parent = find(nodeList[parent].parent);
    }
    static void updateMaxWeightMatrix(int parent, int child, int weight) {
        for (Integer integer : nodeList[find(child)].unionList) {
            for (Integer integer1 : nodeList[find(parent)].unionList) {
                nodeList[integer].weightList[integer1] = Math.min(nodeList[integer].weightList[integer1], weight);
                nodeList[integer1].weightList[integer] = Math.min(nodeList[integer1].weightList[integer], weight);
            }
        }
        for (int i = 0; i < nodeList[find(child)].unionList.size(); i++) {
            nodeList[find(parent)].unionList.add(nodeList[find(child)].unionList.get(i));
        }
        nodeList[find(child)].unionList.clear();
    }
    static void kruskal() {
        while (!edgeList.isEmpty()) {
            Edge edge = edgeList.poll();
            if (find(edge.x) != find(edge.y)) {
                count +=1;
                updateMaxWeightMatrix(edge.x, edge.y, edge.weight);
                union(edge.x, edge.y);
            }
            if (count == n - 1) {
                return;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        nodeList = new Node[n+1];
        for (int i = 0; i <= n; i++) {
            nodeList[i] = new Node(i, i, n);
        }
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            edgeList.add(new Edge(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }
        kruskal();
        str = br.readLine().split(" ");
        q = Integer.parseInt(str[0]);
        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            if (nodeList[Integer.parseInt(str[0])].weightList[Integer.parseInt(str[1])] != Integer.MAX_VALUE) {
                System.out.println(nodeList[Integer.parseInt(str[0])].weightList[Integer.parseInt(str[1])]);
            } else {
                System.out.println(-1);
            }
        }
    }
}
