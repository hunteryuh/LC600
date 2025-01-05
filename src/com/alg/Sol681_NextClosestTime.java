package com.alg;

import java.util.Arrays;
import java.util.TreeSet;

/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.



Example 1:

Input: time = "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
It is not 19:33, because this occurs 23 hours and 59 minutes later.

Example 2:

Input: time = "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.



Constraints:

    time.length == 5
    time is a valid time in the form "HH:MM".
    0 <= HH < 24
    0 <= MM < 60


 */
public class Sol681_NextClosestTime {
    public String nextClosestTime(String time) {
        char[] res = time.toCharArray();
        Character[] digits = new Character[]{res[0],res[1],res[3],res[4]};
        TreeSet<Character> set = new TreeSet<Character>(Arrays.asList(digits));

        res[4] = next(set, res[4],'9');
        if (time.charAt(4) < res[4]) return new String(res);

        res[3] = next(set, res[3],'5');
        if (time.charAt(3) < res[3]) return new String(res);

        res[1] = next(set, res[1], res[0] == '2' ? '3' : '9');
        if (time.charAt(1) < res[1]) return new String(res);

        res[0] = next(set, res[0],'2');
        return new String(res);
    }

    private char next(TreeSet<Character> set, char c, char limit){
        Character n = set.higher(c); // return the least element in this set which is strictly greater than the given element ele. If no such element is there then this method returns NULL.
        return n == null || n > limit ? set.first() : n; // first: returns the lowest element in the set
    }
}
