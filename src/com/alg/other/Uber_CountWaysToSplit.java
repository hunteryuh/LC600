package com.alg.other;
/*

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=698517&ctid=230584
Given a string S, Count number of ways of splitting S into 3 non-empty a,b,c such that a+b, b+c, c+a are all different.

ex. xzxzx OP: 5
    x, z, xzx
	x, zx, zx
	xz, x, zx
	xz, xz, x
	xzx, z, x
ex. xxx OP : 0
给定字符串s，求分解成a, b, c三个substrings的所有可能性的个数，要求 a+ b != b + c != c + a。
 */
public class Uber_CountWaysToSplit {
    public int countWaysToSplit(String s) {
        int n = s.length();
//        int count = 0;
//        for (int i = 1; i < n - 1; i++) {  // i needs start with 1 to make nonempty
//            for (int j = i + 1; j < n; j++) {
//                String a = s.substring(0, i);
//                String b = s.substring(i, j);
//                String c = s.substring(j);
//                if (!(a+b).equals(b+c) && !(b+c).equals(c+a) && !(a+b).equals(c+a)) {
//                    count++;
//                }
//            }
//        }
//        return count;

        // with certain dedupe
        int res = 0;
        for(int i = 1; i<s.length()-1; i++) {
            String a = s.substring(0, i);
            String bc = s.substring(i);
            for (int j = i + 1; j < s.length(); j++) {
                String ab = s.substring(0, j);
                if (ab.equals(bc)) {
                    continue;
                }
                String c = s.substring(j);
                String ca = c + a;
                if (ca.equals(bc) || ca.equals(ab)) {
                    continue;
                }
                res++;

            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "xfxfx";
        Uber_CountWaysToSplit uu = new Uber_CountWaysToSplit();
        System.out.println(uu.countWaysToSplit(s));
    }

    // deduplicate

}
