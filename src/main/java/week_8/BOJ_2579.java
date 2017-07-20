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
        int[] score = new int[N+1];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
        setAccScore(score);
        br.close();
    }

    private static void setAccScore(int[] score) {
        int[][] accScore = new int[score.length][2];
        int size = score.length - 1;
        //0행과 1행은 직접 입력
        accScore[0][1] = score[0];                  //0을 밟은 경우
        accScore[1][0] = score[1] + accScore[0][1]; //0과 1을 밟은 경우
        accScore[1][1] = score[1];                  //0은 밟지 않고 1만 밟은 경우
        for(int i=2; i<size; i++) {
            accScore[i][0] = score[i] + accScore[i-1][1];                               //전 계단을 밟은 경우
            accScore[i][1] = score[i] + Math.max(accScore[i-2][0], accScore[i-2][1]);   //전 계단을 밟지 않은 경우 -2번째 계단 값과 더함
        }
        System.out.println(Math.max(accScore[size-1][0], accScore[size-1][1]));
    }

}
