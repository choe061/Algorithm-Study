package week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 12..
 * DP문제
 */
public class Sticker_9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int[][] sticker;

        for (int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            sticker = new int[2][n+1];
            for (int k=0; k<2; k++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    sticker[k][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(getMaxValue(sticker));
        }

        br.close();
    }

    private static int getMaxValue(int[][] sticker) {
        int length = sticker[0].length;
        int[][] dp = new int[2][length];

        for (int i=1; i<length; i++) {
            dp[0][i] = Math.max(getValue(dp, 1, i-1), getValue(dp, 1, i-2)) + sticker[0][i];
            dp[1][i] = Math.max(getValue(dp, 0, i-1), getValue(dp, 0, i-2)) + sticker[1][i];
        }
        return Math.max(getValue(dp, 0, length-1), getValue(dp, 1, length-1));
    }

    private static int getValue(int[][] dp, int a, int b) {
        if (a < 0 || b < 1) {   //범위를 제한
            return 0;
        }
        return dp[a][b];
    }
}
