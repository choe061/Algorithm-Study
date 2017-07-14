package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 14..
 */
public class NumberCard_10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] card1 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            card1[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        int[] card2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++) {
            card2[i] = Integer.parseInt(st.nextToken());
        }
        binarySearch(card1, card2);
        br.close();
    }

    private static void binarySearch(int[] card1, int[] card2) {

    }
}
