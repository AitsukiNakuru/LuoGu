package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P8687 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);
        int[] candyList = new int[n];
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            int nowCandy = 0;
            for (int j = 0; j < k; j++) {
                // 原本1就在第一位，如果是第5种口味的糖果则需要向左移动4位
                nowCandy = nowCandy | (1 << Integer.parseInt(str[j]) - 1);
            }
            candyList[i] = nowCandy;
        }
        int[] dp = new int[1 << 20];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            dp[candyList[i]] = 1;
            for (int j = 0; j < (1 << m); j++) {
                if (dp[j] == Integer.MAX_VALUE) continue;//无法达到该种情况则continue
                // dp[j] 更新可以到达的位置
                dp[j | candyList[i]] = Math.min(dp[j] + 1, dp[j | candyList[i]]);//核心代码，作用是松弛结果
            }
        }
        if (dp[(1 << m) - 1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[(1 << m) - 1]);
        }
    }
}
