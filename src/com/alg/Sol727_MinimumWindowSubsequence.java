package com.alg;
/*
Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.

If there is no such window in s1 that covers all characters in s2, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.



Example 1:

Input: s1 = "abcdebdde", s2 = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of s2 in the window must occur in order.

Example 2:

Input: s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl", s2 = "u"
Output: ""



Constraints:

    1 <= s1.length <= 2 * 104
    1 <= s2.length <= 100
    s1 and s2 consist of lowercase English letters.


 */
public class Sol727_MinimumWindowSubsequence {
    // https://leetcode.com/problems/minimum-window-subsequence/solutions/2985135/2-pointer-sliding-window-solution-with-comments/
    // time O(m x n), m: length of s2, n length of s1
    // space O(1)
    public String minWindow(String s1, String s2) {
        // initialize pointers
        int index1 = 0;
        int index2 = 0; // target string S2 to find
        int start = 0;
        int end = 0;
        String result = "";
        int minLength = s1.length() + 1;
        while (index1 < s1.length()) {
            if (s1.charAt(index1) == s2.charAt(index2)) {
                index2++;
                // when s1 contains all chars in s2, now index2 is at end of s2
                if (index2 == s2.length()) {
                    start = index1; // end pointer of one possible solution
                    end = index1 + 1; // store temporarily to calculate length later
                    index2--;
                    // create a reverse loop to find the start of the sequence
                    while (index2 >= 0) {
                        if (s1.charAt(start) == s2.charAt(index2)) {
                            index2--;
                        }
                        start--;
                    }
                    start++;
                    // check length of the sequence is smaller than minLength
                    if (end - start < minLength) {
                        minLength = end - start;
                        result = s1.substring(start, end); // end is the index after the window
                    }
                    // reset index1 to start and index2 to 0 to continue rest search
                    index1 = start;
                    index2 = 0;
                }
            }
            index1++;  // not found, move left pointer to 1 step right
        }
        return result;

    }
    // https://leetcode.com/discuss/interview-question/1249989/Airbnb-or-Phone-Screen

    // https://leetcode.com/problems/minimum-window-subsequence/solutions/1808258/very-straightforward-two-pointer-solution-using-java-feasible-for-interview/
    // easy to understand approach 2 with sliding window
    public String minWindow2(String s, String t) {
        int minLen = s.length() + t.length();
        String res = "";
        int left = 0, right = 0;
        while (right < s.length()) {
            int nextRight = extendRight(s, t, left);
            if (nextRight == -1){
                break;
            }
            right = nextRight - 1;
            left = shrinkLeft(s, t, right) + 1;
            if (minLen > (right - left + 1)){
                minLen = right - left + 1;
                res = s.substring(left, right + 1);
            }
            left++;
        }
        return res;
    }

    // return the first right index such that substring s[left:right] contains T as a sequence.
    // return -1 if there is no such substring
    private int extendRight(String s, String t, int left){
        int sIdx = left, tIdx = 0;
        while(sIdx < s.length() && tIdx < t.length()){
            if (s.charAt(sIdx) == t.charAt(tIdx)){
                tIdx++;
            }
            sIdx++;
        }
        if (tIdx < t.length()){
            return -1; // s[left:] not contains T as a sequence.
        }
        return sIdx;
    }

    // return index of last left, such that s[left:right] contains T.
    private int shrinkLeft(String s, String t, int right){
        int sIdx = right, tIdx = t.length() - 1;
        while(sIdx >= 0 && tIdx >= 0){
            if (s.charAt(sIdx) == t.charAt(tIdx)){
                tIdx--;
            }
            sIdx--;
        }
        return sIdx;
    }

}
