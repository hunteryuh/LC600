package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
interview by Karat Compass on 1/14/2022
After catching your classroom students cheating before, you realize your students are getting craftier and hiding words in 2D grids of letters.
The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = [
['c', 'c', 'c', 't', 'i', 'b'],
['c', 'c', 'a', 't', 'n', 'i'],
['a', 'c', 'n', 'n', 't', 't'],
['t', 'c', 's', 'i', 'p', 't'],
['a', 'o', 'o', 'o', 'a', 'a'],
['o', 'a', 'a', 'a', 'o', 'o'],
['k', 'a', 'i', 'c', 'k', 'i'],
]
word1 = "catnip"
word2 = "cccc"

find_word_location(grid1, word1) => [ (0, 2), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
find_word_location(grid1, word2) =>
[(0, 1), (1, 1), (2, 1), (3, 1)]
OR [(0, 0), (1, 0), (1, 1), (2, 1)]
OR [(0, 0), (0, 1), (1, 1), (2, 1)]
OR [(1, 0), (1, 1), (2, 1), (3, 1)]
only go down or right
the word does exists.
 */
public class WordSearchPaths {
    List<int[]> paths = new ArrayList<>();
    private List<int[]> findLocations(char[][] grid, String word) {

//        List<int[]> paths = new ArrayList<>();

        int m = grid.length;
        int n = grid[0].length;
        // wrong
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                dfs(grid, i, j, paths, 0, word);
//            }
//        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // always start with new arraylist at each starting point
                List<int[]> road = new ArrayList<>();
                if (dfs(grid, i, j, road, 0, word)) {
                    return paths;
                }
            }
        }
        return null;
    }

    private boolean dfs(char[][] grid, int x, int y, List<int[]> road, int start, String word) {
        int m = grid.length;
        int n = grid[0].length;
        if (start == word.length()) {
            paths = new ArrayList<>(road);
            return true;
        }
        int[][] dirs = {{1,0}, {0,1}};
        if (grid[x][y] == word.charAt(start)) {
            road.add(new int[]{x, y});
            for (int[] dir: dirs) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                // this will ensure that newx and newy are in valid range
                if (newx >= m || newy >= n) {
                    continue;
                }
                if (dfs(grid, newx, newy, road, start + 1, word)) {
                    return true;
                }
            }
            road.remove(road.size() - 1);
        }
        return false;

    }
    // wrong
    private void dfs_wrong(char[][] grid, int x, int y, List<int[]> paths, int start, String word) {
        int m = grid.length;
        int n = grid[0].length;
        if (start == word.length()) {
            return;
        }
        if (x >= m || y >= n) {
            return;
        }
        int[][] dirs = {{1,0}, {0,1}};
        if (grid[x][y] == word.charAt(start)) {
            paths.add(new int[]{x, y});
            for (int[] dir: dirs) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                dfs(grid, newx, newy, paths, start + 1, word);
            }
            paths.remove(paths.size() - 1);
        }

    }

    public static void main(String[] args) {
        char[][] grid = {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'c', 'a', 't', 'n', 'i'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'c', 's', 'i', 'p', 't'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'o', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'c', 'k', 'i'}
        };
        String word1 = "cccc";
//        String word1 = "catnip";

        WordSearchPaths ww = new WordSearchPaths();
        List<int[]> res = ww.findLocations(grid, word1);
        for (int[] path: res) {
            System.out.println(Arrays.toString(path));
        }

    }
}
