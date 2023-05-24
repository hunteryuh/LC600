package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 1/12/2018.
 */
/*Given a string representing an expression of fraction addition and subtraction, you need to return the calculation result in string format. The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.

Example 1:
Input:"-1/2+1/2"
Output: "0/1"
Example 2:
Input:"-1/2+1/2+1/3"
Output: "1/3"
Example 3:
Input:"1/3-1/2"
Output: "-1/6"
Example 4:
Input:"5/3+1/3"
Output: "2/1"
Note:
The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
The number of given fractions will be in the range [1,10].
The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.*/
public class Sol592_FractionAdditionandSubtraction {
    // method 1, using gcd
    public static String fractionAddition(String expression){
        List<Character> sign = new ArrayList<>();
        if(expression.charAt(0) != '-'){
            sign.add('+');
        }
        for(int i = 0; i < expression.length();i++){
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-'){
                sign.add(expression.charAt(i));
            }
        }
        int prev_num = 0;
        int prev_den = 1;
        int i = 0;
        for(String sub: expression.split("\\+|(-)")){
            if(sub.length() > 0 ){
                String[] fraction = sub.split("/");
                int num = (Integer.parseInt(fraction[0]));
                int den = (Integer.parseInt(fraction[1]));
                int g = Math.abs(gcd(den, prev_den));
                if(sign.get(i++) == '+'){
                    prev_num = prev_num * den / g + num * prev_den/g;
                }else
                    prev_num = prev_num * den / g - num * prev_den/g;
                prev_den = den * prev_den / g ; // lcm
                g = Math.abs(gcd(prev_den,prev_num));
                prev_den = prev_den /g;
                prev_num = prev_num /g; //reduction


            }
        }
        return prev_num +"/"+prev_den;
    }
    private static int gcd(int a, int b){
        while(b != 0){
            int t = b ;
            b = a % b;
            a = t;
        }
        return a;
    }
}
