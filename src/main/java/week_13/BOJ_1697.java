package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 30..
 */
public class BOJ_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Game game = new Game(N, K);
        System.out.println(game.getMinTime());
        br.close();
    }

    static class Game {
        private int N, K;
        private int[] visited;

        public Game(int n, int k) {
            N = n;
            K = k;
            visited = new int[100001];
        }

        public int getMinTime() {
            Arrays.fill(visited, -1);
            visited[N] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(N);

            while (!queue.isEmpty()) {
                int subin = queue.poll();
                if (subin == K) {
                    return visited[subin];
                }
                //세 가지 경우의 수 (X-1, X+1, X*2)
                if (subin-1 >= 0 && visited[subin-1] == -1) {
                    visited[subin-1] = visited[subin] + 1;
                    queue.offer(subin-1);
                }

                if (subin+1 <= 100000 && visited[subin+1] == -1) {
                    visited[subin+1] = visited[subin] + 1;
                    queue.offer(subin+1);
                }

                if (subin*2 <= 100000 && visited[subin*2] == -1) {
                    visited[subin*2] = visited[subin] + 1;
                    queue.offer(subin*2);
                }
            }
            return -1;
        }
    }
}
