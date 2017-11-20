package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by HAU on 11/19/2017.
 */
public class Sol381_InsertDeleteGetRandomO1_duplicateAllowed {
    class RandomizedCollection {
        //solution using a HashMap, LinkedHashSet and an arraylist
        ArrayList<Integer> nums;
        HashMap<Integer,Set<Integer>> map;
        java.util.Random random = new java.util.Random();
        /** Initialize your data structure here. */
        public RandomizedCollection() {
            nums = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contains = map.containsKey(val);
            if(!contains) map.put(val, new LinkedHashSet<>());
            map.get(val).add(nums.size());
            nums.add(val);
            return !contains;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            boolean contains = map.containsKey(val);
            if (!contains) return false;
            int loc = map.get(val).iterator().next();
            map.get(val).remove(loc);
            if ( loc < nums.size() - 1) {// not the last one, swap
                int lastone = nums.get(nums.size() - 1);
                nums.set(loc, lastone);
                map.get(lastone).remove(nums.size() - 1);
                map.get(lastone).add(loc);
            }
            nums.remove(nums.size() - 1); // remove the lastone ( already swapped to loc position)
            if (map.get(val).isEmpty()){
                map.remove(val);
            }
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
