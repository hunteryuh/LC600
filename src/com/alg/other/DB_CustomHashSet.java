package com.alg.other;

import java.util.*;

/*
Create a 'custom set' class that has the methods like add(),remove(),contains(),iterator() like a regular set,
but we should be able to modify the set while it is being iterated.
And the iterator should only contain the elements when it was called.

https://www.1point3acres.com/bbs/thread-888998-2-1.html
databricks phone interview
https://leetcode.com/discuss/interview-question/1807986/databricks-phone-custom-hashset
 */
public class DB_CustomHashSet {
    static class CustomSet {

        private Set<Integer> committed = new HashSet<>();
        private Map<Integer, Boolean> pending = null;

        public boolean add(int i){
            if (pending == null) {
                return committed.add(i);
            } else {
                pending.put(i, true);
                return true;
            }
        }

        public boolean remove(int i){
            if (pending == null) {
                return committed.remove(i);
            } else {
                pending.put(i, false);
                return true;
            }
        }

        public boolean contains(int i){
            if (pending == null) {
                return committed.contains(i);
            } else {
                // first check pending, if empty then fall back to committed
                return pending.getOrDefault(i, committed.contains(i));
            }
        }

        // iterator() should return a snapshot of the elements in the collection
        // at the time the method was called.
        public Iterator<Integer> iterator(){
            if (pending == null) {
                pending = new HashMap<>();
            }
            for (Integer i : pending.keySet()) {
                if (pending.get(i)) {
                    committed.add(i);
                } else {
                    committed.remove(i);
                }
            }
            return committed.iterator();
        }

        public void printAll() {
            //print all elements using the iterator
            Iterator<Integer> it = iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
        CustomSet customSet = new CustomSet();
        customSet.add(1);
        customSet.add(2);
        Iterator<Integer> it1 = customSet.iterator();
        System.out.println(it1.next());  // 1
        customSet.add(3);
        customSet.remove(2);
        System.out.println(it1.next()); // 2
        System.out.println(it1.hasNext()); // false
        Iterator<Integer> it2 = customSet.iterator();
        customSet.printAll(); // 1, 3
    }


}
