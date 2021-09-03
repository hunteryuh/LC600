package com.alg.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
This question is from Facebook recruiting portal.

There are n students, numbered from 1 to n, each with their own yearbook. They would like to pass their yearbooks around and get them signed by other students.
You're given a list of n integers arr[1..n], which is guaranteed to be a permutation of 1..n (in other words, it includes the integers from 1 to n exactly once each, in some order). The meaning of this list is described below.
Initially, each student is holding their own yearbook. The students will then repeat the following two steps each minute: Each student i will first sign the yearbook that they're currently holding (which may either belong to themselves or to another student), and then they'll pass it to student arr[i]. It's possible that arr[i] = i for any given i, in which case student i will pass their yearbook back to themselves. Once a student has received their own yearbook back, they will hold on to it and no longer participate in the passing process.
It's guaranteed that, for any possible valid input, each student will eventually receive their own yearbook back and will never end up holding more than one yearbook at a time.
You must compute a list of n integers output, whose ith element is equal to the number of signatures that will be present in student i's yearbook once they receive it back.*


Signature
int[] findSignatureCounts(int[] arr)
Input
n is in the range [1, 100,000].
Each value arr[i] is in the range [1, n], and all values in arr[i] are distinct.
Output
Return a list of n integers output, as described above.

Example 1
n = 2
arr = [2, 1]
output = [2, 2]
The first student will sign their own yearbook and pass it to the second, who will also sign it and pass it back to the first student, resulting in 2 signatures. Meanwhile, the second student's yearbook will similarly be signed both by themselves and then by the first student.

Example 2
n = 2
arr = [1, 2]
output = [1, 1]
Each student will simply pass their yearbook back to themselves, resulting in 1 signature each.

https://leetcode.com/discuss/interview-question/614096/facebook-interview-preparation-question-passing-yearbooks
 */
public class PassingYearbooks {
    public int[] findSignatureCounts(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (res[i] == 0) {
                Set<Integer> set = new HashSet<>();
                int cur = i;
                while (!set.contains(cur)) {
                    set.add(cur);
                    cur = arr[cur] - 1;
                }
                for (Integer a : set) {
                    res[a] = set.size();
                }
            }
        }
        return res;
    }

    public int[] findSignatureCounts2(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 1; i <= arr.length; i++) {
            if (res[i - 1] == 0) {
                Set<Integer> set = new HashSet<>();
                int cur = i;
                while (!set.contains(cur)) {
                    set.add(cur);
                    cur = arr[cur - 1];
                }
                set.forEach(a -> res[a - 1] = set.size());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PassingYearbooks p = new PassingYearbooks();
        int[] input = {2,1};
        int[] r = p.findSignatureCounts2(input);
        int[] r2 = p.findSigCount(input);
        System.out.println(Arrays.toString(r));
        System.out.println(Arrays.toString(r2));
    }

    // https://leetcode.com/discuss/interview-question/614096/facebook-interview-preparation-question-passing-yearbooks
    public int[] findSigCount(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 1; i <= res.length; i++) {
            int signer = i;
            do {
                res[i - 1]++;
                signer = arr[signer - 1];
            } while (signer != i);
        }
        return res;
    }
}
