package com.alg;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by HAU on 9/23/2017.
 */

/*Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.*/
public class Sol407_TrappingRainWaterII {
    public static class Cell{
        public int x,y,h;
        public Cell(){};
        public Cell(int x, int y, int h){
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0
                || heightMap[0].length == 0)
            return 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        // initialize minheap, visited[][]
        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.h - o2.h;
            }
        });
        boolean[][] visited = new boolean[m][n];
        // initialize: add all border cells to the queue ( minHeap)
        for (int i = 0; i < m; i++){
            visited[i][0] = true;
            visited[i][n-1] = true;
            queue.offer(new Cell(i,0,heightMap[i][0]));
            queue.offer(new Cell(i,n-1,heightMap[i][n-1]));
        }

        for (int i = 0; i < n; i++){
            visited[0][i] = true;
            visited[m-1][i] = true;
            queue.offer(new Cell(0,i,heightMap[0][i]));
            queue.offer(new Cell(m-1,i,heightMap[m-1][i]));
        }
        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height
        // as its height plus the water trapped
        // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int res = 0;
        while(!queue.isEmpty()){
            Cell cell = queue.poll();
            for (int [] dir : dirs){
                int row = cell.x + dir[0];
                int col = cell.y + dir[1];
                if ( row>=0 && row<m && col>=0 && col< n
                        && !visited[row][col]){
                    visited[row][col] = true;
                    res += Math.max(0,cell.h - heightMap[row][col]);
                    queue.offer(new Cell(row,col, Math.max(heightMap[row][col], cell.h)));
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[][] heightMatrix={
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        };
        System.out.println(trapRainWater(heightMatrix));
    }
}
