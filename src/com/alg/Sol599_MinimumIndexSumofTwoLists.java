package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HAU on 11/7/2017.
 */
/*You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement.
You could assume there always exists an answer.*/
public class Sol599_MinimumIndexSumofTwoLists {
    public static String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i< list1.length; i++){
            for (int j = 0; j < list2.length; j++){
                if(list1[i].equals(list2[j])){
                    if(!map.containsKey(i+j)){
                        map.put(i+j,new ArrayList<>());
                    }
                    map.get(i+j).add(list1[i]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k : map.keySet()){
            min = Math.min(min, k);
        }
        //String[] res = new String[map.get(min).size()];
        return map.get(min).toArray(new String[map.get(min).size()]);
    }

    public static void main(String[] args) {
        String[] l1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] l2 = {"KFC", "Tapioca Express", "Shogun"};
        String[] a = findRestaurant(l1,l2);
        System.out.println(Arrays.toString(a));
    }

    //method 2, a different map
    public static String[] findRest2(String[] list1, String[] list2) {
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++){
            map.put(list1[i],i);
        }
        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int sum;
        for (int j = 0; j < list2.length; j++){
            if (map.containsKey(list2[j])){
                sum = j + map.get(list2[j]);
                if ( sum < min){
                    res.clear();
                    res.add(list2[j]);
                    min = sum;
                }else if ( sum == min){
                    res.add(list2[j]);
                }
            }
        }
        return res.toArray( new String[res.size()]);
    }
}
