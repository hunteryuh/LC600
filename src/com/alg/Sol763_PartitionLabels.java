package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 1/25/2018.
 */
/*A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.*/
public class Sol763_PartitionLabels {
    //greedy
    public static List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[] last = new int[26];
        for(int i = 0; i < S.length(); i++){
            last[S.charAt(i)-'a'] = i;
        }
        int j = 0;
        int anchor = 0;
        for(int i = 0; i < S.length(); i++){
            j = Math.max(j, last[S.charAt(i)-'a']);
            if( j == i){
                res.add(j + 1 - anchor);
                anchor = i + 1;
            }
        }
        return res;

    }

    public static void main(String[] args) {
        String s = "abccafeef";
        System.out.println(partitionLabels(s));
    }
}
