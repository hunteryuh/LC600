package com.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string formula representing a chemical formula, return the count of each atom.

The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.

    For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.

Two formulas are concatenated together to produce another formula.

    For example, "H2O2He3Mg4" is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula.

    For example, "(H2O2)" and "(H2O2)3" are formulas.

Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

The test cases are generated so that all the values in the output fit in a 32-bit integer.



Example 1:

Input: formula = "H2O"
Output: "H2O"
Explanation: The count of elements are {'H': 2, 'O': 1}.

Example 2:

Input: formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

Example 3:

Input: formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.

 */
public class Sol726_NumberOfAtoms {
//    public String countOfAtoms(String formula) {
//https://blog.csdn.net/katrina95/article/details/99947645
        //        StringBuilder result = new StringBuilder();
//        Map<String, Integer> map = helper(formula);
//        List<String> res = new ArrayList<>();
//        for (String str: map.keySet()) {
//            if (map.get(str) > 1) {
//                res.add(str + map.get(str));
//            } else {
//                res.add(str);
//            }
//        }
//        Collections.sort(res);
//        for (String str: res) {
//            result.append(str);
//        }
//        return result.toString();
//    }
//
//
//
//    private Map<String, Integer> helper(String s) {
//        Map<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c == '(') {
//                int index = read(s, i + 1);
//                Map<String, Integer> innerMap = helper(s.substring(i+1, index));
//                int mult = 1;
//                i = index - 1;
//                if (isDigit(s.charAt(index + 1))) {  // index out of bound for (H)
//                    int numIndex = readNum(s, index + 1);
//                    mult = Integer.parseInt(s.substring(index + 1, numIndex));
//                    i = numIndex - 1;
//                }
//                for (String str: innerMap.keySet()) {
//                    if (map.containsKey(str)) {
//                        map.put(str, map.get(str) + innerMap.get(str) * mult);
//                    } else {
//                        map.put(str, innerMap.get(str) * mult);
//                    }
//                }
//            } else if (Character.isLetter(c)) {
//                int elementIndex = readElement(s, i + 1);
//                String element = s.substring(i, elementIndex);
//                int count = 1;
//                i = elementIndex - 1;
//                if (elementIndex < s.length() && isDigit(s.charAt(elementIndex))) {
//                    int numIndex = readNum(s, elementIndex);
//                    count = Integer.parseInt(s.substring(elementIndex, numIndex));
//                    i = numIndex - 1;
//                }
//                if (map.containsKey(element)) {
//                    map.put(element, map.get(element) + count);
//                } else {
//                    map.put(element, count);
//                }
//            }
//        }
//        return map;
//    }
//
//    private int readElement(String s, int i) {
//        while (i < s.length() && isLowerCase(s.charAt(i))) {
//            i++;
//        }
//        return i;
//    }
//
//    private int readNum(String s, int i) {
//        while (i < s.length() && isDigit(s.charAt(i))) {
//            i++;
//        }
//        return i;
//        // H2O
//    }
//
//    private int read(String s, int i) {
//        int count = 0;
//        while (i < s.length()) {
//            if (s.charAt(i) == '(') {
//                count++;
//            } else if (s.charAt(i) == ')') {
//                if (count == 0) {
//                    return i;
//                } else {
//                    count--;
//                }
//            }
//            i++;
//        }
//        return -1;
//    }

// https://leetcode.com/problems/number-of-atoms/discuss/709919/Java-Map-%2B-recursive-solution
    private int i = 0;  // global var for current index
    private String s;   // make the formula a global var

    public String countOfAtoms2(String formula) {
        s = formula;
        Map<String, Integer> m = find(); // for find(s)
        StringBuilder sb = new StringBuilder();
//        List<String> temp = new ArrayList<>();
//        for (String str: m.keySet()) {
//            temp.add(str + (m.get(str) > 1 ? m.get(str) : ""));
//        }
//        Collections.sort(temp);
//        for (String str: temp) {
//            sb.append(str);
//        }

        m.keySet().stream().sorted()
                .forEach(k -> sb.append(k + (m.get(k) > 1 ? m.get(k) : "")));
        return sb.toString();
    }

    private Map<String, Integer> find() { // or find(String s); actually s is not changed.
        Map<String, Integer> res = new HashMap<>();
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == '(') {    // recursively build sub map
                i++;
                Map<String, Integer> m = find();
                int times = findTimes(s);
                m.forEach((k, v) -> res.put(k, res.getOrDefault(k, 0) + v * times));
            } else if (ch == ')') { // recurse back when seeing ')'
                i++;
                return res;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                while(++i < s.length() && Character.isLowerCase(s.charAt(i))) {
                    sb.append(s.charAt(i));
                }
                String element = sb.toString();
                res.put(element, res.getOrDefault(element, 0) + findTimes(s));
            }
        }
        return res;
    }

    private int findTimes(String s) {   // this also uses global i for position
        int times = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            times = times * 10 + (s.charAt(i) - '0');
            i++;
        }
        return Math.max(times, 1);
    }
}
