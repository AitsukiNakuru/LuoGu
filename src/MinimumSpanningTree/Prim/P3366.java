package MinimumSpanningTree.Prim;

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
        for (int i = 0; i <= n; i++) {
            array[i] = i;
        }
        return array;
    }

    static int[] fillArrayDec(int[] array, int n) {
        for (int i = 0; i <= n; i++) {
            array[i] = n - i;
        }
        return array;
    }

    static int n, m, index = 0;
    static int[] edgeList, lastEdgeList, finalEdgeList, weightList, dist;
    static boolean[] visitedList;
    static void addEdge(int start, int end, int weight) {
        weightList[index] = weight;
        edgeList[index] = end;
        lastEdgeList[index] = finalEdgeList[start];
        finalEdgeList[start] = index++;
    }
    static class Node {
        int index, distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = parseInt(str[0]);
        m = parseInt(str[1]);
        edgeList = new int[2*m+1];
        lastEdgeList = new int[2*m+1];
        finalEdgeList = new int[n+1];
        weightList = new int[2*m+1];
        dist = new int[n+1];
        visitedList = new boolean[n+1];
        Arrays.fill(finalEdgeList, -1);
        for (int i = 0; i < m; i++) {
            str = bf.readLine().split(" ");
            int u = parseInt(str[0]);
            int v = parseInt(str[1]);
            int w = parseInt(str[2]);
            addEdge(u, v, w);
            addEdge(v, u, w);
        }
        int result = prim(1);
        if (result == -1) {
            System.out.println("orz");
        } else {
            System.out.println(result);
        }
    }
    static int prim(int root) {
        PriorityQueue<Node> accessibleList = new PriorityQueue<>(((o1, o2) -> {
            return o1.distance - o2.distance;
        }));
        Arrays.fill(dist, MAX_INT);
        Arrays.fill(visitedList, false);
        dist[root] = 0;
        int result = 0;
        int count = 0;
        accessibleList.offer(new Node(root, 0));
        while(!accessibleList.isEmpty()) {
            Node node = accessibleList.poll();
            if (visitedList[node.index]) continue;
            visitedList[node.index] = true;
            result+= node.distance;
            count++;
            for (int i = finalEdgeList[node.index]; i != -1; i = lastEdgeList[i]) {
                int endNode = edgeList[i];
                if (dist[endNode] > weightList[i]) {
                    dist[endNode] = weightList[i];
                    accessibleList.offer(new Node(endNode, weightList[i]));
                }
            }

        }
        if (count != n) {
            return -1;
        }
        return result;
    }
}
