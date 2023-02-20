package ShaBi_0;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
public class luogu_P5076 {
    public static void main (String args[]) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        double a = sc.nextDouble();
        int b = sc.nextInt();
        DecimalFormat result = new DecimalFormat("#0.000");

        //System.out.println(result.format(a/b));
        //System.out.printf("%.3f", a/b);
        System.out.println(String.format("%.3f", a/b));
        System.out.println(b*2);
        sc.close();
    }
}
