package com.alg;

public class Sol2148_CountElementsWithStrictlySmallerAndGreaterElements {
    public int countElements(int[] nums) {
        int smallest = nums[0];
        int largest = nums[0];
        for (int num: nums) {
            smallest = Math.min(smallest, num);
            largest = Math.max(largest, num);
        }
        int count = 0;
        for (int num: nums) {
            if (num > smallest && num < largest) {
                count++;
            }
        }
        return count;
    }
}
