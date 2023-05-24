package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 12/26/2017.
 */
/*he gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2*/
public class Sol89_GrayCode {
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1<<n; i++){

            //(1 << n) will return a number with only nth bit set., power(2,n) , if n = 2; it is 2^2 = 4
            res.add(i ^ i>>1);
            // i>>1, right shift 1, is also i/2
        }
        //The idea is simple. G(i) = i^ (i/2).
        return res;
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(grayCode(n));
    }

    //时间复杂度 O(nlogn)，空间复杂度 O(1)
    public static List<Integer> getGrayCode(int n){
        if (n < 0) return null;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        for(int i = 0; i < n ;i++){
            int msb = 1 << i;
            for (int j = result.size() - 1; j>=0; j--){
                result.add(msb | result.get(j));
            }
            /*加 0 的那一部分已经在前一组格雷码中出现，故只需将前一组格雷码镜像后在最高位加 1 即可。第二重 for 循环中需要注意的是currGray.size() - 1并不是常量，只能用于给 j 初始化。
            本应该使用 2n 和上一组格雷码相加，这里考虑到最高位为1的特殊性，使用位运算模拟加法更好。*/
        }
        return result;
    }
}
