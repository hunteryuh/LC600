package com.alg;
/*
You are given a numeric string num, representing a very large palindrome.

Return the smallest palindrome larger than num that can be created by rearranging its digits. If no such palindrome exists, return an empty string "".

A palindrome is a number that reads the same backward as forward.



Example 1:

Input: num = "1221"
Output: "2112"
Explanation: The next palindrome larger than "1221" is "2112".

Example 2:

Input: num = "32123"
Output: ""
Explanation: No palindromes larger than "32123" can be made by rearranging the digits.

Example 3:

Input: num = "45544554"
Output: "54455445"
Explanation: The next palindrome larger than "45544554" is "54455445".

 */
public class Sol1842_NextPalindromeUsingSameDigits {
    // https://leetcode.com/problems/next-palindrome-using-same-digits/solutions/1182057/java-faster-than-100-time-o-n-next-permutation/
    public String nextPalindrome(String num) {
        // Using the function of question 31 to get the next permutation
        //https://leetcode.com/problems/next-permutation/
        int len = num.length();
        int[] arr = new int[len/2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = num.charAt(i) - '0';
        }
        if (!nextPermutation(arr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i: arr) {
            sb.append(i);
        }
        if (len % 2 == 0) {
            return sb.toString() + sb.reverse().toString();
        }
        return sb.toString() + num.substring(len/2, len/2 + 1) + sb.reverse().toString();
    }

    private boolean nextPermutation(int[] nums) {
        int n = nums.length - 1;
        int p = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                p = i;
                break;
            }
        }
        if (p == -1) {
            return false;
        }
        for (int i = n; i >= 0; i--) {
            if (nums[i] > nums[p]) {
                swap(nums, p , i);
                break;
            }
        }
        reverse(nums, p + 1, n);
        return true;
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        String num = "23143034132";
        Sol1842_NextPalindromeUsingSameDigits ss = new Sol1842_NextPalindromeUsingSameDigits();
        System.out.println(ss.nextPalindrome(num));
    }
}
