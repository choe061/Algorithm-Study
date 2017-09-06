package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 9. 2..
 */
public class Iceberg_2573 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        Queue<Pair> ice = new LinkedList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    ice.offer(new Pair(i, j));
                }
            }
        }
        Iceberg iceberg = new Iceberg(N, M, map, ice);
        System.out.println(iceberg.getMinYear());
        br.close();
    }

    static class Iceberg {
        private final int[] Y = {0, 1, 0, -1};
        private final int[] X = {1, 0, -1, 0};
        private int N, M;
        private int[][] map;
        private Queue<Pair> ice;

        public Iceberg(int n, int m, int[][] map, Queue<Pair> ice) {
            N = n;
            M = m;
            this.map = map;
            this.ice = ice;
        }

        public int getMinYear() {
            int year = 0;
            while (!ice.isEmpty()) {
                if (isTwoChunck(ice.peek(), ice.size())) {
                    return year;
                }

                Queue<Pair> tempIce = new LinkedList<>();
                tempIce.addAll(ice);
                ice.clear();
                year++;
                ArrayList<Pair> meltingIce = new ArrayList<>();
                while (!tempIce.isEmpty()) {
                    iceDown(tempIce.poll(), meltingIce);
                }
                for (Pair m : meltingIce) {
                    map[m.getY()][m.getX()] = m.getHeight();
                }
            }
            return 0;
        }

        /**
         * @param p 빙산 p의 동서남북을 살펴 얼마나 내려갈지 계산
         * @param meltingIce 빙산 p를 계산한 후 지도에 바로 넣기 전 meltingIce에 임시로 넣는다
         */
        private void iceDown(Pair p, ArrayList<Pair> meltingIce) {
            int adj_zero = 0;
            for (int i=0; i<4; i++) {
                int adj_y = p.getY() + Y[i];
                int adj_x = p.getX() + X[i];
                if (adj_y >= 0 && adj_x >= 0 && adj_y < N && adj_x < M) {
                    if (map[adj_y][adj_x] == 0) {
                        adj_zero++;
                    }
                }
            }
            int height = map[p.getY()][p.getX()] - adj_zero;
            if (height <= 0) {
                height = 0;
            } else {
                ice.offer(new Pair(p.getY(), p.getX()));
            }
            meltingIce.add(new Pair(p.getY(), p.getX(), height));
        }

        /**
         * @param start bfs탐색을 시작할 빙산 start 지점
         * @param iceSize 맵에 존재하는 빙산의 갯수
         * @return start지점에 인접해있는 빙산의 갯수를 구한다.
         * 그 갯수가 맵에 존재하는 총 빙산의 갯수와 일치하면 덩어리가 한 개 있는 것
         * 더 적다면 또 다른 덩어리가 존재한다는 것
         */
        private boolean isTwoChunck(Pair start, int iceSize) {
            boolean[][] visited = new boolean[N][M];
            Queue<Pair> chunck = new LinkedList<>();
            chunck.offer(start);
            visited[start.getY()][start.getX()] = true;
            int chunckSize = 1;
            while (!chunck.isEmpty()) {
                Pair pair = chunck.poll();
                for (int i=0; i<4; i++) {
                    int adj_y = pair.getY() + Y[i];
                    int adj_x = pair.getX() + X[i];
                    if (adj_y >= 0 && adj_x >= 0 && adj_y < N && adj_x < M) {
                        if (!visited[adj_y][adj_x] && map[adj_y][adj_x] > 0) {
                            chunck.offer(new Pair(adj_y, adj_x));
                            visited[adj_y][adj_x] = true;
                            chunckSize++;
                        }
                    }
                }
            }
            return chunckSize != iceSize;
        }
    }

    static class Pair {
        private int y, x;
        private int height;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Pair(int y, int x, int height) {
            this.y = y;
            this.x = x;
            this.height = height;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getHeight() {
            return height;
        }
    }
}
