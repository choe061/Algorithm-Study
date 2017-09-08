package week_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 9. 9..
 */
public class SafeArea_2468 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] area = new int[N][N];
        int top = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                top = Math.max(top, area[i][j]);
            }
        }
        SafeArea safeArea = new SafeArea(N, area, top);
        System.out.println(safeArea.getMaxSafeArea());
        br.close();
    }

    static class SafeArea {
        private final int[] Y = {0, 1, 0, -1};
        private final int[] X = {1, 0, -1, 0};
        private int N, top;
        private int[][] area;

        public SafeArea(int n, int[][] area, int top) {
            N = n;
            this.top = top;
            this.area = area;
        }

        /**
         * 지역의 높이 1~100
         * 비가 오지 않는 경우 1 리턴
         * @return
         */
        public int getMaxSafeArea() {
            int countOfSafeArea = 1;
            for (int i=1; i<=top; i++) {
                countOfSafeArea = Math.max(countOfSafeArea, getSafeSize(i));
            }
            return countOfSafeArea;
        }

        private int getSafeSize(int height) {
            boolean[][] visited = new boolean[N][N];
            int count = 0;
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (!visited[i][j] && area[i][j] > height) {
                        count++;
                        safe(visited, new Pair(i, j), height);
                    }
                }
            }
            return count;
        }

        private void safe(boolean[][] visited, Pair now, int height) {
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(now);
            visited[now.getY()][now.getX()] = true;

            while (!queue.isEmpty()) {
                Pair p = queue.poll();
                for (int i=0; i<4; i++) {
                    int adj_y = p.getY() + Y[i];
                    int adj_x = p.getX() + X[i];
                    if (adj_y >= 0 && adj_x >= 0 && adj_y < N && adj_x < N) {
                        if (!visited[adj_y][adj_x]) {
                            if (area[adj_y][adj_x] > height) {
                                queue.offer(new Pair(adj_y, adj_x));
                            }
                            visited[adj_y][adj_x] = true;
                        }
                    }
                }
            }
        }
    }

    static class Pair {
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
