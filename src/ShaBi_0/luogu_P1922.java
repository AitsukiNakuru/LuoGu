package ShaBi_0;
import javax.imageio.IIOException;
import java.io.BufferedInputStream;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class luogu_P1922 {


    static class InputReader {
        BufferedReader br;

        public InputReader(InputStream stream) {
            br = new BufferedReader(new InputStreamReader(stream));
        }

        public int nextInt() throws IOException {
            int c = br.read();
            while (c <= 32) {
                c = br.read();
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = br.read();
            }
            int x = 0;
            while (c > 32) {
                x = x * 10 + c - '0';
                c = br.read();
            }
            return negative ? -x : x;
        }

        public long nextLong() throws IOException {
            int c = br.read();
            while (c <= 32) {
                c = br.read();
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = br.read();
            }
            long x = 0;
            while (c > 32) {
                x = x * 10 + c - '0';
                c = br.read();
            }
            return negative ? -x : x;
        }

        public String next() throws IOException {
            int c = br.read();
            while (c <= 32) {
                c = br.read();
            }
            StringBuilder sb = new StringBuilder();
            while (c > 32) {
                sb.append((char) c);
                c = br.read();
            }
            return sb.toString();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }

    static int[] f = new int[100005];
    static int n;
    static int[] du = new int[100005];
    static int[][] reach = new int[100005][1005];

    static void dfs (int x) {
        int count=0, leave=0, total=0;
        if (du[x]==0) return;
        for (int i=1 ; i<=reach[x][0] ; i++) {
            if (du[reach[x][i]]!=0) {
                dfs(reach[x][i]);
                total+=f[reach[x][i]];
            }
            else {
                leave++;
            }
            f[x]=total+(leave+1)/2;
        }

        }
    static InputReader sc;
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //Scanner sc = new Scanner(System.in);
        
        sc = new InputReader(System.in);
        int temp_1, temp_2;
        n = sc.nextInt();

        for (int i=1 ; i<=n-1 ; i++) {
            temp_1 = sc.nextInt();  temp_2 = sc.nextInt();
            reach[temp_1][reach[temp_1][0]+1]=temp_2;   reach[temp_1][0]++;
            du[temp_1]++;
        }
        dfs(1);
        System.out.println(f[1]);
    }
}
