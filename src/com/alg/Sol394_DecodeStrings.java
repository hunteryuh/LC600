package com.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HAU on 11/8/2017.
 */
/*Examples:
microsoft 2/2/2022  decode string
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
            if ( ch >='0' && ch <= '9') {
                int start = i;
                while (Character.isDigit(i + 1)) i++;
                // isdigit
                count.push(Integer.parseInt(s.substring(start,i+1)));
            }else if ( ch == '[') {
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

    // bottom  2[bc]   top
    // decoded cb
    // https://leetcode.com/problems/decode-string/solution/
    public String decodeString2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                List<Character> decodedString = new ArrayList<>();
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                    // decoded cb
                }
                // now the top element is [
                stack.pop();
                // get the number k
                int base = 1;
                int k = 0;
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString]
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j>=0; j--) {
                        stack.push(decodedString.get(j));
                        // bottom   bcbc
                    }
                    k--;
                }

            } else {
                stack.push(c);
            }
        }
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }


    /*
Complexity Analysis

Assume, nnn is the length of the string sss.

    Time Complexity: O(maxK⋅n)\mathcal{O}(\text{maxK} \cdot n)O(maxK⋅n), where maxK\text{maxK}maxK is the maximum value of kkk and nnn is the length of a given string sss. We traverse a string of size nnn and iterate kkk times to decode each pattern of form k[string]\text{k[string]}k[string]. This gives us worst case time complexity as O(maxK⋅n)\mathcal{O}(\text{maxK} \cdot n)O(maxK⋅n).

    Space Complexity: O(m+n)\mathcal{O}(m+n)O(m+n), where mmm is the number of letters(a-z) and nnn is the number of digits(0-9) in string sss. In worst case, the maximum size of stringStack\text{stringStack}stringStack and countStack\text{countStack}countStack could be mmm and nnn respectively.

     */
    public String decodeString3(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}
