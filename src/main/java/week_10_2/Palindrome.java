package week_10_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 8. 9..
 */
public class Palindrome {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(makePalindrome(str));
        br.close();
    }

    private static int makePalindrome(String str) {
        if (str.length() == 0 || str.length() == 1) {   //문자열은 0이상
            return str.length();
        } else {
            StringBuilder temp = new StringBuilder(str);
            if (checkPalindrome(temp)) {
                return temp.length();
            }
            for (int i=0; i<str.length()-1; i++) {
                for (int j = i; j >= 0; j--) {
                    temp.append(str.charAt(j));
                }
                if (checkPalindrome(temp)) {
                    return temp.length();
                } else {
                    temp = new StringBuilder(str);
                }
            }
        }
        return str.length() * 2;
    }

    private static boolean checkPalindrome(StringBuilder sb) {
        StringBuilder temp = new StringBuilder(sb);
        return sb.toString().equals(temp.reverse().toString());
    }
}
