package week_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 7. 30..
 * 최종 답을 % 9901 한 값을 구해야한다
 * for문 안에서도 계속 연산한 결과를 % 9901 해야 정답
 * for문 안에서도 % 나머지 연산을 하는게 핵심인듯
 */
public class Zoo_1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cage = new int[N][3];
        System.out.println(getCase(cage));
        br.close();
    }

    private static int getCase(int[][] cage) {
        int len = cage.length - 1;
        cage[0][0] = 1;
        cage[0][1] = 1;
        cage[0][2] = 1;
        for (int i=1; i<=len; i++) {
            cage[i][0] = (cage[i-1][1] + cage[i-1][2]) % 9901;
            cage[i][1] = (cage[i-1][0] + cage[i-1][2]) % 9901;
            cage[i][2] = (cage[i-1][0] + cage[i-1][1] + cage[i-1][2]) % 9901;
        }
        return (cage[len][0] + cage[len][1] + cage[len][2]) % 9901;
    }
}
