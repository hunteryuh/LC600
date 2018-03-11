package com.alg;

import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by HAU on 12/27/2017.
 */
/*给两个长方形的topLeft和bottomRight坐标, 判断这两个长方形是否重叠

Rectangle Overlap。这题和leetcode 算相交面积的区别：它帮你定义好两个类，一个叫Point，一个叫Rectangle，
Rectangle包括左上右下两个Point, Point包括x, y的值， */
public class Sol0_amz_overlapRectangle {
    public static boolean isOverlap(Point pa1, Point pa2, Point pb1, Point pb2){
        // Returns true if two rectangles (l1, r1) and (l2, r2) overlap

        // If one rectangle is on left side of other
        if ( pb1.getX() > pa2.getX() || pa1.getX() > pb2.getX())
            return false;
        // If one rectangle is above other
        if ( pb1.getY() < pa2.getY() || pa1.getY() < pb2.getY())
            return false;
        return true;
    }

    public static void main(String[] args) {
        /* Driver program to test above function */

        Point l1 = new Point(0,10), r1 = new Point(10, 0);
        Point l2 = new Point(5,5), r2 = new Point(15, 0);
        if (isOverlap(l1, r1, l2, r2))
            System.out.println("Rectangles Overlap");
        else {
            System.out.println("Rectangles Don't Overlap");
        }


    }
    //预处理, 不管是左上角+右下角 还是 左下角+右上角
//都统一为左上角+右上角
    public static boolean overlap(Point l1, Point r1, Point l2, Point r2) {
        int a = min(l1.x, r1.x);
        int b = max(l1.y, r1.y);
        int c = max(l1.x, r1.x);
        int d = min(l1.y, r1.y);
        int e = min(l2.x, r2.x);
        int f = max(l2.y, r2.y);
        int g = max(l2.x, r2.x);
        int h = min(l2.y, r2.y);
        if (d > f || h > b) return false;
        if (c < e || g < a) return false;
        return true;
    }
}
