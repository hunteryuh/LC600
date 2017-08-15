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
        List<String> list2 = LetterCom2(digits);
        System.out.println(list2);
    }

    public static List<String> LetterCom2(String digits){
        LinkedList<String> res = new LinkedList<>();
        if ( digits == null || digits.length() == 0) return res;

        String[] maps ={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        res.add("");
        for (int i = 0; i < digits.length(); i++){
            int t = digits.charAt(i) - '0';
            while( res.peek().length() == i){
                String ss = res.remove(); // return the first
                for (char c : maps[t].toCharArray()){
                    res.add( ss + c);
                }
            }

        }
        return res;
    }
}
