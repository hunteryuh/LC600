package com.alg;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
You are given an elevation map represents as an integer array heights where heights[i] representing the height of the terrain at index i. The width at each index is 1. You are also given two integers volume and k. volume units of water will fall at index k.

Water first drops at the index k and rests on top of the highest terrain or water at that index. Then, it flows according to the following rules:

    If the droplet would eventually fall by moving left, then move left.
    Otherwise, if the droplet would eventually fall by moving right, then move right.
    Otherwise, rise to its current position.

Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction. Also, level means the height of the terrain plus any water in that column.

We can assume there is infinitely high terrain on the two sides out of bounds of the array. Also, there could not be partial water being spread out evenly on more than one grid block, and each unit of water has to be in exactly one block.



Example 1:

Input: heights = [2,1,1,2,1,2,2], volume = 4, k = 3
Output: [2,2,2,3,2,2,2]
Explanation:
The first drop of water lands at index k = 3. When moving left or right, the water can only move to the same level or a lower level. (By level, we mean the total height of the terrain plus any water in that column.)
Since moving left will eventually make it fall, it moves left. (A droplet "made to fall" means go to a lower height than it was at previously.) Since moving left will not make it fall, it stays in place.

The next droplet falls at index k = 3. Since the new droplet moving left will eventually make it fall, it moves left. Notice that the droplet still preferred to move left, even though it could move right (and moving right makes it fall quicker.)

The third droplet falls at index k = 3. Since moving left would not eventually make it fall, it tries to move right. Since moving right would eventually make it fall, it moves right.

Finally, the fourth droplet falls at index k = 3. Since moving left would not eventually make it fall, it tries to move right. Since moving right would not eventually make it fall, it stays in place.

Example 2:

Input: heights = [1,2,3,4], volume = 2, k = 2
Output: [2,3,3,4]
Explanation: The last droplet settles at index 1, since moving further left would not cause it to eventually fall to a lower height.

Example 3:

Input: heights = [3,1,3], volume = 5, k = 1
Output: [4,4,4]



Constraints:

    1 <= heights.length <= 100
    0 <= heights[i] <= 99
    0 <= volume <= 2000
    0 <= k < heights.length


 */
public class Sol755_PourWater {
    // https://leetcode.com/problems/pour-water/solutions/121019/java-o-m-n-lg-n-solution-using-two-pqs/?envType=company&envId=airbnb&favoriteSlug=airbnb-three-months
    public int[] pourWater(int[] Height, int volume, int K) {
        PriorityQueue<Integer> pqLeft = new PriorityQueue<Integer>((a, b) -> Height[a] != Height[b] ? Height[a] - Height[b] : b - a);
        PriorityQueue<Integer> pqRight = new PriorityQueue<Integer>((a,b) -> Height[a] != Height[b] ? Height[a] - Height[b] : a - b);

        int j1 = K - 1, j2 = K + 1;
        for (int i = 0; i < volume; i++) {
            while(j1 >= 0 && Height[j1] <= Height[j1+1]) pqLeft.offer(j1--);
            while(j2 < Height.length && Height[j2-1] >= Height[j2]) pqRight.offer(j2++);

            if (!pqLeft.isEmpty() && Height[pqLeft.peek()] < Height[K]) {
                Height[pqLeft.peek()]++;
                pqLeft.offer(pqLeft.poll());
            } else if (!pqRight.isEmpty() && Height[pqRight.peek()] < Height[K]) {
                Height[pqRight.peek()]++;
                pqRight.offer(pqRight.poll());
            } else {
                Height[K]++;
            }
        }
        return Height;
    }

    public static void main(String[] args) {
        int[] height = new int[]{3, 1, 3};
        int v = 4;
        Sol755_PourWater ss = new Sol755_PourWater();
        int[] res = ss.pourWater3(height, v, 1);
        System.out.println(Arrays.toString(res));
    }

    //https://leetcode.com/problems/pour-water/solutions/4571888/straightforward-java-solution-beats-78/?envType=company&envId=airbnb&favoriteSlug=airbnb-three-months
    public int[] pourWater2(int[] heights, int volume, int k) {
        int j = k;
        for (int i = 0; i < volume; i++) {
            // first rule: move left until it eventually falls
            while (k > 0 && heights[k - 1] <= heights[k]) {
                k--;
            }
            // 2nd rule: move right until it falls
            while (k < heights.length - 1 && heights[k + 1] <= heights[k]) {
                k++;
            }
            // in case it was all level, we reset back to the first index we want to increment on
            while (k > j && heights[k - 1] == heights[k] ) {
                k--;
            }
            heights[k]++;
            k = j;

        }
        return heights;
    }

    // simulate water move https://leetcode.com/problems/pour-water/solutions/5635843/easy-to-understand-java-solution/?envType=company&envId=airbnb&favoriteSlug=airbnb-three-months
    public int[] pourWater3(int[] heights, int volume, int k) {
        for (int i = 0; i < volume; i++) {
            int index = k;

            // Move left
            for (int j = k - 1; j >= 0; j--) {
                if (heights[j] > heights[index]) break;
                if (heights[j] < heights[index]) index = j;
            }

            // If no movement left, move right
            if (index == k) {
                for (int j = k + 1; j < heights.length; j++) {
                    if (heights[j] > heights[index]) break;
                    if (heights[j] < heights[index]) index = j;
                }
            }

            // Pour water at the selected index
            heights[index]++;
        }
        return heights;
    }
}
