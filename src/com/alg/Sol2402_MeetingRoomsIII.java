package com.alg;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
You are given an integer n. There are n rooms numbered from 0 to n - 1.

You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.

Meetings are allocated to rooms in the following manner:

    Each meeting will take place in the unused room with the lowest number.
    If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
    When a room becomes unused, meetings that have an earlier original start time should be given the room.

Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.

A half-closed interval [a, b) is the interval between a and b including a and not including b.



Example 1:

Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
Output: 0
Explanation:
- At time 0, both rooms are not being used. The first meeting starts in room 0.
- At time 1, only room 1 is not being used. The second meeting starts in room 1.
- At time 2, both rooms are being used. The third meeting is delayed.
- At time 3, both rooms are being used. The fourth meeting is delayed.
- At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
- At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
Both rooms 0 and 1 held 2 meetings, so we return 0.

Example 2:

Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
Output: 1
Explanation:
- At time 1, all three rooms are not being used. The first meeting starts in room 0.
- At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
- At time 3, only room 2 is not being used. The third meeting starts in room 2.
- At time 4, all three rooms are being used. The fourth meeting is delayed.
- At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
- At time 6, all three rooms are being used. The fifth meeting is delayed.
- At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.



Constraints:

    1 <= n <= 100
    1 <= meetings.length <= 105
    meetings[i].length == 2
    0 <= starti < endi <= 5 * 105
    All the values of starti are unique.
https://leetcode.com/problems/meeting-rooms-iii/

 */
public class Sol2402_MeetingRoomsIII {
    public int mostBooked(int n, int[][] meetings) {
        // https://leetcode.com/problems/meeting-rooms-iii/solutions/2527347/java-o-nlogn-heaps-and-sort-with-comments/
        /*

    Sort the meetings based on start_time
    Create a MIN priority queue for engaged rooms. Each node of PQ will store {current_meeting_ending_time, room_number}
    Create a MIN priority queue for non-engaged rooms. Each node of PQ will store {room_number}
    Currently all the rooms are empty, so push them in non-engaged rooms PQ.
    For each of the meeting, follow these steps:

    Move the meeting rooms in engaged, with ending_time <= s, to unused
    If there are multiple empty rooms, choose the one with lower room_number (Can be directly fetched from non-engaged rooms PQ)
    If there are no empty rooms, wait for the engaged room with nearest ending time to empty (Topmost node of engaged PQ)
    ** If we are using an engaged room to carry out the meeting, starting_time for this meeting will change and since duration has to be the same, the newEndingTime will be sum(end_time_of_the_prev_meeting, duration)
    Mark the current_room as engaged.
    Increment the count in frequency map for the current_room

    Find the room with maximum meetings from frequency map.

         */
        // sort by meeting start
        Arrays.sort(meetings, (a, b) -> (a[0] - b[0]));
        // available rooms
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.add(i);
        }
        // [(meeting endtime, room taken)]
        PriorityQueue<int[]> runningMeetings = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        // room usage count
        int[] count = new int[n];
        for (int[] meeting : meetings) {
            // return room to available if meeting has ended
            // /* Move the meeting rooms in engaged, with ending_time <= s, to unused *
            while (!runningMeetings.isEmpty() && runningMeetings.peek()[0] <= meeting[0]) {
//                rooms.offer(runningMeetings.poll()[1]);
                availableRooms.offer(runningMeetings.peek()[1]);
                runningMeetings.poll();
            }
            int delayedStart = meeting[0];
            // if no available room, adjust the next meeting start time with delay
            if (availableRooms.isEmpty()) {
                int[] await = runningMeetings.poll();
                delayedStart = await[0];
                availableRooms.offer(await[1]);
            }
            // schedule the next meeting
            int room = availableRooms.poll();
            count[room]++;
            ///* Mark the room as used/ engaged */
            runningMeetings.offer(new int[]{delayedStart + meeting[1] - meeting[0], room});
        }
        //    Find the lowest room_number with maximum frequency */
        int max = 0;
        for (int r = 0; r < n; r++) {
            if (count[r] > count[max]) {
                max = r;
            }
        }
        return max;
    }
}
