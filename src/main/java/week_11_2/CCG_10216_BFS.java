package week_11_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 12..
 * BFS 사용
 */
public class CCG_10216_BFS {
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
        int result;

        public EnermyGroup(ArrayList<Enermy> enermy) {
            this.enermy = enermy;
            this.visited = new boolean[enermy.size()];  //visited 배열을 1차원 - 해당 노드를 방문했는지 아닌지 확인만하면 되기 때문에
            result = 0;
        }

        public int getEnermyGroup() {
            int N = enermy.size();
            for (int i=0; i<N; i++) {
                if (!visited[i]) {      //방문하지않은 노드인 경우 BFS 탐색 시작하고, result++
                    bfs(i);
                    result++;
                }
            }
            return result;
        }

        private void bfs(int s) {   //start
            Queue<Enermy> queue = new LinkedList<>();
            queue.offer(enermy.get(s));
            visited[s] = true;

            while (!queue.isEmpty()) {
                Enermy e = queue.poll();

                for (int i=0; i<enermy.size(); i++) {
                    if (!visited[i] && isConnected(enermy.get(i), e)) {
                        queue.offer(enermy.get(i));
                        visited[i] = true;
                    }
                }
            }
        }

        private boolean isConnected(Enermy e1, Enermy e2) {
            return Math.pow(e1.getR()+e2.getR(), 2) >=
                    Math.pow(e2.getX() - e1.getX(), 2) +
                            Math.pow(e2.getY() - e1.getY(), 2);
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
