package week_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 22..
 */
public class BOJ_1504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //정점의 개수
        int E = Integer.parseInt(st.nextToken());   //간선의 개수
        ArrayList<ArrayList<Graph>> graphs = new ArrayList<>();
        int[] distance = new int[N+1];
        for (int i=0; i<=N; i++) {
            graphs.add(new ArrayList<>());
        }
        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graphs.get(a).add(new Graph(b, c));
            graphs.get(b).add(new Graph(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        long[] sum = new long[2];
        dijkstra(1, graphs, distance);
        sum[0] += (distance[A] <= Integer.MAX_VALUE) ? distance[A] : 0;      //1 -> A
        sum[1] += (distance[B] <= Integer.MAX_VALUE) ? distance[B] : 0;      //1 -> B
        dijkstra(A, graphs, distance);
        sum[0] += (distance[B] <= Integer.MAX_VALUE) ? distance[B] : 0;      //A -> B
        sum[1] += (distance[N] <= Integer.MAX_VALUE) ? distance[N] : 0;      //A -> N
        dijkstra(B, graphs, distance);
        sum[0] += (distance[N] <= Integer.MAX_VALUE) ? distance[N] : 0;      //B -> N
        sum[1] += (distance[A] <= Integer.MAX_VALUE) ? distance[A] : 0;      //B -> A
        long result = Math.min(sum[0], sum[1]);
        System.out.println((result <= Integer.MAX_VALUE) ? result : -1);
        br.close();
    }

    private static void dijkstra(int start, ArrayList<ArrayList<Graph>> graphs, int[] distance) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        distance[start] = 0;
        pq.offer(new Graph(start, distance[start]));

        while (!pq.isEmpty()) {
            Graph graph = pq.poll();
            int nb = graph.getB();
            int nc = graph.getC();
            if (distance[nb] >= nc) {
                for (Graph g : graphs.get(nb)) {
                    int adj_v = g.getB();
                    int adj_w = g.getC();
                    if (distance[adj_v] > distance[nb] + adj_w) {
                        distance[adj_v] = distance[nb] + adj_w;
                        pq.offer(new Graph(adj_v, distance[adj_v]));
                    }
                }
            }
        }
    }

    static class Graph implements Comparable<Graph> {
        private int b, c;

        public Graph(int b, int c) {
            this.b = b;
            this.c = c;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }

        @Override
        public int compareTo(Graph o) {
            return this.c >= o.c ? 1 : -1;
        }
    }
}
