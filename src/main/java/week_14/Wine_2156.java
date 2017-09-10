package week_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 9. 10. PM 8:03.
 * 1. 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
 * 2. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
 */
public class Wine_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n+1];
        for (int i=1; i<=n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        Wine w = new Wine(n, wine);
        System.out.println(w.getDrinkMaxWine());
        br.close();
    }

    static class Wine {
        private int n;
        private int[] wine;

        public Wine(int n, int[] wine) {
            this.n = n;
            this.wine = wine;
        }

        public int getDrinkMaxWine() {
            if (n == 1) {
                return wine[1];
            }
            int[] dp = new int[n+1];
            dp[0] = 0;
            dp[1] = wine[1];
            dp[2] = wine[1] + wine[2];
            for (int i=3; i<=n; i++) {
                dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i]));
            }
            return dp[n];
        }
    }
}
