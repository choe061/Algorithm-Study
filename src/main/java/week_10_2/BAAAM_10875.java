package week_10_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 8. 9..
 */
public class BAAAM_10875 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        Queue<Move> move = new LinkedList<>();

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            move.offer(new Move(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }
        BAAAM baaam = new BAAAM(L, move);
        System.out.println(baaam.getEndTime());
        br.close();
    }

    static class BAAAM {
        private final int[] ROW = {0, 1, 0, -1};
        private final int[] COL = {1, 0, -1, 0};
        private long time;
        private int tempTime;
        private long L;
        private Queue<Move> moves;
        private Deque<Pair> baaam = new ArrayDeque<>(); //뱀의 머리와 꼬리를 추가하고 삭제할 수 있게 Deque로 표현한다

        public BAAAM(long l, Queue<Move> moves) {
            L = l;
            this.moves = moves;
            init();
        }

        private void init() {
            time = 0;
            tempTime = 0;
            Pair head = new Pair(L, L);
            baaam.offerFirst(head);
        }

        public long getEndTime() {
            int direction = 0;
            while (!isCrash(direction)) {
                moveBaaam(direction);
                direction = changeDirection(direction);
            }
            return time;
        }

        private void moveBaaam(int direction) {
            Pair next = getNextPair(baaam.getFirst(), direction);
            baaam.offerFirst(next);
        }

        private int changeDirection(int direction) {
            if (++tempTime == moves.peek().getTime()) {
                tempTime = 0;
                Move moveInfo = moves.poll();
                char c = moveInfo.getDir();
                if ((direction == 0 && c == 'L')
                        || (direction == 2 && c == 'R')) {
                    direction = 3;
                } else if ((direction == 0 && c == 'R')
                        || (direction == 2 && c == 'L')) {
                    direction = 1;
                } else if ((direction == 1 && c == 'L')
                        || (direction == 3 && c == 'R')) {
                    direction = 0;
                } else if ((direction == 1 && c == 'R')
                        || (direction == 3 && c == 'L')) {
                    direction = 2;
                }
            }
            return direction;
        }

        private boolean isCrash(int direction) {
            time++;
            Pair next = getNextPair(baaam.getFirst(), direction);   //Deque의 First(뱀 머리에 해당)를 가져온다
            if (next.getX() < 0 || next.getX() >= 2*L+1 || next.getY() < 0 || next.getY() >= 2*L+1) {
                return true;
            }
            for (Pair body : baaam) {
                if (next.getX() == body.getX() && next.getY() == body.getY()) {
                    return true;
                }
            }
            return false;
        }

        private Pair getNextPair(Pair head, int direction) {
            long next_row = head.getX() + ROW[direction];
            long next_col = head.getY() + COL[direction];
            return new Pair(next_row, next_col);
        }
    }

    static class Move {
        private int time;
        private char dir;

        public Move(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }

        public int getTime() {
            return time;
        }

        public char getDir() {
            return dir;
        }
    }

    static class Pair {
        private long x, y;

        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }
    }
}
