package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 7/6/2017.
 */
public class test2 {
    public static void main(String[] args) {

        final List<String> list1 = new ArrayList<>(Arrays.asList("Hello", "World!", "How", "Are", "You"));
        System.out.println(list1);
        final List<String> list2 = new ArrayList<>(Arrays.asList("Hello", "excep"));
        System.out.println(list2);
        list1.removeAll(list2);
        System.out.println("after removeall");
        System.out.println(list1);

        list1.retainAll(list2);
        int hi = 2;
        int lo = 0;
        System.out.println(lo);
        int t = 0 + 9 - lo++ + lo++;
        System.out.println(t);
        System.out.println(lo);
    }

    public int[] decode(int[] encoded, int first) {
        int len = encoded.length;
        int[] result = new int[len + 1];
        result[0] = first;
        for (int i = 1; i < len + 1; i++) {
            result[i] = encoded[i -1] ^ result[i - 1];
        }

        return result;
    }
}
