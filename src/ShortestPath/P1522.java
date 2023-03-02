package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1522 {
    static int n, index = 0;
    static int[] destinationList, lastEdgeList, finalEdgeList, bigPastureList;
    //static double ans;
    static double[] weightList, maxWeightList, diameterList;
    static double[][] weightMatrix;
    static boolean[] visitedList;
    static class Pasture {
        int index, x, y;

        public Pasture(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }
    static Pasture[] pastureList;
    static void addEdge(int start, int end, double weight) {
        destinationList[index] = end;
        weightList[index] = weight;
        lastEdgeList[index] = finalEdgeList[start];
        finalEdgeList[start] = index++;
    }
    static double calculateDistance(Pasture pastureA, Pasture pastureB) {
        return Math.sqrt(Math.pow(pastureA.x - pastureB.x, 2) + Math.pow(pastureA.y - pastureB.y, 2));
    }
    static void dfs(int index, int pastureIndex) {
        for (int i = finalEdgeList[index]; i != -1; i = lastEdgeList[i]) {
            int destination = destinationList[i];
            if (!visitedList[destination]) {
                visitedList[destination] = true;
                bigPastureList[destination] = pastureIndex;
                dfs(destination, pastureIndex);
            }
        }
    }
    static void floyd() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (weightMatrix[j][i] != Double.MAX_VALUE && weightMatrix[i][k] != Double.MAX_VALUE && j!=k) {
                        weightMatrix[j][k] = Math.min(weightMatrix[j][k], weightMatrix[j][i] + weightMatrix[i][k]);
                        weightMatrix[k][j] = Math.min(weightMatrix[k][j], weightMatrix[j][i] + weightMatrix[i][k]);
                    }
                }
            }
        }
    }
    static double findMaxDistance(Pasture pasture) {
        double ans = 0;
        for (int i = 0; i < n; i++) {
            if (i!= pasture.index && weightMatrix[pasture.index][i] != Double.MAX_VALUE) {
                ans = Math.max(ans, weightMatrix[pasture.index][i]);
            }
        }
        return ans;
    }

    static double findMinDiameter() {
        double ans = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            maxWeightList[i] = findMaxDistance(pastureList[i]);
            diameterList[bigPastureList[i]] = Math.max(diameterList[bigPastureList[i]], maxWeightList[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i!=j && bigPastureList[i] != bigPastureList[j]) {
                    double ans2 = Math.max(diameterList[bigPastureList[i]], diameterList[bigPastureList[j]]);

                    ans = Math.min(ans, Math.max(maxWeightList[i] + maxWeightList[j] + calculateDistance(pastureList[i], pastureList[j]), ans2));
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        destinationList = new int[n*(n-1)];
        lastEdgeList = new int[n*(n-1)];
        weightList = new double[n*(n-1)];
        finalEdgeList = new int[n];
        visitedList = new boolean[n];
        weightMatrix = new double[n][n];
        maxWeightList = new double[n];
        bigPastureList = new int[n];
        diameterList = new double[n];
        Arrays.fill(finalEdgeList, -1);
        pastureList = new Pasture[n];
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            pastureList[i] = new Pasture(i, Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            Arrays.fill(weightMatrix[i], Double.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            str = br.readLine().split("");
            for (int j = i+1; j < n; j++) {
                if (Integer.parseInt(str[j]) == 1) {
                    double weight = calculateDistance(pastureList[i], pastureList[j]);
                    addEdge(i, j, weight);
                    addEdge(j, i, weight);
                    weightMatrix[i][j] = weight;
                    weightMatrix[j][i] = weight;
                }
            }
        }
        Arrays.fill(visitedList, false);
        int pastureIndex = 0;
        for (int i = 0; i < n; i++) {
            if (!visitedList[i]) {
                visitedList[i] = true;
                bigPastureList[i] = pastureIndex;
                dfs(i, pastureIndex);
                pastureIndex+=1;
            }
        }
        floyd();
        Arrays.fill(visitedList, false);
        System.out.printf("%.6f", findMinDiameter());
    }
}
