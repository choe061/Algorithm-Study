package week_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 9. 7..
 */
public class FileUnion_11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            int K = Integer.parseInt(br.readLine());
            int[] file = new int[K+1];
            int[] seq = new int[K+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            seq[0] = 0;
            for (int j=1; j<=K; j++) {
                file[j] = Integer.parseInt(st.nextToken());
                seq[j] = seq[j-1] + file[j];
            }
            FileUnion fileUnion = new FileUnion(file, seq, K);
            System.out.println(fileUnion.getMinCost());
        }
        br.close();
    }

    static class FileUnion {
        private int[] file, seq;
        private int K;
        private int[][] dp;

        public FileUnion(int[] file, int[] seq, int k) {
            this.file = file;
            this.seq = seq;
            K = k;
        }

        public int getMinCost() {
            dp = new int[K+1][K+1];
            for (int i=0; i<=K; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            return union(1, K);
        }

        private int union(int start, int end) {
            if (start >= end) {
                return 0;
            } else if (end == start + 1) {
                return file[start] + file[end];
            }

            if (dp[start][end] < Integer.MAX_VALUE) {
                return dp[start][end];
            }
            for (int i=start; i<end; i++) {
                int temp = union(start, i) + union(i+1, end) + seq[end] - seq[start-1];
                dp[start][end] = Math.min(dp[start][end], temp);
            }
            return dp[start][end];
        }
    }
}
