package week_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 9. 9..
 */
public class PinaryNum_2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(getCountOfPinaryNumber(N));
        br.close();
    }

    private static long getCountOfPinaryNumber(int n) {
        long[] count = new long[n+1];
        count[0] = 0;
        count[1] = 1;

        //0은 무조건 1 - 한 가지 경우, 두자리수일때도 10 - 한가지 경우
        for (int i=2; i<=n; i++) {
            count[i] = count[i-1] + count[i-2];
        }
        return count[n];
    }
}
