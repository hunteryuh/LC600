package com.alg;

import java.util.*;

/**
 * Created by HAU on 12/27/2017.
 */
/* Amazon warehouse: 给卡车载重M 以及一系列地点(类似 a[x][y] = z，（x， y）为以卡车为原点的坐标
z为该点货物重量) 输出距离最近的 N个点的坐标 */
public class Sol0_amz_truckTopK {
    public static List<List<Integer>> trucktopK(List<List<Integer>> input, int m, int n){
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(n, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0)*o1.get(0)+ o1.get(1)*o1.get(1)-o2.get(0)*o2.get(0)-o2.get(1)*o2.get(1);
            }
        });
        for(List<Integer> list : input){
            pq.add(list);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){ //     for(int i = 0;i < m && i < n;i++){
            res.add(pq.remove());

        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> d1 = new ArrayList<>(Arrays.asList(3,2));
        List<Integer> d2 = new ArrayList<>(Arrays.asList(5,6));
        List<Integer> d3 = new ArrayList<>(Arrays.asList(1,4));
        List<List<Integer>> dlist = new ArrayList<>();
        dlist.add(d1);
        dlist.add(d2);
        dlist.add(d3);

        System.out.println(trucktopK(dlist,4,2));

    }
}
