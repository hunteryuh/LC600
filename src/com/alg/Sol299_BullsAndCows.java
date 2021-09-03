package com.alg;
/*
You are playing the Bulls and Cows game with your friend.

You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:

The number of "bulls", which are digits in the guess that are in the correct position.
The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.



Example 1:

Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
  |
"7810"
Example 2:

Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1123"        "1123"
  |      or     |
"0111"        "0111"
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
Example 3:

Input: secret = "1", guess = "0"
Output: "0A0B"
Example 4:

Input: secret = "1", guess = "1"
Output: "1A0B"
 */
public class Sol299_BullsAndCows {
    // https://leetcode.com/problems/bulls-and-cows/discuss/74621/One-pass-Java-solution
    public String getHint(String secret, String guess) {
        int[] count = new int[10];
        int bulls = 0;
        int cows = 0;
        int n = secret.length();
        for (int i = 0; i<n; i++) {
            int s = secret.charAt(i) - '0';  // Character.getNumericValue(secret.charAt(i) - 1)
            int g = guess.charAt(i) - '0';
            if (s == g) {
                //accurate match (same digit with same position)
                bulls++;
            } else {
                //if prev part of guess has curr digit in secret
                //then we found a pair that has same digit with different position
                if (count[s] < 0) cows++;
                //if prev part of secret has curr digit in guess
                //then we found a pair that has same digit with different position
                if (count[g] > 0) cows++;
                count[s]++;
                count[g]--;
            }
        }
        return bulls+"A"+cows+"B";
    }

    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";
        Sol299_BullsAndCows s = new Sol299_BullsAndCows();
        System.out.println(s.getHint(secret, guess));
    }
}
