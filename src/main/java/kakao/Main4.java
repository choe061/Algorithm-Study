package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by choi on 2017. 8. 5..
 */
public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
        br.close();
    }

    //조건 1 - *뒤에는 반드시 +가 *의 갯수 *2개 이상 있어야 한다
    //조건 2 - *가 0개면 +가 올수 없다 (i번째 *의 위치는 3*(i-1) 이전)
    //조건 3 - *가 n개이면 뒤로 +가 0~Nx2개 있을 수 있다
    public static int solution(int n) {
        int answer = 0;
        Queue<Song> queue = new LinkedList<>();
        queue.offer(new Song(1, 0, 0, "")); //시작 음높이는 1
        while (!queue.isEmpty()) {
            Song song = queue.poll();
            if (song.getHeight() == n) {
                if (song.getMulCount() * 2 == song.getAddCount()) {
                    System.out.println(song.getHeight() + ", " + song.getMulCount() + ", " + song.getAddCount()+", "+song.getStr());
                    answer++;
                }
            }
            if (song.getHeight() < n) {
                int mulCount = song.getMulCount();
                int addCount = song.getAddCount();
                String sb = song.getStr();
                //*는 +의 갯수에 상관없이 추가 가능
                if (song.getHeight() * 3 <= n) {
                    queue.offer(new Song(song.getHeight() * 3, mulCount + 1, addCount, sb + "*"));
                }
                int size = mulCount * 2 - addCount;
                if (size > 0 && song.getHeight() + 1 <= n) {
                    queue.offer(new Song(song.getHeight() + 1, mulCount, addCount + 1, sb + "+"));
                }
            }
        }
        return answer;
    }

    static class Song {
        private int height;
        private int mulCount, addCount;   //현재 음 높이 까지 들어간 *와 +의 갯수
        private String str;

        public Song(int height, int mulCount, int addCount, String str) {
            this.height = height;
            this.mulCount = mulCount;
            this.addCount = addCount;
            this.str = str;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getMulCount() {
            return mulCount;
        }

        public void setMulCount(int mulCount) {
            this.mulCount = mulCount;
        }

        public int getAddCount() {
            return addCount;
        }

        public void setAddCount(int addCount) {
            this.addCount = addCount;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
