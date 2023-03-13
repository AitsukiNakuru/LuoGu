package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class P3383 {
    static ArrayList<Integer> getPrimeList(int n) {
        ArrayList<Integer> primeList = new ArrayList<>();
        boolean[] isPrime = new boolean[n + 1];
        isPrime[0] = false;
        isPrime[1] = false;
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
            for (Integer prime : primeList) {
                if (prime * i >= n) break;
                isPrime[prime * i] = false;
                if (i % prime == 0) break;
            }
        }
        return primeList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);
        ArrayList<Integer> primeList = getPrimeList(n);
        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            System.out.println(primeList.get(Integer.parseInt(str[0]) - 1));
        }
    }
}
