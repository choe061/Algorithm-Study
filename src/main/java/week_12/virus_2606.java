package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 8. 16..
 * 플로이드-와샬 문제
 * 다익스트라가 하나의 정점에서 다른 모든 정점으로 가는 최단거리를 구하는 알고리즘이다. 그리고 음의 가중치가 있어서는 안된다.
 * 플로이드-와샬은 모든 최단 경로를 구하는 알고리즘이다. 그리고 음의 가중치의 존재를 인정한다.
 */
public class virus_2606 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computer = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> networks = new ArrayList<>();
        for (int i=0; i<=computer*2; i++) {
            networks.add(new ArrayList<>());
        }
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int com1 = Integer.parseInt(st.nextToken());
            int com2 = Integer.parseInt(st.nextToken());
            networks.get(com1).add(com2);
            networks.get(com2).add(com1);
        }

        Virus virus = new Virus(networks);
        System.out.println(virus.getInfectionCount());
    }

    static class Virus {
        ArrayList<ArrayList<Integer>> networks = new ArrayList<>();

        public Virus(ArrayList<ArrayList<Integer>> networks) {
            this.networks = networks;
        }

        public int getInfectionCount() {
            int count = 0;
            boolean[] visited = new boolean[networks.size()];
            Queue<Integer> queue = new LinkedList<>();  //감염된 컴퓨터(ArrayList의 인덱스)
            queue.offer(1);
            visited[1] = true;
            while (!queue.isEmpty()) {
                int computer = queue.poll();
                ArrayList<Integer> infections = networks.get(computer);
                for (int i=0; i<infections.size(); i++) {
                    if (!visited[infections.get(i)]) {
                        count++;
                        queue.offer(infections.get(i));
                        visited[infections.get(i)] = true;
                    }
                }
            }
            return count;
        }
    }
}
