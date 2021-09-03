package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 8/14/2017.
 */
/*
Given a digit string, return all possible letter combinations that the number could represent.
        Input:Digit string "23"
        Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
        Note:
        Although the above answer is in lexicographical order, your answer could be in any order you want.*/
public class Sol17_LetterCombinationOfPhoneNumber {
    public static List<String> letterCombinations(String digits){
        String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        ArrayList<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        StringBuilder sb = new StringBuilder();
        dfs(digits,map,sb,result,0);
        return result;
    }

    private static void dfs(String digits, String[] map, StringBuilder sb, ArrayList<String> result, int index) {
        if (sb.length() == digits.length()){
            result.add(sb.toString());
            return;
        }
        char c = digits.charAt(index);
        String letters = map[c-'0'];
        for (int i = 0; i < letters.length();i++){
            sb.append(letters.charAt(i));
            dfs(digits,map,sb,result,index + 1);
            sb.deleteCharAt(sb.length() - 1);

        }
    }

    public static void main(String[] args) {
        String digits  = "23";
        //List<String> list = letterCombinations(digits);
        //System.out.println(list);
//        List<String> list2 = LetterCom2(digits);
//        System.out.println(list2);

        Sol17_LetterCombinationOfPhoneNumber s = new Sol17_LetterCombinationOfPhoneNumber();
        List<String> l3 = s.letterComb(digits);
        System.out.println(l3);
    }

    public static List<String> LetterCom2(String digits){
        LinkedList<String> res = new LinkedList<>();
        if ( digits == null || digits.length() == 0) return res;

        String[] maps ={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        res.add("");
        for (int i = 0; i < digits.length(); i++){
            int t = digits.charAt(i) - '0';
//            int t = Character.getNumericValue(digits.charAt(i));
            while (res.peek().length() == i){
                String ss = res.remove(); // return the first
                for (char c : maps[t].toCharArray()){
                    res.add( ss + c);
                }
            }

        }
        return res;
    }


    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0017.%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81%E7%9A%84%E5%AD%97%E6%AF%8D%E7%BB%84%E5%90%88.md
    public List<String> letterComb(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            return res;
        }
        String[] dlmap = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
        StringBuilder sb = new StringBuilder();
        dfs(digits, dlmap, res, sb, 0);
        return res;
    }

    private void dfs(String digits, String[] map, List<String> res, StringBuilder sb, int start) {
        if (start == digits.length()) {
            res.add(sb.toString());
            return;
        }
        int index = digits.charAt(start) - '0';  // to track and convert the digit in the digits to the index in the map
        String possibleLetters = map[index];
        for (int i = 0; i < possibleLetters.length(); i++) {
            sb.append(possibleLetters.charAt(i));
            dfs(digits, map, res, sb, start + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
