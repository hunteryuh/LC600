package com.alg;
/*
You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.

The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.

You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.

When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.

Design an algorithm to clean the entire room using the following APIs:

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}

Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.



Custom testing:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the four mentioned APIs without knowing the room layout and the initial robot's position.



Example 1:

Input: room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]], row = 1, col = 3
Output: Robot cleaned all rooms.
Explanation: All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.

Example 2:

Input: room = [[1]], row = 0, col = 0
Output: Robot cleaned all rooms.

*/


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
    interface Robot {
       // Returns true if the cell in front is open and robot moves into the cell.
       // Returns false if the cell in front is blocked and robot stays in the current cell.
       public boolean move();
       // Robot will stay in the same cell after calling turnLeft/turnRight.
       // Each turn will be 90 degrees.
       public void turnLeft();
       public void turnRight();

       // Clean the current cell.
       public void clean();
   }

public class Sol489_RobotRoomCleaner {
    private final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    Set<List<Integer>> visited = new HashSet<>();  // cannot use Set<int[]>
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0);
    }

    private void dfs(Robot robot, int x, int y, int dir) {
        visited.add(Arrays.asList(x, y));
        robot.clean();

        for (int i = 0; i < 4; i++) {
            int newdir = (dir + i) % 4;
            int newx = x + dirs[newdir][0];
            int newy = y + dirs[newdir][1];
            if (!visited.contains(Arrays.asList(newx, newy)) && robot.move()) {
                dfs(robot, newx, newy, newdir);
                // Moves backward one step while maintaining the orientation.
                goback(robot);
            }
            // Changed orientation.  right-hand rule  https://en.wikipedia.org/wiki/Maze-solving_algorithm#Wall_follower
            robot.turnRight();
        }
    }

   // go back from the coming direction & turn to original direction to avoid unnecessary condition judgements
    // initial position (row, col, dir)
    private void goback(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public static void main(String[] args) {
        Set<List<Integer>> seta = new HashSet<>();
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(1, 2);
        seta.add(list1);
        // lists are equal if same size and  contain the same elements in the same order.
        System.out.println("set contains list1? " + seta.contains(list1)); // true
        System.out.println("set contains list2? " + seta.contains(list2)); // trie

    }

}
