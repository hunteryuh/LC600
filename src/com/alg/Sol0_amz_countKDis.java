package com.alg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HAU on 12/24/2017.
 */
/*Count number of substrings with exactly k distinct characters
*
* Given a string of lowercase alphabets, count all possible substrings
* (not necessarily distinct) that has exactly k distinct characters.*/
public class Sol0_amz_countKDis {
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
         countDistSubstring(s,k);  //abc abcb abcba abcbaa bcba bcbaa cba cbaa
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
}
