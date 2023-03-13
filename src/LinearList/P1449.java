package LinearList;

import java.io.BufferedReader;
import java.io.*;
import java.util.LinkedList;

public class P1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split("");
        LinkedList<Integer> numberList = new LinkedList<>();
        boolean flag = true;
        StringBuilder tempNumber = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("@")) {
                break;
            } else if (str[i].equals("-")) {
                int num1 = numberList.pollFirst();
                int num2 = numberList.pollFirst();
                numberList.addFirst(num2 - num1);
            } else if (str[i].equals("/")) {
                int num1 = numberList.pollFirst();
                int num2 = numberList.pollFirst();
                numberList.addFirst(num2 / num1);
            } else if (str[i].equals("*")) {
                int num1 = numberList.pollFirst();
                int num2 = numberList.pollFirst();
                numberList.addFirst(num2 * num1);
            }else if (str[i].equals("+")) {
                int num1 = numberList.pollFirst();
                int num2 = numberList.pollFirst();
                numberList.addFirst(num2 + num1);
            } else if (str[i].equals(".")) {
                numberList.addFirst(Integer.parseInt(tempNumber.toString()));
                tempNumber = new StringBuilder();
            } else {
                tempNumber.append(str[i]);
            }
        }
        System.out.println(numberList.pollFirst());
    }
}
