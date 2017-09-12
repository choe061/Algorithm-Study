package kakaodemo;

/**
 * Created by choi on 2017. 9. 12. PM 6:06.
 */
public class Demo_1 {
    public static void main(String[] args) {
        System.out.println(solution(123));
        System.out.println(solution(987));
    }

    public static int solution(int n) {
        String stringNum = String.valueOf(n);
        int answer = 0;
        for (int i=0; i<stringNum.length(); i++) {
            answer += stringNum.charAt(i) - '0';
        }
        return answer;
    }
}
