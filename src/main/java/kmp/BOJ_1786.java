package kmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by choi on 2017. 7. 24..
 * 백준 1786번 : 찾기
 * kmp는 두가지로 수행
 * 1. 주어진 패턴에 대해 pi배열을 얻는 함수
 * 2. pi배열을 문자열 검색을 하는 kmp 함수
 *
 * 입력
 * T - Text
 * P - Pattern
 * 출력
 * P가 T에 몇번 나타나는지 횟수 출력
 * P가 나타나는 위치를 출력 (index는 1부터 시작)
 */
public class BOJ_1786 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();
        Search search = new TSearch(T, P);
        ArrayList<Integer> result = search.kmp();
        System.out.println(result.size());
        for(Object o : result) {
            System.out.print(((int)o+1)+" ");
        }
        br.close();
    }

    static class TSearch implements Search {
        private String t, p;

        public TSearch(String T, String P) {
            this.t = T;
            this.p = P;
        }

        private ArrayList<Integer> getPI(String p) {
            ArrayList<Integer> pi = new ArrayList<>();
            int m = p.length();
            int j = 0;
            pi.add(0);                  //패턴 문자열의 첫번째 문자는 index 0
            for(int i=1; i<m; i++) {    //index 1부터 시작
                while(j>0 && p.charAt(i) != p.charAt(j)) {
                    j = pi.get(j-1);    //이전의 데이터를 사용   ,(i번째와 j번째가 일치하는 경우) 이전의 데이터에 1을 증가시켜 삽입
                }
                if(p.charAt(i) == p.charAt(j)) {
                    pi.add(i, ++j);
                } else {
                    pi.add(i, 0);
                }
            }
            System.out.println(pi);
            return pi;
        }

        @Override
        public ArrayList<Integer> kmp() {
            ArrayList<Integer> ans = new ArrayList<>();
            ArrayList<Integer> pi = getPI(p);
            int t_size = t.length();
            int p_size = p.length();
            int j = 0;

            for(int i=0; i<t_size; i++) {
                while(j>0 && t.charAt(i) != p.charAt(j)) {
                    j = pi.get(j-1);
                    System.out.println("점프!!! : "+t.charAt(i)+", "+p.charAt(j)+", "+j);
                }
                System.out.println("t["+i+"] : "+t.charAt(i)+", p["+j+"] : "+p.charAt(j)+", "+(t.charAt(i) == p.charAt(j)));
                if(t.charAt(i) == p.charAt(j)) {
                    if(j == p_size-1) {         //j의 index와 pattern size-1가 같아지는 순간
                        ans.add(i-(p_size-1));  //(i는 Text의 인덱스)i번째에서 Pattern-1만큼 빼면 패턴 문자열이 시작하는 지점이 된다
                        System.out.println("ans add : "+(i-(p_size-1)));
                        j = pi.get(j);
                    } else {
                        j++;
                    }
                }
            }
            return ans;
        }
    }

    interface Search {
        ArrayList<Integer> kmp();
    }
}
