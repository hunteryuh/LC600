package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 12/1/2017.
 */
/*A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which
turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of
islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands
horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.*/
public class Sol305_NumberOfIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] roots = new int[m*n];
        Arrays.fill(roots,-1);
        ArrayList<Integer> res = new ArrayList<>();
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
        int count = 0;

        for ( int[] p : positions){
            int root = p[0] *n + p[1]; // assume the new point is an isolated island
            roots[root] = root;
            count++;

            for(int[] dir: dirs){
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int neibor = n * x + y;
                if(x>=0 && y>=0 && x<m && y<n&& roots[x*n+y]!=-1){
                    // get neibor's root
                    int neiborRoot = findRoot(roots,neibor);
                    int curRoot = roots[root]; // here curRoot = root, helps understanding the comparison below
                    if(curRoot != neiborRoot){
                        roots[neiborRoot] = curRoot;  //set previous root's root
                        count--;
                    }
                }
            }
            res.add(count);
        }
        return res;
    }

    private int findRoot(int[] roots, int id) {
        while(id != roots[id]){
            roots[id] = roots[roots[id]]; // optimization, path compression
            id = roots[id];
        }
        return id;
    }
}
