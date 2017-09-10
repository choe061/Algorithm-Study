package week_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 9. 9. PM 8:26.
 */
public class MinimumSub_3090 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }

    static class MyArrays {

    }

    static class Number {
        private int value;
        private int sub;

        public Number(int value, int sub) {
            this.value = value;
            this.sub = sub;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getSub() {
            return sub;
        }

        public void setSub(int sub) {
            this.sub = sub;
        }
    }
}
