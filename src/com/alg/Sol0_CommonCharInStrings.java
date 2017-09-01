package com.alg;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by HAU on 8/31/2017.
 */
public class Sol0_CommonCharInStrings {
    public static String commonChar(String s1, String s2){
        Set<Character> set1 = new TreeSet<>();
        Set<Character> set2 = new TreeSet<>();
        for(Character c : s1.toCharArray()){
            set1.add(c);
        }
        for(Character c2: s2.toCharArray()){
            set2.add(c2);
        }

        set1.retainAll(set2);
        String res = set1.toString();
        return res;


    }

    public static void main(String[] args) {
        String s1 = "happiness";
        String s2 = "many set";
        System.out.println(commonChar(s1,s2));
    }
}
