package ShaBi_0;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class luogu_P1298 {
    public static String str1;
    public  static String str2;
    public  static String temp1, temp2;
    public static int left, right, num_index;

    public static String read (int index) {
        if (index>=str1.length()) {
            return temp2;
        }
        int i = index;
        String s1, s2, s3;
        s1 = new String("");
        s2 = new String("");
        s3 = new String("");
        while (str1.charAt(i)!='['&&str1.charAt(i)!=']'&&i<=str1.length()-1) {
             s1=s1+str1.charAt(i);
             i++;
        }
        if (str1.charAt(i)=='[') {
            int j=i+1;

            for ( ; j<str1.length()-1 ; j++) {
                //System.out.println(j);
                if(!(str1.charAt(j)>='0'&&str1.charAt(j)<='9')) {

                    break;
                }
            }
            //System.out.println(i);
            //System.out.println(j+1);
            s3 = str1.substring(i+1, j);

            int n = Integer.valueOf(s3);
            //System.out.println(n);
            for ( ; n>=1 ; n--) {
                s2=read(j);
                s1=s1+s2;
            }
        }
        System.out.println(i);
        if (str1.charAt(i)==']') {
            System.out.println(i);
            if (i<str1.length()-1) {
                s2=read(i+1);
                s1=s1+s2;
            }
        }
        return s1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        str1 = sc.next();
        //System.out.println(str1);
        if (str1.length()<=1) {
            System.out.print(str1);
            return;
        }
        str1=str1+" ";
        System.out.print(read(0));
    }
}
