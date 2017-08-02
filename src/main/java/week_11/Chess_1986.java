package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 2..
 */
public class Chess_1986 {

    private static final int[] KY = {-2, -1, -2, -1, 2, 1, 2, 1};   //Knight가 움직일 수 있는 좌표
    private static final int[] KX = {-1, -2, 1, 2, -1, -2, 1, 2};
    private static final int[] QY = {-1, -1, -1, 0, 0, 1, 1, 1};    //Queen이 움직일 수 있는 좌표
    private static final int[] QX = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static int N;
    private static int M;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[][] dangerousZone = new boolean[N+1][M+1];  //한번 위험 지역으로 판단되면 더 이상 탐색하지 않아도된다
        map = new char[N+1][M+1];

        st = new StringTokenizer(br.readLine());
        int queenSize = Integer.parseInt(st.nextToken());
        for (int i=0; i<queenSize; i++) {
            Pair pair = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            map[pair.getY()][pair.getX()] = 'Q';
            dangerousZone[pair.getY()][pair.getX()] = true;
        }

        st = new StringTokenizer(br.readLine());
        int knightSize = Integer.parseInt(st.nextToken());
        for (int i=0; i<knightSize; i++) {
            Pair pair = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            map[pair.getY()][pair.getX()] = 'K';
            dangerousZone[pair.getY()][pair.getX()] = true;
        }

        st = new StringTokenizer(br.readLine());
        int pawnSize = Integer.parseInt(st.nextToken());
        for (int i=0; i<pawnSize; i++) {
            Pair pair = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            map[pair.getY()][pair.getX()] = 'P';
            dangerousZone[pair.getY()][pair.getX()] = true;
        }

        int count = getSafetyZone(dangerousZone);
        System.out.println(count);
        br.close();
    }

    private static int getSafetyZone(boolean[][] dangerousZone) {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (map[i][j] == 'Q') {
                    for (int q=0; q<8; q++) {
                        moveQueen(new Pair(i, j), dangerousZone, q);
                    }
                } else if (map[i][j] == 'K') {
                    moveKnight(new Pair(i, j), dangerousZone);
                }
            }
        }

        int count = 0;
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (!dangerousZone[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param s start지점
     * @param dangerousZone 위험한 지역을 true로 바꾼다
     * @param direction QY, QX의 인덱스로 한번 이동한 방향으로 쭉 이동하기 위한 것
     */
    private static void moveQueen(Pair s, boolean[][] dangerousZone, int direction) {
        int adj_y = s.getY() + QY[direction];
        int adj_x = s.getX() + QX[direction];
        if (adj_y > 0 && adj_x > 0 && adj_y <= N && adj_x <= M) {
            Pair node = new Pair(adj_y, adj_x);
            if (checkWall(node)) {     //인접 노드가 벽인지 아닌지 확인, 벽이라면 DFS 탐색(재귀)을 멈춘다
                dangerousZone[adj_y][adj_x] = true;
                moveQueen(node, dangerousZone, direction);
            }
        }
    }

    //해당 노드에 말이 있으면 false, 이동 가능하면 true를 리턴
    private static boolean checkWall(Pair node) {
        if (map[node.getY()][node.getX()] != 'Q'
                && map[node.getY()][node.getX()] != 'K'
                && map[node.getY()][node.getX()] != 'P') {
            return true;
        }
        return false;
    }

    private static void moveKnight(Pair s, boolean[][] dangerousZone) {
        for (int i=0; i<8; i++) {
            int adj_y = s.getY() + KY[i];
            int adj_x = s.getX() + KX[i];
            if (adj_y > 0 && adj_x > 0 && adj_y <= N && adj_x <= M) {
                if (!dangerousZone[adj_y][adj_x]) {
                    dangerousZone[adj_y][adj_x] = true;
                }
            }
        }
    }

    static class Pair {
        private int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
