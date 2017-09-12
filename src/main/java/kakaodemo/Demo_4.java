package kakaodemo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by choi on 2017. 9. 12. PM 6:33.
 */
public class Demo_4 {
    public static void main(String[] args) {
        System.out.println(solution2(new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}}));
        System.out.println(solution2(new int[][]{{0, 0, 1, 1}, {1, 1, 1, 1}}));
        System.out.println(solution2(new int[][]{{0, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 1, 1, 1, 1}}));
    }

    public static int solution2(int[][] board) {

        return 1;
    }

    public static int solution(int[][] board) {
        int h = board.length;
        int w = board[0].length;
        int max = 1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == 1) {
                    int size = 1;
                    Queue<Pair> queue = new LinkedList<>();
                    boolean[][] visited = new boolean[h][w];
                    queue.offer(new Pair(i, j));
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        Pair p = queue.poll();
                        int adj_y = p.getY() + 1;
                        int adj_x = p.getX() + 1;
                        if (adj_y < h && adj_x < w) {
                            if (board[adj_y][p.getX()] == 0 || board[p.getY()][adj_x] == 0 || board[adj_y][adj_x] == 0) {
                                break;
                            }
                            if (!visited[adj_y][p.getX()] && !visited[p.getY()][adj_x] && !visited[adj_y][adj_x]) {
                                queue.offer(new Pair(adj_y, p.getX()));
                                queue.offer(new Pair(p.getY(), adj_x));
                                queue.offer(new Pair(adj_y, adj_x));
                                visited[adj_y][p.getX()] = true;
                                visited[p.getY()][adj_x] = true;
                                visited[adj_y][adj_x] = true;
                                size++;
                            }
                        }
                    }
                    max = Math.max(max, size);
                }
            }
        }
        return max * max;
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
