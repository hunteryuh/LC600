package com.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.



Example 1:

Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.

Example 2:

Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.

Example 3:

Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.



Constraints:

    1 <= heights.length <= 105
    1 <= heights[i] <= 109


 */
public class Sol1762_BuildingWithAnOceanView {
    // tle... O(n^2)
    public int[] findBuildings(int[] heights) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < heights.length - 1; i++) {
            int cur = heights[i];
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] >= cur) {
                    break;
                }
                if (j == heights.length - 1) {
                    res.add(i);
                }
            }
        }
        res.add(heights.length - 1);
        return res.stream().mapToInt(i -> i).toArray();
    }

    // https://leetcode.com/problems/buildings-with-an-ocean-view/editorial/
    // time: O(n) linear scan
    public int[] findBuildings2(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (!list.isEmpty() && heights[list.get(list.size() - 1)] <= heights[i]) {
                list.remove(list.size() - 1);
            }
            list.add(i);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    // monotonic stack (decreasing)
    public int[] findBuildings3(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                list.add(i);
            }
            stack.push(i);
        }
        int [] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(list.size() - 1 - i);
        }
        // res.stream().mapToInt(i -> i).toArray()
        return res;

    }

    // O(n), just keep one variable to denote the tallest building seen so far
    public int[] findBuildings4(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        int maxHeight = -1;

        for (int current = n - 1; current >= 0; current--) {
            // If there is no building higher (or equal) than the current one to its right,
            // push it in the list.
            if (maxHeight < heights[current]) {
                list.add(current);

                // Update max building till now.
                maxHeight = heights[current];
            }
        }

        // Push building indices from list to res array in reverse order.
        System.out.println(list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(list.size() - 1 - i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = {4,2,3,1};
        Sol1762_BuildingWithAnOceanView ss = new Sol1762_BuildingWithAnOceanView();
        ss.findBuildings4(heights);
    }

}
