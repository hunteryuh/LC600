package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 2/2/2018.
 */
// return numbers of non unique value in the int array
public class Sol0_countDuplicates {
    public static int countDuplicates(int[] nums){
        Arrays.sort(nums);
        int prev = nums[0] - 1;
        int res = 0;
        int local = 0;
        for(int i = 0; i < nums.length; i++){
            if ( nums[i]== prev){

                if(local == 0){
                    res++;
                    local++;
                }
            }else{
                prev = nums[i];
                local = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 2,4,1,6,2,7,1,1};
        System.out.println(countDuplicates(nums)); // 2
        int[] numbers = { 7, 2, 6, 1, 4, 7, 4, 5, 4, 7, 7, 3, 1 };
        System.out.println(countDuplicates(numbers)); //3
    }
}
