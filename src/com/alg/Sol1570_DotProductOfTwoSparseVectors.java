package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given two sparse vectors, compute their dot product.

Implement class SparseVector:

    SparseVector(nums) Initializes the object with the vector nums
    dotProduct(vec) Compute the dot product between the instance of SparseVector and vec

A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?



Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

Example 2:

Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

Example 3:

Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6



Constraints:

    n == nums1.length == nums2.length
    1 <= n <= 10^5
    0 <= nums1[i], nums2[i] <= 100
https://leetcode.com/problems/dot-product-of-two-sparse-vectors/description/

 */
public class Sol1570_DotProductOfTwoSparseVectors {

    // non-efficient array approach
    class SparseVector {
        private int[] array;

        SparseVector(int[] nums) {
            array = nums;
        }

        public int dotProduct(SparseVector vec) {
            int result = 0;

            for (int i = 0; i < array.length; i++) {
                result += array[i] * vec.array[i];
            }
            return result;
        }


    }

    class SparseVector2 {
        // Map the index to value for all non-zero values in the vector
        Map<Integer, Integer> mapping;

        // DETAILED EXPLANATION:
        // Technically Hashmap uses an array of nodes internally,
        // but to get an element we hash the key,
        // and then access this array based on the hashcode.
        // It's highly probable that the chunk of array where
        // our destination lies is not in the cache, hence a cache miss.

        SparseVector2(int[] nums) {
            mapping = new HashMap<>();
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != 0) {
                    mapping.put(i, nums[i]);
                }
            }
        }

        // O(n) time  to create the map, O(L) to calculate the dot product
        public int dotProduct(SparseVector2 vec) {
            int result = 0;

            // iterate through each non-zero element in this sparse vector
            // update the dot product if the corresponding index has a non-zero value in the other vector
            for (Integer i : this.mapping.keySet()) {
                if (vec.mapping.containsKey(i)) {
                    result += this.mapping.get(i) * vec.mapping.get(i);
                }
            }
            return result;
        }
    }

    // https://leetcode.com/problems/dot-product-of-two-sparse-vectors/solutions/1522271/java-o-n-solution-using-two-pointers-with-detailed-explanation-and-follow-up/
    class SparseVector3 {

        // represent elements of a sparse vector as a list of <index, value> pairs. We use two pointers to iterate through the two vectors to calculate the dot product.
        List<int[]> pairs;

        // DETAILED EXPLANATION: List uses an array internally. When an array is created, it is allocated a contiguous/ continious block of memory (divided in terms of page size). When an array is interated upon, the system loads a chunk of this allocated space (in terms of page size) and then returns the value.
        //
        //When subsequent elments of the array are accessed linearly, for a lot of these requests, the data is already present in cache so its a cache success.
        //
        //Eventually when there is a cache miss, the system loads the next chunk of the allocated space in cache; then for a lot of the next set of request we get cache success.
        //
        //Moral of story: we get a lot less cache miss with the list/array.
        SparseVector3(int[] nums) {
            pairs = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    pairs.add(new int[]{i, nums[i]});
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector3 vec) {
            int result = 0, p = 0, q = 0;
            while (p < pairs.size() && q < vec.pairs.size()) {
                if (pairs.get(p)[0] == vec.pairs.get(q)[0]) {
                    result += pairs.get(p)[1] * vec.pairs.get(q)[1];
                    p++;
                    q++;
                } else if (pairs.get(p)[0] > vec.pairs.get(q)[0]) {
                    q++;
                } else {
                    p++;
                }
            }
            return result;
        }
    }

    // https://sugarac.gitbooks.io/facebook-interview-handbook/content/fei-leetcode-ti/vector-dot-product.html

    public int dotProduct(int[][] A, int[][] B) {
        int result = 0;
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            if (A[i][0] == B[j][0]) { //二元组中第一个元素是index，第二个元素是值。
                result += A[i][1] * B[j][1];
                i++;
                j++;
            } else if (A[i][0] > B[j][0])
                j++;
            else
                i++;
        }
        return result;
    }

    // what if one of the vector is very long (not very sparse)
    // 优化：Binary Search on array B O(m log n); O(1)
    public int docProduct2(int[][] A, int[][] B) {
        int result = 0;
        for (int[] pair : A) { //O(m)
            int index = pair[0];
            int indexB = binarySearch(B, index); //O(log n)
            if (indexB != -1)
                result += pair[1] * B[indexB][1];
        }
        return result;
    }

    public int binarySearch(int[][] B, int index) { //九章模板
        int start = 0, end = B.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (B[mid][0] >= index) //注意>=；原来写的是1，应该是0，第一个元素是index
                end = mid;
            else
                start = mid;
        }
        if (B[start][0] == index)
            return start;
        else if (B[end][0] == index)
            return end;
        return -1;
    }


    // 3.two inputs are sorted by index0, have same size, sometimes dense, sometimes sparse;
    //
    //又问如果两个数组一样长，且一会sparse一会dense怎么办。他说你可以在two pointer的扫描中内置一个切换二分搜索的机制。看差值我说过，设计个反馈我说过，他说不好。他期待的解答是，two pointers找到下个位置需要m次比较，而直接二分搜需要log(n)次比较。那么在你用two pointers方法移动log(n)次以后，就可以果断切换成二分搜索模式了。
    //two pointers + binary search

    public int sparseVectorMultiplication(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
        if (a == null || b == null || a.size() == 0 || b.size() == 0) {
            return 0;
        }
        int m = a.size();
        int n = b.size();
        int res = 0;

        int i = 0;
        int j = 0;
        int countA = 0;
        int countB = 0;
        while (i < m && j < n) {
            ArrayList<Integer> pairA = a.get(i);
            ArrayList<Integer> pairB = b.get(j);
            if (pairA.get(0) < pairB.get(0)) {
                i++;
                countA++;
                countB = 0;
                if (countA > Math.log(m)) {
                    i = search(a, i, m, pairB.get(0));
                    countA = 0;
                }
            } else if (pairA.get(0) > pairB.get(0)) {
                j++;
                countB++;
                countA = 0;
                if (countB > Math.log(n)) {
                    j = search(b, j, n, pairA.get(0));
                    countB = 0;
                }
            } else {
                res += pairA.get(1) * pairB.get(1);
                i++;
                j++;
                countA = 0;
                countB = 0;
            }
        }
        return res;
    }

    private int search(ArrayList<ArrayList<Integer>> array, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            ArrayList<Integer> pair = array.get(mid);
            if (pair.get(0) == target) {
                return mid;
            } else if (pair.get(0) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (array.get(end).get(0) == target) {
            return end;
        }
        return start;
    }

    // Follow up:
    //If one of the vector is sparse, then we can use the third approach,
    // pick upon the indices of the sparse vector and
    // then binary search the indices in the second vector.
    // In this case complexity would be (l log N)
    // where l is the length of sparse vector and
    // N is the length of non-sparse vector.

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
}
