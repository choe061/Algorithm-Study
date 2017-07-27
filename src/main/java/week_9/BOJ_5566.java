package week_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 25..
 */
public class BOJ_5566 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] x = new int[N+1];
        int[] nums = new int[M+1];
        for (int i = 1; i <= N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= M; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        count(x, nums);
        br.close();
    }

    private static void count(int[] x, int[] nums) {
        int now = 1;
        for (int i = 1; i <= nums.length; i++) {
            now += nums[i];
            if (now < x.length - 1) {
                now += x[now];
            }
            if (now >= x.length - 1) {
                System.out.println(i);
                return;
            }
        }
    }
}
