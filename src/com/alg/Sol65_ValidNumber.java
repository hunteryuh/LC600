package com.alg;

/**
 * Created by HAU on 7/29/2017.
 */

/*Validate if a given string is numeric.

        Some examples:
        "0" => true
        " 0.1 " => true
        "abc" => false
        "1 a" => false
        "2e10" => true*/
public class Sol65_ValidNumber {
    public static boolean isNumber(String s){
        String str = s.trim();

        if (str.isEmpty()) return false;
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
        if(str.matches(regex)){
            return true;
        }else return false;

    }
    public static boolean isNumberE(String s){
        String str = s.trim();

        if (str.isEmpty()) return false;
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*([eE][-+]?\\d+)?";
        if(str.matches(regex)){
            return true;
        }else return false;

    }

    public static boolean isNumber_raw(String s) {

        s=s.trim();

        boolean isNumberSeen = false;
        boolean isNumberAfterE = true;
        boolean isPointSeen =  false;
        boolean isEseen = false;

        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch>='0' && ch<='9')
            {
                isNumberSeen = true;
                isNumberAfterE = true;
            }
            else if(ch=='.')
            {
                if(isPointSeen || isEseen)
                    return false;

                isPointSeen =  true;
            }
            else if(ch=='e' || ch =='E')
            {
                if(isEseen || !isNumberSeen)
                    return false;

                isEseen = true;

                isNumberAfterE = false;
            }
            else if(ch=='+' || ch=='-')
            {
                if(i!=0 && (s.charAt(i-1)!='e' &&  s.charAt(i-1)!='E' ))
                    return false;
            }
            else
                return false;
        }

        return (isNumberSeen && isNumberAfterE);



    }
    public static void main(String[] args) {
        String s = "1.3abc";
        String s2 = "0 ";
        String s3 = "06.11 ";
        String s4 = "1.4E2 ";
        String s5 = "  1e2 ";

/*        System.out.println(isNumberE(s));
        System.out.println(isNumberE(s2));
        System.out.println(isNumberE(s3));
        System.out.println(isNumberE(s4));
        System.out.println(isNumberE(s5));*/
/*        System.out.println(isNumber_raw(s));
        System.out.println(isNumber_raw(s2));
        System.out.println(isNumber_raw(s3));
        System.out.println(isNumber_raw(s4));
        System.out.println(isNumber_raw(s5));*/
        //System.out.println(s5);
        //s5 = s5.trim();
        //System.out.println(s5);
        //String s6 = "-e10"; // False
        String s7 = " 2e-9 "; // True
       // String s8 = "+e1"; // False
       // String s9 = "1+e"; // False
       // String s10 = " "; // False

        /*String s11 = "e9"; // False
        String s12 = "4e+"; // False
        String s13 = " -."; // False
        String s14 = "+.8"; // True*/
        String s15 = " 005047e+6"; // True

/*        String s16 = ".e1"; // False
        String s17 = "3.e"; // False
        String s18 = "3.e1"; // True*/
        String s19 = "+1.e+5"; // True
/*        String s20 = " -54.53061"; // True*/

        String s21 = ". 1"; // False
        //System.out.println(isNumber_raw(s6));
        System.out.println(isNumber_raw(s7));
        System.out.println(isNumberE(s7));
        //System.out.println(isNumber_raw(s8));
        //System.out.println(isNumber_raw(s9));
        //System.out.println(isNumber_raw(s10));
        System.out.println();
/*        System.out.println(isNumber_raw(s11));
        System.out.println(isNumber_raw(s12));
        System.out.println(isNumber_raw(s13));
        System.out.println(isNumber_raw(s14));*/
        System.out.println(isNumber_raw(s15));
        System.out.println(isNumberE(s15));
        System.out.println();
/*        System.out.println(isNumber_raw(s16));
        System.out.println(isNumber_raw(s17));
        System.out.println(isNumber_raw(s18));*/
        System.out.println(isNumber_raw(s19));
        System.out.println(isNumberE(s19));
/*        System.out.println(isNumber_raw(s20));*/


        //System.out.println(isNumber_raw(s21));
    }
}
