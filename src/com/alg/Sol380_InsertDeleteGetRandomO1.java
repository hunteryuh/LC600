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
            map.put(val,nums.size());
            nums.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean contains = map.containsKey(val);
            if( !contains) return false;
            int pos = map.get(val);
            if (pos < nums.size() - 1) { // if not the last one, then swap the last one with this val
                int last = nums.get(nums.size() - 1);
                nums.set(pos, last);  // set the element at "pos" position to be last element in the list
                map.put(last, pos); // put the last element in the list with the pos position
            }
            map.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
