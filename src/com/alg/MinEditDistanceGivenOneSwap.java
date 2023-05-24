package com.alg;
/*
https://www.1point3acres.com/bbs/thread-816299-3-1.html
"abcd"  "abdc"  0
"aabc"  "aacd"  1
用2个map统计2个string的char和对应的index应该可以，我没写完

assume same length
 */
public class MinEditDistanceGivenOneSwap {
    public int MinEditDistance1Swap(String s, String t) {
        int n = s.length();
        int countOfSameCharDiffIndex = 0;
        int[] sc = new int[26];
        int[] tc = new int[26];
        int same = 0;
        for (int i = 0; i < n; i++) {
            char si = s.charAt(i);
            char ti = t.charAt(i);
            if (si == ti) {
                same++;
            } else {
                sc[si - 'a']++;
                tc[ti - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            countOfSameCharDiffIndex += Math.min(tc[i], sc[i]);
        }
        if (countOfSameCharDiffIndex >= 1) {
            countOfSameCharDiffIndex = 1;
        }

        return n - same - countOfSameCharDiffIndex;
        // lc 299? cows
    }

    public static void main(String[] args) {
        String s = "abcd";
        String t = "dbca";
        MinEditDistanceGivenOneSwap mm = new MinEditDistanceGivenOneSwap();

        int res1 = mm.MinEditDistance1Swap(s, t);
        System.out.println(res1);
        String s1 = "aebc";
        String t1 = "aace";
        System.out.println(mm.MinEditDistance1Swap(s1, t1));
    }
}
