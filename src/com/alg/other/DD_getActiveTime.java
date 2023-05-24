package com.alg.other;

import java.util.ArrayList;
import java.util.List;

/*
Given a sequence of timestamps & actions of a dasher's activity within a day, we would like to know the active time of the dasher. Idle time is defined as the dasher has NO delivery at hand. (That means all items have been dropped off at this moment and the dasher is just waiting for another pickup) Active time equals total time minus idle time. Below is an example. Dropoff can only happen after pickup. 12:00am means midnight and 12:00pm means noon. All the time is within a day.

Timestamp(12h) | Action
8:30am | pickup
9:10am | dropoff
10:20am| pickup
12:15pm| pickup
12:45pm| dropoff
2:25pm | dropoff

total time = 2:25pm-8:30am = 355 mins;
idle time = 10:20am-9:10am = 70 mins;
active time = total time-idle time = 355-70 = 285 mins;
 */
public class DD_getActiveTime {
    private int getActiveTimes(List<String> activity) {
        int timer = 0;
        int startTime = 0;
        int endTime = 0;
        int result = 0;
        for (String currentActivity : activity) {
            String[] split = currentActivity.split("\\s+");
            String timing = split[0];
            String method = split[1];
            if(method.equals("pickup")) {
                timer++;
                if(timer == 1) {
                    startTime = getMinutes(timing);
                }
            } else {
                timer--;
                if (timer == 0) {
                    endTime = getMinutes(timing);
                    result += endTime - startTime;
                }
            }
//            if(timer == 0) {
//                result += endTime - startTime;
//            }
        }
        return result;
    }

    private static int getMinutes(String timing) {
        String split[] = timing.split(":");
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1].substring(0, split[1].length() - 2));
        if (timing.endsWith("am") || (timing.endsWith("pm") && hours == 12)) {
            return hours * 60 + minutes;
        } else {
            return (hours + 12) * 60 + minutes;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<String> activity = new ArrayList<>();
        activity.add("8:30am pickup");
        activity.add("9:10am dropoff");
        activity.add("10:20am pickup");
        activity.add("12:15pm pickup");
        activity.add("12:45pm dropoff");
        activity.add("2:25pm dropoff");

        DD_getActiveTime dd_getActiveTime = new DD_getActiveTime();
        System.out.println(dd_getActiveTime.getActiveTimes(activity));
    }
}
