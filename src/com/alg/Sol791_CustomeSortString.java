package com.alg;
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

    public static void main(String[] args) {
        String s = "abc";
        String t = "bccda";
        System.out.println(customSortString(s,t));
    }
}
