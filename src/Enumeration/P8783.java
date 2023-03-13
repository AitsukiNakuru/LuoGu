package Enumeration;

import java.io.IOException;
import java.util.Scanner;

public class P8783 {
    static Long[][] sum;
    static int[][] matrix;
    static Long ans = 0L, K;
    static int n, m;

    static class Coordinate {
        int row, column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public boolean isSame(Coordinate o) {
            if (this.row != o.row) {
                return false;
            }
            return this.column == o.column;
        }

    }

    static Long calculate(int rowA, int columnA, int rowB, int columnB) {
        return sum[rowB][columnB] - sum[rowB][columnA - 1] - sum[rowA - 1][columnB] + sum[rowA - 1][columnA - 1];
    }

    static void search() {
        for (int i = 0; i <= n; i++) {
            sum[i][0] = 0L;
        }
        for (int i = 0; i <= m; i++) {
            sum[0][i] = 0L;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i][j];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; i + k <= n; k++) {
                    for (int l = 0; j + l <= m; l++) {
                        if (calculate(i, j, i + k, j + l) <= K) {
                            ans++;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] str = br.readLine().split(" ");
//        n = Integer.parseInt(str[0]);
//        m = Integer.parseInt(str[1]);
//        K = Long.parseLong(str[2]);
//        matrix = new int[n + 1][m + 1];
//        sum = new Long[n +1][m + 1];
//        for (int i = 0; i < n; i++) {
//            str = br.readLine().split(" ");
//            for (int j = 0; j < m; j++) {
//                matrix[i + 1][j + 1] = Integer.parseInt(str[j]);
//            }
//        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        K = sc.nextLong();
        matrix = new int[n+1][m+1];
        sum = new Long[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        search();

        System.out.println(ans);
    }
}
