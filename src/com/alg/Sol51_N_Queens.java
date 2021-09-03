package com.alg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.



Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]
 */
public class Sol51_N_Queens {

    // https://www.jiuzhang.com/problem/n-queens/
    // https://leetcode-cn.com/problems/n-queens/solution/shou-hua-tu-jie-cong-jing-dian-de-nhuang-hou-wen-t/
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        dfs(n, results, sol);
        return results;
    }

    private void dfs(int n, List<List<String>> results, List<Integer> sol) {
        if (sol.size() == n) {
            results.add(buildBoard(sol));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (canPut(i, sol)) {
                sol.add(i);
                dfs(n, results, sol);
                sol.remove(sol.size() - 1);
            }
        }
    }

    private List<String> buildBoard(List<Integer> sol) {
        List<String> board = new ArrayList<>();
        int size = sol.size();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                if (j == sol.get(i)) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            board.add(sb.toString());
        }
        return board;
    }

    private boolean canPut(int k, List<Integer> sol) { // k is the column index
        // 1  2  3  4
        //若有其他皇后在同一列或同一斜线上则不合法
        int row = sol.size();
        for (int i = 0; i < sol.size(); i++) {  // is the row index
            if (k == sol.get(i)) return false;
            if (row + k == i + sol.get(i)) return false;
            if (row - k == i - sol.get(i)) return false;
        }
        return true;
    }

    // version 2  https://leetcode.com/problems/n-queens/discuss/19805/My-easy-understanding-Java-Solution


}
