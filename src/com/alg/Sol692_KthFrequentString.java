package com.alg;

import java.util.*;

/**
 * Created by HAU on 11/21/2017.
 */
public class Sol692_KthFrequentString {
    public static String kthMostFrequent(String[] strings, int k){
        //hashmap and sort
        //O(nlogn)
        HashMap<String,Integer> map = new HashMap<>();
        for (String s: strings) {
            Integer x = map.get(s);// this Integer is an object, can be null
            if (x == null) {
                x = 0;
            }
            map.put(s, ++x);
        }
        List<Map.Entry> list = new ArrayList<>(map.entrySet());// arraylist so can be indexed
        Collections.sort(list, new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                    Integer v1 = (Integer)((Map.Entry) o1).getValue();
                    Integer v2 = (Integer)((Map.Entry) o2).getValue();
                    return v2.compareTo(v1);
            }
        });  // sorting nlogn

        if (list.size() > k-1){
            return (String) list.get(k-1).getKey(); // arraylist lookup O(1)
        }
        return null;
    }

    public static void main(String[] args) {
        String[] strings = {"cd","cd","r","r","r","ab"};
        int k = 1;
        System.out.println(kthMostFrequent(strings,1));
        System.out.println(kthMostFrequent(strings,2));
        System.out.println(kthMostFrequent(strings,3));
    }
}
