package com.alg;

/**
 * Created by HAU on 1/30/2018.
 */
/*Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:*/
public class Sol744_FindSmallestLetterGreaterThanTarget {
    // linear scan
    public static char nextGreatestLetter(char[] letters, char target) {
        for (char c : letters) {
            if (c > target)
                return c;
        }
        return letters[0];
    }

    public static void main(String[] args) {
        char[] ca = {'a', 'c', 'f'};
        char t = 'e';
        System.out.println(nextGreatestLetter(ca, t));
        System.out.println(nextGreatestLetter2(ca, t));
    }

    // binary search, time logN
    public static char nextGreatestLetter2(char[] letters, char target) {
        int lo = 0;
        int hi = letters.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (letters[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return letters[lo % letters.length];

    }
}
