package ShortestPath.SPFA;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class P4779 {
    static int n, m, s, index = 0;
    static int MAX_INT = 2147483647;
    static int[] edgeList, lastEdgeList, finalEdgeList, weightList;
    static int[] dist;
    static boolean[] visitedList;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        s = Integer.parseInt(str[2]);
        finalEdgeList = new int[n + 1];
        edgeList = new int[m + 1];
        lastEdgeList = new int[m + 1];
        dist = new int[n + 1];
        weightList = new int[m + 1];
        visitedList = new boolean[n + 1];
        Arrays.fill(finalEdgeList, -1);
        while (m-- > 0) {
            str = bf.readLine().split(" ");
            int start = Integer.parseInt(str[0]), end = Integer.parseInt(str[1]), weight = Integer.parseInt(str[2]);
            addEdge(start, end, weight);
        }
        bf.close();
        spfa();
        for (int i = 1; i <= n; i++) {
            System.out.print(dist[i] + " ");
        }
    }
    static void addEdge(int start, int end, int weight) {
        edgeList[index] = end;
        weightList[index] = weight;
        lastEdgeList[index] = finalEdgeList[start];
        finalEdgeList[start] = index++;
    }
    static void spfa() {
        Queue<Node> accessibleQueue = new LinkedList<>();
        accessibleQueue.add(new Node(s, 0));
        Arrays.fill(dist, MAX_INT);
        Arrays.fill(visitedList, false);
        dist[s] = 0;
        visitedList[s] = true;
        while (!accessibleQueue.isEmpty()) {
            int nowNode = accessibleQueue.poll().index;
            visitedList[nowNode] = false;
            for (int i = finalEdgeList[nowNode]; i != -1; i = lastEdgeList[i]) {
                int endNode = edgeList[i];
                if (dist[endNode] > dist[nowNode] + weightList[i]) {
                    dist[endNode] = dist[nowNode] + weightList[i];
                    if (!visitedList[endNode]) {
                        accessibleQueue.add(new Node(endNode, dist[endNode]));
                        visitedList[endNode] = true;
                    }
                }
            }
        }
    }
}
class Node {
    int index, distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
}
