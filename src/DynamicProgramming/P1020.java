package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P1020 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split(" ");
        int n = str.length;
        int[] missile = new int[n+1];
        for (int i = 0; i < n; i++) {
            missile[i+1] = Integer.parseInt(str[i]);
        }
        int[] dp = new int[n+1];
        int result = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 1; j--) {
                if (missile[i] <= missile[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        List<Integer> system = new ArrayList<>();
        system.add(missile[1]);
        for (int i = 2; i <= n; i++) {
            boolean flag = false;
            for (int j = 0 ; j < system.size() ; j++) {
                if (system.get(j) >= missile[i]) {
                    system.set(j, missile[i]);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                system.add(missile[i]);
            }
        }
        System.out.println(result);
        System.out.println(system.size());
    }
}
