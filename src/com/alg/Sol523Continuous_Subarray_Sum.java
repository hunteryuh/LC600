package com.alg;

import java.util.HashMap;

/**
 * Created by HAU on 5/24/2017.
 */
/*Given a list of non-negative numbers and a target integer k,
write a function to check if the array has a continuous subarray
of size at least 2 that sums up
to the multiple of k, that is, sums up to n*k where n is also an integer.
*/
public class Sol523Continuous_Subarray_Sum {
    public static boolean checkSubarraySum(int[] nums, int k){
        if (nums.length ==0) return false;

        int sum = 0;
        boolean result = false;
        int n = nums.length;
        for(int i = 0; i<n-1; i++){
            sum = nums[i];
            for (int j = i+1;j<n;j++){

                sum += nums[j];
                if (k!=0 && sum%k==0) {
                    result = true;
                    break;
                } else if (sum==0)
                    result = true;
            }
        }
        return result;  // time limit exceeded, too slow, time
        //time O(n2),  space O(1)

    }

    public static boolean checkSubarray(int[] nums, int k){
        if (nums.length <=1) return false;
        int n = nums.length;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<n;i++){
            sum += nums[i];
            if(k!=0) sum %= k;
            if(map.containsKey(sum) && i-map.get(sum)>1) return true;
            if(!map.containsKey(sum)) map.put(sum,i);
        }
        return false;
    }

    public static void main(String[] args){
        int[] nums = {23, 2, 4, 6, 7};
        int[] num2 ={23, 2, 6, 4, 7};
        int k = 3;
        int t = 0;
        System.out.println(checkSubarraySum(nums,6));
        System.out.println(checkSubarraySum(num2,6));
        System.out.println(checkSubarraySum(num2,t));

        int[] n3 = {0,0};
        System.out.println(checkSubarraySum(n3,t));
        System.out.println(checkSubarray(n3,t));
    }
}
