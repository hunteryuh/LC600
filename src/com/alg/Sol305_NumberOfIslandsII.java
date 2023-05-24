package com.alg;

import javax.print.attribute.IntegerSyntax;
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

        for (int[] p : positions){
            int root = p[0] *n + p[1]; // assume the new point is an isolated island
            roots[root] = root;
            count++;

            for(int[] dir: dirs){
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int neibor = n * x + y;
                if(x>=0 && y>=0 && x<m && y<n&& roots[x*n+y]!=-1){
                    // get neighbor's root
                    int neighborRoot = findRoot(roots,neibor);
                    int curRoot = roots[root]; // here curRoot = root, helps understanding the comparison below
                    if(curRoot != neighborRoot){
                        roots[neighborRoot] = curRoot;  //set previous root's root
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


    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    public List<Integer> numOfLand2(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m * n);
        boolean[][] isLand = new boolean[m][n];
        List<Integer> res = new ArrayList<>();
        int count = 0;
        for (int[] pos: positions) {
            if (isLand[pos[0]][pos[1]]) {
                res.add(count);
                continue;
            }
            isLand[pos[0]][pos[1]] = true;
            count++;
            for (int[] dir: dirs) {
                int newx = pos[0] + dir[0];
                int newy = pos[1] + dir[1];
                if (newx < 0 || newy < 0 || newx >= m || newy >=n || !isLand[newx][newy]) continue;
                int r1 = dsu.find(newx * n + newy);
                int r2 = dsu.find(pos[0] * n + pos[1]);
                if (r1 != r2) {
                    dsu.union(r1, r2);
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }


    class DSU {
        int[] parents;
        public DSU(int n) {
            parents = new int[n];
            for (int i = 0;i < n; i++) {
                parents[i] = i;
            }
        }
        int find(int u) {
            if (parents[u] != u) {
                parents[u] = find(parents[u]);
            }
            return parents[u];
        }
        void union(int u, int v) {
            parents[find(u)] = parents[find(v)];
        }
    }

    // dsu 优化 by rank
    class DSU_rank {
        int[] parents;
        int[] rank;
        public DSU_rank(int n) {
            parents = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)  {
                parents[i] = i;
            }
            Arrays.fill(rank, -1);
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]) {
                parents[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parents[rootY] = rootX;
            } else { // rank equal, only then maintain rank
                parents[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }

    // dsu 优化 by size
    class DSU_size {
        int[] parent;
        int[] size;
        public DSU_size(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            Arrays.fill(size, 1);
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (size[rootX] <= size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else /*if (size[rootX] > size[rootY])*/ {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }
}
