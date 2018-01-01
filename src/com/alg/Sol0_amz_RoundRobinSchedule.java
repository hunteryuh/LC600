package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 12/30/2017.
 */
/*一个处理器要处理一堆request，一次只能处理一条，每次执行一个任务最多执行时间q，接着执行等待着的下一个任务。若前一个任务没执行完则放到队尾，等待下一次执行

假设只要有任务开始以后cpu是不会空闲的，也就是说cpu开始后如果空闲了就说明没有任务了，另外Robin Round最后返回值是float*/
public class Sol0_amz_RoundRobinSchedule {
    static class MyProcess{
        int arrTime;
        int exeTime;
        MyProcess(int arr, int exe){
            arrTime = arr;
            exeTime = exe;
        }
    }
    public static float RRSchedule(int[] Atime, int[] Etime, int q){
        if ( Atime == null || Etime == null || Atime.length != Etime.length)
            return 0;
        int len = Atime.length;
        Queue<MyProcess> queue = new LinkedList<MyProcess>();
        int curTime = 0;
        int totalWaitTime = 0;
        int index = 0;

        while(!queue.isEmpty() || index < len){
            if(queue.isEmpty()){
                queue.offer(new MyProcess(Atime[index],Etime[index]));
                curTime = Atime[index++];
            }else{
                // deal with the current process
                MyProcess curps = queue.poll();
                totalWaitTime  += curTime - curps.arrTime;
                curTime += Math.min(q, curps.exeTime);
                //add those ps that come while busy into the queue
                while (index < len && Atime[index] <= curTime){
                    queue.offer(new MyProcess(Atime[index],Etime[index]));
                    index++;
                }
                // if current process is not finished yet, add it to the tail
                if(curps.exeTime > q){
                    queue.offer(new MyProcess(curTime,curps.exeTime - q));
                }
            }


        }
        return (float) totalWaitTime/len;

    }

    public static void main(String[] args) {
        int[] Atime = {0,1,4};
        int[] Etime = {5,2,3};
        System.out.println(RRSchedule(Atime, Etime, 3));
    }

}
