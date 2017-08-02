package kmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by choi on 2017. 8. 2..
 * 접미사 배열(suffix array)과 LCP(Longest Common Prefix, 가장 긴 공통 접두사)를 이용
 */
public class Cubeditor_1701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        int lcp = 0;

        String[] suffixArray = getSuffixArray(T);
        Arrays.sort(suffixArray);

        for (int i=0; i<suffixArray.length - 1; i++) {
            int current = getLCP(suffixArray[i], suffixArray[i + 1]);
            lcp = Math.max(current, lcp);
        }
        System.out.println(lcp);
        br.close();
    }

    //접미사 배열 구하기
    private static String[] getSuffixArray(String T) {
        String[] suffixArray = new String[T.length()];
        for (int i=0; i<T.length(); i++) {
            suffixArray[i] = T.substring(i, T.length());
        }
        return suffixArray;
    }

    private static int getLCP(String s1, String s2) {
        int length = s1.length() > s2.length() ? s2.length() : s1.length();
        int lcpLength = length;

        for (int i=0; i<length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                lcpLength = i;
                break;
            }
        }
        return lcpLength;
    }
}
