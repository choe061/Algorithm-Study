package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int[] dp = new int[51];         //i일 까지 얻는 이익
        int max = Integer.MIN_VALUE;
        for (int i=1; i<length; i++) {
            int next1 = i + T[i];
            int next2 = i + 1;

            if (next1 <= length) {
                dp[next1] = Math.max(dp[next1], dp[i] + P[i]);
            }
            if (next2 <= length) {
                dp[next2] = Math.max(dp[next2], dp[i]);
            }
            max = Math.max(Math.max(max, dp[next1]), dp[next2]);
        }
        return max;
    }
}
