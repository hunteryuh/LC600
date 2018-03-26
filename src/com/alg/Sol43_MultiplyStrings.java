package com.alg;

/**
 * Created by HAU on 5/26/2017.
 */
/*

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/
public class Sol43_MultiplyStrings {
    public static String multiply(String num1, String num2) {
        int a = Integer.parseInt(num1);
        int b = Integer.parseInt(num2);
        a = a*b;
        return Integer.toString(a);
    }

    public static String multi2(String num1, String num2){
        if(num1.isEmpty() || num2.isEmpty()) return null;

        int a = str2int(num1);
        int b = str2int(num2);
        int c = a*b;  // cannot handle large int multiplication
        String result = int2str(c);

        return result;
    }
    private static int str2int(String s){
        if (s.isEmpty()) return 0;
        int a = s.charAt(0)-'0';
        for (int i=1;i<s.length();i++){
            a = a*10+s.charAt(i)-'0';
        }
        return a;
    }
    private static String int2str(int n){
        StringBuilder str = new StringBuilder();
        if (n==0) str.append(n);
        while (n>0){
            str.append(n%10);
            n = n/10;
        }
        return str.reverse().toString();
    }

    public static String multiply3(String num1, String num2){
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m+n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int dg1 = num1.charAt(i) - '0';
                int dg2 = num2.charAt(j) - '0';
                int mul = dg1 * dg2;
                int dhi = i+j;  //left
                int dlo = i+j+1;  // right
                int sum = mul + res[dlo];
                res[dhi] += sum/10;
                res[dlo] = sum%10;
            }
        }

        StringBuilder strb = new StringBuilder();

        for ( int i = 0;i < m + n; i++) {
            if(res[i]!=0 || strb.length()!=0) strb.append(res[i]);
        }
        return strb.length()==0 ? "0":strb.toString();
    }

    public static void main(String[] args) {
        /*System.out.println(multiply("34","5"));
        System.out.println(multi2("34","5"));*/
        System.out.println(multiply3("3","1"));
        System.out.println(multiply3("123456789","97654321"));
        //System.out.println('3');

    }
}