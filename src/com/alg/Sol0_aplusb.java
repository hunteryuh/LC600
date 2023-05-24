package com.alg;

/**
 * Created by HAU on 1/9/2018.
 */
public class Sol0_aplusb {
    public static int aplusb(int a, int b){
        while(b != 0){
            int carry = a & b;
            a = a^b;
            b = carry << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 4;
        int b = 10;
        System.out.println(aplusb(a,b));
    }
}
