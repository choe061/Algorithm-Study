package week_11_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 11..
 */
public class CycleQueue_1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] index = new int[M];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i=1; i<=N; i++) {
            deque.offerLast(i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++) {
            index[i] = Integer.parseInt(st.nextToken());
        }
        CycleQueue cq = new CycleQueue(deque, index);
        System.out.println(cq.getCount());
        br.close();
    }

    static class CycleQueue {
        private Deque<Integer> deque = new ArrayDeque<>();
        private int[] index;

        public CycleQueue(Deque<Integer> deque, int[] index) {
            this.deque = deque;
            this.index = index;
        }

        public int getCount() {
            int count = 0;
            for (int i=0; i<index.length; i++) {
                int command = getDirect(index[i]);
                if (command == 0) {
                    deque.pollFirst();
                    System.out.println("pollFirst() : " + deque);
                } else {
                    if (command == 1) {
                        while (index[i] != deque.peekFirst()) {
                            count++;
                            deque.offerFirst(deque.pollLast());
                            System.out.println("1 : "+deque+", " + count);
                        }
                    } else {
                        while (index[i] != deque.peekFirst()) {
                            count++;
                            deque.offerLast(deque.pollFirst());
                            System.out.println("-1 : "+deque+", "+count);
                        }
                    }
                    deque.pollFirst();
                }
            }
            return count;
        }

        private int getDirect(int num) {
            if (deque.peekFirst() == num) {
                return 0;
            } else {
                int index = 0;
                int size = deque.size() - 1;
                for (int i : deque) {
                    if (num == i) {
                        break;
                    }
                    index++;
                }
                if (index > size/2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}
