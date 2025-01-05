package com.alg.other;

import java.util.*;

/*
https://leetcode.com/discuss/interview-question/924141/google-phone-screen-new-grad
You have a stream of rpc requests coming in.
Each log is of the form {id, timestamp, type(start/end)}. Given a timeout T, you need to figure out at the earliest possible time if a request has timed out.
Eg :
id - time - type
0 - 0 - Start
1 - 1 - Start
0 - 2 - End
2 - 6 - Start
1 - 7 - End
Timeout = 3
Ans : {1, 6} ( figured out id 1 had timed out at time 6 )
 */
public class GG_DetectRPCTimeout {
    private static class Log {
        int id;
        int startTime;
        String type;

        public Log(int id, int startTime, String type) {
            this.id = id;
            this.startTime = startTime;
            this.type = type;
        }
    }



    public static int[] findEarliestTimeoutEntry(List<Log> logs, int timeout) {
        Map<Integer, Integer> idWithStartTimeMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[2];
        Arrays.fill(result, -1);

        for (Log log : logs) {
            int id = log.id;
            int currentTime = log.startTime;

            if ("Start".equals(log.type)) {
                queue.offer(id);
                idWithStartTimeMap.put(id, currentTime);
            } else {
                // end type
                if (idWithStartTimeMap.containsKey(id)) {
                    if (idWithStartTimeMap.get(id) + timeout < currentTime) {
                        return new int[]{id, currentTime};
                    }
                    idWithStartTimeMap.remove(id);
                }
            }
            while (!queue.isEmpty()) {
                int earliestId = queue.peek();
                if (idWithStartTimeMap.containsKey(id)) {
                    if (idWithStartTimeMap.get(id) + timeout < currentTime) {
                        return new int[]{earliestId, currentTime};
                    }
                }
                queue.poll();
            }
        }

        return result;
    }

}

