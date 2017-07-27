package week_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 7. 26..
 */
public class BOJ_2234 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[][] map = new String[M+1][N+1];

        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                map[i][j] = Integer.toBinaryString(Integer.parseInt(st.nextToken()));
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        CastleWall castleWall = new CastleWall(map);
        System.out.println(castleWall.roomCount());
        System.out.println(castleWall.maxRoomArea());
        System.out.println(castleWall.sumRoomArea());
        br.close();
    }
                                //8, 4, 2, 1
    static class CastleWall {   //남, 동, 북, 서
//        private final int[] V = {8, 4, 2, 1};
        private final int[] Y = {0, 1, 0, -1};
        private final int[] X = {1, 0, -1, 0};
        private String[][] map;
        private int[][] room;
        private ArrayList<Integer> roomSize = new ArrayList<>();

        public CastleWall(String[][] map) {
            this.map = map;
        }

        public int roomCount() {
            int N = map[0].length;
            int M = map.length;
            boolean[][] visited = new boolean[M][N];
            room = new int[M][N];
            int count = 1;
            for (int i=1; i<M; i++) {
                for (int j=1; j<N; j++) {
                    if (room[i][j] == 0) {
                        roomSize.add(bfs(visited, room, count++, i, j));
                    }
                }
            }
            int max = Integer.MIN_VALUE;
            for (int i=1; i<M; i++) {
                for (int j=1; j<N; j++) {
                    System.out.print(room[i][j]+" ");
                    max = Math.max(max, room[i][j]);
                }
                System.out.println();
            }
            return max;
        }

        private int bfs(boolean[][] visited, int[][] room, int count, int x, int y) {
            Queue<Pair> queue = new LinkedList<>();
            int N = map[0].length;
            int M = map.length;
            queue.offer(new Pair(x, y));
            visited[x][y] = true;
            room[x][y] = count;
            int size = 1;

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                char[] wall = makeFourDigits(map[pair.getX()][pair.getY()]).toCharArray();
                for (int i=0; i<4; i++) {
                    if (wall[i] == '0') {
                        int adj_x = pair.getX() + X[i];
                        int adj_y = pair.getY() + Y[i];
                        if (adj_x > 0 && adj_y > 0 && adj_x < M && adj_y < N) {
                            if (!visited[adj_x][adj_y]) {
                                char[] adj_wall = makeFourDigits(map[adj_x][adj_y]).toCharArray();
                                int adj_i = (i + 2) % 4;
                                if (adj_wall[adj_i] == '0') {
                                    visited[adj_x][adj_y] = true;
                                    room[adj_x][adj_y] = count;
                                    queue.offer(new Pair(adj_x, adj_y));
                                    size++;
                                }
                            }
                        }
                    }
                }
            }
            return size;
        }

        private String makeFourDigits(String wall) {
            StringBuilder sb = new StringBuilder();
            if (wall.length() < 4) {
                int n = 4 - wall.length();
                for (int i=0; i<n; i++) {
                    sb.append('0');
                }
            }
            sb.append(wall);
            return sb.toString();
        }

        public int maxRoomArea() {
            return Collections.max(roomSize);
        }

        public int sumRoomArea() {
            Queue<Pair> queue = new LinkedList<>();
            int N = map[0].length;
            int M = map.length;
            int max = Integer.MIN_VALUE;
            boolean[][] visited = new boolean[M][N];
            visited[1][1] = true;
            queue.offer(new Pair(1, 1));

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();

                for (int i=0; i<4; i++) {
                    int adj_x = pair.getX() + X[i];
                    int adj_y = pair.getY() + Y[i];
                    if (adj_x > 0 && adj_y > 0 && adj_x < M && adj_y < N) {
                        int n1 = room[pair.getX()][pair.getY()];
                        int n2 = room[adj_x][adj_y];
                        if (!visited[adj_x][adj_y]) {
                            if (n1 != n2) {
                                int sum = roomSize.get(n1-1) + roomSize.get(n2-1);
                                max = Math.max(max, sum);
                            }
                            visited[adj_x][adj_y] = true;
                            queue.offer(new Pair(adj_x, adj_y));
                        }
                    }
                }
            }
            return max;
        }
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

    interface Castle {
        int roomCount();
        int maxRoomArea();
        int sumRoomArea();
    }
}
