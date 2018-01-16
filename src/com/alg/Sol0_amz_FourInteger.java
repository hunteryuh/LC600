package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/6/2018.
 */
/*Given four integers, make F(S) = abs(S[0]-S[1])+abs(S[1]-S[2])+abs(S[2]-S[3]) to be largest

*/
public class Sol0_amz_FourInteger {
    public static int[] Max4(int A, int B, int C, int D) {
        int[] nums = new int[]{A,B,C,D};
        Arrays.sort(nums);
        swap(nums,0,1); // min at index 1
        swap(nums,3,2);// max at index 2
        swap(nums,3,0); // second max at index 0
        return nums;
    }
    private static void swap(int[] nums, int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums = Max4(1, 3, 2, 4);
        for(int i: nums) System.out.print(i + " ");
    }
}
