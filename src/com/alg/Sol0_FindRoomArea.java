package com.alg;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*FB on-site two weeks ago last round of coding . (This problem is also in the internal question bank of G.)

There is a robot in a room. The initial location of the robot is unknown. The shape or area of the room is unknown.
You have a remote that could walk the robot into any of the four directions - up, left, down or right.
Here is the move method: boolean move(int direction), direction: 0, 1, 2, 3. If the robot hits the wall, the method returns false.
Otherwise returns true and the robot moves on to the direction given.
Find out the area of the room.


e.g.
X
XXX X
XXXXX 'X' marks the floor plan of the room.

A room like such has an area of 10.*/
public class Sol0_FindRoomArea {
    // scanning a graph, the shape of the room is unknown, so the normal approach like a boolean matrix would
    // not work here; instead we go with a map or set.
    //using 2D coordinates, DFS, and a Hash data structure (HashSet) to keep track of visited coordinates.
    static int[][] room = new int[100][100];
    public static int getArea(){
        int x = 0;
        int y = 0;
        Set<String> visited = new HashSet<>();
        dfshelper(0,0,visited);
        return visited.size();
    }

    private static void dfshelper(int curX, int curY, Set<String> visited) {
        visited.add(curX + "," + curY);
        int[] xMove = {-1,1,0,0};
        int[] yMove = {0,0,-1,1};
        int[] dirs = {1,3,0,2};
        for(int i = 0; i < dirs.length; i++){
            int newX = curX + xMove[i];
            int newY = curY + yMove[i];
            if(!visited.contains(newX + ","+ newY) && move(dirs[i],room, newX, newY)){
                dfshelper(newX,newY, visited);
            }
        }
    }

    private static boolean move(int dir, int[][] room, int x, int y) {
        if(dir == 0){
            if(room[x][--y] == 0) return true; // down
        }
        if(dir == 1){
            if(room[--x][y] == 0) return true; //left
        }
        if(dir == 2){
            if(room[x][++y] == 0) return true; //up
        }
        if(dir == 3){
            if(room[++x][y] == 0) return true; // right
        }
        return false;
    }

    // from forum
    public int areaDFS(int i, int j) {
        Set<String> visited = new HashSet<>();
        dfs(i, j, visited);
        return visited.size();
    }

    void dfs(int i, int j, Set<String> visited) {
        if (visited.contains(encode(i, j))) {
            return;
        }
        visited.add(encode(i, j));
        for (int k = 0; k < 4; k++) {
            if (move(i, j, k)) {
                if (k == 0) {
                        dfs(i - 1, j, visited);
                } else if (k == 1) {
                    dfs(i, j - 1, visited);
                } else if (k == 2) {
                    dfs(i + 1, j, visited);
                } else if (k == 3) {
                    dfs(i, j + 1, visited);
                }
            }
        }
    }

    public int areaBFS(int i, int j) {
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int currI = xy[0];
            int currJ = xy[1];
            if (visited.contains(encode(currI, currJ))) {
                continue;
            }
            visited.add(encode(currI, currJ));
            for (int k = 0; k < 4; k++) {
                if (move(currI, currJ, k)) {
                    if (k == 0) {
                        queue.offer(new int[]{currI - 1, currJ});
                    } else if (k == 1) {
                        queue.offer(new int[]{currI, currJ - 1});
                    } else if (k == 2) {
                        queue.offer(new int[]{currI + 1, currJ});
                    } else if (k == 3) {
                        queue.offer(new int[]{currI, currJ + 1});
                    }
                }
            }
        }
        return visited.size();
    }

    String encode(int i, int j) {
        return "x" + i + "y" + j;
    }

    boolean move(int i, int j, int dir) {
        if (dir == 0) { // up
            if (i <= 0 || (i > 0 && grid[i - 1][j] == 0)) return false;
        } else if (dir == 1) { // left
            if (j <= 0 || (j > 0 && grid[i][j - 1] == 0)) return false;
        } else if (dir == 2) { // down
            if (i >= grid.length - 1 || (i < grid.length - 1 && grid[i + 1][j] == 0)) return false;
        } else if (dir == 3) { // right
            if (j >= grid[0].length - 1 || (j < grid[0].length && grid[i][j + 1] == 0)) return false;
        } else {
            throw new RuntimeException("Invalid direction");
        }
        return true;
    }
//    int[][] grid = {
//            {0, 1, 1, 0},
//            {1, 0, 1, 0},
//            {1, 1, 1, 1},
//            {0, 1, 0, 1}
//    };

    int[][] grid = {
            {0, 1, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1}
    };

    //@Test
    public void test() {
        //assertThat(areaDFS(2, 2), Matchers.is(17));
        //assertThat(areaBFS(2, 2), Matchers.is(17));
    }
}
