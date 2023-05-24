package com.alg.other;
/*
Given a string s containing only a and b, find longest substring of s such that s does not contain more than two contiguous occurrences of a and b.
Example 1:
Input: "aabbaaaaabb"
Output: "aabbaa"
Example 2:
Input: "aabbaabbaabbaa"
Output: "aabbaabbaabbaa"
 */
public class LongestSubstringWithout3ContiguousOccurrences {
    // https://molchevskyi.medium.com/best-solutions-for-microsoft-interview-tasks-5a738a6f8ba9
    public String LongestSubstring(String s) {
        int n = s.length();
        int start_ML = 0;
        int maxLength = 0;
        // start of current processing string of the same letters.
        int start = 0;
        // length of current processing string of the same letters
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i-1)) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                if (i - start + 1 > maxLength) {
                    maxLength = i - start + 1;
                    start_ML = start;
                }
            } else {
                count = 2;
                start_ML = i - 1; // for the start of new sequence
            }
        }
        return s.substring(start_ML, start_ML + maxLength);
    }

    public static void main(String[] args) {
        LongestSubstringWithout3ContiguousOccurrences ll = new LongestSubstringWithout3ContiguousOccurrences();
        String s1 = "aabbaabbaabbaa";
        String s1output = "aabbaabbaabbaa";
        String output = ll.LongestSubstring(s1);
        System.out.println(output.equals(s1output));
    }
}
