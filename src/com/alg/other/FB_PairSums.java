package com.alg.other;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given a list of n integers arr[0..(n-1)], determine the number of different pairs of elements within it which sum to k.
If an integer appears in the list multiple times, each copy is considered to be different; that is, two pairs are considered different if one pair includes at least one array index which the other doesn't, even if they include the same values.
Signature
int numberOfWays(int[] arr, int k)
Input
n is in the range [1, 100,000].
Each value arr[i] is in the range [1, 1,000,000,000].
k is in the range [1, 1,000,000,000].
Output
Return the number of different pairs of elements which sum to k.
Example 1
n = 5
k = 6
arr = [1, 2, 3, 4, 3]
output = 2
The valid pairs are 2+4 and 3+3.
Example 2
n = 5
k = 6
arr = [1, 5, 3, 3, 3]
output = 4
There's one valid pair 1+5, and three different valid pairs 3+3 (the 3rd and 4th elements, 3rd and 5th elements, and 4th and 5th elements).
 */
public class FB_PairSums {
    public int PairSums(int[] arr, int k) {
        Map<Integer, Integer> fq = new HashMap<>();
        for (int i : arr) {
            fq.put(i, fq.getOrDefault(i, 0) + 1);
        }
        int res = 0;
        int pairDiff = 0;
        for (Integer item : fq.keySet()) {
            int count = fq.get(item);
            if (item + item == k) {
                res += count * (count - 1) /2;
            } else if (fq.containsKey(k - item)) {
                pairDiff += fq.get(item) * fq.get(k - item);
            }
        }
        return res + pairDiff / 2;
    }

    // sort and two pointers, nlogn + n
    public int numberOfWays(int[] arr, int k) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        int res = 0;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum < k) {
                left++;
            } else if (sum > k) {
                right--;
            } else {
                if (arr[left] != arr[right]) {
                    int lc = 1;
                    while (arr[left + 1] == arr[left]) {
                        left++;
                        lc++;
                    }
                    left++;

                    int rc = 1;
                    while (arr[right-1] == arr[right]) {
                        right--;
                        rc++;
                    }
                    right--;
                    res += lc * rc;
                } else {
                    int c = right - left + 1;
                    res += c * (c - 1) /2;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 5, 3, 3, 3, 1, 5};
        int k = 6;
        FB_PairSums f = new FB_PairSums();
        System.out.println(f.PairSums(arr, k));
        System.out.println(f.numberOfWays(arr, k));
//        System.out.println(Integer.MAX_VALUE);
//        int count = 100_000;
//        System.out.println((long) count * ((long)count - 1) / 2);
    }
}
