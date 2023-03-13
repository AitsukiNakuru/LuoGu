import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer> getPrimeList(int n) {
        ArrayList<Integer> primeList = new ArrayList<Integer>();
        boolean[] isNotPrime = new boolean[n + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i]) {
                primeList.add(i);
            }
            for (Integer prime : primeList) {
                if (prime * i > n) {
                    break;
                }
                isNotPrime[prime * i] = true;
                if (i % prime == 0) {
                    break;
                }
            }
        }
        return primeList;
    }

    static boolean checkPow(Long x) {
        Long y = (long) Math.sqrt(x);
        if (y * y == x || (y + 1) * (y + 1) == x) {
            return true;
        }
        y = (long) Math.pow(x, 1.0 / 3);
        if (Math.pow(y, 3) == x || Math.pow(y + 1, 3) == x || Math.pow(y + 2, 3) == x) {
            return true;
        }
        return false;
    }

    static boolean calculate(Long number, ArrayList<Integer> primeList) {
        if (checkPow(number)) {
            return true;
        }
        boolean flag = true;
        for (Integer prime : primeList) {
            if (number % prime == 0) {
                int exponent = 0;
                while (number % prime == 0) {
                    exponent++;
                    number /= prime;
                }
                if (exponent == 1) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag && checkPow(number)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int t = Integer.parseInt(str[0]);
        ArrayList<Integer> primeList = getPrimeList(4000);
        for (int i = 0; i < t; i++) {
            str = br.readLine().split(" ");
            if (calculate(Long.parseLong(str[0]), primeList)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

    }
}
