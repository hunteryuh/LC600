package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.

A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.



Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "1111"
Output: ["1.1.1.1"]
Example 4:

Input: s = "010010"
Output: ["0.10.0.10","0.100.1.0"]
Example 5:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
public class Sol93_RestoreIPAddresses {

    // https://www.jiuzhang.com/problem/restore-ip-addresses/
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4) return res;
        dfs(s, res, new ArrayList<>(), 0);
        return res;
    }
    private void dfs(String s, List<String> res, List<String> sol, int start) {
        if (sol.size() == 4 && start == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (String ss : sol) {
                sb.append(ss).append(".");
            }
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
        }
        for (int i = start; i < s.length() && i < start + 3; i++) {
            String sec = s.substring(start, i + 1);
            if (isValid(sec)) {
                sol.add(sec);
                dfs(s, res, sol, i + 1);
                sol.remove(sol.size() - 1);
            }
        }
    }
    private boolean isValid(String s) {
        if (s.startsWith("0") && s.length() > 1) {
            return false;
        }
        if (Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }


    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 4 * 3) {
            return res;
        }
        dfs2(s, res, new LinkedList<String>(), 0);
        return res;
    }
    private void dfs2(String s, List<String> res, LinkedList<String> sol, int start) {
        if (start == s.length() && sol.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (String c : sol) {
                sb.append(c).append(".");
            }
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (sol.size() >= 4) {
                return;
            }
            if (isPossible(s, start, i)) {
                sol.add(s.substring(start, i + 1));
                dfs2(s, res, sol, i + 1);
                sol.removeLast();
            }
        }
    }
    // start and end included
    private boolean isPossible(String s, int start, int end) {
        if (end - start > 2) {
            return false;
        }
        if (end - start > 0 && s.charAt(start) == '0') {
            return false;
        }
        if (Integer.parseInt(s.substring(start, end + 1)) > 255) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "010010";
        Sol93_RestoreIPAddresses ss = new Sol93_RestoreIPAddresses();
        List<String> res = ss.restoreIpAddresses2(s);
        System.out.println(res);
    }
}
