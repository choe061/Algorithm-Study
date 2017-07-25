package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 18..
 * 벽으로 막혀있는 곳이 있어서 섬 다리 만들기 문제 처럼 지역을 나눠야함
 *
 * 현재 열쇠로 문을 열수 있으면 문서를 얻고 map에 있는 문과 문서를 벽(*)으로 만든다
 * 해당하는 문의 열쇠가 없으면 문의 위치(x,y)를 저장하고 열쇠를 얻었을때 그 문의 위치부터 시작한다
 *
 * 열쇠를 구했을때 그 열쇠로 열 수 있는 문의 좌표를 모두 저장해두어야함
 */
public class Key_9328 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] map = new char[h+1][w+1];
            int[][] map_index = new int[h+1][w+1];
            boolean[][] visited = new boolean[h+1][w+1];

            for (int j=1; j<=h; j++) {
                char[] temp = br.readLine().toCharArray();
                for (int k=1; k<=w; k++) {
                    map[j][k] = temp[k];
                }
            }
            char[] key = br.readLine().toCharArray();
            Queue<Character> keys = new LinkedList<>();
            for (char k : key) {
                keys.offer(k);
            }

            //벽으로 나눠진 곳을 넘버링
            int count = 0;
            for (int j=0; j<=h; j++) {
                for (int k=0; k<=w; k++) {
                    if (map[j][k] == '.' && !visited[j][k]) {
                        numberingMap(map, j, k, ++count, visited, map_index);
                    }
                }
            }

            bfs(map, keys);
            //key 하나 당 bfs 한번씩 수행
        }
        br.close();
    }

    private static void numberingMap(char[][] map, int x, int y, int index, boolean[][] visited, int[][] map_index) {

    }

    private static Pair getStartPoint(char[][] map, char[] key) {
        //열쇠가 있다면 가지고있는 일치하는 지점부터 탐색

        //열쇠가 없다면 열쇠가 있는 지점부터 출발
        return null;
    }

    private static int bfs(char[][] map, Queue<Character> keys) {
        int h = map.length;
        int w = map[0].length;
        boolean[][] visited = new boolean[h][w];
        Queue<Pair> queue = new LinkedList<>();


        if (!keys.isEmpty()) {  //key가 있으면 한번 더 bfs 탐색
            bfs(map, keys);
        }
        return 1;
    }

    static class Pair {
        private int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
