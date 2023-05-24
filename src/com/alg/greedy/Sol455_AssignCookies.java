package com.alg.greedy;
/*
Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content.
Your goal is to maximize the number of your content children and output the maximum number.
 */

import java.util.Arrays;

public class Sol455_AssignCookies {
    public static int findContentChildren(int[] g, int[] s) {
        int res = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0;
        for (int i = 0; i < s.length && gi < g.length; i++) {
            if (s[i] >= g[gi]) {
                res++;
                gi++;
            }
        }

        return res;
    }

    public int findContentChildren2(int[] g, int[] s) {
        int res = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        // g: student need
        // s: cookie size
        int id = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if (id >= 0 && s[id] >= g[i]) {
                res++;
                id--;
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2 ,3};
        System.out.println(findContentChildren(g, s));
    }
}
