package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 7/16/2017.
 */
/*
Given a triangle, find the minimum path sum from top to bottom.
        Each step you may move to adjacent numbers on the row below.*/
public class Sol120_Triangle {
    public static int minimumTotal_notadjacent(List<List<Integer>> triangle) {
        int result = 0;
        //List<Integer> min = new ArrayList<>();
        for(List t: triangle){
            int s = minnum(t);
            //min.add(s);
            result += s;
        }
        return  result;
    }

    private static int minnum(List<Integer> t) {
        int m = t.get(0);
        for (int num : t){
            if (num < m){
                m = num;
            }
        }
        return m;
    }

    public static int minimumTotal(List<List<Integer>> triangle){
        int[] num = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >=0 ; i--){
            for (int j = 0; j < triangle.get(i).size() ; j++){
                num[j] = triangle.get(i).get(j) + Math.min(num[j],num[j+1]);
            }
        }
        return num[0]; // so smart and concise
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> tri = new ArrayList<List<Integer>>();
        List<Integer> l1 = Arrays.asList(2);
        List<Integer> l2 = Arrays.asList(3,4);
        List<Integer> l3 = Arrays.asList(6,5,7);
        List<Integer> l4 = Arrays.asList(4,1,7,3);
        //System.out.println(l2);
        tri.add(l1);
        tri.add(l2);
        tri.add(l3);
        tri.add(l4);
        System.out.println(tri);
        int t = minimumTotal(tri);
        System.out.println(t);
    }
}
