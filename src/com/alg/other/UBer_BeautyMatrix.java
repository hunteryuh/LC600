package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
Beauty of a square matrix is minimum positive integer which doesnt exist in the matrix.
ex. for 2x2 matrix 1,2,4,6 beauty is 3.
Given a square matrix of +ve integers 'numbers' and an integer 'size' which evenly divides 'numbers.length' Do the following :-
a) Split numbers into non-overlapping size x size submatrices.
b) Arrange all submatrices in ascending order of beauty and put them back into the matrix. Order should be from left to right, top to bottom. If same beauty, then place them in the original order they were present in the original matrix.
ex.
1,2,2,3
3,4,10,4
2,10,1,2
5,4,4,5
size = 2
Output will be
2,3,2,10
10,4,5,4
1,2,1,2
4,5,3,4

Reasoning :
1,2,3,4 beauty = 5
2,3,10,4 beauty = 1
2,10,5,4 beauty = 1
1,2,4,5 beauty = 3
						1<3<5

https://leetcode.com/discuss/interview-question/949185/Uber-or-SDE-1-or-US-or-CodeSignal-OA
 */
public class UBer_BeautyMatrix {
    public int[][] beautyOfMatrix(int[][] matrix, int k) {
        Map<Integer, int[]> cells = new HashMap<>();

        //store grids into arrays keyed by their grid id
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        int n = matrix.length;
        for(int i =0; i < n; i++){
            for(int j =0; j< n ; j++){
                int r = k * (i/k);  // 0 ,1
                int c = k * (j/k);  // ?
                int key = r*n + c;
                int row = i/k;
                int col = j/k;
                cells.put(key, new int[]{r,c});
                if(!map.containsKey(key)) map.put(key, new ArrayList<>());
                map.get(key).add(matrix[i][j]);
            }
        }
        System.out.println(cells.values());
        System.out.println(map);


        //calculate beauty of each grid
        TreeMap<Integer, List<Integer>> beauty = new TreeMap<>();
        for(Integer kS : map.keySet()){
            int b = getPositive(map.get(kS));
            if(!beauty.containsKey(b)) beauty.put(b, new ArrayList<>());
            beauty.get(b).add(kS);
        }

        System.out.println(beauty);

        //sorting beauty to access them in order
//        for(List kS : map.values()){
//            Collections.sort(kS);
//        }

        int[][] res = new int[n][n]; //resultant array to return
//        int i = 0; int j = 0;
//        for (int beautyvalue: beauty.keySet()) {
//
//            List<Integer> grids = beauty.get(beautyvalue);
//            for (int idx: grids) {
//                List<Integer> submatrix =  map.get(idx); // 2,3,10,4
//                int rs = i/k ; int re = rs + k - 1;
//                int cs = j/k; int ce = cs + k - 1;
//                int t = 0;
//                for (int r = rs; r <= re; r++) {
//                    for (int c = cs; c <= ce; c++) {
//                        res[r][c] = submatrix.get(t++);
//                    }
//                }
//            }
//        }
        int r1 = 0; int c1 = 0;
        for(List<Integer> l : beauty.values()){
            for(int idx : l){
                int row = cells.get(idx)[0];
                int col = cells.get(idx)[1];
                for (int i = row,  rk= 0; i<row+k; i++, rk++){
                    for (int j = col,  ck = 0; j<col+k; j++, ck++){
                        res[k*(r1/k) + rk][k*(c1/k) + ck] = matrix[i][j];
                    }
                }
                if(c1 + k < n)
                {
                    c1+=k;
                }
                else{
                    r1+=k;
                    c1=0;
                }
            }
        }

        return res;
    }

    /* function to get first positive */
    private int getPositive(List<Integer> list){
        int num = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i: list){
            pq.offer(i);
        }

        while(!pq.isEmpty()){
            int val = pq.poll();
            if (val<=0) continue;
            if(val > num) break;
            else num++;
        }

        return num;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,2,3},
            {3,4,10,4},
            {2,10,1,2},
            {5,4,4,5}
        };

        for (int[] row: matrix) {
            System.out.println(Arrays.toString(row));
        }

        UBer_BeautyMatrix uu = new UBer_BeautyMatrix();
        int[][] res = uu.beautyOfMatrix(matrix, 2);
        for (int[] row: res) {
            System.out.println(Arrays.toString(row));
        }
//        System.out.println(Arrays.deepToString(res));
    }
}
