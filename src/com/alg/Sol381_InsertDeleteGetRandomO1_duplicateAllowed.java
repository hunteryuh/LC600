package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/*
RandomizedCollection is a data structure that contains a collection of numbers, possibly duplicates (i.e., a multiset). It should support inserting and removing specific elements and also reporting a random element.

Implement the RandomizedCollection class:

RandomizedCollection() Initializes the empty RandomizedCollection object.
bool insert(int val) Inserts an item val into the multiset, even if the item is already present. Returns true if the item is not present, false otherwise.
bool remove(int val) Removes an item val from the multiset if present. Returns true if the item is present, false otherwise. Note that if val has multiple occurrences in the multiset, we only remove one of them.
int getRandom() Returns a random element from the current multiset of elements. The probability of each element being returned is linearly related to the number of the same values the multiset contains.
You must implement the functions of the class such that each function works on average O(1) time complexity.

Note: The test cases are generated such that getRandom will only be called if there is at least one item in the RandomizedCollection.



Example 1:

Input
["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
[[], [1], [1], [2], [], [1], []]
Output
[null, true, false, true, 2, true, 1]

Explanation
RandomizedCollection randomizedCollection = new RandomizedCollection();
randomizedCollection.insert(1);   // return true since the collection does not contain 1.
                                  // Inserts 1 into the collection.
randomizedCollection.insert(1);   // return false since the collection contains 1.
                                  // Inserts another 1 into the collection. Collection now contains [1,1].
randomizedCollection.insert(2);   // return true since the collection does not contain 2.
                                  // Inserts 2 into the collection. Collection now contains [1,1,2].
randomizedCollection.getRandom(); // getRandom should:
                                  // - return 1 with probability 2/3, or
                                  // - return 2 with probability 1/3.
randomizedCollection.remove(1);   // return true since the collection contains 1.
                                  // Removes 1 from the collection. Collection now contains [1,2].
randomizedCollection.getRandom(); // getRandom should return 1 or 2, both equally likely.


Constraints:

-231 <= val <= 231 - 1
At most 2 * 105 calls in total will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
 */
public class Sol381_InsertDeleteGetRandomO1_duplicateAllowed {
    class RandomizedCollection {
        //solution using a HashMap, LinkedHashSet and an arraylist
        ArrayList<Integer> nums;
        HashMap<Integer, Set<Integer>> map;
        java.util.Random random = new java.util.Random();
        /** Initialize your data structure here. */
        public RandomizedCollection() {
            nums = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the collection.
         * Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contains = map.containsKey(val);
            if(!contains) map.put(val, new LinkedHashSet<>());
            map.get(val).add(nums.size());
            nums.add(val);
            return !contains;
        }

        /** Removes a value from the collection.
         * Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            boolean contains = map.containsKey(val);
            if (!contains) return false;
            int loc = map.get(val).iterator().next(); // LinkedHashSet guarantees O(1) for idx.get(val).iterator().next().
            map.get(val).remove(loc);
            if (loc < nums.size() - 1) { // not the last one, swap
                int lastone = nums.get(nums.size() - 1);
                nums.set(loc, lastone);
                map.get(lastone).remove(nums.size() - 1);
                map.get(lastone).add(loc);
            }
            nums.remove(nums.size() - 1); // remove the lastone ( already swapped to loc position)
            if (map.get(val).isEmpty()) {
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
