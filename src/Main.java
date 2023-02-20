import java.util.LinkedList;
import java.util.Scanner;

public class Main  {

    private static node[] map;
    private static int sum, count;
    private static int[] head, dep, cur, sta;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        map = new node[1200010];
        head = new int[2010];
        cur = new int[2010];
        sta = new int[2010];
        init(n);
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = sc.nextInt();
                if (x != 0) {
                    addnode(i, j, x);
                }
            }

        }
        System.out.println(Dinic(a, b, n));
    }

    public static int Dinic(int start, int end, int n) {
        int maxflow = 0;
        while(BFS(start, end, n)) {
            for(int i = 0; i < n; i++) {
                cur[i] = head[i];
            }
            int u = start;
            int tail = 0;
            while(cur[start]!=-1) {
                if(u == end) {
                    int tp = Integer.MAX_VALUE;
                    for(int i = tail-1; i >= 0; i--) {
                        tp = Math.min(tp, map[sta[i]].getVolume()-map[sta[i]].getFlow());
                    }
                    maxflow = maxflow + tp;
                    for(int i = tail-1; i >= 0; i--) {
                        map[sta[i]].setFlow(map[sta[i]].getFlow()+tp);
                        map[sta[i]^1].setFlow(map[sta[i]^1].getFlow()-tp);
                        if(map[sta[i]].getVolume()-map[sta[i]].getFlow()==0) {
                            tail = i;
                        }
                    }
                    u = map[sta[tail]^1].getTo();
                }else if(cur[u]!=-1&&map[cur[u]].getVolume()>map[cur[u]].getFlow()&&dep[u]+1==dep[map[cur[u]].getTo()]) {
                    sta[tail++] = cur[u];
                    u = map[cur[u]].getTo();
                }else {
                    while(u!=start&&cur[u]==-1) {
                        u=map[sta[--tail]^1].getTo();
                    }
                    cur[u]=map[cur[u]].getNext();
                }
            }
        }
        return maxflow;
    }

    public static void init(int n) {
        count = 2;
        for(int i = 0; i <= n; i++) {
            head[i] = -1;
        }
    }

    public static void addnode(int u, int v, int w) {
        map[count] = new node(v, head[u], w, 0);
        head[u] = count;
        count++;
        map[count] = new node(u, head[v], 0, 0);
        head[v] = count;
        count++;
    }

    public static boolean BFS(int start, int end, int n) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        dep = new int[2010];
        for(int i = 0; i <= n; i++) {
            dep[i] = -1;
        }
        dep[start] = 0;
        queue.addLast(start);
        while(queue.size()!=0) {
            int from = queue.removeFirst();
            for(int i = head[from]; i != -1; i = map[i].getNext()) {
                int to = map[i].getTo();
                if(map[i].getVolume()>map[i].getFlow()&&dep[to]==-1) {
                    dep[to] = dep[from] + 1;
                    if(to == end) {
                        return true;
                    }
                    queue.addLast(to);
                }
            }
        }
        return false;
    }
}

class node{
    private int to;
    private int next;
    private int volume;
    private int flow;
    public int getTo() {
        return to;
    }
    public void setTo(int to) {
        this.to = to;
    }
    public int getNext() {
        return next;
    }
    public void setNext(int next) {
        this.next = next;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public int getFlow() {
        return flow;
    }
    public void setFlow(int flow) {
        this.flow = flow;
    }
    public node(int to, int next, int volume, int flow) {
        super();
        this.to = to;
        this.next = next;
        this.volume = volume;
        this.flow = flow;
    }

}

