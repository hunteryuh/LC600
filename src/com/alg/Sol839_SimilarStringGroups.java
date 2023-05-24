package com.alg;

import sun.awt.image.ImageWatched;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?



Example 1:

Input: strs = ["tars","rats","arts","star"]
Output: 2
Example 2:

Input: strs = ["omv","ovm"]
Output: 1
 */
public class Sol839_SimilarStringGroups {
    Set<String> seen = new HashSet<>();
    public int numSimilarGroups(String[] strs) {
        if (strs.length < 2) return strs.length;
        int res = 0;
        for (int i = 0; i < strs.length; i++) {
            if(seen.contains(strs[i])) {
                continue;
            }
            res++;
            dfs(strs, strs[i]);
        }
        return res;
    }

    void dfs(String[] strs, String s) {
        for (int i = 0; i < strs.length; i++) {
            if (seen.contains(strs[i])) {
                continue;
            }
            if (isSimilar(s, strs[i])) { // in the same group
                seen.add(s);
                seen.add(strs[i]);
                dfs(strs, strs[i]);
            }
        }
    }
    boolean isSimilar(String a, String b) {
        if (a.equals(b)) return true;
        int diff = 0;
        for (int i = 0; i < a.length() && diff <= 2; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }
        return diff == 2;
    }

    public static void main(String[] args) {
        Sol839_SimilarStringGroups ss = new Sol839_SimilarStringGroups();
        String[] arrs = {"tars","rats","arts","star"};
        System.out.println(ss.numSimilarGroups(arrs));
    }


    // bfs
    public int numSimilarGroups_bfs(String[] strs) {
        int group = 0;
        boolean[] visited = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) continue;
            group++;
            Queue<String> queue = new LinkedList<>();
            queue.offer(strs[i]);
            while(!queue.isEmpty()) {
                String cur = queue.poll();
                for (int j = 0; j < strs.length; j++) {
                    if (visited[j]) continue;
                    if (isSimilar(cur, strs[j])) {
                       visited[j] = true;
                       queue.offer(strs[j]);
                    }
                }
            }
        }
        return group;
    }
}
