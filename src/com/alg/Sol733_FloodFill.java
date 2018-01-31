package com.alg;

/**
 * Created by HAU on 1/31/2018.
 */
/*An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input:
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation:
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.*/
public class Sol733_FloodFill {
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if( newColor != color){
            dfs(image, sr,sc,color, newColor);
        }
        return image;
    }

    private static void dfs(int[][] image, int sr, int sc, int color, int newColor) {
        if ( image[sr][sc] == color){
            image[sr][sc] = newColor;
            if ( sr >=1) dfs(image, sr - 1, sc, color, newColor);
            if ( sc >=1) dfs(image, sr, sc - 1, color, newColor);
            if ( sr + 1 <= image.length) dfs(image, sr + 1, sc, color, newColor);
            if ( sc + 1 <= image[0].length) dfs(image, sr, sc + 1, color, newColor);
        }
    }

    // format 2
    public static int[][] flood(int[][] image, int sr, int sc, int newColor){
        if( image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private static void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if ( sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length
                || image[sr][sc] != color ) return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }


}
