package com.alg;

import java.util.HashMap;
import java.util.Map;

/*S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input:
S = "cba"
T = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.*/
public class Sol791_CustomeSortString {
    public static String customSortString(String S, String T) {
        // Time Complexity: O(S.\text{length} + T.\text{length})O(S.length+T.length), the time it takes to iterate through S and T
        int[] count = new int[26];
        for(char c: T.toCharArray()){
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(char c: S.toCharArray()){
            while( count[c - 'a']-- > 0 ){
                sb.append(c);
            }
        }
        for(char i = 'a' ; i <= 'z'; i++){
            while (count[i - 'a']-- > 0){
                sb.append(i);
            }
        }
        return sb.toString();
    }

    public static String customSortString2(String S, String T) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : T.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c,0) + 1);
        }

        for (char c : S.toCharArray()) {
            // need to deal with chars that in S but not T
            if (tMap.containsKey(c)) {
                while (tMap.get(c) > 0) {  
                    // need to be "while" to keep the order of multi-occurrence letters in result
                    result.append(c);
                    tMap.put(c, tMap.get(c) - 1);
                }
            }
        }
        for (char c : T.toCharArray()) {
            if (tMap.get(c) > 0) {  // here "if" is ok as "c" could occur multiple times in the char array
                result.append(c);
                tMap.put(c, tMap.get(c) - 1);
            }
        }
        return result.toString();

    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "bcdea";
        System.out.println(customSortString(s,t));
        System.out.println(customSortString2(s,t));
        String s2 = "kqep";
        String t2 = "pekeqff";
        System.out.println(customSortString(s2,t2));
        System.out.println(customSortString2(s2,t2));
    }
}
