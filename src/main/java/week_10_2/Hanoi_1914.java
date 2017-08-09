package week_10_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by choi on 2017. 8. 9..
 */
public class Hanoi_1914 {

    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(moveCount(N));
        if (N <= 20) {
            moveTower(N, 1, 3, 2);
            System.out.println(result.toString());
        }
        br.close();
    }

    private static BigInteger moveCount(int num) {
        return new BigDecimal(Math.pow(2, num)-1).toBigInteger();
    }

    private static void moveTower(int n, int from, int to, int by) {
        if (n == 1) {
            result.append(from).append(" ").append(to).append("\n");
//            System.out.println(from + " " + to);
        } else {
            moveTower(n - 1, from, by, to);
            result.append(from).append(" ").append(to).append("\n");
//            System.out.println(from + " " + to);
            moveTower(n - 1, by, to, from);
        }
    }
}
