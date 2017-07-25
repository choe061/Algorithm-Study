package week_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 25..
 */
public class BOJ_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(getMaxValue(nums));
        br.close();
    }

    private static int getMaxValue(int[][] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];

        dp[0][0] = nums[0][0];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {           //각 라인에서 가장 왼쪽(0번째)에 저장되는 dp는 윗줄(i-1)의 0번째 dp에서 내려오는 경로 밖에 없다
                    dp[i][j] = nums[i][j] + dp[i-1][j];
                } else if (j == i) {    //각 라인에서 가장 오른쪽에 저장되는 dp는 윗줄의 가장 마지막에 저장된 dp에서 내려오는 경로 밖에 없다
                    dp[i][j] = nums[i][j] + dp[i-1][j-1];
                } else {                //왼쪽에서 내려오는 경로와 오른쪽에서 내려오는 경로 중 큰 값을 가지는 경로를 선택
                    dp[i][j] = nums[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
                }
            }
        }
        Arrays.sort(dp[length - 1]);    //마지막줄 배열을 정렬
        return dp[length - 1][length - 1];
    }
}
