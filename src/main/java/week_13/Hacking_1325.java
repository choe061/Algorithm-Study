package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 28..
 */
public class Hacking_1325 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> network = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            network.add(new ArrayList<>());
        }
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            network.get(B).add(A);  //B를 해킹하면 A도 해킹할 수 있다, 예제는 아니지만 서로 신뢰하는 경우도 있을 수 있다
        }
        Hacking hacking = new Hacking(N, M, network);
        System.out.println(hacking.getComputerList());
        br.close();
    }

    static class Hacking {
        private int N, M;
        private ArrayList<ArrayList<Integer>> network = new ArrayList<>();
        private int[] hackingCount;

        public Hacking(int n, int m, ArrayList<ArrayList<Integer>> network) {
            N = n;
            M = m;
            this.network = network;
            this.hackingCount = new int[N+1];
            Arrays.fill(hackingCount, 0);
        }

        public String getComputerList() {
            for (int i=1; i<=N; i++) {
                boolean[] visited = new boolean[N+1];
                visited[i] = true;
                hackingCount[i]++;
                dfs(i, network.get(i), visited);
            }
            System.out.println(Arrays.toString(hackingCount));
            return getMaxGroup();
        }

        /**
         * @param i 탐색을 시작한 컴퓨터 번호
         * @param computers 탐색 시작 지점에서 퍼져나갈 수 있는 컴퓨터들
         * @param visited
         */
        private void dfs(int i, ArrayList<Integer> computers, boolean[] visited) {
            int size = computers.size();
            for (int j = 0; j < size; j++) {
                int computer = computers.get(j);    //새로 방문할 컴퓨터
                if (!visited[computer]) {           //이미 방문한 컴퓨터면 더 이상 방문하지 않는다
                    visited[computer] = true;
                    hackingCount[i]++;
                    dfs(i, network.get(computer), visited);
                }
            }
        }

        private String getMaxGroup() {
            StringBuilder sb = new StringBuilder();
            int max = getMaxValue();
            for (int i=1; i<hackingCount.length; i++) {
                if (max == hackingCount[i]) {
                    sb.append(i).append(" ");
                }
            }
            return sb.toString();
        }

        private int getMaxValue() {
            int max = Integer.MIN_VALUE;
            for (int i=1; i<hackingCount.length; i++) {
                max = Math.max(max, hackingCount[i]);
            }
            return max;
        }
    }
}
