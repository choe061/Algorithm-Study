package week_10_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 9..
 */
public class BOJ_9517 {
    private static final int BOMB = 210;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        Queue<Question> questions = new LinkedList<>();
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            questions.add(new Question(Integer.parseInt(st.nextToken()), st.nextToken().toCharArray()[0]));
        }
        System.out.println(getNumber(K, questions));
        br.close();
    }

    private static int getNumber(int K, Queue<Question> questions) {
        int time = 0;
        int bombPlayer = K;

        while (!questions.isEmpty()) {
            Question question = questions.poll();
            time += question.getTime();
            if (time >= BOMB) {
                return bombPlayer;
            }
            if (question.getAnswer() == 'T') {  //정답이면 다음 사람으로 폭탄을 패스
                bombPlayer = (bombPlayer % 8) + 1;
            }
        }
        return bombPlayer;
    }

    static class Question {
        private int time;
        private char answer;

        public Question(int time, char answer) {
            this.time = time;
            this.answer = answer;
        }

        public int getTime() {
            return time;
        }

        public char getAnswer() {
            return answer;
        }
    }
}
