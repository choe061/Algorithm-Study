package week_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 23..
 */
public class Move_11048 {
    private static final int[] X = {1, 0, 1};
    private static final int[] Y = {0, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][M+1];
        int[][] dp = new int[N+1][M+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(map, dp);
        System.out.println(dp[N][M]);
        br.close();
    }

    private static void bfs(int[][] map, int[][] dp) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(1, 1));
        dp[1][1] = map[1][1];

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            for (int i=0; i<3; i++) {
                int adj_x = pair.getX() + X[i];
                int adj_y = pair.getY() + Y[i];
                if (adj_x >= 1 && adj_y >= 1 && adj_x < map.length && adj_y < map[0].length) {
                    //if (dp[adj_x][adj_y] <= dp[pair.getX()][pair.getY()] + map[adj_x][adj_y])
                    //시간초과나는 코드 <=에서 =는 필요없는 코드이다
                    if (dp[adj_x][adj_y] < dp[pair.getX()][pair.getY()] + map[adj_x][adj_y]) {
                        dp[adj_x][adj_y] = dp[pair.getX()][pair.getY()] + map[adj_x][adj_y];
                        queue.offer(new Pair(adj_x, adj_y));
                    }
                }
            }
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
