package Greedy;

import java.io.*;
import java.util.*;

public class P8787 {
    static Long magic(Long height) {
        return (long) Math.sqrt((height+2)/2);
    }
    static class Bamboo implements Comparable<Bamboo>{
        Integer left, right;
        Long height;


        public Bamboo(Integer left, Integer right, Long height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }

        @Override
        public int compareTo(Bamboo o) {
            if (this.height.equals(o.height)) {
                return this.left - o.left;
            } else {
                return o.height.compareTo(this.height);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        str = br.readLine().split(" ");
        PriorityQueue<Bamboo> bambooQueue = new PriorityQueue<>();
        Long[] heightList = new Long[n];
        Long count = 0L;
        int lastIndex = 0;
        for (int i = 0; i < n; i++) {
            heightList[i] = Long.parseLong(str[i]);
            if (i != 0 && !heightList[i].equals(heightList[lastIndex])) {
                bambooQueue.add(new Bamboo(lastIndex, i-1, heightList[lastIndex]));
                lastIndex = i;
            }
        }
        if (heightList[n-1].equals(heightList[lastIndex])) {
            bambooQueue.add(new Bamboo(lastIndex, n-1, heightList[n-1]));
        }

        while (!bambooQueue.isEmpty()) {
            Bamboo tempBamboo = bambooQueue.poll();
            if (tempBamboo.height <= 1) {
                break;
            }
            if (!bambooQueue.isEmpty()) {
                if (tempBamboo.height.equals(bambooQueue.peek().height)) {
                    if (tempBamboo.right + 1 >= bambooQueue.peek().left) {
                        tempBamboo.left = Math.min(tempBamboo.left, bambooQueue.peek().left);
                        tempBamboo.right = Math.max(tempBamboo.right, bambooQueue.peek().right);
                        bambooQueue.poll();
                        bambooQueue.add(tempBamboo);
                        continue;
                    }
                }
            }
            tempBamboo.height = magic(tempBamboo.height);
            count++;
            if (tempBamboo.height > 1) {
                bambooQueue.add(tempBamboo);
            }
        }

        System.out.println(count);
    }
}