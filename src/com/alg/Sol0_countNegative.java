package com.alg;

/**
 * Created by HAU on 12/1/2017.
 */
/*count number of negative numbers in a matrix, both row and columns are sorted in
* ascending order*/
public class Sol0_countNegative {
    public static int countNegative(int[][] matrix){
        // time complexity is O(N + M)
        int count = 0;
        int i = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int j = n - 1;
        while ( i <= m - 1 && j >=0){
            if(matrix[i][j] < 0){
                count += j + 1;
                i++;
            }else{
                j--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] m = {
                { -4, -2, -1, 4, 7 },
                { -3, -1, 0, 4, 8 },
                { -1, 3, 5, 7, 9 }
        };
        System.out.println(countNegative(m));
    }
}
