package com.alg;

/**
 * Created by HAU on 1/31/2018.
 */
/*Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate

Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.

It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.

The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.

Example 1:
Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
Output: "steps"
Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
Note that the answer is not "step", because the letter "s" must occur in the word twice.
Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
Example 2:
Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
Output: "pest"
Explanation: There are 3 smallest length words that contains the letters "s".
We return the one that occurred first.*/
public class Sol748_ShortestCompletingWord {
    public static String shortestCompletingWord(String licensePlate, String[] words) {
        int[] target = count(licensePlate);
        String ret = "";
        for(String word: words){
            if (( ret.length() == 0 || word.length()<ret.length() )&&
            completes(count(word.toLowerCase()),target) ){
                ret = word;
            }
        }
        return ret;
    }

    private static boolean completes(int[] count0, int[] target) {
        for(int i = 0; i < 26; i++){
            if(count0[i] < target[i]){
                return false;
            }
        }
        return true;
    }

    private static int[] count(String licensePlate) {
        int[] res = new int[26];
        for(char c:licensePlate.toCharArray()){
            int index = Character.toLowerCase(c) - 'a';
            if( index >=0 && index < 26){
                res[index]++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String plate = "ptst";
        String[] dics ={"powerpoints","topst","tt4sp","pts1t"};
        System.out.println(shortestCompletingWord(plate,dics));
    }
}
