package com.alg;

/**
 * Created by HAU on 11/21/2017.
 */
public class Sol692_CountBinarySubstrings {
    public static int countBinarySubstrings(String s) {
        int zeros=0, ones=0;
        int last=-1, count=0;
        for(char c:s.toCharArray()) {
            if(c == '0') {
                if (last == 1) zeros=0;
                zeros++;
                last=0;
            } else {
                if (last == 0) ones=0;
                ones++;
                last=1;
            }
            if((last==1 && zeros >= ones) || (last==0 && ones >= zeros)) count++;
        }
        return count;
    }

    // version 2: optimization
    // https://leetcode.com/problems/count-binary-substrings/discuss/108600/Java-O(n)-Time-O(1)-Space
    public static int countBinarySubstrings2(String s) {
        int res= 0;
        int curr = 1;
        int pre = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                curr++;
            } else {
                pre = curr;
                curr = 1;
            }
            if ( pre >= curr) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "00110011";
        System.out.println(countBinarySubstrings(s));
        System.out.println(countBinarySubstrings2(s));
    }
}
