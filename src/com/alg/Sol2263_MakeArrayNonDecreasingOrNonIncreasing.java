package com.alg;

import java.util.PriorityQueue;
import java.util.Queue;

/*
You are given a 0-indexed integer array nums. In one operation, you can:

    Choose an index i in the range 0 <= i < nums.length
    Set nums[i] to nums[i] + 1 or nums[i] - 1

Return the minimum number of operations to make nums non-decreasing or non-increasing.



Example 1:

Input: nums = [3,2,4,5,0]
Output: 4
Explanation:
One possible way to turn nums into non-increasing order is to:
- Add 1 to nums[1] once so that it becomes 3.
- Subtract 1 from nums[2] once so it becomes 3.
- Subtract 1 from nums[3] twice so it becomes 3.
After doing the 4 operations, nums becomes [3,3,3,3,0] which is in non-increasing order.
Note that it is also possible to turn nums into [4,4,4,4,0] in 4 operations.
It can be proven that 4 is the minimum number of operations needed.

Example 2:

Input: nums = [2,2,3,4]
Output: 0
Explanation: nums is already in non-decreasing order, so no operations are needed and we return 0.

Example 3:

Input: nums = [0]
Output: 0
Explanation: nums is already in non-decreasing order, so no operations are needed and we return 0.



Constraints:

    1 <= nums.length <= 1000
    0 <= nums[i] <= 1000



Follow up: Can you solve it in O(n*log(n)) time complexity?

 */
public class Sol2263_MakeArrayNonDecreasingOrNonIncreasing {
    // https://leetcode.com/problems/make-array-non-decreasing-or-non-increasing/solutions/3673335/nlog-n-heap-based-approach-with-detailed-explanation/
    // https://leetcode.com/problems/make-array-non-decreasing-or-non-increasing/solutions/2270209/java-concise-solutin-min-max-heap-o-nlogn/
    // time O(n logn)
    public int convertArray(int[] nums) {
        int n = nums.length;
        int res1 = 0;
        int res2 = 0;
        Queue<Integer> ascending = new PriorityQueue<>((a, b) -> b - a);
        Queue<Integer> descending = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            //if current max(cur sea level) is larger than nums[i], that means
            //we need to set it to the new number.
            if (!ascending.isEmpty() && ascending.peek() >= nums[i]) {
                res1 += ascending.peek() - nums[i];
                ascending.poll();
                ascending.add(nums[i]);
            }
            //if current max is smaller than nums[i], it's already acending
            //just set the new sea level to nums[i]
            ascending.add(nums[i]);
            if (!descending.isEmpty() && descending.peek() <= nums[i]) {
                res2 += nums[i] - descending.peek();
                descending.poll();
                descending.add(nums[i]);
            }

            descending.add(nums[i]);
        }

        return Math.min(res1, res2);
    }
}
