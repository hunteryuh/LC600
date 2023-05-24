package com.alg;
/*
Given four integers sx, sy, tx, and ty, return true if it is possible to convert the point (sx, sy) to the point (tx, ty) through some operations, or false otherwise.

The allowed operation on some point (x, y) is to convert it to either (x, x + y) or (x + y, y).



Example 1:

Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: true
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)
Example 2:

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: false
Example 3:

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: true
 */
public class Sol780_ReachingTarget {
    // https://leetcode.com/problems/reaching-points/discuss/1569508/Java-linear-approach
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx || ty > sy) {
            if (tx < sx || ty < sy) return false;
            if (ty > tx) {
                if (tx == sx) {
                    return (ty-sy) % sx == 0;
                }
                ty = ty - ty/tx * tx;
            } else {  // ty <=tx
                if (ty == sy) {
                    return (tx-sx) % sy == 0;
                }
                tx = tx - tx/ty * ty;
            }
        }
        return tx == sx && ty == sy;
    }

    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {

        // in case of impossible cases
        if(tx < sx || ty < sy) return false;

        // to avoid the useless loop in those cases
        if(sx == tx) return (ty - sy) % sx == 0;

        if(sy == ty) return (tx - sx) % sy == 0;

        while(tx > sx && ty > sy) {
            if(tx > ty)
                tx %= ty;
            else
                ty %= tx;
        }

        if (tx == sx) {
            if ((ty - sy) % tx == 0)
                return true;
        } else if (ty == sy) {
            if ((tx - sx) % ty == 0)
                return true;
        }

        return false;
    }
}
