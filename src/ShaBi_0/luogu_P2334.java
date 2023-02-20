package ShaBi_0;
import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class luogu_P2334 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n, a, b;
        n = sc.nextInt();
        int[] num = new int[1000005];
        for (int i=1 ; i<=n ; i++) {
            a = sc.nextInt();   b = sc.nextInt();
            for (int j=a ; j<b ; j++) {
                num[j]++;
            }
        }
        int flag=1;
        for (int i=1 ; i<=10 ; i++) {
            System.out.print(num[i]+" ");
        }
        for (int i=1 ; i<1000005 ; i++) {
            if (flag==1&&num[i]!=0) {
                System.out.print((i-1)+" ");
                flag=0;
                continue;
            }
            if (flag==0&&num[i]==0) {
                System.out.print(i);
                System.out.println();
                flag=1;
                continue;
            }
        }
    }
}
