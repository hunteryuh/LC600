package com.alg.lint;
/*
Given a 2D grid, each cell is either a wall 2, an house 1 or empty 0 (the number zero, one, two), find a place to build
a post office so that the sum of the distance from the post office to all the houses is smallest.

Returns the sum of the minimum distances from all houses to the post office.Return -1 if it is not possible.

You cannot pass through wall and house, but can pass through empty.
You only build post office on an empty.
样例
Example 1:

Input：[[0,1,0,0,0],[1,0,0,2,1],[0,1,0,0,0]]
Output：8
Explanation： Placing a post office at (1,1), the distance that post office to all the house sum is smallest.
Example 2:

Input：[[0,1,0],[1,0,1],[0,1,0]]
Output：4
Explanation： Placing a post office at (1,1), the distance that post office to all the house sum is smallest.



https://www.jiuzhang.com/problem/build-post-office-ii/
 */
public class P_BuildOfficeII {
    class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
