package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 11/8/2017.
 */
/*Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".*/
public class Sol394_DecodeStrings {
    public static String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<String> res = new Stack<>();
        int i = 0;
        res.push("");
        while ( i < s.length()){
            char ch = s.charAt(i);
            if ( ch >='0' && ch <= '9'){
                int start = i;
                while ( s.charAt(i + 1 ) >= '0' && s.charAt(i+1) <='9') i++;
                count.push(Integer.parseInt(s.substring(start,i+1)));
            }else if ( ch == '['){
                res.push("");
            }else if ( ch ==']'){
                String str = res.pop();
                StringBuilder sb = new StringBuilder();
                int times = count.pop();
                for (int j = 0; j < times; j++){
                    sb.append(str);
                }
                res.push(res.pop()+ sb.toString()); // a stack, not a string list that can add..
                // so use .pop() + the new string to be pushed
            }else{
                res.push(res.pop() + ch);
            }
            i++;
        }
        return res.pop();
    }

    public static void main(String[] args) {
        String s = "3[b2[a]]c";
        System.out.println(decodeString(s));
    }
}
