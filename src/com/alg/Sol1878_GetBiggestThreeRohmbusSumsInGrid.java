package com.alg;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
You are given an m x n integer matrix grid​​​.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:


Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.



Example 1:


Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
Output: [228,216,211]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 20 + 3 + 200 + 5 = 228
- Red: 200 + 2 + 10 + 4 = 216
- Green: 5 + 200 + 4 + 2 = 211
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: [20,9,8]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 4 + 2 + 6 + 8 = 20
- Red: 9 (area 0 rhombus in the bottom right corner)
- Green: 8 (area 0 rhombus in the bottom middle)
Example 3:

Input: grid = [[7,7,7]]
Output: [7]
Explanation: All three possible rhombus sums are the same, so return [7].


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 105
 */
public class Sol1878_GetBiggestThreeRohmbusSumsInGrid {
    // brute force
    // https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/discuss/1238787/Java-Brute-Force-Clean-O(n-*-m-*-L2)
    public int[] getBiggestThree(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Set<Integer> set = new HashSet<>();
        int maxSide = 1 + Math.min(n, m) / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m;j ++) {
//                if (pq.contains(grid[i][j])) continue;
                set.add(grid[i][j]);
//                if (pq.size() > 3) pq.poll();
                for (int k = 1; k <= maxSide; k++) {
                    int cur = getSum(grid, k, i , j, n, m);
                    if (cur != 1e9) {
//                        if (pq.contains(cur)) continue;
                        set.add(cur);
//                        if (pq.size() > 3) pq.poll();
                    }
                }
            }
        }
        return set.stream().sorted(Collections.reverseOrder()).limit(3).mapToInt(i -> i).toArray();
    }

    private int getSum(int[][] grid, int sidesize, int i, int j, int n, int m) {
        // treat i j as the left corner
        if (i + sidesize >= n || i - sidesize < 0 || j + 2 * sidesize >= m) {
            return (int) 1e9;
        }
        int sum = 0;
        for (int k = 1; k < sidesize; k++) {
            sum += grid[i-k][j+k];
            sum += grid[i+k][j+k];
            sum += grid[i-k][j + 2 * sidesize - k];
            sum += grid[i+k][j + 2 * sidesize - k];
        }
        sum += grid[i][j];
        sum += grid[i][j+ 2 * sidesize];
        sum += grid[i-sidesize][j + sidesize];
        sum += grid[i+sidesize][j + sidesize];
        return sum;
    }


    // set + pq
    //https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/discuss/1252908/Java-Simple-and-easy-to-understand-solution-clean-code-with-comments

    // treeset
    // https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/discuss/1238981/Java-Concise-Simple-28-lines-code.-O(N3)-or-No-Prefix-Sum

    // find largest rhombus
    // https://stackoverflow.com/questions/2607959/dynamic-programming-find-largest-diamond-rhombus
    public int rhombus(int[][] matrix) {
        int n = matrix.length;
        int maxr=0;
        int count = 0;
        for (int i=n-1;i>=0;i--)
        {
            for (int j=n-1;j>=0;j--)
            {
                if (matrix[i][j]>0)
                {
                    if ((i==n-1) || (j==n-1) || (i==0) || (j==0)) matrix[i][j]=1;
                    else {
                        matrix[i][j]=min4(matrix[i][j+1],matrix[i][j-1], matrix[i+1][j], matrix[i-1][j])+1;
                        if (matrix[i][j]==maxr) count++;
                        else if (matrix[i][j]>maxr) {
                            count=1;
                            maxr=matrix[i][j];
                        }
                    }
                }
            }
        }
        return maxr;
    }

    private int min4(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

}
