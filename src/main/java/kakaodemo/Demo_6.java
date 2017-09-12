package kakaodemo;

/**
 * Created by choi on 2017. 9. 12. AM 10:52.
 */
public class Demo_6 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10}));
        System.out.println(solution(new int[]{1, 3, 2, 5, 4}));
        System.out.println(solution(new int[]{1,1,100,1,1,1,100}));
    }

    private static int solution(int[] sticker) {
        int N = sticker.length;
        if (N == 1) {
            return sticker[0];
        } else if (N == 2) {
            return sticker[0] > sticker[1] ? sticker[0] : sticker[1];
        } else if (N == 3) {    //3개의 경우도 1개 밖에 선택할 수 없다
            return Math.max(sticker[0], Math.max(sticker[1], sticker[2]));
        }
        int[][] dp = new int[N][2];
        dp[0][0] = 0;
        dp[0][1] = sticker[0];
        dp[1][0] = sticker[1];
        dp[1][1] = sticker[1];
        for (int i = 2; i < N; i++) {
            dp[i][0] = Math.max(dp[i-2][0] + sticker[i], dp[i-1][0]);
            if (i < N - 1) {
                dp[i][1] = Math.max(dp[i - 2][1] + sticker[i], dp[i - 1][1]);
            }
        }

        return Math.max(dp[N-1][0], dp[N-2][1]);
    }

    private static int solution2(int[] sticker) {
        int N = sticker.length;
        if (N == 1) {
            return sticker[0];
        } else if (N == 2) {
            return Math.max(sticker[0], sticker[1]);
        } else if (N == 3) {    //3개의 경우도 1개 밖에 선택할 수 없다
            return Math.max(sticker[0], Math.max(sticker[1], sticker[2]));
        }
        int[][] dp = new int[N][3];
        dp[0][0] = 0;
        dp[0][1] = sticker[0];
        dp[0][2] = 0;
        dp[1][0] = dp[0][1];
        dp[1][1] = sticker[1];
        dp[1][2] = 0;
        for (int i=2; i<N; i++) {
            dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1] + sticker[i]), dp[i-1][2] + sticker[i]);
//            dp[i][1] = Math.max(Math.max(dp[i-1][0], ));
        }
        return 1;
    }
}
