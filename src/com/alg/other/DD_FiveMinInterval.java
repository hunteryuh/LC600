package com.alg.other;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Input - ("mon 10:00 am", mon 11:00 am)
Output - [11005, 11010, 11015...11100]
Output starts with 1 if the day is monday, 2 if tuesday and so on till 7 for sunday
Append 5 min interval times to that till the end time
So here it is 10:05 as first case, so its written as 11005
2nd is 10:10 so its written as 11010
...
...
Stop at 11100
 */
public class DD_FiveMinInterval {
    private List<String> getIntervals(String startTime, String endTime) {
        Map<String, Integer> map = new HashMap<>();
        map.put("mon", 1);
        map.put("tue", 2);
        map.put("wed", 3);
        map.put("thu", 4);
        map.put("fri", 5);
        map.put("sat", 6);
        map.put("sun", 7);
        int start = timeConvert(startTime, map);
        int end = timeConvert(endTime, map);
        List<Integer> intervals = new LinkedList<>();
        List<String> res = new LinkedList<>();
        int curTime = start+5;
        while (curTime < end) {
            intervals.add(curTime);
            curTime += 5;
        }
        for (int interval: intervals) {
            res.add(transform(interval));
        }
        //res = intervals.stream()
        // .mapToInt(interval -> interval)
        // .mapToObj(this::transform).
        // collect(Collectors.toCollection(LinkedList::new));
        return res;
    }

    private int timeConvert(String s, Map<String, Integer> map) {
        String[] timeArray = s.split(" ");
        String[] hourMins = timeArray[1].split(":");
        int hour = Integer.parseInt(hourMins[0]);
        int mins = Integer.parseInt(hourMins[1]);
        if (timeArray[2].equals("pm")) {
            hour += 12;
        }
        int time = map.get(timeArray[0]) * 24 * 60 + hour * 60 + mins;
        return time;
    }

    private String transform(int time) {
        int day = time/(24*60);
        int hour = (time%(24*60))/60;
        int mins = time%60;
        String hourStr = hour < 10? "0"+hour: ""+hour;
        String minsStr = mins < 10? "0"+mins: ""+mins;
        return day+ hourStr+ minsStr;
    }
}
