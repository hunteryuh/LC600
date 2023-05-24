package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 Implement the RandomizedSet class:

 RandomizedSet() Initializes the RandomizedSet object.
 bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.

 You must implement the functions of the class such that each function works in average O(1) time complexity.



 Example 1:

 Input
 ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 [[], [1], [2], [2], [], [1], [2], []]
 Output
 [null, true, false, true, 2, true, false, 2]

 Explanation
 RandomizedSet randomizedSet = new RandomizedSet();
 randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 randomizedSet.insert(2); // 2 was already in the set, so return false.
 randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.



 Constraints:

 -231 <= val <= 231 - 1
 At most 2 * 105 calls will be made to insert, remove, and getRandom.
 There will be at least one element in the data structure when getRandom is called.

IXL interview on Dec 20, 2022. Follow up: support duplicate elements

 /*
 Data Structure Design: Insert/Delete/GetRandom in O(1)
 The candidate should implement a data structure with three public methods insert(int x), delete(int x), and getRandom()
 The time complexity for the three above methods should be O(1)
 The inputs will be int values, and no duplicate value will be used in the inputs
 */

public class Sol380_InsertDeleteGetRandomO1 {
    static class RandomizedSet {
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
            map.put(val, nums.size());  // insert O(1) in map(with no collision) (linkedlist)
            nums.add(val);  // linkedlist add one element in the end, O(1)
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean contains = map.containsKey(val);
            if(!contains) return false;
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

    public static void main(String[] args) {
        Sol380_InsertDeleteGetRandomO1 s = new Sol380_InsertDeleteGetRandomO1();
        RandomizedSet rm = new RandomizedSet();
        rm.insert(2);
        rm.insert(31);
        System.out.println(rm.map);
        int a = rm.getRandom();
        rm.remove(4);
        rm.remove(31);
        System.out.println(a);
        System.out.println(rm.map);
    }
}
