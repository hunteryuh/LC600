package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HAU on 10/8/2017.
 */
/*Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.*/
public class Sol242_ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        //   time : O(n), space: O(n)
        if (s.length() != t.length() ) { //|| s.equals(t)
            return false;
        }
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i< s.length(); i++){
            Character c = s.charAt(i);
            if (counts.containsKey(c)) {
                counts.put(c, counts.get(c) + 1);
            } else {
                counts.put(c,1);
            }
            // one line
            //counts.put(c, counts.getOrDefault(c,0) + 1);
        }
        for (int i = 0; i< t.length(); i++){
            Character ch = t.charAt(i);
            if(!counts.containsKey(ch) || counts.get(ch) == 0) {
                //is about to be negative if t contains more ch than that in s
                return false;
            }
            counts.put(ch, counts.get(ch) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "silent";
        String t = "listen";
        System.out.println(isAnagram(s,t));
        String t2 = "lendsi";
        System.out.println(isAnagram3(s,t2));
        System.out.println(isAnagram3(s,t));
        System.out.println(isAnagram4(s,t));

        Sol242_ValidAnagram ss = new Sol242_ValidAnagram();
        String input = "hotpot";
        String[] list = new String[]{"hottop", "hotopt", "hotpit", "httoop", "hptoot"};
        System.out.println(ss.findKAnagrams(input, list, 3));
//        print(["hottop", "hotopt", "hptoot"])
//        input = "omega grill"
//        list = ["omeag grill", "omeea grill", "omega gril", "omegla gril"]
//        print(findSimilarRestaurants(intput, list))
//        print(["omeag grill"])
        //
    }

    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);  // O(n log n) with the sorting
    }

    // method 2 using int array
    public static boolean isAnagram3(String s, String t){
        int[] alpha = new int[26]; //'z'-'a'+1 = 26
        for(char c:s.toCharArray()) alpha[c - 'a']++;
        for(char c:t.toCharArray()) alpha[c - 'a']--;
        for(int i: alpha) if (i!= 0) return false;
        return true;
    }
    // version 3 using array
    // time : O(n), space: O(1)
    public static boolean isAnagram4(String s, String t){
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26]; //'z'-'a'+1 = 26
        for(char c:s.toCharArray()) count[c - 'a']++;
        for(char c:t.toCharArray()) {
            if (count[c-'a'] == 0) {
                return false;
            }
            count[c - 'a']--;
        }

        return true;
    }
    // follow up: what if the inputs contain unicode characters?
    // int [256] ( 1k)
    // each unicode： 16 bits, 2 bytes 2^16 = 64k, too large, can use hashtable


    // k anagram doordash
    // https://leetcode.com/discuss/interview-question/1505566/doordash-coding-new-grad
    // DoorDash obtains restaurant data from various sources which have varying quality. These sources often have duplicate merchants with minor typos in their names. The assignment is to create a list of unique restaurants across various sources ignoring the errors before onboarding them.
    //Definition: Similar restaurants
    //Two restaurants R1 and R2 are similar if we can swap a maximum of two letters (in different positions) of R1, so that it equals R2.
    //For example, source one may have a restaurant named "omega grill" while another source may have the same restaurant as "omgea grill".
    //For example, "biryani" and "briyani" are similar (swapping at positions 1 and 2). "biryani" is not similar to following, "biryeni" (no e to swap with), "briynai"(Needs 2 swap)
    //For a given restaurant name, find and return all the similar restaurant names in the list.
    //Implement the function below:
    //public List findSimilarRestaurants(String name, String[] list) {}
    //"""
    //
    //#Tests
    //input = "hotpot"
    //list = ["hottop", "hotopt", "hotpit", "httoop", "hptoot"]
    //print(findSimilarRestaurants(intput, list))
    //print(["hottop", "hotopt", "hptoot"])
    //
    //input = "biryani"
    //list = ["biryani", "biryeni", "biriyani", "biriany", "briynai"]
    //print(findSimilarRestaurants(intput, list))
    //print(["biryani", "biriany"])
    //
    //input = "omega grill"
    //list = ["omeag grill", "omeea grill", "omega gril", "omegla gril"]
    //print(findSimilarRestaurants(intput, list))
    //print(["omeag grill"])
    //
    //"""
    //Given a restaurant name, find other restaurants in the list that are k-anagrams with each other.
    // A name is a k-anagram with another name if both the conditions below are true:
    //The names contain the same number of characters.
    //The names can be turned into anagrams by changing at most k characters in the string
    //For example:
    //name = "grammar", k = 3, and list = ["anagram"], "grammar" is k-anagram with "anagram" since they contain the same number of characters and you can change 'r' to 'n' and 'm' to 'a'.
    //name = "omega grill", k = 2 and list = ["jmegra frill"], "omega grill" is k-anagram with "jmega frill" since they contain same number of characters and you can change 'o' to 'j' and 'g' to 'f'
    //public List findKAnagrams(String name, String[] list, int K) {}
    //"""

    // https://www.geeksforgeeks.org/check-two-strings-k-anagrams-not/

    public List<String> findKAnagrams(String name, String[] list, int K) {
        int n = name.length();

        List<String> res = new ArrayList<>();

        for (String item: list) {
            if (areKAnagrams(name, item, K)) {
                res.add(item);
            }
        }
        return res;
    }
    public boolean areKAnagrams(String str1, String str2, int k) {
        // If both strings are not of equal
        // length then return false
        int n = str1.length();
        if (str2.length() != n)
            return false;
//
        int[] freq = new int[256];
//
//        // Store the occurrence of all characters
//        // in a hash_array
        for (int i = 0; i < n; i++)
            freq[str1.charAt(i) - 'a']++;

        // Store the occurrence of all characters
        // in a hash_array
        int count = 0;
        for (int i = 0; i < n ; i++) {
            if (freq[str2.charAt(i)-'a'] > 0)
                freq[str2.charAt(i)-'a']--;
            else
                count++;

            if (count > k)
                return false;
        }

        // Return true if count is less than or
        // equal to k
        return true;
    }
    public boolean kAnagrams(String str1, String str2, int k) {
        int flag = 0;

        List<Character> list = new ArrayList<>();

        // First Condition: If both the
        // strings have different length ,
        // then they cannot form anagram
        if (str1.length() != str2.length()) {
            return false;
        }

        // Converting str1 to Character Array arr1
        char arr1[] = str1.toCharArray();

        // Converting str2 to Character Array arr2
        char arr2[] = str2.toCharArray();

        // Sort arr1 in increasing order
        Arrays.sort(arr1);

        // Sort arr2 in increasing order
        Arrays.sort(arr2);

        // Iterate till str1.length()
        for (int i = 0; i < str1.length(); i++) {

            // Condition if arr1[i] is
            // not equal to arr2[i]
            // then add it to list
            if (arr1[i] != arr2[i]) {
                list.add(arr2[i]);
            }
        }

        // Condition to check if
        // strings for K-anagram or not
        if (list.size() <= k)
            flag = 1;

        if (flag == 1)
            return true;
        else
            return false;
    }
}
