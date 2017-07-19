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
            bfs(map, keys);
            //key 하나 당 bfs 한번씩 수행
        }
        br.close();
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
