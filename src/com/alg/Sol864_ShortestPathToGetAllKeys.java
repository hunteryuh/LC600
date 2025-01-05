package com.alg;

import sun.awt.image.ImageWatched;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
You are given an m x n grid grid where:

    '.' is an empty cell.
    '#' is a wall.
    '@' is the starting point.
    Lowercase letters represent keys.
    Uppercase letters represent locks.

You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.

If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.

For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys. If it is impossible, return -1.



Example 1:

Input: grid = ["@.a.#","###.#","b.A.B"]
Output: 8
Explanation: Note that the goal is to obtain all the keys not to open all the locks.

Example 2:

Input: grid = ["@..aA","..B#.","....b"]
Output: 6

Example 3:

Input: grid = ["@Aa"]
Output: -1



Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 30
    grid[i][j] is either an English letter, '.', '#', or '@'.
    The number of keys in the grid is in the range [1, 6].
    Each key in the grid is unique.
    Each key in the grid has a matching lock.


 */
public class Sol864_ShortestPathToGetAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        // https://leetcode.com/problems/shortest-path-to-get-all-keys/discuss/1131736/java
        // https://leetcode.com/problems/shortest-path-to-get-all-keys/discuss/1603242/Java-BFS-with-comments-with-proper-explanations
        int rows = grid.length;
        int cols = grid[0].length();
        int[][] dirs = {{-1,0}, {1, 0}, {0,1}, {0,-1}};
        Queue<int[]> queue = new LinkedList();
        int dest = 0;
        int[][][] visited = new int[rows][cols][1<<6];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i].charAt(j) == '@') {
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j][0] = 1;
                } else if (grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'f') {
                    dest |= 1 << (grid[i].charAt(j) - 'a');  //| is the bit-wise OR operator.  //this is all the keys in the map
                }
            }
        }
        System.out.println("dest is " + dest);
//        System.out.println(queue.toArray().st);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (cur[2] == dest) {
                    return step; //all keys are collected, return step.
                }
                for (int[] dir : dirs) {
                    int nexti = cur[0] + dir[0];
                    int nextj = cur[1] + dir[1];
                    if (nexti == rows || nextj == cols || nexti <0 || nextj < 0) continue;
                    char next = grid[nexti].charAt(nextj);
                    int nextKeyStat = cur[2]; //keys that we've owned up to now
                    if (next == '#') continue; // wall
                    if (next >= 'A' && next <= 'F' && (nextKeyStat & (1<< (next-'A'))) == 0) { // //can't proceed w/o the corresponding key
                        continue;
                    }
                    if (next >= 'a' && next <= 'f') {
                        nextKeyStat |= 1 << (next - 'a');  //collect this key
                    }
                    if (visited[nexti][nextj][nextKeyStat] == 1) continue;
                    visited[nexti][nextj][nextKeyStat] = 1;
                    queue.offer(new int[]{nexti, nextj, nextKeyStat});
                }
            }
            step++;
        }
        return -1;
    }


    public static void main(String[] args) {
        String[] s = {"@.a.#","###.#","b.A.B"};
        Sol864_ShortestPathToGetAllKeys ss = new Sol864_ShortestPathToGetAllKeys();
        System.out.println(ss.shortestPathAllKeys(s));
    }
    class State {
        int row;
        int col;
        String key;
        State(int row, int col, String key) {
            this.row = row;
            this.col = col;
            this.key = key;
        }
        public String toString() {
            return row + "-" + col + "-" + key;
        }
    }
    public int shortestPathAllKeys2(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        int[][] dirs = { {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Set<String> visited = new HashSet<>();
        int steps = 0;
        Queue<State> queue = new LinkedList<>();

        int keycount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i].charAt(j) == '@') {
                    queue.offer(new State(i, j, ""));
                } else if (grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'f') {
                    keycount++;
                }
            }
        }
        visited.add(queue.peek().toString());

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                State cur = queue.poll();
                int r = cur.row;
                int c = cur.col;
                String key = cur.key;
                if (key.length() == keycount) {
                    return steps;
                }
                for (int[] dir: dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols
                    || grid[nr].charAt(nc) == '#' ) {
                        continue;
                    }
                    char ch = grid[nr].charAt(nc);
                    if (ch>= 'A' && ch <= 'F' && key.indexOf(Character.toLowerCase(ch)) == -1) {
                        continue;
                    }
                    String newStateKey = key;

                    if (key.indexOf(ch) == -1 && ch >= 'a' && ch <= 'f') {
                        newStateKey += ch;
                    }
                    State newState = new State(nr , nc , newStateKey);
                    if (!visited.contains(newState.toString())) {
                        queue.offer(newState);
                        visited.add(newState.toString());
                    }
                }
            }
            steps++;
        }
        return -1;
    }

}
