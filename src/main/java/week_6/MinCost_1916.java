package week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 7. 12..
 * 최소비용 구하기
 * 다익스트라 문제
 * 1. 도시의 갯수 = 정점의 갯수
 * 2. A도시에서 B도시로 운행하는 버스 = 간선
 * 3. 도착하는데 걸리는 시간 = 가중치
 * 1~3번을 보면 다익스트라 기본 문제와 똑같다
 */
public class MinCost_1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    //도시 = 정점 갯수
        int m = Integer.parseInt(br.readLine());    //버스 갯수
        int[] distance = new int[n+1];
        ArrayList<ArrayList<Map>> maps = new ArrayList<>(); //인접 리스트로 구현
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i=0; i<=n; i++) {
            maps.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());   //출발
            int e = Integer.parseInt(st.nextToken());   //도착
            int w = Integer.parseInt(st.nextToken());   //가중치
            maps.get(s).add(new Map(e, w));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra(start, end, maps, distance));
        br.close();
    }

    private static int dijkstra(int start, int end, ArrayList<ArrayList<Map>> maps, int[] distance) {
        PriorityQueue<Map> pq = new PriorityQueue<>();
        distance[start] = 0;
        pq.offer(new Map(start, distance[start]));
        ArrayList<Integer> cost = new ArrayList<>();

        while (!pq.isEmpty()) {
            Map map = pq.poll();
            if (map.getV() == end) {
                cost.add(map.getW());
            }
            if (distance[map.getV()] >= map.getW()) {
                for (Map m : maps.get(map.getV())) {
                    int adj_v = m.getV();
                    int adj_w = m.getW();

                    if (distance[adj_v] > distance[map.getV()] + adj_w) {
                        distance[adj_v] = distance[map.getV()] + adj_w;
                        pq.offer(new Map(adj_v, distance[adj_v]));
                    }
                }
            }
        }
        return Collections.min(cost);
    }

    static class Map implements Comparable<Map> {
        private int v, w;

        public Map(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }

        @Override
        public int compareTo(Map o) {
            return (this.w >= o.w) ? 1 : -1;
        }
    }
}
