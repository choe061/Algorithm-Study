package kakaodemo;

/**
 * Created by choi on 2017. 9. 12. PM 4:31.
 */
public class Demo_5 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}}));
    }

    private static int solution(int[][] land) {
        int N = land.length;
        int[][] dp = new int[N][4];
        for (int i=0; i<4; i++) {
            dp[0][i] = land[0][i];
        }
        for (int i=1; i<N; i++) {
            dp[i][0] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][3]) + land[i][0];
            dp[i][1] = Math.max(Math.max(dp[i-1][0], dp[i-1][2]), dp[i-1][3]) + land[i][1];
            dp[i][2] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][3]) + land[i][2];
            dp[i][3] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]) + land[i][3];
        }
        return Math.max(Math.max(dp[N-1][0], dp[N-1][1]), Math.max(dp[N-1][2], dp[N-1][3]));
    }
}
