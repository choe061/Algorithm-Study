package week_10_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 6..
 * 전깃줄을 하나씩 추가해나가는 관점에서, 최대한 몇 개 까지 겹치지 않고 추가할 수 있는지를 확인해나가는 것이 더 쉬운 접근 방법
 * LIS
 *
 */
public class BOJ_2565 {

    private static int N;
    private static Line[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = new Line[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            line[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(line);
        for (int i=0; i<N; i++) {
            System.out.println(line[i].getA()+", "+line[i].getB());
        }
        System.out.println(getMinimumLine());
        br.close();
    }

    private static int getMinimumLine() {
        int[] table = new int[N];
        int length = 1;
        table[0] = line[0].getB();

        for (int i=1; i<N; i++) {
            int candidate = line[i].getB();
            if (candidate < table[0]) {     //table[0](비교를 하는 시작점)이 더 크면 table[0](시작점)을 candidate로 다시 초기화
                table[0] = candidate;
            } else if (candidate > table[length - 1]) {
                table[length++] = candidate;
            } else {    //이진 탐색으로 0 ~ length 범위에서 candidate를 찾는다    //binarySearch메소드는 찾지못하면 -(low + 1)을 반환한다.
                int index = Arrays.binarySearch(table, 0, length, candidate);
//                System.out.println("index : "+index);
                index = (index < 0) ? -(index + 1) : index;
                table[index] = candidate;
            }
        }
        return (N - length);
    }

    static class Line implements Comparable<Line> {
        private int a, b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        @Override
        public int compareTo(Line o) {
            return (this.a >= o.a) ? 1 : -1;
        }
    }
}
