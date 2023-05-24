package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 10/3/2017.
 */
/*Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.*/
public class Sol386_LexicographicalNumbers {
    public static List<Integer> lexicalOrder(int n ){
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++){
            dfs(i,n,res);
        }
        return res;
    }

    private static void dfs(int i, int n, List<Integer> res) {
        if ( i > n) return;
        else{
            res.add(i);
            for (int j = 0; j< 10; j++){
                if ( 10 * i + j > n){
                    return;
                }
                dfs( 10*i+j,n,res);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = lexicalOrder(14);
        System.out.println(list.toString());
        List<Integer> l2 = lexiOrder2(14);
        System.out.println(l2.toString());
    }

    public static List<Integer> lexiOrder2(int n){
        // O(n) time O(1) space
        List<Integer> list = new ArrayList<>();
        int cur = 1;
        for (int i = 1; i <= n; i++){
            list.add(cur);
            if (cur * 10 <= n){
                cur *= 10;
            }else {
                if (cur >= n){
                    cur /= 10;
                }
                cur++;
                while (cur %10 ==0){
                    cur /=10;
                }
            }
        }
        return list;
    }


}
