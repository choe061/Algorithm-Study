package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 14..
 */
public class BOJ_4485 {

    private static final int[] X = {0, -1, 0, 1};
    private static final int[] Y = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N;
        int[][] map;
        int[][] distance;
        int count = 1;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            map = new int[N+1][N+1];
            distance = new int[N+1][N+1];
            for (int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=1; j<=N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            dijkstra(map, distance);
            System.out.println("Problem "+(count++)+": "+distance[N][N]);
        }
        br.close();
    }

    private static void dijkstra(int[][] map, int[][] distance) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        distance[1][1] = map[1][1];
        pq.offer(new Pair(1, 1, distance[1][1]));

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            if (distance[pair.x][pair.y] >= pair.w) {
                for (int i=0; i<4; i++) {
                    int adj_x = pair.x + X[i];
                    int adj_y = pair.y + Y[i];
                    if (adj_x > 0 && adj_y > 0 && adj_x < map.length && adj_y < map.length) {
                        if (distance[adj_x][adj_y] > distance[pair.x][pair.y] + map[adj_x][adj_y]) {
                            distance[adj_x][adj_y] = distance[pair.x][pair.y] + map[adj_x][adj_y];
                            pq.offer(new Pair(adj_x, adj_y, distance[adj_x][adj_y]));
                        }
                    }
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        private int x, y, w;

        Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {
            return (this.w >= o.w) ? 1 : -1;
        }
    }
}
