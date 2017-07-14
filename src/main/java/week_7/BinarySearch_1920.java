package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 14..
 */
public class BinarySearch_1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            sb.append(binarySearch(nums, Integer.parseInt(st.nextToken()))).append('\n');
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int binarySearch(int[] nums, int num) {
        int front = 0;
        int rear = nums.length - 1;
        int mid;

        while (front <= rear) {     //front와 rear이 교차되어지는 순간까지 탐색하지 못하면 해당 숫자는 없는 것
            mid = (front + rear) / 2;

            if (nums[mid] == num) {
                return 1;
            } else if (num < nums[mid]) {
                rear = mid - 1;
            } else if (num > nums[mid]) {
                front = mid + 1;
            }
        }
        return 0;
    }
}
