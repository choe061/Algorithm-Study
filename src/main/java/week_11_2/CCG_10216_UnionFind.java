package week_11_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 13..
 * Union-Find 알고리즘 사용
 *
 * Union-Find 알고리즘
 * 초기화 : N개의 원소가 각각의 집합에 포함되어 있도록(구성되도록) 초기화한다. (-> 이후에 각각의 집합들을 합친다)
 * Union(합치기) : 두 원소 a b가 있을 때, 조건에 따라 두 집합을 합친다.
 * Find(찾기) : 어떤 원소 a가 있을 때, 조건에 따라 a가 속할 수 있는 집합을 찾는다.
 *
 * Union-Find의 구현은 배열을 이용하지 않고, 트리 구조를 사용하여 구현했을 때 장점이 더 크다.
 *
 * -배열로 표현하였을 때
 *  1차원 배열로 표현한다.
 *  1) Array[i] : i번째 원소가 속하는 집합의 번호로 초기화한다.(Array[i] = i)
 *  2) Union(합치기) 연산
 *     두 집합을 합치기 위해 배열의 <모든 원소를 순회>하면서 하나의 집합 번호를 나머지 한 개의 집합 번호로 교체한다.
 *     모든 원소를 순회해야하기 때문에 시간복잡도 O(N)이 되서 연산이 느려진다.
 *  3) Find(찾기) 연산
 *     한 번만에 원소가 속하는 집합을 알 수 있다. 시간 복잡도는 O(1)
 *
 * -트리로 표현하였을 때
 *  배열보다 Union 연산을 더 빠르게 할 수 있다.
 *
 */
public class CCG_10216_UnionFind {
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
//            System.out.println(enermyGroup.getEnermyGroup());
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
