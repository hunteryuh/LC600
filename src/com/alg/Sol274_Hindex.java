package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 11/30/2017.
 */
/* Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index. */
public class Sol274_Hindex {
    public static int hIndex(int[] citations){
        // time O(nlogn), space O(1)
        Arrays.sort(citations);
        int i = 0;
        while( i < citations.length && citations[citations.length-1-i] >i){
            i++;
        }
        return i; // after the loop, i = i' + 1 the i'th one is not cited at least h times , 0,..i'-1 are.
    }

    public static int Hindex(int[] citations){
        // bucket sort
        int n = citations.length;
        int[] buckets = new int[n+1];
        for (int c : citations){
            if(c >= n){
                buckets[n]++;
            }else{
                buckets[c]++;
            }
        }
        int count = 0;
        for ( int i = n; i>=0; i--){
            count += buckets[i];
            if( count >= i){
                return i;
            }
        }
        return 0;

    }

    public static void main(String[] args) {
        int[] cites = {0,1};
        //System.out.println(hIndex(cites));
        System.out.println(Hindex(cites));
    }
}
