package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 5..
 */
public class Main2 {
    static int MOD = 20170805;

    private static int[] PY = {-1, 0};
    private static int[] PX = {0, -1};

    private static int[] NY = {1, 0};
    private static int[] NX = {0, 1};

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
        System.out.println(soultion(m, n, map));
        br.close();
    }

    private static int soultion(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    if (cityMap[i][j] == 1) {
                        dp[i][j] = 0;
                    } else if (cityMap[i][j] == 2) {    //현재 위치가 2인 경우 직진만
                        if (i - 1 < 0) {
                            dp[i][j] = dp[i][j - 1];
                        } else if (j - 1 < 0) {
                            dp[i][j] = dp[i - 1][j];
                        } else {
                            if (cityMap[i-1][j] == 2 && i == 1) {
                                dp[i][j] = dp[i][j-1];
                            } else if (cityMap[i][j-1] == 2 && j == 1) {
                                dp[i][j] = dp[i-1][j];
                            } else {
                                if (i == m - 1) {
                                    dp[i][j] = dp[i][j-1];
                                } else if (j == n - 1) {
                                    dp[i][j] = dp[i-1][j];
                                } else {
                                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                                }
                            }
                        }
                    } else if (cityMap[i][j] == 0) {    //현재 위치가 0인 경우
                        if (i - 1 < 0) {
                            dp[i][j] = dp[i][j - 1];
                        } else if (j - 1 < 0) {
                            dp[i][j] = dp[i - 1][j];
                        } else {
                            if (cityMap[i-1][j] == 2 && i == 1 ) {
                                dp[i][j] = dp[i][j-1];
                            } else if (cityMap[i][j-1] == 2 && j == 1) {
                                dp[i][j] = dp[i-1][j];
                            } else {
                                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[m - 1][n - 1];
    }
}
