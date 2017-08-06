package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 8. 5..
 */
public class Main1 {

    private static final int[] Y = {-1, 1, 0, 0};
    private static final int[] X = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] picture = new int[n][m];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                picture[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(Arrays.toString(soultion(n, m, picture)));
        br.close();
    }

    private static int[] soultion(int n, int m, int[][] picture) {
        boolean[][] visited = new boolean[n][m];
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                int value = picture[i][j];
                if (map.containsKey(value)) {
                    map.put(value, map.get(value) + 1);
                } else {
                    map.put(value, 1);
                }
                if (!visited[i][j]) {
                    max = Math.max(max, bfs(i, j, picture, visited, value));
                }
            }
        }
        System.out.println("원소 갯수 : "+map.size());
        System.out.println("최대값 : "+max);
        return new int[]{map.size(), max};
    }
    //i, j는 시작점
    private static int bfs(int i, int j, int[][] picture, boolean[][] visited, int value) {
        int count = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i, j));
        visited[i][j] = true;
        count++;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int k=0; k<4; k++) {
                int adj_y = pair.getY() + Y[k];
                int adj_x = pair.getX() + X[k];
                if (adj_y >= 0 && adj_x >= 0 && adj_y < picture.length && adj_x < picture[0].length) {
                    if (!visited[adj_y][adj_x] && picture[adj_y][adj_x] == value) {
                        if (value != 0) {
                            count++;
                        }
                        visited[adj_y][adj_x] = true;
                        queue.offer(new Pair(adj_y, adj_x));
                    }
                }
            }
        }
        return count;
    }

    static class Pair {
        private int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }
}
