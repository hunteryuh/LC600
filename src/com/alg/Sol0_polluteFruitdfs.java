package com.alg;

/**
 * Created by HAU on 2/4/2018.
 */
/*
2 1 0 0
1 1 0 1
1 1 0 2

2 腐烂水果
1 新鲜水果
0 空位

腐烂水果可以在1个时间单位里面把上下左右新鲜水果变腐烂。
求要多少时间单位才能把所有水果变腐烂。如果永远不能变腐烂，返回-1.
*/
public class Sol0_polluteFruitdfs {
    public static int polluteFruit(int[][] matrix){

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] days = new int[m][n];
        for(int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                days[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0 ; i < m; i++){
            for(int j = 0;j < n;j++){
                if(matrix[i][j] == 2 ){
                    dfs(matrix,i,j,0, days);
                }
            }
        }
        int max = 0;


        for(int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && days[i][j]== Integer.MAX_VALUE) return -1;
                if(days[i][j] < Integer.MAX_VALUE && days[i][j] > max){
                    max = days[i][j];
                }
            }
        }
        return max;
    }

    private static void dfs(int[][] matrix, int i, int j, int step, int[][] days) {
        if ( i < 0 || i >= matrix.length || j <0 || j >= matrix[0].length) return;
        if ( matrix[i][j] == 0 || step >= days[i][j] ) return;

        days[i][j] = step;
        dfs(matrix,i-1,j,step + 1, days);
        dfs(matrix,i+1,j,step + 1, days);
        dfs(matrix,i,j-1,step + 1, days);
        dfs(matrix,i,j+1,step + 1, days);


    }

    public static void main(String[] args) {
        int[][] matrix = {
                {2,1,0,0},
                {1,1,0,1},
                {1,1,0,2}
        };
        System.out.println(polluteFruit(matrix));
    }
}
