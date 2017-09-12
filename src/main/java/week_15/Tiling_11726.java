package week_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 9. 11. AM 12:00.
 */
public class Tiling_11726 {
    private static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(getCountOfTile(n));
        br.close();
    }

    private static int getCountOfTile(int n) {
        if (n == 1) {
            return 1;
        }
        int[] tile = new int[n+1];
        tile[0] = 0;
        tile[1] = 1;
        tile[2] = 2;
        for (int i=3; i<=n; i++) {
            tile[i] = (tile[i-1] + tile[i-2]) % MOD;
        }
        return tile[n] % MOD;
    }
}
