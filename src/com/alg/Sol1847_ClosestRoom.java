package com.alg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/*
There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei. Each roomIdi is guaranteed to be unique.

You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej]. The answer to the jth query is the room number id of a room such that:

The room has a size of at least minSizej, and
abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
If there is a tie in the absolute difference, then use the room with the smallest such id. If there is no such room, the answer is -1.

Return an array answer of length k where answer[j] contains the answer to the jth query.



Example 1:

Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
Output: [3,-1,3]
Explanation: The answers to the queries are as follows:
Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
Example 2:

Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
Output: [2,1,3]
Explanation: The answers to the queries are as follows:
Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.
 */
public class Sol1847_ClosestRoom {
    public static int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        int k = queries.length;
        Integer[] queryIndex = new Integer[k];
        for (int i = 0; i < k; i++) {
            queryIndex[i] = i;
        }

//        Arrays.sort(rooms, (a, b) -> Integer.compare(b[1], a[1])); //Sort by decreasing order of room size
//        Arrays.sort(queryIndex, (a, b) -> Integer.compare(queries[b][1], queries[a][1])); // Sort by decreasing order of query minSize
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);  //Sort by decreasing order of room size nlogn
        Arrays.sort(queryIndex, (a, b) -> queries[b][1] - queries[a][1]); // Sort by decreasing order of query minSize, klogk
        TreeSet<Integer> roomIds = new TreeSet<>();
        int[] result = new int[k];
        int j = 0;
        for (int i = 0; i < k; i++) {  //k
            int curQuery = queryIndex[i];
            while (j < n && rooms[j][1] >= queries[curQuery][1]) { //n
                roomIds.add(rooms[j][0]);   //logn    nlogn  just 1 time
                j++;
//                if (j == n) {  // not work as j is not reset after one while loop of each i
//                    break;
//                }
            }
            result[curQuery] = findClosestNumber(roomIds, queries[curQuery][0]);
        }

        return result;

    }

    private static int findClosestNumber(TreeSet<Integer> roomIds, int preferred) {  //logn
        Integer floor = roomIds.floor(preferred);
        Integer ceiling = roomIds.ceiling(preferred);
        int ans;
        if (floor == null && ceiling == null) {
            ans = -1;
        } else if (floor == null) {
            ans = ceiling;
        } else if (ceiling == null) {
            ans = floor;
        } else {
            ans = Math.abs(floor - preferred) <= Math.abs(ceiling - preferred) ? floor : ceiling;  // deal with tie
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("pen", "blue", "appp", "atom", "to");


//        words.sort(Comparator.comparing( e -> e.length()));
        // if same, then the order is kept by original index, the item with smaller index ordered first no matter
        // what the "natural order" is

//        words.stream().sorted(Comparator.comparing(String::length).thenComparing(e -> e.charAt(0)));
        //pen
        //appp
        //blue
        //atom
        //to
        List<String> result = words.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());


        result.forEach(System.out::println);

        int[][] rooms = { {2,2}, {1,2}, {3,2} };
        int[][] queries = { {3,1}, {11,3}, {5,2}, {3,3}};

        int[][] rooms2 = { {1,4}, {2,3}, {3,5}, {4,1}, {5,2} };
        int[][] queries2 = { {2,3}, {2,4}, {2,5}};

        int[] res = closestRoom(rooms, queries);
        System.out.println(Arrays.toString(res));

        int[] res2 = closestRoom(rooms2, queries2);
        System.out.println(Arrays.toString(res2));
    }


}
