package week_11_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 10..
 */
public class Lab_14502 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        ArrayList<Pair> virus = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new Pair(i, j));
                }
            }
        }
        Laboratory laboratory = new Laboratory(N, M, map, virus);
        System.out.println(laboratory.getSafeArea());
        br.close();
    }

    static class Laboratory {
        private final int BLANK = 0;
        private final int WALL = 0;
        private final int VIRUS = 0;

        private final int[] X = {0, 0, 1, -1};
        private final int[] Y = {1, -1, 0, 0};
        private int N, M;
        private int[][] map;
        private int[][] tempMap;
        private boolean[][] visited;
        private ArrayList<Pair> virus = new ArrayList<>();

        public Laboratory(int n, int m, int[][] map, ArrayList<Pair> virus) {
            N = n;
            M = m;
            this.map = map;
            this.virus = virus;
            tempMap = map;
            visited = new boolean[N][M];
        }

        public int getSafeArea() {
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (map[i][j] == 0) {
                        dfs(i, j, 1);
                    }
                }
            }
            return 1;
        }

        /**
         * 벽 설치는 dfs를 사용
         */
        private void dfs(int x, int y, int count) {
            if (count == 3) {
                bfs();
                rollbackMap();
                return ;
            }

        }

        /**
         * 벽 설치 후 바이러스 퍼트리기는 bfs를 사용
         */
        private void bfs() {
            Queue<Pair> queue = new LinkedList<>();
            for (Pair v : virus) {
                queue.offer(v);
            }
            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                for (int i=0; i<4; i++) {
                    int adj_x = pair.getX() + X[i];
                    int adj_y = pair.getY() + Y[i];
                    if (adj_x >=0 && adj_y >= 0 && adj_x < N && adj_y < M) {
                        if (map[adj_x][adj_y] != 1) {
                            map[adj_x][adj_y] = 2;
                            queue.offer(new Pair(adj_x, adj_y));
                        }
                    }
                }
            }
        }

        private void rollbackMap() {
            this.map = tempMap;
        }
    }

    static class Pair {
        private int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
