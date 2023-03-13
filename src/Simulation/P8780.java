package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P8780 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        Long a = Long.parseLong(str[0]);
        Long b = Long.parseLong(str[1]);
        Long n = Long.parseLong(str[2]);
        Long ans = n/(a*5+b*2);
        Long mod = n%(a*5+b*2);
        if (mod <= a*5) {
            if (mod % a == 0) {
                System.out.println(ans*7+mod/a);
            } else {
                System.out.println(ans*7+mod/a+1);
            }
        } else {
            mod = mod-a*5;
            if (mod % b == 0) {
                System.out.println(ans*7+mod/b+5);
            } else {
                System.out.println(ans*7+mod/b+6);
            }
        }
    }
}
