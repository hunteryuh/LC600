package com.alg;

import java.util.LinkedList;
import java.util.Queue;

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
我开始用BFS，结果被白哥指出错误，还没写完，我觉得太烦，直接改dfs写完。*/
public class Sol0_polluteFruitbfs {


    public static int polluteFruitbfs(int[][] matrix){
        int[][] dirs= new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0 ; i < m; i++){
            for(int j = 0;j < n;j++){
                if(matrix[i][j] == 2 ){
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int steps = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int[] p = queue.poll();
                for(int i = 0; i < 4; i++){
                    int x = p[0] + dirs[i][0];
                    int y = p[1] + dirs[i][1];
                    if( x < 0 || y < 0 || x >=m || y >=n
                            || visited[x][y] || matrix[x][y] != 1){
                        continue;
                    }
                    queue.add(new int[]{x,y});
                    visited[x][y] = true;
                }
                size--;
            }
            if ( queue.size() > 0 )
                steps++;
        }
        for(int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && !visited[i][j])
                  return -1;
            }
        }
        return steps;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {2,1,0,0},
                {1,1,0,1},
                {1,1,0,2}
        };
        System.out.println(polluteFruitbfs(matrix));  //3
    }
}
