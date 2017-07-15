package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 14..
 */
public class NumberCard_10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] card = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(card);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            sb.append(binarySearch(card, Integer.parseInt(st.nextToken()))).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int binarySearch(int[] card, int num) {
        int front = 0;
        int rear = card.length - 1;
        int mid;

        while (front <= rear) {
            mid = (front + rear) / 2;

            if (card[mid] == num) {
                return 1;
            } else if (card[mid] < num) {
                front = mid + 1;
            } else if (card[mid] > num) {
                rear = mid - 1;
            }
        }
        return 0;
    }
}
