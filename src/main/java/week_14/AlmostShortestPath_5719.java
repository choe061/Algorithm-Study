package week_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 9. 5..
 */
public class AlmostShortestPath_5719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            ArrayList<ArrayList<Graph>> map = new ArrayList<>();
            ArrayList<ArrayList<Graph>> reverseMap = new ArrayList<>();
            for (int i=0; i<N; i++) {
                map.add(new ArrayList<>());
                reverseMap.add(new ArrayList<>());
            }
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                map.get(U).add(new Graph(V, P));
                reverseMap.get(V).add(new Graph(U, P));
            }
            Navigation navigation = new Navigation(map, reverseMap, N, S, D);
            System.out.println(navigation.getAlmostShortestPath());
        }
        br.close();
    }

    static class Navigation {
        private ArrayList<ArrayList<Graph>> map;
        private ArrayList<ArrayList<Graph>> reverseMap;
        private int[] distance;
        private boolean[][] visited;
        private int start, end;


        public Navigation(ArrayList<ArrayList<Graph>> map, ArrayList<ArrayList<Graph>> reverseMap, int N, int start, int end) {
            this.map = map;
            this.reverseMap = reverseMap;
            this.distance = new int[N];
            this.visited = new boolean[N][N];
            this.start = start;
            this.end = end;
        }

        public int getAlmostShortestPath() {
            getShortestPath();
            removeShortestPath();
            getShortestPath();
            return (distance[end] >= Integer.MAX_VALUE) ? -1 : distance[end];
        }

        private void getShortestPath() {
            Arrays.fill(distance, Integer.MAX_VALUE);
            PriorityQueue<Graph> pq = new PriorityQueue<>();
            distance[start] = 0;
            pq.offer(new Graph(start, distance[start]));

            while (!pq.isEmpty()) {
                Graph g = pq.poll();
                int v = g.getV();
                int p = g.getP();
                if (distance[v] >= p) {
                    for (Graph graph : map.get(v)) {
                        int adj_v = graph.getV();
                        int adj_p = graph.getP();
                        if (distance[adj_v] > distance[v] + adj_p && !visited[v][adj_v]) {
                            distance[adj_v] = distance[v] + adj_p;
                            pq.offer(new Graph(adj_v, distance[adj_v]));
                        }
                    }
                }
            }
        }

        private void removeShortestPath() {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(end);                       //도착 지점에서부터 출발 지점까지 경로를 구하는 것 (경로는 반대로 탐색)
            while (!queue.isEmpty()) {
                int now = queue.poll();
                if (now != start) {
                    for (Graph g : reverseMap.get(now)) {  //현재 지점의 리스트를 불러옴, reverseMap은 map의 방향을 반대로 저장한 맵(단방향이기 때문에)
                        if (distance[now] == distance[g.getV()] + g.getP()) {   //현재까지의 dist와 인접 지점의 dist + P(가중치)가 같은 경우 해당 지점이 경로
                            visited[g.getV()][now] = true;
                            queue.offer(g.getV());
                        }
                    }
                }
            }
        }
    }

    static class Graph implements Comparable<Graph> {
        private int v, p;

        public Graph(int v, int p) {
            this.v = v;
            this.p = p;
        }

        public int getV() {
            return v;
        }

        public int getP() {
            return p;
        }

        @Override
        public int compareTo(Graph o) {
            return this.p >= o.p ? 1 : -1;
        }
    }
}
