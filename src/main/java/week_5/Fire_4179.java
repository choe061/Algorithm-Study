package week_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 7. 21..
 */
public class Fire_4179 {
    private static final int[] X = {0, 0, -1, 1};
    private static final int[] Y = {-1, 1, 0, 0};

    private static int R, C;
    private static char[][] map;
    private static int[][] a_map;
    private static int[][] fireMap;
    private static ArrayList<Pair> fire = new ArrayList<>();
    private static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C+1];
        a_map = new int[R+1][C+1];
        fireMap = new int[R+1][C+1];
        distance = new int[R+1][C+1];
        int jx = 0, jy = 0; //지훈의 위치
        for (int i=1; i<=R; i++) {
            String str = br.readLine();
            for (int j=1; j<=C; j++) {
                map[i][j] = str.charAt(j-1);
                if (map[i][j] == 'J') {
                    jx = i;
                    jy = j;
                }
            }
        }

        for (int i=0; i<=R; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        moveJ(jx, jy);

        int time = getTime();
        System.out.println((time == -1) ? "IMPOSSIBLE" : (time+1));
        br.close();
    }

    private static void fire() {
        for (int i=1; i<=R; i++) {
            for (int j=1; j<=C; j++) {
                if (map[i][j] == 'F' && a_map[i][j] != 1) {
                    a_map[i][j] = 1;
                    fire.add(new Pair(i, j));
                }
            }
        }

        for (Pair p : fire) {
            bfsFire(p);     //p는 불이 난 지점
        }
        fire.clear();
    }

    private static void bfsFire(Pair p) {
        for (int i = 0; i < 4; i++) {
            int nx = p.getX() + X[i];
            int ny = p.getY() + Y[i];
            if (nx > 0 && nx <= R && ny > 0 && ny <= C) {
                if (map[nx][ny] != '#' && map[nx][ny] != 'F') {
                    map[nx][ny] = 'F';
                    fireMap[nx][ny] = fireMap[p.getX()][p.getY()] + 1;
                }
            }
        }
    }

    private static void moveJ(int x, int y) {
        PriorityQueue<Jyhun> priorityQueue = new PriorityQueue<>();
        distance[x][y] = 0;
        priorityQueue.offer(new Jyhun(x, y, distance[x][y]));

        while (!priorityQueue.isEmpty()) {
            Jyhun jyhun = priorityQueue.poll();
            if (distance[jyhun.getX()][jyhun.getY()] >= jyhun.getW()) {
                fire();
                for (int i=0; i<4; i++) {
                    int adj_x = jyhun.getX() + X[i];
                    int adj_y = jyhun.getY() + Y[i];
                    if (adj_x > 0 && adj_x <= R && adj_y > 0 && adj_y <= C) {  //불이 붙은 지역, 벽 으로는 이동할 수 없다
                        if (map[adj_x][adj_y] != '#' && map[adj_x][adj_y] != 'F') {
                            if (distance[adj_x][adj_y] > distance[jyhun.getX()][jyhun.getY()] + 1) {
                                distance[adj_x][adj_y] = distance[jyhun.getX()][jyhun.getY()] + 1;
                                priorityQueue.offer(new Jyhun(adj_x, adj_y, distance[adj_x][adj_y]));
                            }
                        }
                    }
                }
            }
        }
    }

    private static int getTime() {
        ArrayList<Integer> time = new ArrayList<>();
        boolean fire = isFire();
        for (int i=1; i<=R; i++) {
            for (int j=1; j<=C; j++) {
                if (i == 1 || i == R || j == 1 || j == C) {
                    if (fire) {
                        if (map[i][j] != '#' && fireMap[i][j] >= distance[i][j]) {
                            time.add(distance[i][j]);
                        }
                    } else {
                        if (map[i][j] != '#') {
                            time.add(distance[i][j]);
                        }
                    }
                }
            }
        }
        return (time.size() == 0) ? -1 : Collections.min(time);
    }

    private static boolean isFire() {
        for (int i=1; i<=R; i++) {
            for (int j=1; j<=C; j++) {
                if (map[i][j] == 'F') {
                    return true;
                }
            }
        }
        return false;
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

    static class Jyhun implements Comparable<Jyhun> {
        private int x, y, w;

        public Jyhun(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Jyhun o) {
            return (this.w >= o.w) ? 1 : -1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getW() {
            return w;
        }
    }
}
