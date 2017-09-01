package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 9. 1..
 */
public class PrimeRoute_1963 {
    private static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        boolean[] prime = Prime.getPrime();
        for (int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            Converter converter = new Converter(A, B, prime);
            int count = converter.getCountOfConvert();
            if (count == -1) {
                System.out.println(IMPOSSIBLE);
            } else {
                System.out.println(count);
            }
        }
        br.close();
    }

    //10000 이하 숫자의 소수 판별 배열을 구해주는 클래스
    static class Prime {
        public static boolean[] getPrime() {
            boolean[] isPrime = new boolean[10000];
            Arrays.fill(isPrime, true);
            isPrime[1] = false;
            int N = (int) Math.sqrt(9999);
            for (int i=2; i<=N; i++) {
                if (isPrime[i]) {
                    for (int j=2; i*j<=9999; j++) {
                        isPrime[i*j] = false;
                    }
                }
            }
            return isPrime;
        }
    }

    static class Converter {
        private int a, b;
        private boolean[] prime;
        private int[] visited;

        public Converter(int a, int b, boolean[] prime) {
            this.a = a;
            this.b = b;
            this.prime = prime;
            init();
        }

        private void init() {
            this.visited = new int[10000];
            Arrays.fill(visited, -1);
        }

        public int getCountOfConvert() {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(a);
            visited[a] = 0;
            while (!queue.isEmpty()) {
                int number = queue.poll();
                if (number == b) {
                    return visited[number];
                }
                //각 자리수 별로 바꿀
                for (int i=0; i<4; i++) {
                    int[] temp = convertToArray(number);
                    for (int j=0; j<10; j++) {
                        if (i != 0 || j != 0) {
                            if (temp[i] != j) {
                                int result = convertToNumber(temp, i, j);
                                if (visited[result] == -1 && prime[result]) {
                                    visited[result] = visited[number] + 1;
                                    queue.offer(result);
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        private boolean isPrime(int num) {
            for (int i=2; i<num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

        private int[] convertToArray(int num) {
            int[] temp = new int[4];
            temp[0] = num / 1000;
            temp[1] = (num / 100) % 10;
            temp[2] = (num / 10) % 10;
            temp[3] = num % 10;
            return temp;
        }

        private int convertToNumber(int[] nums, int index, int num) {
            nums[index] = num;
            return (nums[0] * 1000) + (nums[1] * 100) + (nums[2] * 10) + nums[3];
        }
    }
}
