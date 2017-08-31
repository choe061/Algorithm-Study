package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 31..
 */
public class Cabbage_1012 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            boolean[][] map = new boolean[N][M];
            for (int j=0; j<K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            }
            Cabbage cabbage = new Cabbage(N, M, map);
            System.out.println(cabbage.getMinEarthworm());
        }
        br.close();
    }

    static class Cabbage {
        private final int[] Y = {0, 1, 0, -1};
        private final int[] X = {1, 0, -1, 0};

        private int N, M;
        private boolean[][] map;
        private int[][] earthwormNum;
        private boolean[][] visited;

        public Cabbage(int n, int m, boolean[][] map) {
            N = n;
            M = m;
            this.map = map;
            earthwormNum = new int[N][M];
            visited = new boolean[N][M];
        }

        public int getMinEarthworm() {
            int n = 0;
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (!visited[i][j] && map[i][j]) {
                        boundary(i, j, ++n);
                    }
                }
            }
            return n;
        }

        private void boundary(int i, int j, int n) {
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(i, j));
            visited[i][j] = true;
            earthwormNum[i][j] = n;

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                for (int k=0; k<4; k++) {
                    int adj_y = pair.getY() + Y[k];
                    int adj_x = pair.getX() + X[k];
                    if (adj_y >= 0 && adj_x >= 0 && adj_y < N && adj_x < M) {
                        if (!visited[adj_y][adj_x] && map[adj_y][adj_x]) {
                            visited[adj_y][adj_x] = true;
                            earthwormNum[adj_y][adj_x] = n;
                            queue.offer(new Pair(adj_y, adj_x));
                        }
                    }
                }
            }
        }

        class Pair {
            private int y, x;

            public Pair(int y, int x) {
                this.y = y;
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }
    }
}
