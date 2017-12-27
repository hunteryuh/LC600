package com.alg;

import java.awt.*;

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
}
