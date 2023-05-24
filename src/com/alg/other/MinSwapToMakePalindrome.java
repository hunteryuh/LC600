package com.alg.other;
/*
Given a string, what is the minimum number of adjacent swaps required to convert a string into a palindrome. If not possible, return -1.

Example 1:

Input: "mamad"
Output: 3
Example 2:

Input: "asflkj"
Output: -1
Example 3:

Input: "aabb"
Output: 2
Example 4:

Input: "ntiin"
Output: 1
Explanation: swap 't' with 'i' => "nitin"
 */
public class MinSwapToMakePalindrome {
    // https://algo.monster/problems/min_swaps_to_make_palindrome
    // https://leetcode.com/discuss/interview-question/351783/
    /*
    /**
     * Algorithm:
     *     1. First check the given string is a jumbled/shuffled palindrome or not.
     *     2. Next have two pointers p1 at 0 and p2 at s.length - 1 and start iterating.
     *     3. If chars at both the pointers are same then just shrink the window (increase the p1 and decrease the p2).
     *     4. or Else
     *          a. find the matching pair and swap the char to p2 index (increase swap count while swapping) and finally shrink the window.
     *          b. if not found just swap once with adjacent index and increase the swap count (do not shrink the window here)
     *     5. Print the Swap Count
     *
     * Time Complexity: O(n) to find Palindrome + [ O(n) for two pointer iteration * O(n) for checking and swapping ] so => O(n^2)
     * Space Complexity: O(n)
     *
     */
    public int minSwapForPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        if (!isValid(s)) return -1;
        // 2 pointer
        int p1 = 0;
        int p2 = s.length() - 1;
        char[] chars = s.toCharArray();
        int totalSwaps = 0;
        while (p1 < p2) {
            if (chars[p1] == chars[p2]) {
                p1++;
                p2--;
            } else {
                int k = p2;
                while (k > p1 && chars[k] != chars[p1]) {
                    k--;
                }
                if (k == p1) {// no matching found
                    //  # THIS LONER CHAR WILL EVENTUALLY END UP IN THE MIDDLE OF THE STRING
                    swap(chars, p1, p1 +1);
                    totalSwaps++;
                } else {  // matching char found , swap until k reaches p2
                    // CHAR FOUND - SWAP WITH RIGHT NEIGHBOR UNTIL IT ENDS UP AT THE BACK
                    while (k < p2) {
                        swap(chars, k, k+1);
                        k++;
                        totalSwaps++;
                    }
                    p1++;
                    p2--;
                }
            }
        }
        return totalSwaps;
    }
    private void swap(char[] chars, int p, int q) {
        char temp= chars[p];
        chars[p] = chars[q];
        chars[q] = temp;
    }

    private boolean isValid(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int odd = 0;
        for (int count: freq) {
            if (count % 2 == 1) {
                odd++;
            }
            if (odd > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MinSwapToMakePalindrome mm = new MinSwapToMakePalindrome();
        String s1 = "mamad";  //3
        String s2 = "asflkj";  // -1
        String s3 = "aabb";  //
        String s4 = "acabb";  //
        System.out.println(mm.minSwapForPalindrome(s4));
    }
}
