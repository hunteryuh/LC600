package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 1/5/2018.
 */
/*给个matrix,其中只有一格是9，其他格子是0或1，
0表示此路不通，1表示可以走，判断从（0,0) 点开始上下左右移动能否找到这个是9的格子*/
public class Sol0_amz_Maze {

    private final static int[] dx = {-1,0,0,1};
    private final static int[] dy = {0,1,-1,0};
    public static int findCheese(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length ==0)
            return 0;
        if (matrix[0][0] == 9) return 1;
        int m = matrix.length;
        int n = matrix[0].length;
        if (matrix[0][0] == 0) return 0;
        boolean[][] visited = new boolean[m][n];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0));
        while (!q.isEmpty()){
            Point cur = q.poll();
            for(int i = 0 ; i < 4; i++){
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                if ( x < 0 || y < 0 || x >= m || y >= m || matrix[x][y] == 0
                        || visited[x][y] ) continue;
                if(matrix[x][y] == 9 ) return 1;
                else if( matrix[x][y] == 1){
                    visited[x][y] = true;
                    q.add(new Point(x,y));

                }
            }
        }
        return 0;

    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,1,1},
                {1,1,0,1,1},
                {0,1,1,0,0},
                {9,0,1,1,0}
        };
        System.out.println(findCheese(matrix));
    }
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
