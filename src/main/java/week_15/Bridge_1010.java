package week_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 9. 11. AM 12:57.
 */
public class Bridge_1010 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.println(getCountOfCase(N, M));
        }
        br.close();
    }

    private static long getCountOfCase(int N, int M) {
        if (N == M) {
            return 1;
        }
        long[][] bridge = new long[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {

            }
        }
        return bridge[N - 1][M - 1];
    }
}
