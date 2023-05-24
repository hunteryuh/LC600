package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 1/23/2018.
 */
/*Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?*/
public class Sol119_PascalTriangleII {
    public static List<Integer> getRow(int rowIndex){
        List<Integer> res = new ArrayList<>();
        if ( rowIndex < 0) return res;
        for(int i = 0; i <= rowIndex; i++){
            res.add(0,1); // add 1 at index 0
            for(int j = 1; j < res.size() - 1; j++){
                res.set(j,res.get(j)+ res.get(j+1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int x = 2;
        System.out.println(getRow(x));
    }
}
