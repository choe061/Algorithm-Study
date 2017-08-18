package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 8. 18..
 */
public class BOJ_2667 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i=0; i<N; i++) {
            String[] temp = br.readLine().split("");
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        HouseNumber houseNumber = new HouseNumber(map, N);
        ArrayList<Integer> house = houseNumber.getHouse();
        System.out.println(house.size());
        Collections.sort(house);
        for (int i=0; i<house.size(); i++) {
            System.out.println(house.get(i));
        }
        br.close();
    }

    static class HouseNumber {
        private final int[] X = {0, 1, 0, -1};
        private final int[] Y = {1, 0, -1, 0};

        private int[][] map;
        private int N;

        public HouseNumber(int[][] map, int N) {
            this.map = map;
            this.N = N;
        }

        public ArrayList<Integer> getHouse() {
            ArrayList<Integer> house = new ArrayList<>();
            boolean[][] visited = new boolean[N][N];
//            for (int i=0; i<N; i++) {
//                for (int j=0; j<N; j++) {
//                    if (!visited[i][j] && map[i][j] == 1) {
//                        house.add(bfs(i, j, visited));
//                    }
//                }
//            }
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        int size = 0;
                        size = dfs(i, j, visited, ++size);
                        house.add(size);
                    }
                }
            }
            return house;
        }

        private int bfs(int x, int y, boolean[][] visited) {
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(x, y));
            visited[x][y] = true;
            int size = 1;

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                for (int i=0; i<4; i++) {
                    int adj_x = pair.getX() + X[i];
                    int adj_y = pair.getY() + Y[i];
                    if (adj_x >= 0 && adj_y >= 0 && adj_x < N && adj_y < N) {
                        if (map[adj_x][adj_y] == 1 && !visited[adj_x][adj_y]) {
                            queue.offer(new Pair(adj_x, adj_y));
                            visited[adj_x][adj_y] = true;
                            size++;
                        }
                    }
                }
            }
            return size;
        }

        private int dfs(int x, int y, boolean[][] visited, int size) {
            visited[x][y] = true;
            for (int i=0; i<4; i++) {
                int adj_x = x + X[i];
                int adj_y = y + Y[i];
                if (adj_x >= 0 && adj_y >= 0 && adj_x < N && adj_y < N) {
                    if (map[adj_x][adj_y] == 1 && !visited[adj_x][adj_y]) {
                        size = dfs(adj_x, adj_y, visited, ++size);
                    }
                }
            }
            return size;
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
}
