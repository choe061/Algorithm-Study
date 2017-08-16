package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 8. 16..
 */
public class Area_2583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[M][N];
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            setMap(map, new Square(x1, y1, x2, y2));
        }

        Area area = new Area(map, M, N);
        System.out.println(area.getAreaCount());
        System.out.println(area.getAreas());
        br.close();
    }

    private static void setMap(boolean[][] map, Square square) {
        for (int i=square.getY1(); i<square.getY2(); i++) {
            for (int j=square.getX1(); j<square.getX2(); j++) {
                map[i][j] = true;
            }
        }
    }

    static class Area {
        private final int[] X = {1, 0, -1, 0};
        private final int[] Y = {0, 1, 0, -1};

        private boolean[][] map;
        private int[][] numberMap;
        private int M, N;
        ArrayList<Integer> areas = new ArrayList<>();

        public Area(boolean[][] map, int m, int n) {
            this.map = map;
            M = m;
            N = n;
            numberMap = new int[M][N];
        }

        public int getAreaCount() {
            int count = 0;
            boolean[][] visited = new boolean[M][N];
            for (int i=0; i<M; i++) {
                for (int j=0; j<N; j++) {
                    if (!visited[i][j] && !map[i][j]) {
                        bfs(visited, new Pair(i, j), ++count);
                    }
                }
            }
            return count;
        }

        private void bfs(boolean[][] visited, Pair pair, int count) {
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(pair);
            visited[pair.getX()][pair.getY()] = true;
            numberMap[pair.getX()][pair.getY()] = count;
            int area = 1;

            while (!queue.isEmpty()) {
                Pair p = queue.poll();
                for (int i=0; i<4; i++) {
                    int adj_x = p.getX() + X[i];
                    int adj_y = p.getY() + Y[i];
                    if (adj_x >= 0 && adj_y >= 0 && adj_x < M && adj_y < N) {
                        if (!map[adj_x][adj_y] && !visited[adj_x][adj_y]) {
                            queue.offer(new Pair(adj_x, adj_y));
                            visited[adj_x][adj_y] = true;
                            numberMap[adj_x][adj_y] = count;
                            area++;
                        }
                    }
                }
            }
            areas.add(area);
        }

        public String getAreas() {
            Collections.sort(areas);
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<areas.size(); i++) {
                sb.append(areas.get(i)).append(" ");
            }
            return sb.toString();
        }

        class Pair {
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

    static class Square {
        private int x1, y1, x2, y2;

        public Square(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }
    }

}
