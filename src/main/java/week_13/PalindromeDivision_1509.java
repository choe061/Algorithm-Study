package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by choi on 2017. 9. 2..
 */
public class PalindromeDivision_1509 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Palindrome palindrome = new Palindrome(str);
        System.out.println(palindrome.getMinDivision());
        br.close();
    }

    static class Palindrome {
        private String str;

        public Palindrome(String str) {
            this.str = str;
        }

        public int getMinDivision() {
            int len = str.length();
            int[] minCount = new int[len + 1];
            minCount[0] = 0;
            for (int i=1; i<=len; i++) {
                minCount[i] = -1;
                for (int j=1; j<=i; j++) {
                    if (isPalindrome(str, j, i)) {
                        minCount[i] = Math.min(minCount[i], minCount[j - 1] + 1);
                    }
                }
            }
            return minCount[len];
        }

        private boolean isPalindrome(String origin, int j, int i) {
            StringBuilder temp = new StringBuilder(origin.substring(j, i));
            System.out.println(temp);
            return temp.reverse().toString().equals(origin.substring(j, i));
        }

        private boolean isPalindrome(String origin) {
            StringBuilder temp = new StringBuilder(origin);
            return origin.equals(temp.toString());
        }
    }
}
