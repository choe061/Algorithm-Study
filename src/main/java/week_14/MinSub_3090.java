package week_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 9. 9..
 * 평균을 구한 후 평균과 가장 차이가 큰 수를 우선순위 큐에 추가
 * 우선순위 구현 클래스 index, value, subAvg
 *
 * 주의점 : value - 1 (감소)만 가능하다 + value가 1보다 작아질 수 없다
 * 1. 양 옆 숫자와 차이를 검사???
 */
public class MinSub_3090 {
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

        MyArrays arrays = new MyArrays(N, T, A);
        arrays.forMinimum();
        System.out.println(arrays.toString());
        br.close();
    }

    static class MyArrays {
        private PriorityQueue<Num> pq = new PriorityQueue<>();
        private ArrayList<Num> nums = new ArrayList<>();
        private int n, t;
        private int[] a;

        public MyArrays(int n, int t, int[] a) {
            this.n = n;
            this.t = t;
            this.a = a;
        }

        public void forMinimum() {
            Number[] numbers = new Number[n];
            for (int i=0; i<n; i++) {
                int subLeft = 0, subRight = 0;
                if (i > 0) {
                    subLeft = a[i] - a[i-1];
                }
                if (i < n - 1) {
                    subRight = a[i] - a[i+1];
                }
                numbers[i] = new Number(a[i], Math.max(subLeft, subRight));
//                pq.offer(new Num(i, a[i], Math.max(subLeft, subRight)));
            }
            for (int i=0; i<t; i++) {
//                setNum();
                search(numbers);
            }
        }

        private void search(Number[] numbers) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            for (int i=0; i<n; i++) {
                if (max < numbers[i].getSub()) {
                    max = numbers[i].getSub();
                    index = i;
                }
            }
            numbers[index].setSub(setSub(index, numbers[index].getValue() - 1));
        }

        private void setNum() {
            Num num = pq.poll();
            if (num.getValue() > 1) {   //value가 1보다 크면 -1 후 다시 우선순위 큐에 추가
                int index = num.getIndex();
                int value = num.getValue() - 1;
                num.setValue(value);
                num.setSub(setSub(index, value));

                pq.offer(num);
            } else {                    //value가 1과 같으면 더 이상 뺄 수 없으므로 우선순위 큐가 아닌 임시 temp 리스트에 추가
                nums.add(num);
                setNum();
            }
        }

        private int setSub(int index, int value) {
            int subLeft = 0, subRight = 0;
            if (index > 0) {
                subLeft = value - a[index - 1];
            }
            if (index < n - 1) {
                subRight = value - a[index + 1];
            }
            return Math.max(subLeft, subRight);
        }

        @Override
        public String toString() {
            nums.addAll(pq);
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<n; i++) {
                for (Num num : nums) {
                    if (num.getIndex() == i) {
                        sb.append(num.getValue()).append(" ");
                    }
                }
            }
            return sb.toString();
        }
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

    static class Num implements Comparable<Num> {
        private int index;
        private int value;
        private int sub;

        public Num(int index, int value, int sub) {
            this.index = index;
            this.value = value;
            this.sub = sub;
        }

        public int getIndex() {
            return index;
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

        @Override
        public int compareTo(Num o) {
            if (this.sub > o.sub) {
                return -1;
            } else if (this.sub == o.sub) {
                return this.value > o.value ? -1 : 1;
            } else {
                return 1;
            }
        }
    }
}
