package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class P1462 {
    static int n, m, index = 0, leftSide, rightSide;
    static long b;
    static long[] consumeHpList;
    static int[] cityCostList, destinationList, lastEdgeList, finalEdgeList, weightList;
    static boolean[] visitedList;
    static class City {
        int index;
        long consumeHp;

        public City(int index, long consumeHp) {
            this.index = index;
            this.consumeHp = consumeHp;
        }
    }
    static void addEdge(int start, int end, int weight) {
        weightList[index] = weight;
        destinationList[index] = end;
        lastEdgeList[index] = finalEdgeList[start];
        finalEdgeList[start] = index++;
    }
    static boolean dijkstra(int maxCost) {
        Arrays.fill(visitedList, false);
        Arrays.fill(consumeHpList, Integer.MAX_VALUE);
        if (cityCostList[1] > maxCost) {
            return false;
        }
        Queue<City> accessibleCityList = new PriorityQueue<>(Comparator.comparingLong(o -> o.consumeHp));
        accessibleCityList.add(new City(1, 0));
        consumeHpList[1] = 0;

        while(!accessibleCityList.isEmpty()) {
            City nowCity = accessibleCityList.poll();
            if (nowCity.index == n) {
                return true;
            }
            if (visitedList[nowCity.index]) continue;
            if (nowCity.consumeHp > b) {
                return false;
            };
            visitedList[nowCity.index] = true;
            for (int i = finalEdgeList[nowCity.index]; i != -1; i = lastEdgeList[i]) {
                int destinationIndex = destinationList[i];
                if (consumeHpList[destinationIndex] > consumeHpList[nowCity.index] + weightList[i] && cityCostList[destinationIndex] <= maxCost) {
                    consumeHpList[destinationIndex] = consumeHpList[nowCity.index] + weightList[i];
                    accessibleCityList.add(new City(destinationIndex, consumeHpList[destinationIndex]));
                }
            }
        }
        return false;
    }
    static int binarySearch(int left, int right) {
        if (left == right) return left;
        if (dijkstra((left+right) / 2)) {
            return binarySearch(left, (left+right)/2);
        }
        return binarySearch((left+right) / 2 +1, right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        b = Long.parseLong(str[2]);
        cityCostList = new int[n+1];
        destinationList = new int[m*2];
        lastEdgeList = new int[m*2];
        finalEdgeList = new int[n+1];
        weightList = new int[m*2];
        consumeHpList = new long[n+1];
        visitedList = new boolean[n+1];

        Arrays.fill(finalEdgeList, -1);
        for (int i = 1; i <= n; i++) {
            str = br.readLine().split(" ");
            cityCostList[i] = Integer.parseInt(str[0]);
            rightSide = Math.max(rightSide, cityCostList[i]);
        }
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int u = Integer.parseInt(str[0]);
            int v = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            addEdge(u, v, w);
            addEdge(v, u, w);
        }
        if (!dijkstra(Integer.MAX_VALUE)){
            System.out.println("AFK");
            return;
        }
        leftSide = Math.min(cityCostList[1], cityCostList[n]);
        System.out.println(binarySearch(leftSide, rightSide));
    }
}
