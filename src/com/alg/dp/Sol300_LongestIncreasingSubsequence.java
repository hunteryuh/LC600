package com.alg.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by HAU on 9/2/2017.
 */

/*Given an unsorted array of integers, find the length of longest  strictly increasing
        subsequence.

        For example,
        Given [10, 9, 2, 5, 3, 7, 101, 18],
        The longest increasing subsequence is [2, 3, 7, 101],
        therefore the length is 4. Note that there may be more than
        one LIS combination,
        it is only necessary for you to return the length.*/

    //https://leetcode.com/problems/longest-increasing-subsequence/solution/
public class Sol300_LongestIncreasingSubsequence {
    public static int lengthofLIS(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] L = new int[n];
        for (int i = 0; i < n ; i++) {
            L[i] = 1;
            for (int j = 0 ; j < i; j++){
                if (nums[j] < nums[i] && L[i] < 1 + L[j]){
                    L[i] = 1 + L[j];
                }
            }
        }
        int res = 1;
        for (int i = 0; i < n; i++) {
            if (res < L[i]){
                res = L[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,0,5,3,7,111,17};
        //assert 4 == lengthofLIS(nums);
        int[] n2 = { -3, 0};
        System.out.println(lengthOfLIS_bs(nums));
        System.out.println(lengthofLIS(n2));
        System.out.println(lengthofLIS2(n2));
        System.out.println(lengthofLIS2_trial2(n2));
        System.out.println(lengthofLIS2_trial2(nums));
    }

    //Dynamic Programming
    /*
    dp[i] represents the length of the longest increasing subsequence possible
    considering the array elements upto the i^{th}
    index only ,by necessarily including the ith element.
    dp[i]=max(dp[j])+1,∀0≤j<i, and num[i] > num[j]

    At the end, the maximum out of all the dp[i]'s to determine the final result.
    LIS_length=max(dp[i]),∀0≤i<n
     */
    public static int lengthofLIS2(int[] nums){
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static int lengthofLIS2_trial2(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int result = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int length = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && length < dp[j]) {
                    length = dp[j];
                }
            }
            dp[i] = 1 + length;
        }
        for (int i = 0; i < dp.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    // dp[i] represents the length of the longest increasing subsequence that ends at index i
    // time O(n^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 1;
        for (int res: dp) {
            ans = Math.max(ans, res);
        }
        return ans;
    }



    // method 3, dynamic programming with binary search
    /*Note: dp array does not result in longest increasing subsequence,
    but length of dp array will give you length of LIS.*/
    public static int lengthOfLIS_bs(int[] nums){
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num: nums) {
            int i = Arrays.binarySearch(dp,0,len,num);
            if (i < 0){
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len){
                len++;
            }
        }
        return len;
    }

    // treeset  O(nlogn)
    // https://leetcode.com/problems/longest-increasing-subsequence/discuss/1639802/JAVA-oror-TREESET-oror-O(nlogn)
    //     //in java treeset implements binary search tree interface
    //    //and also in treeset any kind of insertion or deletion takes O(logn) tc....
    // Take a look at patience sort, there's concept of pile/bucket, the resultant set we are getting is the number of piles/buckets and not the actual longest increasing subsequence
    //
    //If you implement actual patience sort, you can get all increasing subsequences
    // https://leetcode.com/problems/longest-increasing-subsequence/discuss/460046/Java-TreeSet-nlogn-%2B-explanation
    public int lengthOfLIS_treeset(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num: nums) {
            //returns just the least higher value of the current number passed if present.
            //else returns null
            Integer higher = set.ceiling(num);  // higher >> num
            if (higher == null) {
                set.add(num);
            } else {
                set.remove(higher);
                set.add(num);
            }
        }
        return set.size();
    }

    // binary search nlogn
    public int lengthOfLIS_bs2(int[] nums) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            if (list.size() == 0 || list.get(list.size() - 1) < nums[i]) {
                list.add(nums[i]);
            } else {
                int left = 0, right = list.size();
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (nums[i] > list.get(mid)) {
                        left = mid + 1;
                    } else if (nums[i] < list.get(mid)) {
                        right = mid;
                    } else {
                        right = mid;
                    }
                }
                list.set(left, nums[i]);
            }
        }

        return list.size();  // the element in the list may not be correct sequence but the size is correct size
    }

    // one pass without binary search , O(n^2)
    public int LengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                // Find the first element in sub that is greater than or equal to num
                int j = 0;
                while (num > sub.get(j)) j++;
                sub.set(j, num);
            }
        }
        return sub.size();
    }
}
