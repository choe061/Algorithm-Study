package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by choi on 2017. 8. 5..
 */
public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution("SpIpGpOpNpGJqOqA");
        br.close();
    }

    public static String solution(String sentence) {
        String answer = "";
        char[] ch = sentence.toCharArray();
        ArrayList<Character> rule = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<ch.length; i++) {
            if (ch[i] >= 'a') {
                if (rule.isEmpty()) {
                    rule.add(ch[i]);
                } else {
                    if (ch[i] != rule.get(rule.size() - 1)) {
                        rule.add(ch[i]);
                        sb.append(" ");
                    }
                }
            } else {
                sb.append(ch[i]);
            }
        }
        sb.trimToSize();
        System.out.println(sb.toString());
        return answer;
    }
}
