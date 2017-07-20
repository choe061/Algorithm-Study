package week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 7. 12..
 * 4방향 탐색이 아닌 대각선 방향을 포함한 8방향 탐색
 * 내가 생각한 방법
 * 범위안에 있는 값이면 가중치가 0
 * 없는 값이면 가중치가 범위를 벗어난 만큼
 *
 * 다익스트라로 최단거리를 계산하면 0이 되거나 최단거리가 구해짐
 * 기존의 (MAX - MIN) + K를 가는 최단거리 중 가장 높은 값을 더함
 */
public class Sangduk_2842 {       //상, 하, 좌, 우,5시, 7시, 11시, 1시
    private static final int[] X = {0, 0, -1, 1, 1,  1, -1, -1};
    private static final int[] Y = {-1, 1, 0, 0, 1, -1, -1,  1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N+1][N+1];              //높이
        int[][] height = new int[N+1][N+1];             //고도
        ArrayList<Pair> k_location = new ArrayList<>(); //K가 있는 위치의 리스트
        Pair p = new Pair();                                         //P가 있는 위치, 시작점(이자 목표지점)
        int[][] cost = new int[N+1][N+1];
        int[][] distance = new int[N+1][N+1];

        for (int i=1; i<=N; i++) {
            String s = br.readLine();
            for (int j=1; j<=N; j++) {
                map[i][j] = s.charAt(j-1);
                if (map[i][j] == 'K') {
                    k_location.add(new Pair(i, j));
                } else if (map[i][j] == 'P') {
                    p = new Pair(i, j);
                }
            }
        }

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = getMax(height, k_location);
        int min = getMin(height, k_location);
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                distance[i][j] = Integer.MAX_VALUE;
                //범위 안에 없다면 가중치는 값 - 범위와의 오차만큼
                if (height[i][j] < min) {
                    cost[i][j] = min - height[i][j];
                } else if (height[i][j] > max) {
                    cost[i][j] = height[i][j] - max;
                } else {    //범위 안에 있다면 가중치는 0
                    cost[i][j] = 0;
                }
            }
        }

        for (int i=1; i<=N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(cost[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        dijkstra(p.x, p.y, cost, distance);

        for (int i=1; i<=N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(distance[i][j]+" ");
            }
            System.out.println();
        }

        int result = max - min + getMaxK(distance, k_location);
        System.out.println(result);
        br.close();
    }

    private static void dijkstra(int x, int y, int[][] cost, int[][] distance) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        distance[x][y] = cost[x][y];
        pq.offer(new Pair(x, y, distance[x][y]));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (distance[p.x][p.y] >= p.w) {
                System.out.println("Pair : "+p.x+", "+p.y+", "+p.w);
                for (int i=0; i<8; i++) {
                    int adj_x = p.x + X[i];
                    int adj_y = p.y + Y[i];
                    if (adj_x > 0 && adj_y > 0 && adj_x < distance.length && adj_y < distance.length) {
                        System.out.println("Adj : "+adj_x+", "+adj_y+", "+distance[adj_x][adj_y]);
                        if (distance[adj_x][adj_y] > distance[p.x][p.y] + cost[adj_x][adj_y]) {
                            System.out.println(p.x+", "+p.y+", "+distance[p.x][p.y]);
                            distance[adj_x][adj_y] = distance[p.x][p.y] + cost[adj_x][adj_y];
                            pq.offer(new Pair(adj_x, adj_y, distance[adj_x][adj_y]));
                        }
                    }
                }
            }
        }
    }

    private static int getMaxK(int[][] distance, ArrayList<Pair> k_location) {
        int max = Integer.MIN_VALUE;
        for (Pair pair : k_location) {
            max = (max < distance[pair.x][pair.y]) ? distance[pair.x][pair.y] : max;
        }
        return max;
    }

    //K지점들 중 가장 고도가 낮은 값을 구한다
    private static int getMin(int[][] height, ArrayList<Pair> k_location) {
        int min = Integer.MAX_VALUE;
        for (Pair p : k_location) {
            int h = height[p.x][p.y];
            min = (min > h) ? h : min;
        }
        return min;
    }

    //K지점들 중 가장 고도가 높은 값을 구한다
    private static int getMax(int[][] height, ArrayList<Pair> k_location) {
        int max = Integer.MIN_VALUE;
        for (Pair p : k_location) {
            int h = height[p.x][p.y];
            max = (max < h) ? h : max;
        }
        return max;
    }

    static class Pair implements Comparable<Pair> {
        private int x, y, w;

        public Pair() {
        }

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int w) {
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