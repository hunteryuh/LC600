package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
An array is squareful if the sum of every pair of adjacent elements is a perfect square.

Given an integer array nums, return the number of permutations of nums that are squareful.

Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].



Example 1:

Input: nums = [1,17,8]
Output: 2
Explanation: [1,8,17] and [17,8,1] are the valid permutations.

Example 2:

Input: nums = [2,2,2]
Output: 1



Constraints:

    1 <= nums.length <= 12
    0 <= nums[i] <= 109


 */
public class Sol996_NumberOfSquarefulArrays {
    private int res;
    public int numSquarefulPerms(int[] nums) {
        if (nums.length == 1) return 0;
        Arrays.sort(nums);
        List<Integer> sol = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs0(nums, sol, visited); // works too if return in termination and no "else"
//        dfs(nums, sol, visited); // not return in termination condition
        return res;
    }

    private void dfs(int[] nums, List<Integer> sol, boolean[] visited) {
        if (sol.size() == nums.length) {
            if (isPerfectSquare(sol.get(sol.size() - 1) + sol.get(sol.size() - 2))) {
                res++;
            }
        } else {
            if (sol.size() <= 1 || isPerfectSquare(sol.get(sol.size() - 1) + sol.get(sol.size() - 2))) {
                for (int i = 0; i < nums.length; i++) {
                    if (visited[i]) continue;
                    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.md
                    if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
                    sol.add(nums[i]);
                    visited[i] = true;
                    dfs(nums, sol, visited);
                    visited[i] = false;
                    sol.remove(sol.size() - 1);
                }
            }
        }
    }

    private void dfs0(int[] nums, List<Integer> sol, boolean[] visited) {
        if (sol.size() == nums.length) {
            if (isPerfectSquare(sol.get(sol.size() - 1) + sol.get(sol.size() - 2))) {
                res++;
                return;
            }
        }
        if (sol.size() <= 1 || isPerfectSquare(sol.get(sol.size() - 1) + sol.get(sol.size() - 2))) {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) continue;
                if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
                sol.add(nums[i]);
                visited[i] = true;
                dfs0(nums, sol, visited);
                visited[i] = false;
                sol.remove(sol.size() - 1);
            }
        }
    }

    private boolean isPerfectSquare(int x) {
        return x == (int) Math.sqrt(x) * (int) Math.sqrt(x);
    }

    public static void main(String[] args) {
        int[] nums = {1,8,17};
        Sol996_NumberOfSquarefulArrays ss = new Sol996_NumberOfSquarefulArrays();
        System.out.println(ss.numSquarefulPerms(nums));
        System.out.println(ss.numSquarefulPerms2(nums));
    }

    private int res2;
    public int numSquarefulPerms2(int[] A) {
        if(A.length == 1) return 0;
        Arrays.sort(A);
        List<Integer> sol = new ArrayList<>();
        boolean[] visited = new boolean[A.length];
        dfs2(A, sol, visited);
        return this.res2;
    }
    private void dfs2(int[] A, List<Integer> temp, boolean[] visited){
        if (temp.size() == A.length) {
            if (square(temp.get(temp.size() - 1) + temp.get(temp.size() - 2))) this.res2++;
         }else if(temp.size() <= 1 || square(temp.get(temp.size() - 1) + temp.get(temp.size() - 2))) {
            for (int i = 0; i < A.length; i++){
                if(visited[i]) continue;
                if(i > 0 && A[i] == A[i - 1] && !visited[i - 1]) continue;
                temp.add(A[i]);
                visited[i] = true;
                dfs2(A, temp, visited);
                visited[i] = false;
                temp.remove(temp.size() - 1);
            }
        }

    }
    private boolean square(int x){
        return x == (int)Math.sqrt(x) * (int)Math.sqrt(x);
    }
}
