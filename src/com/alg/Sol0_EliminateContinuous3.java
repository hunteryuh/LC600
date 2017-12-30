package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 12/1/2017.
 */
/*
*  要实现一个一维的消消乐，也就是多于三个连续的元素要删掉，要考虑级联输入(1,2,2,2,1)要输出(1,1).
*
输入(1,2,2,2,1,1)要输出()
输入(1,2,2,2,1,1,3,3,3,1)要输出(1)，因为消除三个2的时候，三个1放到了一起被消掉了。所以最后的1留下来了*/
public class Sol0_EliminateContinuous3 {
    // in-place
    public static void eliminateContinuous(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i++){
            if( len < 2 || nums[i]!= nums[len -1] || nums[i]!=nums[len -2]){
                nums[len] = nums[i];
                len++;
            }else{
                len -= 2;
            }
        }
        for(int i = 0; i < len; i++){
            System.out.print(nums[i]+ " ");

        }

    }
    public static void main(String[] args) {
        //eliminateContinuous(new int[]{1,2,2,1,1,3,3,3,1});
        System.out.println("p");
        eliminateContinuous(new int[]{1,2,2,2,1,1,3,3,3});
    }
}
