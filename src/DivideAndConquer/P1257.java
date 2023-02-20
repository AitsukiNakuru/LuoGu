package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P1257 {
    static int MAX_INT = 2147483647;
    static int MIN_INT = -2147483648;

    static int parseInt(String str) {
        return Integer.parseInt(str);
    }

    static double parseDouble(String str) {
        return Double.parseDouble(str);
    }

    static double getDouble(double d, int b) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(b);
        return Double.parseDouble(nf.format(d));
    }

    static int n;

    static class Node{
        double x, y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public Node(Node node) {
            this.x = node.x;
            this.y = node.y;
        }

    }

    static Node[] nodeList;

    static double calculateDistance(Node nodeA, Node nodeB) {
        return Math.sqrt(Math.pow(nodeB.x - nodeA.x, 2) + Math.pow(nodeB.y - nodeA.y, 2));
    }

    static double findMinDistance(int left, int right) {

        if (right - left == 1) {
            return calculateDistance(nodeList[left], nodeList[right]);
        }
        int mid = (right + left) / 2;
        if (right - left == 2) {
            double temp =  Math.min(calculateDistance(nodeList[left], nodeList[left+1]), calculateDistance(nodeList[right-1], nodeList[right]));
            return Math.min(temp, calculateDistance(nodeList[left], nodeList[right]));
        }
        double sideMin = Math.min(findMinDistance(left, mid), findMinDistance(mid, right));
        double midMin = MAX_INT;
        ArrayList<Node> tempList = new ArrayList<>();
        for (int i = mid - 1; i>=left; i--) {
            if (Math.abs(nodeList[i].x - nodeList[mid].x) > sideMin) {
                break;
            }
            tempList.add(new Node(nodeList[i]));

        }
        for (int i = mid +1; i<=right; i++) {
            if (Math.abs(nodeList[i].x - nodeList[mid].x) > sideMin) {
                break;
            }
            tempList.add(new Node(nodeList[i]));
        }
        //tempList.sort((o1, o2) -> Double.compare(o1.y, o2.y));
        for (int i = 0; i < tempList.size() - 1; i++) {
            for (int j = i + 1; j < tempList.size(); j++) {
                midMin = Math.min(midMin, calculateDistance(tempList.get(i), tempList.get(j)));
            }
        }
        return Math.min(midMin, sideMin);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        nodeList = new Node[n];
        for (int i = 0; i < n; i++) {
            str = bf.readLine().split(" ");
            nodeList[i] = (new Node(parseDouble(str[0]), parseDouble(str[1])));
        }
        Arrays.sort(nodeList, (Comparator.comparingDouble(o -> o.x)));
        System.out.printf("%.4f", findMinDistance(0, n-1));
    }
}
