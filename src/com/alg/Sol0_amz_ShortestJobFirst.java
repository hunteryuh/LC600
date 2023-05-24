package com.alg;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by HAU on 1/2/2018.
 */
/*一个处理器要处理一堆request，一次只能处理一条，如果它有几个积压着的requests，它会先执行持续时间短的那个；
对于持续时间相等的requests，先执行最早到达处理器的request。
问平均每个request要等多久才能被处理。input：requestTimes[]，每个request到达处理器的时间;
durations[] 每个request要处理的持续时间。 两个数组是一一对应的，并已按requestTimes[] 从小到大排序过

public double CalWaitingTime(int requestTimes[], int[] durations[]){}

用priorityqueue做，地里有一个两层循环的答案，没仔细看，
做完round robin以后发现思路很相似。注意用priorityqueue写comparator的时候，
要先判断两者的execute time,如果execute time相同，则返回arrival time之差，即先按执行时间排序，
若执行时间相同则按到达的时间排。*/
public class Sol0_amz_ShortestJobFirst {
    static class MyProcess{
        int arrTime;
        int exeTime;
        MyProcess(int arr, int exe){
            arrTime = arr; // process arrival time
            exeTime = exe; //process, time to take to finish the execution
        }
    }
    public static float ShortestJobFirst(int[] req, int[] dur){
        if ( req == null || dur == null || dur.length != req.length) return 0;
        int len = req.length;
        int index = 0;
        int waitTime = 0;
        int curTime = 0;
        PriorityQueue<MyProcess> pq = new PriorityQueue<>(new Comparator<MyProcess>() {
            @Override
            public int compare(MyProcess o1, MyProcess o2) {
                return o1.exeTime == o2.exeTime? o1.arrTime - o2.arrTime : o1.exeTime-o2.exeTime;
            }
        });

        while(!pq.isEmpty() || index < len){
            if(!pq.isEmpty()){
                MyProcess cur = pq.poll();
                waitTime += curTime - cur.arrTime;
                curTime += cur.exeTime;
                while( index < len && curTime >= req[index]){
                    pq.offer(new MyProcess(req[index],dur[index]));
                    index++;
                }
            }else{
                pq.offer(new MyProcess(req[index],dur[index]));
                curTime = req[index++];
            }
        }
        // while style 2
       /* while(!pq.isEmpty() || index < len){
            if(pq.isEmpty()){
                pq.offer(new MyProcess(req[index],dur[index]));
                curTime = req[index++];
            }else{
                // execute current request
                MyProcess curr = pq.poll();
                curTime += curr.exeTime;
                waitTime += curTime - curr.arrTime;
                // add waiting process to the list (queue)
                while( index < len && curTime > req[index]){
                    pq.add(new MyProcess(req[index],dur[index]));
                    index++;
                }
            }
        }*/
        return (float) waitTime/len;
    }
}
