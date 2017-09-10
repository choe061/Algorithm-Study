package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by choi on 2017. 8. 5..
 * 영문 대문자는 원래 문구, 소문자는 특수기호를 의미
 * (규칙 1) 특정 단어를 선택하여 글자 사이마다 같은 기호를 넣는다. ex) HELLO -> HaEaLaLaO
 * (규칙 2) 특정 단어를 선택하여 단어 앞뒤에 같은 기호를 넣는다. ex) WORLD -> bWORLDb
 *
 * 위의 두 가지 규칙은 한 단어에 모두 적용될 수 있지만 같은 규칙은 두 번 적용될 수 없다.
 * 한 번 쓰인 소문자(특수기호)는 다시 쓰일 수 없다.
 */
public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution("SpIpGpOpNpGJqOqA");
        br.close();
    }

    public static String solution(String sentence) {
        char[] chs = sentence.toCharArray();
        ArrayList<Character> answer = new ArrayList<>();
        ArrayList<Character> temp = new ArrayList<>();
        Stack<Character> rules = new Stack<>();
        int continusUpperCaseCount = 0;
        int size = chs.length;
        for (int i=0; i<size; i++) {
            if (chs[i] >= 'a' && chs[i] <= 'z') {
                char top;
                if (!rules.isEmpty()) {
                    top = rules.peek();
                    if (rules.peek() == chs[i]) {
                        if (continusUpperCaseCount >= 2) {
                            answer.add(' ');
                            answer.addAll(temp);
                            temp.clear();
                            rules.pop();
                        } else {
                            answer.addAll(temp);
                            temp.clear();
                        }
                        if (i < size - 1) {
                            if (chs[i + 1] >= 'A' && chs[i + 1] <= 'Z') {
                                answer.add(chs[++i]);
                            }
                        }
                    } else {
                        if (continusUpperCaseCount >= 2) {
                            answer.add(' ');
                            answer.addAll(temp);
                            temp.clear();
                            rules.pop();
                        } else {
                            answer.addAll(temp);
                            temp.clear();
                        }
                        rules.push(chs[i]);
                    }
                } else {
                    rules.push(chs[i]);
                }
                continusUpperCaseCount = 0;
            } else {    //대문자인 경우
                continusUpperCaseCount++;
                temp.add(chs[i]);
            }
        }
        answer.addAll(temp);
        System.out.println(answer);

        return "";
    }

    private static String changeFirstRule(String sentence) {

        return null;
    }

    private static String changeSecondRule(String sentence) {

        return null;
    }
}
