package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by HAU on 11/18/2017.
 */
public class Sol380_InsertDeleteGetRandomO1 {
    class RandomizedSet {
        //solution using a HashMap and an arraylist
        /** Initialize your data structure here. */
        ArrayList<Integer> nums;
        HashMap<Integer,Integer> map;
        java.util.Random random = new java.util.Random();
        public RandomizedSet() {
            nums = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contains = map.containsKey(val);
            if (contains) return false;
            map.put(val,nums.size());  // insert O(1) in map(with no collision) ( linkedlist)
            nums.add(val);  // linkedlist add one element in the end, O(1)
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean contains = map.containsKey(val);
            if( !contains) return false;
            int pos = map.get(val);
            if (pos < nums.size() - 1) { // if not the last one, then swap the last one with this val
                int last = nums.get(nums.size() - 1);  // arraylist get() O(1), basically a value in an array
                nums.set(pos, last);  // set the element at "pos" position to be last element in the list , set like assign value in an array element
                map.put(last, pos); // put the last element in the list with the pos position
            }
            map.remove(val);  // hashmap remove() It is O(1) only when removing the last element by index.
            // O(1); map remove(obj)
            //O(1+k/n) where k is the no. of collision elements
            // added to the same LinkedList (k elements had same hashCode)
            nums.remove(nums.size() - 1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
