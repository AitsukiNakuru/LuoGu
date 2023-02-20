package ShaBi_0;
import java.io.*;
import java.util.*;

public class luogu_P5708 {
    public static void main (String args[]) {
        Scanner sc = new Scanner (new BufferedInputStream(System.in));
        Double a, b, c, p, result;
        a=b=c=p=result=0.0;

        a = sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();
        p=0.5*(a+b+c);
        result = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        System.out.println(String.format("%.1f", result));
    }
}
