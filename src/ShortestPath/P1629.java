package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P1629 {
    static int MAX_INT = 2147483647;
    static int MIN_INT = -2147483648;
    static int n, m;
    static int[] dist, edgeList, weightList, finalEdgeList, lastEdgeList;
    static int[] reversedDist, reversedEdgeList, reversedWeightList, reversedFinalEdgeList, reversedLastEdgeList;
    static boolean[] visitedList;
    static boolean[] reversedVisitedList;
    static int index = 0;
    static int reversedIndex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = parseInt(str[0]);
        m = parseInt(str[1]);
        dist = new int[n + 1];
        edgeList = new int[m + 1];
        weightList = new int[m + 1];
        finalEdgeList = new int[n + 1];
        lastEdgeList = new int[m + 1];
        visitedList = new boolean[n + 1];

        reversedDist = new int[n + 1];
        reversedEdgeList = new int[m + 1];
        reversedWeightList = new int[m + 1];
        reversedFinalEdgeList = new int[n + 1];
        reversedLastEdgeList = new int[m + 1];
        reversedVisitedList = new boolean[n + 1];

        Arrays.fill(finalEdgeList, -1);
        Arrays.fill(reversedFinalEdgeList, -1);

        while (m-- > 0) {
            str = bf.readLine().split(" ");
            int start = parseInt(str[0]);
            int end = parseInt(str[1]);
            int weight = parseInt(str[2]);
            addEdge(start, end, weight);
            addReversedEdge(start, end, weight);
        }
        bf.close();
        int result = 0;
        dijkstra(1);
        reversedDijkstra(1);
        for (int i = 1; i <= n; i++) {
            result+=dist[i];
            result+=reversedDist[i];
        }



        System.out.println(result);
    }

    static int parseInt(String str) {
        return Integer.parseInt(str);
    }

    static void addEdge(int start, int end, int weight) {
        edgeList[index] = end;
        weightList[index] = weight;
        lastEdgeList[index] = finalEdgeList[start];
        finalEdgeList[start] = index++;
    }
    static void addReversedEdge(int start, int end, int weight) {
        reversedEdgeList[reversedIndex] = start;
        reversedWeightList[reversedIndex] = weight;
        reversedLastEdgeList[reversedIndex] = reversedFinalEdgeList[end];
        reversedFinalEdgeList[end] = reversedIndex++;
    }
    static void dijkstra(int n) {
        Arrays.fill(dist, MAX_INT);
        Arrays.fill(visitedList, false);
        dist[n] = 0;
        PriorityQueue<Node> accessibleList = new PriorityQueue<>((o1, o2) -> {
            return o1.distance - o2.distance;
        });
        accessibleList.add(new Node(n, 0));
        while (!accessibleList.isEmpty()) {
            int nowNode = accessibleList.poll().index;
            if (visitedList[nowNode]) continue;
            visitedList[nowNode] = true;
            for (int i = finalEdgeList[nowNode]; i != -1; i = lastEdgeList[i]) {
                int endNode = edgeList[i];
                if (dist[endNode] > dist[nowNode] + weightList[i]) {
                    dist[endNode] = dist[nowNode] + weightList[i];
                    accessibleList.add(new Node(endNode, dist[endNode]));
                }
            }
        }
    }
    static void reversedDijkstra(int n) {
        Arrays.fill(reversedDist, MAX_INT);
        Arrays.fill(reversedVisitedList, false);
        reversedDist[n] = 0;
        PriorityQueue<Node> accessibleList = new PriorityQueue<>((o1, o2) -> {
            return o1.distance - o2.distance;
        });
        accessibleList.add(new Node(n, 0));
        while (!accessibleList.isEmpty()) {
            int nowNode = accessibleList.poll().index;
            if (reversedVisitedList[nowNode]) continue;
            reversedVisitedList[nowNode] = true;
            for (int i = reversedFinalEdgeList[nowNode]; i != -1; i = reversedLastEdgeList[i]) {
                int endNode = reversedEdgeList[i];
                if (reversedDist[endNode] > reversedDist[nowNode] + reversedWeightList[i]) {
                    reversedDist[endNode] = reversedDist[nowNode] + reversedWeightList[i];
                    accessibleList.add(new Node(endNode, reversedDist[endNode]));
                }
            }
        }
    }
    static class Node {
        int index, distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

}
