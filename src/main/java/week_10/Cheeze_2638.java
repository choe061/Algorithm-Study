package week_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 30..
 * visited 배열을 int형으로 만들고
 * bfs탐색을 하면서 현재 위치가 공기이고, 인접 노드가 치즈이면 visited의 값을 1올리고
 * visited가 2이면 두 면 이상 공기가 접촉된 것
 *
 * 외부 공기와 내부 공기를 구별하는 bfs탐색이랑
 * 치즈를 지우는 search랑 나누어 두번 탐색할 필요가 없이
 * bfs한번만 탐색
 */
public class Cheeze_2638 {
    private static final int[] Y = {0, 0, -1, 1};
    private static final int[] X = {-1, 1, 0, 0};
    private static int N;
    private static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][M+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(getTime(map));
        br.close();
    }

    public static int getTime(int[][] map) {
        int time = 0;
        while (isCheeze(map)) {
            ArrayList<Pair> cheeze = new ArrayList<>();
            //가장 외곽 i=1, N or j=1, M일땐 무조건 공기와 접촉해있음, 치즈안에 있는 경우가 없다
            bfs(map, cheeze);
            search(map, cheeze);
            time++;
            print(map, time);
        }
        return time;
    }

    private static void print(int[][] map, int time) {
        System.out.println(time+"초 경과----------------");
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //치즈가 있는지 없는지 체크
    private static boolean isCheeze(int[][] map) {
        for (int i=2; i<N; i++) {
            for (int j=2; j<M; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //치즈 내부 공간(공기X)을 찾는 과정
    //내부 공기를 찾는게 아니라, 외부 공기를 찾는것!
    //내부 공간은 그대로 0, 외부 공기를 2로 만든다
    private static void bfs(int[][] map, ArrayList<Pair> cheeze) {
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][M+1];
        Pair start = new Pair(1, 1);
        queue.offer(start);
        visited[1][1] = true;
        map[1][1] = 2;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int i=0; i<4; i++) {
                int adj_y = pair.getY() + Y[i];
                int adj_x = pair.getX() + X[i];
                if (adj_y > 0 && adj_x > 0 && adj_y <= N && adj_x <= M) {
                    if (map[adj_y][adj_x] == 1) {
                        cheeze.add(new Pair(adj_y, adj_x));
                    }
                    if ((map[adj_y][adj_x] == 0 || map[adj_y][adj_x] == 2) && !visited[adj_y][adj_x]) {
                        visited[adj_y][adj_x] = true;
                        Pair p = new Pair(adj_y, adj_x);
                        map[p.getY()][p.getX()] = 2;
                        queue.offer(p);
                    }
                }
            }
        }
    }

    //녹는 치즈를 탐색
    private static void search(int[][] map, ArrayList<Pair> cheeze) {
        ArrayList<Pair> temp = new ArrayList<>();
        for (Pair pair : cheeze) {
            int air = 0;
            for (int z=0; z<4; z++) {
                int adj_y = pair.getY() + Y[z];
                int adj_x = pair.getX() + X[z];
                if (map[adj_y][adj_x] == 2) {
                    if (++air >= 2) {
                        temp.add(new Pair(pair.getY(), pair.getX()));
                        break;
                    }
                }
            }
        }
//        for (int i=2; i<N; i++) {
//            for (int j=2; j<M; j++) {
//                if (map[i][j] == 1) {
//                    int air = 0;
//                    for (int z=0; z<4; z++) {
//                        int adj_y = i + Y[z];
//                        int adj_x = j + X[z];
//                        if (map[adj_y][adj_x] == 2) {
//                            if (++air >= 2) {
//                                temp.add(new Pair(i, j));
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        for (Pair pair : temp) {
            map[pair.getY()][pair.getX()] = 0;
        }
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
