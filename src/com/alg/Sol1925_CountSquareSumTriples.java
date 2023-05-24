package com.alg;
/*
A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.

Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.


 */
public class Sol1925_CountSquareSumTriples {
    public int countTriples(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j<=n ;j++) {
                int k = i * i + j * j;
                int c = (int) Math.sqrt(k);
                if (c * c == k && c <=n) {
                    res += 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Sol1925_CountSquareSumTriples s = new Sol1925_CountSquareSumTriples();

    }
}
