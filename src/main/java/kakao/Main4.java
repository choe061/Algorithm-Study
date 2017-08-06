package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 8. 5..
 */
public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(getCount(n));
        br.close();
    }

    //조건 1 - *뒤에는 반드시 +가 *의 갯수 *2개 이상 있어야 한다
    //조건 2 - *가 0개면 +가 올수 없다
    //조건 3 - *가 n개이면 뒤로 +가 0~Nx2개 있을 수 있다
    public static int solution(int n) {
        int answer = 0;
        int mulCount = 0;
        int addCount = 0;
        int num = 1;
        int set = getCount(n);
        for (int i=0; i<set; i++) {
            while (num <= n) {
                for (int j=1; j<=set; j++) {
                    int temp = j * 3;
                    for (int z=1; z<=set; z++) {
                        temp += 2;
                        if (temp == n);
                    }
                }
//                if (mulCount == 0) {
//                    num *= 3;
//                    mulCount++;
//                } else {
//
//                }
//                if (num == n) {
//                    answer++;
//                }
            }
        }
        return answer;
    }

    public static int getCount(int n) {
        int v = 1;
        int count = 0;
        while (v <= n) {
            v = 1;
            count++;
            for (int i=0; i<count; i++) {
                v *= 3;
            }
            for (int i=0; i<=count; i++) {
                v += 2;
            }
        }
        return count;
    }
}
