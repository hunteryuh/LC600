package com.alg;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by HAU on 1/5/2018.
 */
/*What we want is to compute the average of the five highest score of each student.
* 我们可以用Heap来解决这个问题，要求最大的5个，那么我们可以维护一个大小为5的最小堆。每次遇到一个新值，如果heap未到容量，那么一律加入；如果到达容量，那么只有当前值比peek()更大时，才删除peek()并加入新值。*/
public class Sol0_amz_HighFive {
    static class Result{
        int id;
        int value;
        public Result(int id, int value){
            this.id = id;
            this.value = value;
        }
    }
    public static Map<Integer,Double> getHighFive(Result[] src){
        Map<Integer,PriorityQueue<Integer>> map = new HashMap<>();
        Map<Integer,Double> res = new HashMap<>();
        final int Cap = 5;
        for ( Result item: src){
            int key = item.id;
            int val = item.value;
            if(!map.containsKey(key)){
                map.put(key, new PriorityQueue<>());
            }
            PriorityQueue<Integer> pq = map.get(key);
            /*
            优先级队列是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素。
            如果不提供Comparator的话，优先队列中元素默认按自然顺序排列，
            也就是数字默认是小的在队列头，字符串则按字典序排列。*/
            if(pq.size() < Cap){
                pq.add(val);
            }else if( val > pq.peek()){ // peek出最小值
                pq.poll();
                pq.offer(val);
            }
        }

        for(Integer key: map.keySet()){
            PriorityQueue<Integer> pq = map.get(key);
            int sum = 0;
            while (!pq.isEmpty()){
                sum += pq.poll();
            }
            res.put(key,sum/5.0);
        }
        return res;
    }
    public static void main(String[] args) {
        Result r1 = new Result(1, 100);
        Result r2 = new Result(1, 90);
        Result r3 = new Result(1, 40);
        Result r4 = new Result(1, 10);
        Result r5 = new Result(1, 30);
        Result r6 = new Result(1, 60);

        Result r7 = new Result(2, 6);
        Result r8 = new Result(2, 6);
        Result r9 = new Result(2, 7);
        Result r10 = new Result(2, 6);
        Result r11 = new Result(2, 6);

        Result[] src = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11};

        Map<Integer, Double> ans = getHighFive(src);

        for (int key: ans.keySet()) {
            System.out.println("id: " + key + ", value: " + ans.get(key));
        }
    }

}
