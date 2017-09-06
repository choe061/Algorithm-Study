package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 5..
 * 3
 * 6
 * 0 2 0 0 0 2
 * 0 0 2 0 1 0
 * 1 0 0 2 2 0
 * 정답 : 2
 * 3
 * 3
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * 정답 : 6
 */
public class Main2 {
    private static final int MOD = 20170805;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(m, n, map));
        br.close();
    }

    private static int solution(int m, int n, int[][] cityMap) {
        Navi[][] dp = new Navi[m][n];
        dp[0][0] = new Navi(1, 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0 || j != 0) {
                    int right = 0, down = 0;
                    if (i - 1 >= 0) {
                        down = dp[i - 1][j].getV();
                    }
                    if (j - 1 >= 0) {
                        right = dp[i][j - 1].getH();
                    }
                    if (cityMap[i][j] == 0) {
                        int sum = (right + down) % MOD;
                        dp[i][j] = new Navi(sum, sum);
                    } else if (cityMap[i][j] == 1) {
                        dp[i][j] = new Navi(0, 0);
                    } else {
                        dp[i][j] = new Navi(right, down);
                    }
                }
            }
        }

        Navi end = dp[m - 1][n - 1];
        return end.getH() % MOD;
    }

    static class Navi {
        private int h;
        private int v;

        public Navi(int h, int v) {
            this.h = h;
            this.v = v;
        }

        public int getH() {
            return h;
        }

        public int getV() {
            return v;
        }

        public void setH(int h) {
            this.h = h;
        }

        public void setV(int v) {
            this.v = v;
        }
    }
}
