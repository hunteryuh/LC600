package com.alg;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Created by HAU on 1/10/2018.
 */
/*k <= 0 return new Point[0]是一个test case
k > size算是返回原数组，但是里面的points要sort一边，
距离最近的点是第一个，第二近的是 第二个and so on
给定⼀一些 points 和⼀一个 origin，从 points 中找到 k 个离 origin 最近的点。按照距离由
⼩小到⼤大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值
排序。
*/
public class Sol0_amz_KClosestPoints {
    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
        public static Point[] kClosest(Point[] points, Point origin, int k){
            Point[] res = new Point[k];
            int index = 0;
            // // 大顶堆 , the largest element is on the top of the queue ( first to be polled)
            PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                     int diff = getDist(o2,origin) - getDist(o1,origin);
                     if (diff == 0){
                         diff = o2.x - o1.x;
                         if ( diff == 0){
                             diff = o2.y - o1.y;
                         }
                     }
                     return diff;
                }
            });
            for( int i = 0; i < points.length; i++){
                pq.add(points[i]);
                if ( pq.size() > k){
                    pq.poll();

                }
            }
 /*           while( !pq.isEmpty()){
                res[index++] = pq.poll();
            }*/


            for (int i = k - 1; i >= 0; i--) {
                res[i] = pq.poll();  //res[0] the closest point
            }

            return res;
        }
        private static int getDist(Point a, Point b){
            return (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y);
        }

    public static void main(String[] args) {
        Point p1 = new Point(4,6);
        Point p2 = new Point(4,7);
        Point p3 = new Point(4,4);
        Point p4 = new Point(2,5);
        Point p5 = new Point(0,1);
        Point[] points = {p1,p2,p3,p4,p5};
        Point origin = new Point();
        int k = 3;
        Point[] res = kClosest(points,origin,k);
        for(Point p: res){
            System.out.println(p.x + ", "+ p.y);
            System.out.println();
        }

    }
}
