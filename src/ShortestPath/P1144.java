package ShortestPath;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.text.NumberFormat;

import java.util.Arrays;

import java.util.PriorityQueue;

public class P1144 {
    static int MAX_INT = 2147483647;
    static int MIN_INT = -2147483648;



    static int n, m, index = 0;
    static int[] endList, finalEdgeList, lastEdgeList, dist, countList;
    static boolean[] visitedList;

    static class Node {
        int index;
        int depth;

        public Node(int index, int depth) {
            this.index = index;
            this.depth = depth;
        }
    }

    static void addEdge(int u, int v) {
        endList[index] = v;
        lastEdgeList[index] = finalEdgeList[u];
        finalEdgeList[u] = index++;
    }

    static class Read {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        public int nextInt() throws Exception {
            st.nextToken();
            return (int) st.nval;
        }
    }


    static void prim(int root) {
        Arrays.fill(visitedList, false);
        Arrays.fill(dist, MAX_INT);
        PriorityQueue<Node> accessibleList = new PriorityQueue<>((o1, o2) -> {
            return o1.depth - o2.depth;
        });
        dist[root] = 0;
        countList[root] = 1;
        accessibleList.offer(new Node(root, 0));
        while (!accessibleList.isEmpty()) {
            Node node = accessibleList.poll();
            int nodeIndex = node.index;
            int nodeDepth = node.depth;
//            if (visitedList[nodeIndex]) continue;
//            visitedList[nodeIndex] = true;
            for (int i = finalEdgeList[nodeIndex]; i != -1; i = lastEdgeList[i]) {
                int endNode = endList[i];
                if (dist[endNode] > nodeDepth + 1) {
                    countList[endNode] = countList[nodeIndex];
                    dist[endNode] = nodeDepth + 1;
                    //accessibleList.add(new Node(endNode, nodeDepth + 1));
                    if (!visitedList[endNode]) {
                        visitedList[endNode] = true;
                        accessibleList.add(new Node(endNode, nodeDepth + 1));
                    }
                } else if (dist[endNode] == nodeDepth + 1) {
                    countList[endNode] = (countList[endNode] + countList[nodeIndex] % 100003) % 100003;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Read read = new Read();

        n = read.nextInt();
        m = read.nextInt();
        visitedList = new boolean[1000010];
        endList = new int[2000010];
        finalEdgeList = new int[2000010];
        lastEdgeList = new int[2000010];
        countList = new int[1000010];
        dist = new int[1000010];
        Arrays.fill(finalEdgeList, -1);
        for (int i = 0; i < m; i++) {
            int u = read.nextInt();
            int v = read.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }
        prim(1);
        for (int i = 1; i <= n; i++) {
            System.out.println(countList[i]);
        }
    }
}
