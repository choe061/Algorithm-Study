package week_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 7. 20..
 */
public class BOJ_2579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
        printMaxValue(score);
        br.close();
    }

    private static void printMaxValue(int[] score) {
        int[][] dp = new int[score.length][2];
        dp[0][0] = 0;
        dp[0][1] = score[0];
        dp[1][0] = score[1] + dp[0][1];
        dp[1][1] = score[1];
        for (int i=2; i<score.length; i++) {
            dp[i][0] = score[i] + dp[i-1][1];
            dp[i][1] = score[i] + Math.max(dp[i-2][0], dp[i-2][1]);
        }
        System.out.println(Math.max(dp[score.length - 1][0], dp[score.length - 1][1]));
    }

}
