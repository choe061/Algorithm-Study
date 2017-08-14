package week_11_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 13..
 * Union-Find 알고리즘 사용
 *
 * Union-Find 알고리즘
 * 초기화 : N개의 원소가 각각의 집합에 포함되도록(각자 본인의 집합이 구성되도록) 초기화한다. (-> 이후에 각각의 집합들을 합친다)
 * Union(합치기) : 두 원소 a b가 있을 때, 조건에 따라 두 집합을 합친다.
 * Find(찾기) : 어떤 원소 a가 있을 때, 조건에 따라 a가 속할 수 있는 집합을 찾는다.
 *
 * Union-Find의 구현은 배열을 이용하지 않고, 트리 구조를 사용하여 구현했을 때 장점이 더 크다.
 *
 * <!--배열로 표현하였을 때-->
 *  1차원 배열로 표현한다.
 *  1) Array[i] : i번째 원소가 속하는 집합의 번호로 초기화한다.(Array[i] = i)
 *  2) Union(합치기) 연산
 *     두 집합을 합치기 위해 배열의 <모든 원소를 순회>하면서 하나의 집합 번호를 나머지 한 개의 집합 번호로 교체한다.
 *     모든 원소를 순회해야하기 때문에 시간복잡도 O(N)이 되서 연산이 느려진다.
 *  3) Find(찾기) 연산
 *     한 번만에 원소가 속하는 집합을 알 수 있다. 시간 복잡도는 O(1)
 *
 * <!--트리로 표현하였을 때-->
 *  배열보다 Union 연산을 더 빠르게 할 수 있다.
 *  1) 각 원소가 속하는 집합의 번호를 루트 노드의 원소로 정한다.
 *     초기화 => 각 노드는 모두 루트 노드가 된다. N개의 루트 노드를 생성하고, 자기 자신을 가리키는 포인터를 가지도록 설정한다.
 *  2) Union 연산을 하기 전 같은 집합에 속하는 원소인지 확인해야 한다.
 *     => 다른 집합인 경우만 Union 연산을 수행한다.
 *     같은 집합인지 아닌지 확인하는 방법은 같은 루트 노드를 가지는지 확인하면 된다.
 *     => 어떤 원소의 Root 노드를 찾는 Find 연산을 지원해야 한다.
 *  3) Find 연산을 수행하기 위해선 모든 자식 노드들은 부모 노드에 대한 레퍼런스를 가지고 있어야 한다.
 *     => 레퍼런스 정보를 가지고 어떤 노드의 루트 노드를 찾을 수 있게 된다.
 *     (부모가 자식에 대한 레퍼런스는 가질 필요가 없다.)
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
            UnionFind unionFind = new UnionFind(N);
            for (int i=0; i<N; i++) {
                for (int j=i+1; j<N; j++) {
                    if (unionFind.find(i) != unionFind.find(j) && isConnected(enermy.get(i), enermy.get(j))) {
                        unionFind.union(i, j);
                    }
                }
            }
            return unionFind.getCount();
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

    static class UnionFind {
        private int[] root;
        private int count;

        public UnionFind(int v) {
            this.root = new int[v];
            Arrays.fill(root, -1);
            this.count = v;
        }

        public int find(int node) {
            if (root[node] < 0) {   //root[node] == -1
                return node;
            }
            return root[node] = find(root[node]);
        }

        public void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);

            if (node1 != node2) {       //node1과 node2가 이미 같은 트리에 속하면(같은 값) 합치지 않고, 다른 트리인 경우 합친다.
                                        //작은 그룹을 더 큰 그룹에 결합한다
                if (root1 > root2) {    //root1이 더 큰 경우 root1과 root2를 임시적으로 swap한다
                    root1 ^= root2;     //int형 변수 두 개를 쉽게 swap하는 방법으로 XOR연산을 사용한다.
                    root2 ^= root1;
                    root1 ^= root2;
                }   //link : https://stackoverflow.com/questions/13766209/effective-swapping-of-elements-of-an-array-in-java
                root[root1] += root[root2]; //루트 노드의 원소 값을 root[root1] + root[root2]의 값으로 갱신하고
                root[root2] = root1;        //root2의 원소도 root1로 바꾼다
                count--;
            }
        }

        public int getSize(int node) {
            return -root[find(node)];
        }

        public int getCount() {
            return count;
        }
    }

//    static class UnionFind2 {
//        private int[] root;
//        private int[] rank;
//        private int count;
//
//        public UnionFind2(int v) {
//            this.root = new int[v];
//            this.rank = new int[v];
//            for (int i=0; i<v; i++) {
//                root[i] = i;
//                rank[i] = 1;
//            }
//            this.count = v;
//        }
//
//        public int find(int node) {
//            if (root[node] == node) {
//                return node;
//            }
//            return root[node] = find(root[node]);
//        }
//
//        public void union(int u, int v) {
//            int root1 = find(u);
//            int root2 = find(v);
//            if (root1 != root2) {
//                if (rank[root1] > rank[root2]) {
//                    root1 ^= root2;
//                    root2 ^= root1;
//                    root1 ^= root2;
//                }
//                root[root1] = root2;
//                if (rank[root1] == rank[root2]) {
//                    rank[v]++;
//                    count--;
//                }
//            }
//        }
//
//        public int getCount() {
//            return count;
//        }
//    }
}
