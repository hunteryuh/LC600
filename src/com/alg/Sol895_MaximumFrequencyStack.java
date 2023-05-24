package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.

Implement the FreqStack class:

FreqStack() constructs an empty frequency stack.
void push(int val) pushes an integer val onto the top of the stack.
int pop() removes and returns the most frequent element in the stack.
If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.


Example 1:

Input
["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
Output
[null, null, null, null, null, null, null, 5, 7, 5, 4]

Explanation
FreqStack freqStack = new FreqStack();
freqStack.push(5); // The stack is [5]
freqStack.push(7); // The stack is [5,7]
freqStack.push(5); // The stack is [5,7,5]
freqStack.push(7); // The stack is [5,7,5,7]
freqStack.push(4); // The stack is [5,7,5,7,4]
freqStack.push(5); // The stack is [5,7,5,7,4,5]
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].


Constraints:

0 <= val <= 109
At most 2 * 104 calls will be made to push and pop.
It is guaranteed that there will be at least one element in the stack before calling pop.
 */
public class Sol895_MaximumFrequencyStack {
    class FreqStack {

        Map<Integer, Integer> freqMap;
        int maxFreq;
        Map<Integer, Stack<Integer>> freqToItem;

        public FreqStack() {
            freqMap = new HashMap<>();
            maxFreq = 0;
            freqToItem = new HashMap<>();
        }

        public void push(int val) {
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
            int freq = freqMap.get(val);
            maxFreq = Math.max(maxFreq, freq);

            if (!freqToItem.containsKey(freq)) {
                freqToItem.put(freq, new Stack<>());
            }
            freqToItem.get(freq).push(val);
//            freqToItem.computeIfAbsent(freq, x -> new Stack<>()).push(val); //for above 3 lines
        }

        public int pop() {
            int val = freqToItem.get(maxFreq).pop();
            freqMap.put(val, freqMap.get(val) - 1);
//            if (freqMap.get(val) == 0) freqMap.remove(val);
            /*
            I/P: [5,7,5,7,5,7,5,7,2,2,4], pop result: 7 5 7 5 2 7 5 4 2 7 5.
7 appears 4 times
5 appears 4 times
2 appears 2 times
4 appears 1 time

So, the hashmap:
    m(4) has value (7,5)
    m(3) has value (7,5)
    m(2) has value (7,5,2)
    m(1) has value (7,5,2)

Hence if (m.get(maxfreq).size() == 0) maxfreq--;
             */
            if (freqToItem.get(maxFreq).size() == 0) {
                maxFreq--;
            }
            return val;
        }
    }

    // https://leetcode.com/problems/maximum-frequency-stack/discuss/163453/JAVA-O(1)-solution-easy-understand-using-bucket-sort
    // list of stack, similar to map of int to stack
    class FreqStack2 {

        List<Stack<Integer>> bucket;
        HashMap<Integer, Integer> map; // item-frequency map

        public FreqStack2() {
            bucket = new ArrayList<>();
            map = new HashMap<>();
        }

        public void push(int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            int freq = map.get(x);
            if (freq - 1 >= bucket.size()) {
                bucket.add(new Stack<Integer>());
            }
            bucket.get(freq - 1).add(x);
        }

        public int pop() {
            int freq = bucket.size();
            int x = bucket.get(freq - 1).pop();
            if (bucket.get(freq - 1).isEmpty()) bucket.remove(bucket.size() - 1);

            map.put(x, map.get(x) - 1);
            if (map.get(x) == 0) map.remove(x);

            return x;
        }
    }

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
}
