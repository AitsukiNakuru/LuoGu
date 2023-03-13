package DepthFirstSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class P8785 {
    static int n, m, ans;

    static class Circle {
        int x, y, r, index;
        LinkedList<Integer> effectList = new LinkedList<>();

        public Circle(int index, int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
    static boolean[] isDetonateList;
    static double calculateDistance(Circle circleA, Circle circleB) {
        return Math.sqrt(Math.pow(circleA.x - circleB.x, 2) + Math.pow(circleA.y - circleB.y, 2));
    }
    static boolean canDetonate(Circle circleA, Circle circleB) {
        return calculateDistance(circleA, circleB) < circleA.r;
    }
    static void landmineDetonate(int source) {
        isDetonateList[source] = true;
        ans++;
        for (int i = landmineList[source].index+1; i < n; i++) {
            if (landmineList[source].x - landmineList[i].x > landmineList[source].r) {
                return;
            }
            if (canDetonate(landmineList[source], landmineList[i]) && !isDetonateList[i]) {
                landmineDetonate(i);
            }
        }
        for (int i = landmineList[source].index-1; i >=0; i--) {
            if (landmineList[i].x - landmineList[source].x > landmineList[source].r) {
                return;
            }
            if (canDetonate(landmineList[source], landmineList[i]) && !isDetonateList[i]) {
                landmineDetonate(i);
            }
        }
    }
    static void rocketDetonate(Circle rocket) {
        for (int i = 0; i < n; i++) {
            if (rocket.x - rocket.r > landmineList[i].x) {
                return;
            }
            if (canDetonate(rocket, landmineList[i]) && !isDetonateList[i]) {
                landmineDetonate(i);
            }
        }
    }
    static Circle[] landmineList, rocketList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        landmineList = new Circle[n];
        rocketList = new Circle[m];
        isDetonateList = new boolean[n];
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            landmineList[i] = new Circle(i, Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }
        Arrays.fill(isDetonateList, false);
        Arrays.sort(landmineList, Comparator.comparingInt(o -> o.x));
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            rocketList[i] = new Circle(i, Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
            rocketDetonate(rocketList[i]);
        }

        System.out.println(ans);
    }
}