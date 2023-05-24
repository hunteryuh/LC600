package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/18/2017.
 */
/*Given an array consists of non-negative integers, your task is to
        count the number of triplets chosen from the array that can
        make triangles
        if we take them as side lengths of a triangle.*/
public class Sol611_ValidTriangleNumber {
    public static int triangleNumber_n3(int[] nums){
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++){
            for (int j = i + 1; j < n -1; j++){
                int k = j + 1;
                while (k < n) {
                    if (nums[i] + nums[j] > nums[k]
                            && nums[j] + nums[k] > nums[i]
                            && nums[i] + nums[k] > nums[j]) {
                        count++;
                    }
                    k++;
                }

            }
        }
        return count;
    }
    public static int triangleNumber(int[] nums){
        //Time complexity : O(n2). Loop of kkk and jjj will be executed O(n2)O(n^2)O(n​2​​) times in total, because, we do not reinitialize the value of kkk for a new value of jjj
        // chosen(for the same iii). Thus the complexity will be O(n^2+n^2)=O(n^2).
        int n = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++){
           int k = i + 2;
            // if ( nums[i] == 0) continue; // a second way to skip nums[i]==0
            for (int j = i + 1; j < n - 1 && nums[i] != 0; j++){ //
                while ( k < n && nums[i]+nums[j]>nums[k] ){
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 2,3,4,2};
        System.out.println(triangleNumber(nums));
        int[] n2 = {1,1,0,1,0};
        System.out.println(triangleNumber(n2));
    }
}
