package ShaBi_0;
import java.math.*;
import java.io.BufferedInputStream;
import java.util.*;
import java.util.Random;
public class luogu_P1177 {
    public static int Partition_Ascending(int[] s, int left, int right) {
        int temp, i;
        while (left<right) {
            while (left<right&&s[left]<s[right]) {
                left++;
            }
            if (left<right) {
                temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                right--;
            }
            while (left<right&&s[left]<s[right]) {
                right--;
            }
            if (left<right) {
                temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
            }
        }
        return left;
    }

    public static void Quick_Sort_Ascending(int[] s, int left, int right) {
        int mid, temp, i;
        if (left<right) {
            Random rand = new Random();
            int randNumber =rand.nextInt(right - left + 1) + left;
            temp = s[randNumber];
            s[randNumber] = s[left];
            s[left]  = temp;
            mid  = Partition_Ascending(s, left, right);
            Quick_Sort_Ascending(s, left, mid-1);
            Quick_Sort_Ascending(s, mid+1, right);
        }
    }

    public static void main (String args[]) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        int n;
        n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0 ; i<n ; i++) {
            a[i] = sc.nextInt();
        }
        Quick_Sort_Ascending(a, 0, n-1);
        System.out.print(a[0]);
        for (int i=1 ; i<n ; i++) {
            System.out.print(" "+a[i]);
        }
    }
}
