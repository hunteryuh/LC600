package com.alg;

/**
 * Created by HAU on 8/7/2017.
 */

/*Given two binary strings, return their sum (also a binary string).

        For example,
        a = "11"
        b = "1"
        Return "100".*/
public class Sol67_AddBinary {
    public static String addBinary(String a, String b){
        String res = "";
        int carry = 0;
        int m = a.length(), n = b.length();
        for (int i = m - 1, j = n - 1; i >=0 || j >=0; i--,j--){
            int sum = carry;
            sum += (i >= 0) ? a.charAt(i) - '0': 0;
            sum += (j >= 0) ? b.charAt(j) - '0': 0;
            res = sum % 2 + res;
            carry = sum / 2;
        }
        if (carry != 0){
            res = carry + res;
        }
        return res;

    }
    public static String addBinary_sb(String a, String b){
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int m = a.length(), n = b.length();
        for (int i = m - 1, j = n - 1; i >=0 || j >=0; i--,j--){
            int sum = carry;
            sum += (i >= 0) ? a.charAt(i) - '0': 0;
            sum += (j >= 0) ? b.charAt(j) - '0': 0;
            sb.append(sum%2);
            carry = sum / 2;
        }
        if (carry != 0){
            sb.append(carry);
        }
        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        String a = "111";
        String b = "1";
        assert "1000".equals(addBinary(a,b));
        assert "1000".equals(addBinary_sb(a,b));
    }
}
