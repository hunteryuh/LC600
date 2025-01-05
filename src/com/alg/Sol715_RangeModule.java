package com.alg;
/*
A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.

A half-open interval [left, right) denotes all the real numbers x where left <= x < right.

Implement the RangeModule class:

    RangeModule() Initializes the object of the data structure.
    void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
    boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
    void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).



Example 1:

Input
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
Output
[null, null, null, true, false, true]

Explanation
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)



Constraints:

    1 <= left < right <= 109
    At most 104 calls will be made to addRange, queryRange, and removeRange.


 */


import java.util.Map;
import java.util.TreeMap;

public class Sol715_RangeModule {
    class RangeModule {
/*
	floorKey(K key)
Returns the greatest key less than or equal to the given key, or null if there is no such key.
floorEntry(K key)
Returns a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.
subMap(K fromKey, K toKey)
Returns a view of the portion of this map whose keys range from fromKey, inclusive, to toKey, exclusive.
 	subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)
Returns a view of the portion of this map whose keys range from fromKey to toKey.
 */
        TreeMap<Integer, Integer> map = new TreeMap<>();

        public RangeModule() {

        }

        // https://leetcode.com/problems/range-module/solutions/108910/java-treemap/
        public void addRange(int left, int right) {
            // find overlap ranges, calc merged range, clear overlapped ranges, insert merged range
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (start != null && map.get(start) >= left) {
                left = start; // update overlap start
            }
            if (end != null && map.get(end) > right) {
                right = map.get(end); // update overlap end
            }
            map.put(left, right);
            map.subMap(left, false, right, true).clear();

        }

        public boolean queryRange(int left, int right) {
            Integer start = map.floorKey(left);
            if (start == null) return false;
            return map.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            Integer start = map.floorKey(left); // left possible overlap entry
            Integer end = map.floorKey(right); // right possible overlap entry
            if (end != null && map.get(end) > right) {
                map.put(right, map.get(end));  // after removal, if anything left
            }

            if (start != null && map.get(start) > left) {
                map.put(start, left); // after removal, if anything left
            }
            map.subMap(left, true, right, false).clear();

        }
    }

    /**
     * Your RangeModule object will be instantiated and called as such:
     * RangeModule obj = new RangeModule();
     * obj.addRange(left,right);
     * boolean param_2 = obj.queryRange(left,right);
     * obj.removeRange(left,right);
     */

}
