package com.alg;

/**
 * Created by HAU on 8/4/2017.
 */

/*The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

        P   A   H   N
        A P L S I I G
        Y   I   R
        And then read line by line: "PAHNAPLSIIGYIR"*/

/*n=4
        P              I              N
        A         L  S         I   G
        Y   A       H    R
        P              I

        N=5
        P               H
        A          S  I
        Y      I       R
        P   L          I      G
        A              N*/

/*convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".*/
public class Sol6_ZigzagConversion {
    public static String convert(String s, int numRows){
        int n = s.length();
        if (numRows <=1 ) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++){
            sb[i] = new StringBuilder();
        }
        int step = 1;
        int index = 0;
        for (int i = 0; i < n; i++){
            sb[index].append(s.charAt(i));
            if (index ==0) step =1;
            if (index == numRows - 1) step = -1;
            index += step;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sbitem : sb){
            res.append(sbitem);
            //System.out.println(sbitem);
        }
        return res.toString();

    }
    public String convert2(String s, int numR){
        if (numR == 1) return  s;
        StringBuilder sb = new StringBuilder();
        int step = 2 * numR - 2;
        for (int i = 0; i < numR; i++){
            // 1st and last row
            if ( i ==0 || i == numR -1){
                for (int j = i; j < s.length(); j+= step){
                    sb.append(s.charAt(j));
                }
            }
            else {
                int j = i;
                boolean flag = true;
                int step1 = 2 * (numR - 1 - i);
                int step2 = step - step1;
                while (j< s.length()){
                    sb.append(s.charAt(j));
                    if (flag){
                        j += step1;
                    }else {
                        j += step2;
                    }
                    flag = !flag;
                }
            }
        }
        return  sb.toString();
    }

    public static void main(String[] args) {
        String s = "paypalishiring";
        int n = 5;
        //System.out.println(convert(s,n));
        String s2 = "ab";
        System.out.println(convert(s2,1));
    }
}
