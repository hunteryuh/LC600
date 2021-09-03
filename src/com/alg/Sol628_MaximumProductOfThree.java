package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 8/8/2017.
 */
public class Sol628_MaximumProductOfThree {
    public static int maximumProduct_sort(int[] nums) {
        // length >= 3
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[0]*nums[1] * nums[n-1], nums[n-1] * nums[n-2]* nums[n-3]);
    }
    public static int maximumProduct(int[] nums) {
        // length >=3 assumption
        int n = nums.length;
        int max1= Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int t : nums){
            if (t > max1){
                max3 = max2;
                max2 = max1;
                max1 = t;
            }else if ( t>max2){
                max3 = max2;
                max2 = t;
            }else if (t > max3){
                max3 = t;
            }
            if ( t < min1){
                min2 = min1;
                min1 = t;
            }else if (t < min2){
                min2 = t;
            }
        }
        return Math.max(max1*max2*max3, min1 * min2* max1);
    }

    public static void main(String[] args) {
        int[] a = {2,3,3,4};
        System.out.println(maximumProduct(a));
        int[] b = { -1,-2,2,1};
        System.out.println(maximumProduct(b));
        int[] c = {-1,-3,-4,1,10};
        System.out.println(maximumProduct(c));
    }
}
