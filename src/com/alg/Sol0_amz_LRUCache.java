package com.alg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 12/30/2017.
 */
/*给一个array, 给一个cache max size, 输出miss count. 什么时候hit, 什么时候miss的情况写好就好了。

example：   size = 4， input array   【1，2，3，4，5，4，1】

1 miss   2 miss   3 miss   4 miss   5 miss  替换 1

4 hit    把4提前到第一位   1 miss  替换 2*/
/*实现 LRU Cache再判断啥时候miss就好了，返回miss数。建议看看用LinkedHashMap实现lru cache, 代码很简洁。*/
public class Sol0_amz_LRUCache {
    // cash miss
    public static int CacheMiss(int[] arr, int size){
        if (arr == null) return  0;
        int count = 0;
        List<Integer> cache = new LinkedList<>();
        for(int i = 0; i < arr.length; i++){
            if (cache.contains(arr[i])){
                cache.remove(new Integer(arr[i])); // deep copy
                // Removes the first occurrence of the specified element from this list
            } else {
                count++;
                if (size == cache.size()){
                    cache.remove(0);
                }
            }
            cache.add(arr[i]);
        }
        return count;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = {1, 1, 3, 4, 5, 6, 1};
        System.out.println(CacheMiss(input, 4));
    }
}
