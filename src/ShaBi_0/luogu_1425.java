package ShaBi_0;
import java.util.*;
import java.io.*;
public class luogu_1425 {
    public static void main (String args[]) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int a, b, c, d, result1 = 0, result2 = 0;
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();
        result1 = c-a-1;
        if (result1>1) {
            result2 = (60-b)+d;
            if (result2>=60) {
                result1++;
                result2-=60;
            }
        }
        else if (result1<0) {
            result1 = 0;
        }
        else {
            result2 = d-b;
        }
        System.out.println(result1+" "+result2);
    }
}
