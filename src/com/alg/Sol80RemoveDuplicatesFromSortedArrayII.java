package com.alg;

/**
 * Created by HAU on 6/7/2017.
 */
/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5,
with the first five elements of nums being 1, 1, 2, 2 and 3.
It doesn't matter what you leave beyond the new length.
*/
public class Sol80RemoveDuplicatesFromSortedArrayII {
    public static int removeDuplicates(int[] nums) {
        if (nums.length ==0) return 0;
        int n = nums.length;
        int newlength = 0;
        int dupcount = 1;
        for (int i=1;i<n;i++){
            if (nums[i]!=nums[newlength]){

                nums[++newlength]= nums[i];
                dupcount =1;
            }
            else {
                dupcount++;
                if (dupcount<=2){
                    nums[++newlength]= nums[i];  // here need to update entry value too
                } else ;
            }
        }


        return newlength+1;

    }

    public static void main(String[] args) {
        int[] nums= {1,1,2,2,2,3,3};
        System.out.println(removeDuplicates(nums));

        int[] n2 = {1,1,1,1,4,4};
        System.out.println(removeDuplicates(n2));
    }
}
