package week_11_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 13..
 * DFS 사용
 */
public class CCG_10216_DFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Enermy> enermy = new ArrayList<>();
            for (int j=0; j<N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                enermy.add(new Enermy(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }
            EnermyGroup enermyGroup = new EnermyGroup(enermy);
            System.out.println(enermyGroup.getEnermyGroup());
        }
        br.close();
    }

    static class EnermyGroup {
        ArrayList<Enermy> enermy = new ArrayList<>();
        boolean[] visited;
        int result, N;

        public EnermyGroup(ArrayList<Enermy> enermy) {
            this.enermy = enermy;
            N = enermy.size();
            this.visited = new boolean[N];
            result = 0;
        }

        public int getEnermyGroup() {
            for (int i=0; i<N; i++) {
                if (!visited[i]) {
                    dfs(i);
                    result++;
                }
            }
            return result;
        }

        private void dfs(int s) {
            visited[s] = true;
            for (int i=0; i<N; i++) {
                if (!visited[i] && isConnected(enermy.get(s), enermy.get(i))) {
                    dfs(i);
                }
            }
        }

        private boolean isConnected(Enermy e1, Enermy e2) {
            return Math.pow(e1.getR()+e2.getR(), 2) >=
                    Math.pow(e2.getX() - e1.getX(), 2) + Math.pow(e2.getY() - e1.getY(), 2);
        }
    }

    static class Enermy {
        private int x, y, r;

        public Enermy(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getR() {
            return r;
        }
    }
}
