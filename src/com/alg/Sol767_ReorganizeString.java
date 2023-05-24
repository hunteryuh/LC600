package com.alg;

import java.util.*;

/**
 * Created by HAU on 1/29/2018.
 */
/*Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""*/
public class Sol767_ReorganizeString {
    /*Algorithm

Find the count of each character, and use it to sort the string by count.

If at some point the number of occurrences of some character is greater than (N + 1) / 2, the task is impossible.

Otherwise, interleave the characters in the order described above.*/
    public static String reorganizeString(String S){
        int len = S.length();
        int[] count = new int[26];
        for(char c: S.toCharArray()) count[c - 'a'] += 100;
        for(int i = 0 ; i < 26; i++) count[i] += i;
        Arrays.sort(count);

        char[] res = new char[len];
        int t = 1;
        for(int i = 0; i < count.length; i++){
            int ct = count[i]/100; // get the actual count
            char ch = (char)('a' + count[i]%100); // get the char through the original index
            if ( ct > (len + 1)/2) return "";
            for(int j = 0; j < ct; j++){
                if(t >= len) t = 0;
                res[t] = ch;
                t += 2;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String s = "fffac";
        //System.out.println(reorganizeString(s));
        System.out.println(reorgString(s));
    }
    // method 2, priority queue
    public static String reorgString(String S){
        Map<Character,Integer> map = new HashMap<>();
        for(char c: S.toCharArray()){
            int count = map.getOrDefault(c,0) + 1;
            if ( count > (S.length() + 1)/2) return "";
            map.put(c,count);
        }
        // greedy : fetch char of max freq as next char in the result
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1]; //( char, count)
            }
        });

        for(char c: map.keySet()){
            pq.add(new int[]{c,map.get(c)});
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            int[] first = pq.poll();
            if( sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)){
                sb.append((char) first[0]);
                if(--first[1] > 0){
                    pq.add(first);
                }
            }else{
                int[] second = pq.poll();
                sb.append((char)second[0]);
                if(--second[1] > 0){
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }

    // method 2.1 priority queue

    public String reorgString2(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c: s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            if (freqMap.get(c) > ( s.length() + 1 ) / 2 ) {
                // more than half
                // odd: abb   3 + 1 )/2 = 2
                // even: aabb 4 + 1) / 2 = 2  abab
                return "";
            }
        }
        // sort by count DESC.
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b) -> freqMap.get(b) - freqMap.get(a));
        for (char c: freqMap.keySet()) {
            pq.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (pq.size() >= 2) {
            char c1 = pq.poll();
            char c2 = pq.poll();
            sb.append(c1).append(c2);
            freqMap.put(c1, freqMap.get(c1) - 1);
            if (freqMap.get(c1) > 0) {
                pq.add(c1);
            }
            freqMap.put(c2, freqMap.get(c2) - 1);
            if (freqMap.get(c2) > 0) {
                pq.add(c2);
            }
        }
        if (pq.size() > 0) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }
}
