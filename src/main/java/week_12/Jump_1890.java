package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 17..
 */
public class Jump_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Game game = new Game(map);
        System.out.println(game.getRouteCount());
        br.close();
    }

    static class Game {
        private final int[] X = {1, 0};
        private final int[] Y = {0, 1};
        private int[][] map;
        private long[][] dp;
        private int N;

        public Game(int[][] map) {
            this.map = map;
            N = map.length;
            dp = new long[N][N];
            for (long[] d : dp) {
                Arrays.fill(d, -1);
            }
        }

        public long getRouteCount() {
            return dfs(0, 0);
        }

        private long dfs(int x, int y) {
            if (x == N-1 && y == N-1) {
                return 1;
            }
            if (dp[x][y] != -1) {   //이미 값이 갱신된 곳을 발견하면 그 경로의 값을 리턴해준다
                return dp[x][y];
            }
            dp[x][y] = 0;
            for (int i=0; i<2; i++) {
                int adj_x = x + (X[i] * map[x][y]);
                int adj_y = y + (Y[i] * map[x][y]);
                if (adj_x >= 0 && adj_y >= 0 && adj_x < N && adj_y < N) {
                    dp[x][y] += dfs(adj_x, adj_y);
                }
            }
            System.out.println("------------------");
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    System.out.print(dp[i][j]+"\t");
                }
                System.out.println();
            }
            return dp[x][y];
        }
    }
}
