package DivideAndConquer;

import java.util.Scanner;

public class P1228 {
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int specialRow = sc.nextInt();
        int specialColumn = sc.nextInt();
        map = new int[(int) Math.pow(2, k) + 1][(int) Math.pow(2, k) + 1];
        divideAndConquer(specialRow, specialColumn, 1, 1, k);
        int a = 1;
    }
    static void divideAndConquer(int specialRow, int specialColumn, int row, int column, int k) {
        int edgeLength = (int) Math.pow(2, k - 1);
        int topRow = row + edgeLength - 1;
        int leftColumn = column + edgeLength - 1;
        int bottomRow = row + edgeLength;
        int rightColumn = column + edgeLength;
        if (specialRow < bottomRow && specialColumn < rightColumn) {
            System.out.println(bottomRow + " " + rightColumn + " " + 1);
            map[topRow][rightColumn] = 1;
            map[bottomRow][leftColumn] = 1;
            map[bottomRow][rightColumn] = 1;
            if (k == 1) {
                return;
            }
            divideAndConquer(specialRow, specialColumn, row, column, k - 1);
            divideAndConquer(topRow, rightColumn, row, rightColumn, k - 1);
            divideAndConquer(bottomRow, leftColumn, bottomRow, column, k - 1);
            divideAndConquer(bottomRow, rightColumn, bottomRow, rightColumn, k - 1);

        } else if (specialRow < bottomRow) {
            System.out.println(bottomRow + " " + leftColumn + " " + 2);
            map[topRow][leftColumn] = 2;
            map[bottomRow][leftColumn] = 2;
            map[bottomRow][rightColumn] = 2;
            if (k == 1) {
                return;
            }
            divideAndConquer(topRow, leftColumn, row, column, k - 1);
            divideAndConquer(specialRow, specialColumn, row, rightColumn, k - 1);
            divideAndConquer(bottomRow, leftColumn, bottomRow, column, k - 1);
            divideAndConquer(bottomRow, rightColumn, bottomRow, rightColumn, k - 1);
        } else if (specialColumn < rightColumn) {
            System.out.println(topRow + " " + rightColumn + " " + 3);
            map[topRow][leftColumn] = 3;
            map[topRow][rightColumn] = 3;
            map[bottomRow][rightColumn] = 3;
            if (k == 1) {
                return;
            }
            divideAndConquer(topRow, leftColumn, row, column, k - 1);
            divideAndConquer(topRow, rightColumn, row, rightColumn, k - 1);
            divideAndConquer(specialRow, specialColumn, bottomRow, column, k - 1);
            divideAndConquer(bottomRow, rightColumn, bottomRow, rightColumn, k - 1);
        } else {
            System.out.println(topRow + " " + leftColumn + " " + 4);
            map[topRow][leftColumn] = 4;
            map[topRow][rightColumn] = 4;
            map[bottomRow][leftColumn] = 4;
            if (k == 1) {
                return;
            }
            divideAndConquer(topRow, leftColumn, row, column, k - 1);
            divideAndConquer(topRow, rightColumn, row, rightColumn, k - 1);
            divideAndConquer(bottomRow, leftColumn, bottomRow, column, k - 1);
            divideAndConquer(specialRow, specialColumn, bottomRow, rightColumn, k - 1);
        }
    }
}
