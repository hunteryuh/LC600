package com.alg;

import java.util.*;

/*Find the length of the longest substring T of a given string (consists of lowercase letters only) such that
every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.*/
public class Sol395_LongestSubstringwithAtLeastKRepeatingChar {
    // split the string with pointers
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        int max = 0;
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i)-'a']++;
        }
        System.out.println(Arrays.toString(count));
        List<Integer> pos = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            if(count[s.charAt(i)-'a'] < k){
                pos.add(i);
            }
        }
        System.out.println(pos);
        if(pos.size() == 0) return s.length();
        pos.add(0,-1); // add -1 as first item in the list
        pos.add(s.length()); // end index
        for(int i = 1; i < pos.size(); i++){
            int start = pos.get(i-1) + 1;
            int end = pos.get(i);
            int next = longestSubstring(s.substring(start,end),k);
            if ( next > max) max = next;
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "aaabb";
        int k = 3;
        Sol395_LongestSubstringwithAtLeastKRepeatingChar cc = new Sol395_LongestSubstringwithAtLeastKRepeatingChar();
        String s1 = "abcde";
        System.out.println(cc.longestSubstring(s1, 2));
//        System.out.println(cc.getMaxUniqueLetters(s));
//        System.out.println(cc.longestSubstring(s,k));
//        String s2 = "abaaacbb";
//        System.out.println(cc.longestSubstring2(s2, 2));
        String s3 = "aaaa";
        System.out.println(cc.longestSubstring2(s3, 1));
    }

    // tiktok  10/23/2023
    // https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/editorial/
    // Time Complexity : O(maxUnique⋅N)
    // https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/solutions/170010/java-o-n-solution-with-detailed-explanation-sliding-window/
    public int longestSubstring2(String s, int k) {
        char[] str = s.toCharArray();
        int[] countMap = new int[26];
        int totalUnique = getTotalUniqueLetters(s);
        int result = 0;
        for (int currUnique = 1; currUnique <= totalUnique; currUnique++) {
            // reset countMap
            Arrays.fill(countMap, 0);
            int windowStart = 0, windowEnd = 0;
            int idx = 0, unique = 0, countAtLeastK = 0;
            while (windowEnd < str.length) {
                // expand the sliding window  abaaacbb 2
                if (unique <= currUnique) { // can be equal
                    idx = str[windowEnd] - 'a'; // index of right pointer
                    if (countMap[idx] == 0) unique++;
                    countMap[idx]++;
                    if (countMap[idx] == k) countAtLeastK++; // has to be equal, not >= otherwise it adds one more which is already k+
                    windowEnd++;
                }
                // shrink the sliding window
                else {
                    idx = str[windowStart] - 'a'; // this is the index of left pointer
                    if (countMap[idx] == k) countAtLeastK--;
                    countMap[idx]--;
                    if (countMap[idx] == 0) unique--;
                    windowStart++;
                }
//                if (currUnique == 1) {
//                    System.out.println("unique is " + unique + "windowStart=" + windowStart + " windownEnd=" + windowEnd);
//                }
                /*
                This means that our overall expression is confirming these two things in tandem:

    Yes, we have found "u" unique characters this time around
    and
    Yes, each one of those "u" unique characters found has at least K occurrences in the substring we are currently considering
    (therefore it is a legitimate length and we'll want to record it)

                 */
                if (unique == currUnique && unique == countAtLeastK) // checked without "unique == currUnique", still works
                    result = Math.max(windowEnd - windowStart, result);
            }
        }

        return result;
    }

    // get the maximum number of unique letters in the string s
    int getTotalUniqueLetters(String s) {
//        return (int) s.chars().distinct().count();  // oneline java 8
        boolean map[] = new boolean[26];
        int maxUnique = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map[s.charAt(i) - 'a']) {
                maxUnique++;
                map[s.charAt(i) - 'a'] = true;
            }
        }
        return maxUnique;
    }

    // sliding window 2, gucheng  基础算法(八) -- 滑动窗口
    // without the outer loop 1..26, it is hard to know how to move the right pointer and when to shrink due to different cases

    public int longestSubstringAtLeastKRepeatingChars(String s, int k) {
        int res = 0;
        int maxUnique = getTotalUniqueLetters(s);
        for (int u = 1; u <= maxUnique; u++) {
            Map<Character, Integer> map = new HashMap<>();
            int left = 0;
            int numOfCharNoLessThank = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
                if (map.get(c) == k) {
                    numOfCharNoLessThank++;
                }
                while (map.keySet().size() > u) {
                    char leftChar = s.charAt(left);
                    if (map.getOrDefault(leftChar, 0) == k) {
                        numOfCharNoLessThank--;
                    }
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) {
                        map.remove(leftChar);
                    }
                    left++;
                }
                int numUniqueChars = map.keySet().size();
                if (numUniqueChars == numOfCharNoLessThank) {
                    res = Math.max(res, i - left + 1);
                }
            }

        }
        return res;
    }


    // divide and conquer
    public int longestSubstringd(String s, int k) {
        return longestSubstringUtil(s, 0, s.length(), k);
    }

    int longestSubstringUtil(String s, int start, int end, int k) {
        if (end < k) return 0;
        int[] countMap = new int[26];
        // update the countMap with the count of each character
        for (int i = start; i < end; i++)
            countMap[s.charAt(i) - 'a']++;
        for (int mid = start; mid < end; mid++) {
            if (countMap[s.charAt(mid) - 'a'] >= k) continue;
            int midNext = mid + 1;
            while (midNext < end && countMap[s.charAt(midNext) - 'a'] < k) midNext++;
            return Math.max(longestSubstringUtil(s, start, mid, k),
                    longestSubstringUtil(s, midNext, end, k));
        }
        return (end - start);
    }
}
