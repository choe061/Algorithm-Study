package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 2..
 */
public class Down_2096 {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][3];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] nums = getMaxMin(map);
        System.out.println(nums[0]+" "+nums[1]);
        br.close();
    }

    private static int[] getMaxMin(int[][] map) {
        int[][] maxDP = new int[N+1][3];
        int[][] minDP = new int[N+1][3];
        maxDP[1] = map[1];
        minDP[1] = map[1];
        for (int i=2; i<=N; i++) {
            maxDP[i][0] = map[i][0] + Math.max(maxDP[i-1][0], maxDP[i-1][1]);
            maxDP[i][1] = map[i][1] + Math.max(Math.max(maxDP[i-1][0], maxDP[i-1][1]), maxDP[i-1][2]);
            maxDP[i][2] = map[i][2] + Math.max(maxDP[i-1][1], maxDP[i-1][2]);

            minDP[i][0] = map[i][0] + Math.min(minDP[i-1][0], minDP[i-1][1]);
            minDP[i][1] = map[i][1] + Math.min(Math.min(minDP[i-1][0], minDP[i-1][1]), minDP[i-1][2]);
            minDP[i][2] = map[i][2] + Math.min(minDP[i-1][1], minDP[i-1][2]);
        }
        int min = Math.min(Math.min(minDP[N][0], minDP[N][1]), minDP[N][2]);
        int max = Math.max(Math.max(maxDP[N][0], maxDP[N][1]), maxDP[N][2]);
        return new int[]{max, min};
    }
}
