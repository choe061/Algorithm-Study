package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 16..
 */
public class BOJ_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+1];
        int[] P = new int[N+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(getMaxBenefit(T, P));
        br.close();
    }

    private static int getMaxBenefit(int[] T, int[] P) {
        int length = T.length;
        int[] dp = new int[length];
        dp[0] = 0;
        for (int i=1; i<length; i++) {
            System.out.println(T[i]+", "+P[i]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[length - 1];
    }

    private static double getWageOfDay(int[] T, int[] P, int today) {
        int lastDay = T.length;
        if (T[today] + today - 1 > lastDay) {
            return 0;   //N을 넘는다, 퇴사 전에 끝나는 일이 아님
        } else {
            return (double) P[today] / T[today];
        }
    }
}
