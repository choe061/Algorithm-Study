package week_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 7. 19..
 */
public class Bridge_2146 {
    private static final int[] X = {0, 0, -1, 1};
    private static final int[] Y = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];
        boolean[][] visited = new boolean[N+1][N+1];
        int[][] distance = new int[N+1][N+1];
        Queue<Bridge> queue = new LinkedList<>();

        //input
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //각 섬을 넘버링
        int count = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    numberingMap(map, i, j, ++count, visited);
                }
            }
        }

        //섬들을 큐에 넣고
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] != 0) {
                    queue.add(new Bridge(i, j));
                }
            }
        }

        //bfs탐색
        int min = Collections.min(makeBridge(map, queue, distance));
        System.out.println(min);
        br.close();
    }

    //bfs를 사용하여 각 섬에 번호를 메긴다.
    private static void numberingMap(int[][] map, int x, int y, int index, boolean[][] visited) {
        int n = map.length - 1;
        Queue<Bridge> queue = new LinkedList<>();
        queue.offer(new Bridge(x, y));
        map[x][y] = index;
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            x = queue.peek().getX();
            y = queue.peek().getY();
            queue.poll();
            for (int i=0; i<4; i++) {
                //현재 위치의 상하좌우에 대해 탐색
                int nx = x + X[i];
                int ny = y + Y[i];
                if (nx >= 0 && nx <= n && ny >= 0 && ny <= n) {
                    if (map[nx][ny] != 0 && !visited[nx][ny]) {
                        queue.offer(new Bridge(nx, ny));
                        map[nx][ny] = index;
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    //bfs를 사용하여 다리를 하나씩 건설
    private static ArrayList<Integer> makeBridge(int[][] map, Queue<Bridge> queue, int[][] distance) {
        ArrayList<Integer> finalDistance = new ArrayList<>();
        int n = map.length - 1;

        while (!queue.isEmpty()) {
            int x = queue.peek().getX();
            int y = queue.peek().getY();
            queue.poll();

            for (int i=0; i<4; i++) {
                int nx = x + X[i];
                int ny = y + Y[i];
                System.out.println("x : "+x+", y : "+y+", nx : "+nx+", ny : "+ny);
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {               //map의 범위로 제한
                    if (map[nx][ny] != 0 && map[nx][ny] != map[x][y]) {     //맵이 연결됬을때
                        System.out.println("Add List, x : "+x+", y : "+y+", nx : "+nx+", ny : "+ny);
                        System.out.println("map : "+map[x][y]+", "+map[nx][ny]);
                        finalDistance.add(distance[nx][ny] + distance[x][y]);
                    }
                    if (map[nx][ny] == 0) {             //다음 위치가 0인 경우 = 아직 다리가 만들어지지 않은 곳
                        queue.add(new Bridge(nx, ny));  //다리를 만들고
                        map[nx][ny] = map[x][y];        //맵에 섬을 확장
                        distance[nx][ny] = distance[x][y] + 1;  //이전 거리에서 +1를 하여 disance배열에 저장
                    }
                }
            }

        }
        return finalDistance;
    }

    static class Bridge {
        private int x, y;

        public Bridge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }


}