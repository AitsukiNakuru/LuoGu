package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P8742 {
    static int functionA() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        boolean[] flagList = new boolean[100001];
        Arrays.fill(flagList, false);
        int count = 0;
        List<Integer> accessibleList = new ArrayList<>();
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int weight = Integer.parseInt(str[i]);
            if (i != 0) {
                int size = accessibleList.size();
                for (int j = 0; j < size; j++) {
                    int accessibleWeight = accessibleList.get(j);
                    int weightA = Math.abs(accessibleWeight - weight);
                    int weightB = Math.abs(accessibleWeight + weight);
                    if (!flagList[weightA] && weightA != 0) {
                        accessibleList.add(weightA);
                        flagList[weightA] = true;
                        count++;
                    }
                    if (!flagList[weightB] && weightB != 0) {
                        accessibleList.add(weightB);
                        flagList[weightB] = true;
                        count++;
                    }
                }
                if (!flagList[weight]) {
                    accessibleList.add(weight);
                    flagList[weight] = true;
                    count++;
                }
            } else {
                accessibleList.add(weight);
                flagList[weight] = true;
                count++;
            }
        }
        return count;
    }

    static int functionB() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int MAX_WEIGHT = 0;
        int[] weightList = new int[n + 1];
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int weight = Integer.parseInt(str[i]);
            weightList[i + 1] = weight;
            MAX_WEIGHT += weight;
        }
        int[][] dp = new int[n + 1][MAX_WEIGHT + 1];
        dp[0][0] = 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= MAX_WEIGHT; j++) {

                if (dp[i - 1][j] == 1) {
                    dp[i][j] = 1;
                    dp[i][Math.abs(j + weightList[i])] = 1;
                    dp[i][Math.abs(j - weightList[i])] = 1;
                }
            }
            dp[i][weightList[i]] = 1;
        }
        for (int i = 1; i <= MAX_WEIGHT; i++) {
            if (dp[n][i] == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(functionB());
    }
}
