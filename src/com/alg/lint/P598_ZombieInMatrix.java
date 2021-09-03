package com.alg.lint;

import java.util.LinkedList;
import java.util.Queue;

/*
Description
Give a two-dimensional grid, each grid has a value, 2 for wall, 1 for zombie, 0 for human (numbers 0, 1, 2).
Zombies can turn the nearest people(up/down/left/right) into zombies every day, but can not through wall.
How long will it take to turn all people into zombies? Return -1 if can not turn all people into zombies.

Example
Example 1:

Input:
[[0,1,2,0,0],
 [1,0,0,2,1],
 [0,1,0,0,0]]
Output:
2
Example 2:

Input:
[[0,0,0],
 [0,0,0],
 [0,0,1]]
Output:
4


https://www.jiuzhang.com/problem/zombie-in-matrix/
https://www.lintcode.com/problem/zombie-in-matrix/
 */
public class P598_ZombieInMatrix {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */


    public int zombie(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int days = 0;
        int m = grid.length;
        int n = grid[0].length;
        int totalPeople = 0;
        Queue<Zombie> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    totalPeople++;
                } else if (grid[i][j] == 1) {
                    queue.offer(new Zombie(i, j));
                }
            }
        }
        int[] dirx = {-1, 1, 0, 0};
        int[] diry = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            days++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Zombie z = queue.poll();
                for (int k = 0; k < dirx.length; k++) {
                    int newx = z.x + dirx[k];
                    int newy = z.y + diry[k];

                    Zombie newz = new Zombie(newx, newy);
                    if (canMoveToEat(newz, grid)) {
                        grid[newx][newy] = 1; // turn this position of people to a zombie,
                        queue.offer(newz); // add the new zombie to the queue
                        totalPeople--;
                        if (totalPeople == 0) {
                            return days;
                        }
                    }
                }
            }
        }
        return -1;
    }

    static class Zombie {
        int x;
        int y;
        Zombie(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean canMoveToEat(Zombie z, int[][] grid) {
        if (z.x <0 || z.x >= grid.length) {
            return false;
        }
        if (z.y < 0 || z.y >= grid[0].length) {
            return false;
        }
        if (grid[z.x][z.y] != 0) {
            return false;
        }
        return true;
    }
}
