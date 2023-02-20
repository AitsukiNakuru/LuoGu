package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.PriorityQueue;

public class P1991 {
    static int MAX_INT = 2147483647;
    static int MIN_INT = -2147483648;

    static int parseInt(String str) {
        return Integer.parseInt(str);
    }

    static double parseDouble(String str) {
        return Double.parseDouble(str);
    }

    static double getDouble(double d, int b) {
        BigDecimal decimal = new BigDecimal(d);
        return decimal.setScale(b, RoundingMode.HALF_UP).doubleValue();
    }

    static int s, p;
    static int[] parentList;

    static class Edge {
        int u, v;
        double weight;

        public Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class Post {
        int index;
        int x, y;

        public Post(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    static Post[] postList;
    static PriorityQueue<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        s = parseInt(str[0]);
        p = parseInt(str[1]);
        parentList = new int[p + 1];
        postList = new Post[p + 1];
        edgeList = new PriorityQueue<>((o1, o2) -> {
            if (o1.weight - o2.weight == 0) {
                return 0;
            }
            return o1.weight - o2.weight > 0 ? 1 : -1;
        });
        for (int i = 1; i <= p; i++) {
            str = bf.readLine().split(" ");
            int x = parseInt(str[0]);
            int y = parseInt(str[1]);
            postList[i] = new Post(i, x, y);
            parentList[i] = i;
        }
        for (int i = 1; i < p; i++) {
            for (int j = 1 + i; j <= p; j++) {
                Post postI = postList[i];
                Post postJ = postList[j];
                edgeList.offer(new Edge(i, j, calculateDistance(postI.x, postI.y, postJ.x, postJ.y)));
            }
        }
        System.out.printf("%.2f", getDouble(kruskal(), 2));

    }

    static double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
    }

    static void union(int parent, int child) {
        parentList[parentList[child]] = find(parentList[parent]);
    }

    static int find(int index) {
        if (parentList[index] == index) {
            return parentList[index];
        }
        parentList[index] = find(parentList[index]);
        return parentList[index];
    }

    static double kruskal() {
        int count = 0;
        double result = 0;
        while (!edgeList.isEmpty()) {
            if (count == p - s) {
                return result;
            }
            Edge edge = edgeList.poll();
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                count++;
                result = Math.max(result, edge.weight);
            }
        }
        return result;
    }
}
