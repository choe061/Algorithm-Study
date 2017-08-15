package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 15..
 */
public class MazeSearch_2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i=0; i<N; i++) {
            String[] temp = br.readLine().split("");
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        Maze maze = new Maze(N, M, map);
        System.out.println(maze.getMinDistance());
        br.close();
    }

    static class Maze {
        final int[] X = {1, -1, 0, 0};
        final int[] Y = {0, 0, -1, 1};

        int N, M;
        int[][] map;
        int[][] distance;
        boolean[][] visited;

        public Maze(int n, int m, int[][] map) {
            N = n;
            M = m;
            this.map = map;
            distance = new int[N][M];
            visited = new boolean[N][M];
        }

        public int getMinDistance() {
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(0, 0));
            visited[0][0] = true;
            distance[0][0] = 1;

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();

                for (int i=0; i<4; i++) {
                    int adj_x = pair.getX() + X[i];
                    int adj_y = pair.getY() + Y[i];
                    if (adj_x >= 0 && adj_y >= 0 && adj_x < N && adj_y < M && map[adj_x][adj_y] == 1) {
                        if (!visited[adj_x][adj_y]) {
                            setDistance(queue, pair, adj_x, adj_y);
                        } else {
                            if (distance[pair.getX()][pair.getY()] + map[adj_x][adj_y] < distance[adj_x][adj_y]) {
                                setDistance(queue, pair, adj_x, adj_y);
                            }
                        }
                    }
                }
            }
            return distance[N-1][M-1];
        }

        private void setDistance(Queue<Pair> queue, Pair pair, int x, int y) {
            distance[x][y] = distance[pair.getX()][pair.getY()] + 1;
            queue.offer(new Pair(x, y));
            visited[x][y] = true;
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

            @Override
            public String toString() {
                return "Pair{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }
    }
}
