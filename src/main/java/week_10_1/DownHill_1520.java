package week_10_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 5..
 */
public class DownHill_1520 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][N];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Route route = new Route(M, N, map);
        System.out.println(route.searchRoute(0, 0));
        br.close();
    }

    static class Route {
        private final int[] Y = {-1, 0, 1, 0};
        private final int[] X = {0, -1, 0, 1};
        private int m, n;
        private int[][] map;
        private int[][] dp;

        public Route(int m, int n, int[][] map) {
            this.m = m;
            this.n = n;
            this.map = map;
            dp = new int[m][n];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
        }

        public int searchRoute(int x, int y) {
            System.out.println(x + ", " + y + " = " + dp[x][y]);
            if (x == m - 1 && y == n - 1) {
                return 1;
            }
            if (dp[x][y] != -1) {
                return dp[x][y];
            }
            dp[x][y] = 0;
            for (int i=0; i<4; i++) {
                int next_y = y + Y[i];
                int next_x = x + X[i];
                if (next_x >= 0 && next_y >= 0 && next_x < m && next_y < n) {
                    if (map[x][y] > map[next_x][next_y]) {
                        dp[x][y] += searchRoute(next_x, next_y);
                    }
                }
            }
            return dp[x][y];
        }
    }
}
