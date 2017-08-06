package week_10_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 8. 6..
 */
public class BAAAM_3190 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];
        Queue<Move> move = new LinkedList<>();
        for (int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = 1;  //사과가 있는 위치를 map에 1로 바꿔준다
        }
        int L = Integer.parseInt(br.readLine());
        for (int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            move.offer(new Move(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }
        BAAAM baaam = new BAAAM(N, map, move);
        System.out.println(baaam.getEndTime());
        br.close();
    }

    static class BAAAM {
        private final int[] ROW = {0, 1, 0, -1};
        private final int[] COL = {1, 0, -1, 0};
        private int N;
        private int[][] map = new int[N+1][N+1];
        private Queue<Move> move = new LinkedList<>();  //방향 변환 정보를 가지고 있는 큐, 한번 변환되면 poll()되어 지움
        private Deque<Pair> baaam = new ArrayDeque<>(); //뱀의 머리와 꼬리를 추가하고 삭제할 수 있게 Deque로 표현한다
        private int time;

        public BAAAM(int n, int[][] map, Queue<Move> move) {
            N = n;
            this.map = map;
            this.move = move;
            this.time = 0;
        }

        //최종 시간을 구하는 메소드
        public int getEndTime() {
            Pair head = new Pair(1, 1);
            baaam.offerFirst(head);
            int direction = 0;
            while (!isCrash(direction)) {
                moveBaaam(direction);
                direction = changeDirection(direction);
                for (Pair p : baaam) {
                    System.out.println("Now Time : " + time + ", " + p + ", " + p.getRow() + ", " + p.getCol());
                }
            }
            return time;
        }

        //기존의 방향에 따라 다른 결과가 나온다
        private int changeDirection(int direction) {
            if (time == move.peek().getX()) {
                Move moveInfo = move.poll();
                char c = moveInfo.getC();
                if ((direction == 0 && c == 'L')
                        || (direction == 2 && c == 'D')) {
                    direction = 3;
                } else if ((direction == 0 && c == 'D')
                        || (direction == 2 && c == 'L')) {
                    direction = 1;
                } else if ((direction == 1 && c == 'L')
                        || (direction == 3 && c == 'D')) {
                    direction = 0;
                } else if ((direction == 1 && c == 'D')
                        || (direction == 3 && c == 'L')) {
                    direction = 2;
                }
            }
            return direction;
        }

        /**
         * @return 벽 또는 자신의 몸과 부딫히면 true 리턴
         */
        private boolean isCrash(int direction) {
            time++;
            Pair next = getNextPair(baaam.getFirst(), direction);   //Deque의 First(뱀 머리에 해당)를 가져온다
            if (next.getRow() <= 0 || next.getRow() >= N+1 || next.getCol() <= 0 || next.getCol() >= N+1) {
                System.out.println("Wall Crash!!!");
                return true;
            }
            for (Pair body : baaam) {
                if (next.getRow() == body.getRow() && next.getCol() == body.getCol()) {
                    System.out.println("Body Crash!!!");
                    return true;
                }
            }
            return false;
        }

        //뱀을 한칸 이동시키는 메소드
        private void moveBaaam(int direction) {
            Pair next = getNextPair(baaam.getFirst(), direction);
            if (next.getRow() >= 0 && next.getCol() >= 0 && next.getRow() <= N && next.getCol() <= N) {
                baaam.offerFirst(next);                         //사과가 있다면 꼬리는 움직이지 않고, 머리만 추가
                if (map[next.getRow()][next.getCol()] != 1) {   //사과가 없다면 머리를 추가하고, 꼬리를 삭제
                    baaam.pollLast();
                } else {
                    map[next.getRow()][next.getCol()] = 0;      //사과를 먹으면 해당 위치를 0으로 초기화
                }
            }
        }

        //뱀의 머리가 이동할 next 지역을 구하는 메소드
        private Pair getNextPair(Pair head, int direction) {
            int next_row = head.getRow() + ROW[direction];
            int next_col = head.getCol() + COL[direction];
            return new Pair(next_row, next_col);
        }
    }

    static class Pair {
        private int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    static class Move {
        private int X;
        private char C;

        public Move(int x, char c) {
            X = x;
            C = c;
        }

        public int getX() {
            return X;
        }

        public char getC() {
            return C;
        }
    }
}
