package com.alg;

import java.util.*;

/*
Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

    TimeMap() Initializes the object of the data structure.
    void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
    String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".



Example 1:

Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"



Constraints:

    1 <= key.length, value.length <= 100
    key and value consist of lowercase English letters and digits.
    1 <= timestamp <= 107
    All the timestamps timestamp of set are strictly increasing.
    At most 2 * 105 calls will be made to set and get.
https://leetcode.com/problems/time-based-key-value-store/

 */
public class Sol981_TimeBasedKeyValueStore {
    class TimeMap {

        private Map<String, TreeMap<Integer, String>> map;
        public TimeMap() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.putIfAbsent(key, new TreeMap<>());
            map.get(key).put(timestamp, value);
        }

        // floorKey, 小一点点， inclusive
        // ceilingKey, 大一点点， inclusive
        // higherKey  大一点点, EXCLUSIVE
        // lowerKey 小一点点， EXCLUSIVE
        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            TreeMap<Integer, String> treeMap = map.get(key);
            Integer t = treeMap.floorKey(timestamp);
            if (t == null) return "";
            return treeMap.get(t);
        }



    }

    // approach 2 with vanilla binary search

    class TimeMap2 {

        class Data {
            String value;
            int timestamp;
            Data(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }
        private Map<String, List<Data>> map;

        public TimeMap2() {
            this.map = new HashMap<>();
        }
        public void set(String key, String value, int timestamp) {
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(new Data(value, timestamp));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            List<Data> list = map.get(key);
            int low = 0;
            int high = list.size() - 1;
            while (low + 1 < high) {
                int mid = low + (high - low ) / 2;
                Data data = list.get(mid);
                if (data.timestamp == timestamp) {
                    return data.value;
                } else if (data.timestamp < timestamp) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
            if (list.get(high).timestamp <= timestamp) {
                return list.get(high).value;
            }
            if (list.get(low).timestamp <= timestamp) {
                return list.get(low).value;
            }
            return "";
        }
    }


/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
}
