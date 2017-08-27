package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 27..
 */
public class Tomato_Easy_7576 {

    private static final int BAD = 1;
    private static final int GOOD = 0;
    private static final int EMPTY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        Queue<Pair> tomatoQueue = new LinkedList<>();
        int[][] countOfDay = new int[N][M];
        boolean isEmptyMap = true, isGood = false;
        for (int i = 0; i < N; i++) {
            Arrays.fill(countOfDay[i], -2);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == BAD) {
                    tomatoQueue.offer(new Pair(i, j));
                    countOfDay[i][j] = 0;
                    isEmptyMap = false;
                }
                if (map[i][j] == GOOD) {
                    isEmptyMap = false;
                    isGood = true;
                }
            }
        }
        if (isEmptyMap) {
            System.out.println(0);
        } else {
            if (!isGood) {
                System.out.println(0);
            } else {
                Tomato tomato = new Tomato(M, N, map, tomatoQueue);
                System.out.println(tomato.getMinimumDay());
            }
        }
    }

    static class Tomato {
        private final int[] X = {1, 0, -1, 0};
        private final int[] Y = {0, 1, 0, -1};

        private int M, N;
        private int[][] map;
        private int[][] days;
        private Queue<Pair> tomatoQueue = new LinkedList<>();

        public Tomato(int m, int n, int[][] map, Queue<Pair> tomatoQueue) {
            M = m;
            N = n;
            this.map = map;
            this.tomatoQueue = tomatoQueue;
            init();
        }

        private void init() {
            days = new int[N][M];
            for (int i=0; i<N; i++) {
                Arrays.fill(days[i], -2);
            }
        }

        public int getMinimumDay() {
            boolean[][] visited = new boolean[N][M];
            int countOfDay = 0;
            while (!tomatoQueue.isEmpty()) {
                Queue<Pair> tempTomato = new LinkedList<>();
                tempTomato.addAll(tomatoQueue);
                tomatoQueue.clear();
                while (!tempTomato.isEmpty()) {
                    Pair tomato = tempTomato.poll();
                    spreadBadTomato(visited, tomato, countOfDay);
                }
                countOfDay++;
            }
            return getResult();
        }

        private void spreadBadTomato(boolean[][] visited, Pair tomato, int countOfDay) {
            visited[tomato.getX()][tomato.getY()] = true;
            days[tomato.getX()][tomato.getY()] = countOfDay;
            for (int i=0; i<4; i++) {
                int adj_x = tomato.getX() + X[i];
                int adj_y = tomato.getY() + Y[i];
                if (adj_x >= 0 && adj_y >= 0 && adj_x < N && adj_y < M) {
                    if (!visited[adj_x][adj_y] && map[adj_x][adj_y] != BAD) {
                        visited[adj_x][adj_y] = true;
                        if (map[adj_x][adj_y] == GOOD) {
                            days[adj_x][adj_y] = days[tomato.getX()][tomato.getY()] + 1;
                            map[adj_x][adj_y] = BAD;
                            tomatoQueue.offer(new Pair(adj_x, adj_y));
                        } else if (map[adj_x][adj_y] == EMPTY) {
                            days[adj_x][adj_y] = EMPTY;
                        }
                    }
                }
            }
        }

        private int getResult() {
            int max = Integer.MIN_VALUE;
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (days[i][j] == -2) {
                        return -1;
                    }
                    max = Math.max(max, days[i][j]);
                }
            }
            return max;
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
