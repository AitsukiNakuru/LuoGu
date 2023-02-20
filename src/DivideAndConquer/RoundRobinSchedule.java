package DivideAndConquer;

import java.util.Scanner;
public class RoundRobinSchedule {
    static int[][] schedule;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = (int) Math.pow(2, k);
        schedule = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            schedule[1][i] = i;
        }
        divideAndConquer(k, 1, 1);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(schedule[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void divideAndConquer(int k, int row, int column) {
        int size = (int) Math.pow(2, k - 1);
        if (k == 1) {
            schedule[row + 1][column] = schedule[row][column + 1];
            schedule[row + 1][column + 1] = schedule[row][column];
            return;
        }
        divideAndConquer(k - 1, row, column);
        divideAndConquer(k - 1, row, column + size);
        divideAndConquer(k - 1, row + size, column);
        divideAndConquer(k - 1, row + size, column + size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                schedule[row + size + i][column + size + j] = schedule[row + i][column + j];
                schedule[row + size + i][column + j] = schedule[row + i][column + size + j];
            }
        }
    }
}
