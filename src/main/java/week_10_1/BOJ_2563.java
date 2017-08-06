package week_10_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 6..
 */
public class BOJ_2563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[102][102];
        int count = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 10; j++) {
                for (int z = 0; z < 10; z++) {
                    if (map[x + j][y + z] == 0) {
                        map[x + j][y + z] = 1;
                        count++;
                    }
                }
            }
        }

        for (int j = 0; j <= 100; j++) {
            for (int z = 0; z <= 100; z++) {
                System.out.print(map[j][z] + " ");
            }
            System.out.println();
        }

        System.out.println(count);
        br.close();
    }
}
