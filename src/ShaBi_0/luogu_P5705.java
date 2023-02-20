package ShaBi_0;
import java.util.*;
import  java.io.*;
public class luogu_P5705 {
    public static void main (String args[]) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String a = sc.nextLine();
        int len = a.length();
        for (int i=len-1 ; i>=0 ; i--) {
            System.out.print(a.charAt(i));
        }
    }
}
