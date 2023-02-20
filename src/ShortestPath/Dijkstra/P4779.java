package ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P4779 {
    static int n, m, s;
    static int INF = 2147483647;
    static int[] finalEdgeList;
    static int[] edgeList;
    static int[] lastEdgeList;
    static int[] weightList;
    static int index = 0;
    // 最短路径的值
    static int[] dist;
    // 已经找到最短路径的节点
    static boolean[] visitedList; 

    static void addEdge(int start, int end, int weight) {
        // 第index条边的终点是end
        edgeList[index] = end;

        // 第index条边的权值是weight
        weightList[index] = weight;

        // 第index条边的上一条边起始节点是原本start节点的最后一条边，每个节点的第一条边的上一条边是-1
        lastEdgeList[index] = finalEdgeList[start];

        // 将start节点的最后一条边更新为index，index加一
        finalEdgeList[start] = index++;

        // 查找一个点a的所有边过程为：
        // 1：从finalEdgeList中查找出点a的最后一条边finalEdge
        // 2：从lastEdgeList中查找出finalEdge的上一条边lastEdge
        // 3：从lastEdgeList中查找出lastEdge的上一条边，更新lastEdge
        // 4：重复过程3，直到lastEdge为-1
    }

    static void dijkstra() {

        PriorityQueue<Node> accessibleList = new PriorityQueue<>((o1, o2) -> {
            return o1.distance - o2.distance;
        });
        Arrays.fill(dist, INF);
        Arrays.fill(visitedList, false);
        accessibleList.add(new Node(s, 0));
        dist[s] = 0;

        while (!accessibleList.isEmpty()) {
            int nowNode = accessibleList.poll().index;

            if (visitedList[nowNode]) continue;
            visitedList[nowNode] = true;
            for (int i = finalEdgeList[nowNode]; i != -1; i = lastEdgeList[i]) {
                int j = edgeList[i];
                if (dist[j] > dist[nowNode] + weightList[i]) {
                    dist[j] = dist[nowNode] + weightList[i];
                    accessibleList.add(new Node(j, dist[j]));
                }
            }
        }
    }

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
        dijkstra();
        for (int i = 1; i <= n; i++) {
            System.out.print(dist[i] + " ");
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
