package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by HAU on 12/24/2017.
 */
/*Count number of substrings with exactly k distinct characters
*
* Given a string of lowercase alphabets, count all possible substrings
* (not necessarily distinct) that has exactly k distinct characters.*/
public class Sol0_amz_countSubstringsWithExactlyKDistinctCharacters {
    public static int countkDist(String str, int k){
        int res = 0;
        int len = str.length();
        //int arr[] = new int[26];
        for(int i = 0; i < len; i++){
            int number_dis = 0;
            int arr[] = new int[26];
            for(int j = i; j < len; j++){
                if(arr[str.charAt(j)-'a'] == 0){
                    number_dis++;
                }
                arr[str.charAt(j) - 'a']++;
                if( number_dis == k){
                    res++;
                }
                if (number_dis >k) break;
            }
        }
        return res;
    }

    // method 2: hashmap + substring printout
    public static void countDistSubstring(String str, int k){
        ArrayList<String> aList = new ArrayList<>();
        char[] cArr = str.toCharArray();
        for(int i = 0 ; i < cArr.length ; i++) {
            HashMap<Character, Boolean> hMap = new HashMap<>();
            String distinctStr = "";
            int currentlyFoundDistinctCount = 0;
            for(int j = i ; j < cArr.length ; j++) {
                boolean isDistinct = false;
                if(hMap.get(cArr[j]) == null) {
                    isDistinct = true;
                }
                if((isDistinct == false && currentlyFoundDistinctCount == k) || (currentlyFoundDistinctCount < k)) {
                    distinctStr += cArr[j];
                    hMap.put(cArr[j], true);
                    if(isDistinct) {
                        currentlyFoundDistinctCount++;
                    }

                    if(currentlyFoundDistinctCount == k) {
                        aList.add(distinctStr);
                    }
                } else {
                    break;
                }
            }
        }

        int len = aList.size();
        System.out.println("\n---------------------------------------------------------------");
        System.out.println(k+ " distinct characters for string \""+str +"\" are "+len+" : ");
        for(String listStr : aList) {
            System.out.print(" "+listStr);
        }
    }


    public static void main(String[] args) {
        String s = "abcbaa";
        int k = 3;
        //System.out.println(countkDist(s, k));
        //System.out.println(countDisSubs(s,k));
        // method 2 with all valid substring printed
//         countDistSubstring(s,k);  //abc abcb abcba abcbaa bcba bcbaa cba cbaa
        String t = "pqpq"; // pq qp pq pqp qpq  pqpq  6
        String tt = "abba"; // ab ba abb bba abba  5
        System.out.println(countkDist(t, 2));
        System.out.println(countkDist(tt, 2));
        Sol0_amz_countSubstringsWithExactlyKDistinctCharacters ss = new Sol0_amz_countSubstringsWithExactlyKDistinctCharacters();
        System.out.println(ss.KdistinctSubString(t, 2));
        System.out.println(ss.KdistinctSubString(tt, 2));
    }

    // method 3, hashmap, same idea as method 1
    // Time Complexity : O(n*n)
    public static int countDisSubs(String str, int k){
        ArrayList<String> resList = new ArrayList<>();
        HashMap<Character,Integer> map = new HashMap<>();
        int res = 0;
        for (int i =0; i < str.length(); i++){
            int dist = 0;
            map.clear();
            String subs = "";
            for (int j =i ; j < str.length(); j++){
                if(!map.containsKey(str.charAt(j))){
                    map.put(str.charAt(j),1);
                    dist++;
                }else{
                    map.put(str.charAt(j),1 + map.get(str.charAt(j)));
                }
                subs += str.charAt(j);
                if(dist == k && !resList.contains(subs)){
                    res++;
                    resList.add(subs);
                }
            }
        }
        for (String s: resList){
            System.out.print(s + " "); // abc abcb abcba abcbaa bcba bcbaa cba cbaa
        }
        return  res;
    }

    // O(n^2)
    public int kDistinctSub(String s, int k) {
        int count = 0;
        Set<Character> charsFound = new HashSet<>();
        for (int i=0; i < s.length(); i++) {
            int uniqueFound = 0;
            for (int j=i; j < s.length(); j++) {
                if (uniqueFound == k) {
                    if (charsFound.contains(s.charAt(j))) {
                        count++;
                    } else {
                        charsFound.clear();
                        break;
                    }
                } else {
                    if (!charsFound.contains(s.charAt(j))) {
                        charsFound.add(s.charAt(j));
                        uniqueFound++;
                        if (uniqueFound == k) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    // direct sliding window at https://leetcode.com/discuss/interview-question/370157 ??!!
    // doordash
    // https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window
    // sliding window
    public int KdistinctSubString(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }

    public int atMostK(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        int left = 0;
        int right = 0;
        int n = s.length();
        while (right < n) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)  + 1);
            while (map.size() > k) { // need to shrink the window
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }
                left++;
            }

            // window valid, the window length at the right point, is equal to the number of windows ending here
            count += right - left + 1; // this is a valid window [left, right] with at most K distinct elements
            right++;
        }
        return count;
    }
    // put in one loop:
    // https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/235417/Sliding-Window-Logical-Thinking
    public int subarraysWithKDistinct(int[] A, int K) {
        Map<Integer, Integer> window1 = new HashMap<>(), window2 = new HashMap<>();
        int l1 = 0, l2 = 0, result = 0;
        // At each loop we count all "good" subarrays that end at A[r].
        for (int r = 0; r < A.length; ++r) {
            // Add A[r] to both windows.
            window1.put(A[r], window1.getOrDefault(A[r], 0) + 1);
            window2.put(A[r], window2.getOrDefault(A[r], 0) + 1);

            while (window1.size() > K) {
                window1.put(A[l1], window1.get(A[l1]) - 1);
                if (window1.get(A[l1]) == 0) window1.remove(A[l1]);
                l1++;
            }
            while (window2.size() >= K) {
                window2.put(A[l2], window2.get(A[l2]) - 1);
                if (window2.get(A[l2]) == 0) window2.remove(A[l2]);
                l2++;
            }

            // We now count all subarrays with exactly K distinct elements that start in A[l1, l2 - 1] and that end at A[r].
            // These are the subarrays we count: A[l1, r], A[l1 + 1, r], A[l1 + 2, r], ..., A[l2 - 2, r], A[l2 - 1, r].
            result += l2 - l1;
        }
        return result;
    }

}
