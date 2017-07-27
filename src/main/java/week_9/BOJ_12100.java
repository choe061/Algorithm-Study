package week_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 27..
 * 큐를 사용하여 빈 값을 없앤다
 *
 * 가장 첫번째 이동을 끝냈을 때 값이 10번째 1024라면
 * 그 다음부턴 1024와 비교하여 더 작으면 이후는 탐색하지 않는다
 * 예) 첫번째 탐색의 값이 1024이면 9번째는 512보다 작다면 최대 값을 갱신할 수 없다
 */
public class BOJ_12100 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        br.close();
    }

    private static int bfs(int[][] map) {
        Queue<Pair> queue = new LinkedList<>();
        return 1;
    }

    static class Pair {
        private int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
