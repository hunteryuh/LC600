package com.alg;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/*
There is a long and thin painting that can be represented by a number line. You are given a 0-indexed 2D integer array paint of length n, where paint[i] = [starti, endi]. This means that on the ith day you need to paint the area between starti and endi.

Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting at most once.

Return an integer array worklog of length n, where worklog[i] is the amount of new area that you painted on the ith day.



Example 1:

Input: paint = [[1,4],[4,7],[5,8]]
Output: [3,3,1]
Explanation:
On day 0, paint everything between 1 and 4.
The amount of new area painted on day 0 is 4 - 1 = 3.
On day 1, paint everything between 4 and 7.
The amount of new area painted on day 1 is 7 - 4 = 3.
On day 2, paint everything between 7 and 8.
Everything between 5 and 7 was already painted on day 1.
The amount of new area painted on day 2 is 8 - 7 = 1.

Example 2:

Input: paint = [[1,4],[5,8],[4,7]]
Output: [3,3,1]
Explanation:
On day 0, paint everything between 1 and 4.
The amount of new area painted on day 0 is 4 - 1 = 3.
On day 1, paint everything between 5 and 8.
The amount of new area painted on day 1 is 8 - 5 = 3.
On day 2, paint everything between 4 and 5.
Everything between 5 and 7 was already painted on day 1.
The amount of new area painted on day 2 is 5 - 4 = 1.

Example 3:

Input: paint = [[1,5],[2,4]]
Output: [4,0]
Explanation:
On day 0, paint everything between 1 and 5.
The amount of new area painted on day 0 is 5 - 1 = 4.
On day 1, paint nothing because everything between 2 and 4 was already painted on day 0.
The amount of new area painted on day 1 is 0.



Constraints:

    1 <= paint.length <= 105
    paint[i].length == 2
    0 <= starti < endi <= 5 * 104


 */
public class Sol2158_AmountOfNewAreaPaintedEachDay {
    // https://leetcode.com/problems/amount-of-new-area-painted-each-day/solutions/2104462/java-jump-line-approach/
    // Time: O(n + m), where m is the length of the fence, n is number of intervals
    // Memory: O(n + m).

    public int[] amountPainted(int[][] paint) {
        if (paint == null || paint.length == 0) {
            return new int[0];
        }
//        int maxNumDays = 0;
//        for (int[] p: paint) {
//            maxNumDays = Math.max(maxNumDays, p[1]);
//        }
        int[] lines = new int[50001];
        int[] res = new int[paint.length];
        //iterate over all the paint intervals
        for (int i= 0; i < paint.length; i++) {
            int start = paint[i][0];
            int end = paint[i][1];
            while (start < end) {
                // jump one step at a time or do frog jump (if line value is already taken)
                int jump = Math.max(start + 1, lines[start]);
                //increase res value only when line value was empty
                System.out.println(jump + " at i " + i);
                if (lines[start] == 0) {
                    res[i]++;
                }
                // final step is to compress the line
                lines[start] = Math.max(lines[start], end);
                System.out.println("lines[" + start + "] == " + lines[start]);
                // update the start index to jump
                start = jump;

            }
        }
//        System.out.println(Arrays.toString(lines));
        return res;
    }

    public static void main(String[] args) {
        Sol2158_AmountOfNewAreaPaintedEachDay ss = new Sol2158_AmountOfNewAreaPaintedEachDay();
        int[][] paint = {{1,4}, {5,8}, {4,7}};
        int[] res = ss.amountPainted2(paint);
        System.out.println(Arrays.toString(res));
    }

    // sweep line
    // https://leetcode.com/problems/amount-of-new-area-painted-each-day/solutions/1740546/simple-and-short-line-sweep/
    public int[] amountPainted2(int[][] paint) {
        int n = paint.length;
        int max = 0;
        for (int[] p : paint) {
            max = Math.max(max, p[1]);
        }
        int[] res = new int[n];
        List<List<int[]>> lines = new ArrayList<>(max + 1);
        for (int i = 0; i <= max; i++) {
            lines.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            lines.get(paint[i][0]).add(new int[]{i, 1});
            lines.get(paint[i][1]).add(new int[]{i, -1});
        }
        for (int i = 0; i <= max; i++) {
            System.out.println(Arrays.deepToString(lines.get(i).toArray()));
        }
        // Store indices of paint that are available now
        TreeSet<Integer> set = new TreeSet<>();
        for (List<int[]> point : lines) {
            for (int[] layer: point) {
                if (layer[1] == 1) {
                    set.add(layer[0]);
                } else {
                    set.remove(layer[0]);
                }
            }
            if (!set.isEmpty()) {
                res[set.first()]++; // If there is any layer, we take the top layer and increment result for that layer by 1.
            }
            System.out.println("set is " + set);
            System.out.println(Arrays.toString(res));
            System.out.println();
        }
        return res;

    }

    // solution similar to leetcode 57 insert interval
    // https://leetcode.com/problems/amount-of-new-area-painted-each-day/solutions/1998564/java-solution-similar-to-leetcode-57-insert-interval/
    public int[] amountPainted3(int[][] paint) {
        List<int[]> intervals = new ArrayList<>();
        int[] ans = new int[paint.length];
        for (int i = 0; i < paint.length; i++) {
            int[] p = paint[i];
            List<int[]> merged = new ArrayList<>();
            int left = p[0];
            int right = p[1];
            int len = right - left;
            for (int j = 0; j < intervals.size(); j++) {
                int[] interval = intervals.get(j);
                if (merged.size() == 0 && p[1] < interval[0] ||
                        merged.size() > 0 && p[0] > merged.get(merged.size() - 1)[1] && p[1] < interval[0]) {
                    merged.add(p);
                }
                if (interval[1] < p[0] || interval[0] > p[1]) {
                    merged.add(interval);
                } else {
                    int[] intersection = new int[2];
                    intersection[0] = Math.max(interval[0], left);
                    intersection[1] = Math.min(interval[1], right);
                    len -= intersection[1] - intersection[0];
                    p[0] = Math.min(interval[0], p[0]);
                    p[1] = Math.max(interval[1], p[1]);
                }
            }

            if (merged.isEmpty() || p[0] > merged.get(merged.size() - 1)[1]) {
                merged.add(p);
            }
            ans[i] = len;
            intervals = merged;
        }
        return ans;
    }


    // O(n^2) brute force
    //https://leetcode.com/problems/amount-of-new-area-painted-each-day/solutions/4139292/easy-to-understand-just-mark-unvisited/

    public int[] amountPainted4(int[][] paint) {
        boolean[] visited = new boolean[50000 + 1];
        int n = paint.length;
        int[] rt = new int[n];
        for(int i = 0; i < n; i ++){
            int start = paint[i][0], end = paint[i][1];
            int k = 0;
            for(int p = start; p < end; p ++){
                if(!visited[p]) {
                    visited[p] = true;
                    k++;
                }
            }
            rt[i] = k;
        }
        return rt;
    }
}
