package com.alg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Example:
Input:
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
Explanation:
*/
public class Sol554_BrickWall {
    public static int leastBricks(List<List<Integer>> wall) {
        // time : O(nm): n layers, m bricks in each layer
        int max = 0;
        Map<Integer, Integer> sumToCount = new HashMap<>();
        //key: sum  value: occurence of the sum
        for(List<Integer> row: wall){
            int sum = 0;
            for(int i = 0; i < row.size() - 1; i++){
                sum += row.get(i);
                int count = sumToCount.getOrDefault(sum,0) + 1;
                sumToCount.put(sum,count);
                max = Math.max(max, count); // max number of bricks that the line does not go through
            }
        }
        return wall.size() - max;
    }
}
