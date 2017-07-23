package week_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by choi on 2017. 7. 22..
 */
public class Puyo_11559 {
    private static final int[] X = {0, 0, -1, 1};
    private static final int[] Y = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] field = new char[12][6];
        boolean[][] visited = new boolean[12][6];
        for (int i=0; i<12; i++) {
            field[i] = br.readLine().toCharArray();
        }
        ArrayList<Pair> result = new ArrayList<>();
        int count = 0;
        int i = 0, j = 0;
        while (i < 12 && j < 6) {
            for (i = 0; i < 12; i++) {
                for (j = 0; j < 6; j++) {
                    if (field[i][j] != '.') {
                        bfs(field, i, j, result, visited);
                    }
                }
            }
            if (boom(field, result)) {
                i = 0;
                j = 0;
                count++;
                result.clear();
            }
        }
        System.out.println(count);
        br.close();
    }

    private static void bfs(char[][] field, int x, int y, ArrayList<Pair> result, boolean[][] visited) {
        ArrayList<Pair> tempList = new ArrayList<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(x, y));
        visited[x][y] = true;
        tempList.add(new Pair(x, y));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i=0; i<4; i++) {
                int adj_x = p.getX() + X[i];
                int adj_y = p.getY() + Y[i];
                if (adj_x >= 0 && adj_y >= 0 && adj_x < field.length && adj_y < field[0].length) {
                    if (!visited[adj_x][adj_y] && field[x][y] == field[adj_x][adj_y]) {
                        visited[adj_x][adj_y] = true;
                        tempList.add(new Pair(adj_x, adj_y));
                        queue.offer(new Pair(adj_x, adj_y));
                    }
                }
            }
        }
        if (tempList.size() >= 4) {
            result.addAll(tempList);
        } else {
            for (Pair p : tempList) {
                visited[p.getX()][p.getY()] = false;
            }
        }
    }

    private static boolean boom(char[][] field, ArrayList<Pair> pairs) {
        int size = pairs.size();
        if (size >= 4) {
            for (int i=0; i<size; i++) {
                field[pairs.get(i).getX()][pairs.get(i).getY()] = '.';
            }
            System.out.println("Boom!!!");
            for (int i=0; i<12; i++) {
                for (int j=0; j<6; j++) {
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }

            for (int i=10; i>=0; i--) {
                for (int j=0; j<6; j++) {
                    downPuyo(field, i, j);
                }
            }
            System.out.println("Down");
            for (int i=0; i<12; i++) {
                for (int j=0; j<6; j++) {
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return true;
        }
        return false;
    }

    private static void downPuyo(char[][] field, int y, int x) {
        while (y < 11 && field[y][x] != '.' && field[y+1][x] == '.') {
            field[y+1][x] = field[y][x];
            field[y][x] = '.';
            y++;
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
}
