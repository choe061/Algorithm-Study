package week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 7. 12..
 * 시뮬레이션
 */
public class Homework_5532 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());

        System.out.println(getMaxPlayDay(l, a, b, c, d));
        br.close();
    }

    private static int getMaxPlayDay(int l, int a, int b, int c, int d) {
        int m = a / c;
        int lang = b / d;
        m += (a % c == 0) ? 0 : 1;
        lang += (b % d == 0) ? 0 : 1;
        int day = (m > lang) ? m : lang;
        return l - day;
    }
}
