package com.alg;

/**
 * Created by HAU on 12/25/2017.
 */
/* check if string 1 can be right rotated by s2
 * ir yes, return how many chars to rotate */
public class Sol0_amz_rightRotate {
    public static int rightRot(String s1, String s2){
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 ||
                s1.length() != s2.length()){
            return -1;
        }
        String s = s1 + s1;
        return s.indexOf(s2)!= -1 ? s.indexOf(s2): -1; // ? 1: -1  1 means it can be right rotated.

    }

    public static void main(String[] args) {
        String s1 = "amazon";
        String s2 = "azonam";
        System.out.println(rightRot(s1,s2));
    }
}
