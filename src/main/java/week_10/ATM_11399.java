package week_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 30..
 */
public class ATM_11399 {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] line = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(getMinTime(line));
        br.close();
    }

    private static int getMinTime(int[] line) {
        Arrays.sort(line);
        int sum = 0;
        int[] dp = new int[N+1];
        for (int i=1; i<=N; i++) {
            dp[i] = dp[i-1] + line[i];
            sum += dp[i];
        }
        System.out.println(Arrays.toString(dp));
        return sum;
    }
}
