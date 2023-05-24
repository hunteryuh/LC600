package com.alg;
/*
Implement a SnapshotArray that supports the following interface:

    SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
    void set(index, val) sets the element at the given index to be equal to val.
    int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
    int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id



Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation:
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5



Constraints:

    1 <= length <= 5 * 104
    0 <= index < length
    0 <= val <= 109
    0 <= snap_id < (the total number of times we call snap())
    At most 5 * 104 calls will be made to set, snap, and get.


 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
public class Sol1146_SnapShotArray {
    class SnapshotArray {
        List<TreeMap<Integer, Integer>> list;
        int snapId = 0;
        // time O(n)
        public SnapshotArray(int length) {
            list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                list.add(new TreeMap<>());
                list.get(i).put(0, 0);
            }
        }
        // time O(log S)
        public void set(int index, int val) {
            list.get(index).put(snapId, val);
        }

        // time O(1)
        public int snap() {
            return snapId++;  // snapId++; return snapId - 1;
        }

        /*
        Gets the entry corresponding to the specified key; if no such entry exists,
        returns the entry for the greatest key less than the specified key; if no such entry exists, returns null.
         */
        // time O(logS), binary search
        public int get(int index, int snap_id) {
            return list.get(index).floorEntry(snap_id).getValue();
        }
    }
}
