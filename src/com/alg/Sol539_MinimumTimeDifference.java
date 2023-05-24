package com.alg;

import java.util.List;

/*Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1*/
public class Sol539_MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
//There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element stands for if we see that time point or not. Then things become simple...
        boolean[] mark = new boolean[24*60];
        for(String time: timePoints){
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if( mark[h*60+m]) return 0;
            mark[h*60 + m] = true;
        }

        int prev = 0;
        int min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for(int i = 0; i < 24* 60; i++){
            if(mark[i]){
                if(first != Integer.MAX_VALUE){
                    min = Math.min(min, i - prev);
                }
                first = Math.min( first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        /*一种O(n)时间复杂度的方法，由于时间点并不是无穷多个，而是只有1440个，
        所以我们建立一个大小为1440的数组来标记某个时间点是否出现过，如果之前已经出现过，
        说明有两个相同的时间点，直接返回0即可；若没有，将当前时间点标记为出现过。我们还需要
        一些辅助变量，pre表示之前遍历到的时间点，first表示按顺序排的第一个时间点，last表示按顺序排的最后一个时间点，然后我们再遍历这个mask数组，如果当前时间点出现过，再看如果first不为初始值的话，说明pre已经被更新过了，
        我们用当前时间点减去pre来更新结果res，然后再分别更新first，last，和pre即可，
        数组在loop的时候 已经是最小到大在找了，所以有pre时，已经是相邻的时间在相减了*/
        min = Math.min(min, 24*60 - last + first);
        return min;
    }
}
