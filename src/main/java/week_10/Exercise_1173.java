package week_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 7. 31..
 */
public class Exercise_1173 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //N분 운동이 목표
        int mm = Integer.parseInt(st.nextToken());  //맥박이 m보다 작아질 수 없다, 첫 맥박
        int M = Integer.parseInt(st.nextToken());   //맥박 최대치
        int T = Integer.parseInt(st.nextToken());   //맥박이 T만큼 증가
        int R = Integer.parseInt(st.nextToken());   //휴식 시 맥박이 R만큼 감소

        int time = 0;
        int count = 0;
        int now = mm;
        if (mm + T > M) {   //가장 낮은 맥박에서 1번 운동했을 때의 맥박이 최대치를 넘는다면 운동을 할 수 없음
            time = -1;
        } else {
            while (count < N) {
                time++;
                if (now + T <= M) { //운동을 할 수 있는 경우
                    now += T;
                    count++;
                } else {    //운동을 못하는 경우
                    if (now - R >= mm) {
                        now -= R;
                    } else {
                        now = mm;
                    }
                }
            }
        }
        System.out.println(time);
        br.close();
    }
}
