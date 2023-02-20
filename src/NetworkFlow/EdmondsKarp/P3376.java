package NetworkFlow.EdmondsKarp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class P3376 {
    static int n, m, s, t, u, v, tot = 1;
    static long w, ans;
    static long[] dis;
    // head

    static int[] vis, pre, head;
    static int[][] flag;

    static class Node {
        int to, net;
        long val;

    }

    static Node[] nodeList;

    static void addEdge(int start, int end, long weight) {
        // 邻接表成对存储， 将正向边和反向边存在 2和3，4和5，6和7
        // 因为在更新边权的时候，我们就可以直接使用xor1的方式，找到对应的正向边和反向边（奇数异或1相当于-1，偶数异或1相当于+1）
        nodeList[++tot] = new Node();
        nodeList[tot].to = end;
        nodeList[tot].val = weight;
        nodeList[tot].net = head[start];
        head[start] = tot;
        nodeList[++tot] = new Node();
        nodeList[tot].to = start;
        nodeList[tot].val = 0;
        nodeList[tot].net = head[end];
        head[end] = tot;
    }
    static int bfs() {
        for (int i = 1; i <= n; i++) {
            vis[i] = 0;
        }
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(s);
        vis[s] = 1;
        dis[s] = 2005020600;
        while(!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = head[x]; i > 0; i = nodeList[i].net) {
                if (nodeList[i].val == 0) continue;
                int v = nodeList[i].to;
                if (vis[v] == 1) continue;
                dis[v] = Math.min(dis[x], nodeList[i].val);
                pre[v] = i;
                queue.add(v);
                vis[v] = 1;
                if (v == t) return 1;
            }
        }
        return 0;
    }
    static void update() {
        int x = t;
        while(x!=s) {
            int v = pre[x];
            nodeList[v].val -= dis[t];
            nodeList[v ^ 1].val += dis[t];
            x = nodeList[v ^ 1].to;
        }
        ans+=dis[t];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        s = Integer.parseInt(str[2]);
        t = Integer.parseInt(str[3]);

        flag = new int[n+1][n+1];
        vis = new int[n+1];
        nodeList = new Node[2*m + 2];
        pre = new int[n+1];
        head = new int[n+1];
        dis = new long[n+1];
        for (int i = 1; i <=m; i++) {
            str = br.readLine().split(" ");
            u = Integer.parseInt(str[0]);
            v = Integer.parseInt(str[1]);
            w = Integer.parseInt(str[2]);
            if (flag[u][v] == 0) {
                // 新的边加上
                addEdge(u, v, w);
                flag[u][v] = tot;
            } else {
                // 重复的边先用flag[u][v] - 1找到原本的正向边然后加上w；
                nodeList[flag[u][v] - 1].val += w;
            }
        }

        while(bfs() != 0) {
            update();
        }
        System.out.println(ans);
    }
}
