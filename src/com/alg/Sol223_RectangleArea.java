package com.alg;

/**
 * Created by HAU on 12/27/2017.
 */
/*Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.*/
public class Sol223_RectangleArea {
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (E - G) * (F - H);

        int overLapArea = OLA(A, B, C, D, E, F, G, H);
        return area1 + area2 - overLapArea;
    }
    public static int OLA(int A, int B, int C, int D, int E, int F, int G, int H) {
        int rleft= Math.max(A,E);
        int rright = Math.max(rleft,Math.min(G,C));
        int r = rright - rleft;


        int hlow = Math.max(B,F);
        int hhigh = Math.max(hlow,Math.min(H,D));
        int h = hhigh - hlow;

        if ( r <= 0 || h <= 0) return  0;
        return r*h;
    }

    public static void main(String[] args) {


        int A = -3, B = 0, E = 0, F= -1, C = 3, D = 4, G = 9, H = 2;
        // area 1 = 6 x 4 = 24;
        // area 2 = 9 * 3 = 27;
        // overlaparea = 6;
        System.out.println(Integer.MAX_VALUE);
        A = -1500000001;
        B =         0;
        C =                 -1500000000;
        D =         1;
        E =         1500000000;
        F =         0;
        G =         1500000001;
        H =         1;
        System.out.println(computeArea(A,B,C,D,E,F,G,H));
    }
}
