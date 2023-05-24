package com.alg.other;

import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
rippling: https://www.1point3acres.com/bbs/thread-919110-1-1.html

Things to consider:
what happens if all IDs used up? throws Exception or return -1
release invalid ID or release id that is not allocated, how to handle? just return or throw Exception??
what happens if all IDs used up? throws Exception or return -1
release invalid ID or release id that is not allocated, how to handle? just return or throw Exception??

FreeList approach:
Space is O(n), heavy, because of the data structure of queue and set too
O(1) time in allocate and release
 */
// Create a class that can allocate and release ids.
//https://massivealgorithms.blogspot.com/2016/03/dropbox-interview-misc.html
public class Sol_IdAllocator {
    private Queue<Integer> queue;
    private Set<Integer> allocatedSet;
    private final int MAX_ID;

    // time O(1) for both allocate and release
    // space O(n)
    public Sol_IdAllocator(int maxId){
        this.MAX_ID = maxId;
        this.queue = new LinkedList<>();
        this.allocatedSet = new HashSet<>();
        for (int i = 0; i < maxId; i++) {
            queue.offer(i);
        }
    }
    public int allocate() {
        if (queue.isEmpty()) return -1;
        int id = queue.poll();
        allocatedSet.add(id);

        return id;
    }
    public void release(int id) {
        if (id < 0 || id >= MAX_ID || !allocatedSet.contains(id)) return;
        allocatedSet.remove(id);
        queue.offer(id);  // linkedlist add to the front O(1)
    }

    public boolean check(int id) {
        // unnecessary check, the set contains can handle
//         if(id<0 || id>=MAX_ID) return false;
        return !allocatedSet.contains(id);
    }
    /*
     *If no need to maintain an ordering allocation, can use just one set called available to achieve the above.
     * Allocate is get and remove the first element in the set. (available.iterator().next())
     */

    public static void main(String[] args) {
        Sol_IdAllocator allocator = new Sol_IdAllocator(10);
        int id1 = allocator.allocate();
        int id2 = allocator.allocate();
        int id3 = allocator.allocate();
        System.out.println(id1+", "+id2+", "+id3);

        System.out.println(allocator.check(id1));
        System.out.println(allocator.check(id2));
        System.out.println(allocator.check(id3));
        System.out.println(allocator.check(11));
        System.out.println(allocator.check(-1));

        allocator.release(2);
        System.out.println(allocator.check(id3));

        Set<Integer> set = new LinkedHashSet<>();
        set.add(8);
        set.add(2);
        set.add(10);
        System.out.println(set.iterator().next());  // 8
        System.out.println(set.iterator().next());  // 8
//
//        boolean[] bits = new boolean[1024];
//        System.out.println();
    }

    // bitset approach
    // https://www.baeldung.com/java-bitset
    /*
    Space is much efficient: O(c), wouldn’t say it is O(1) because we still need max_size number of bits
      Time: worst case O(n) in searching for next available id
     */
    public class Allocator {
        private final int MAX_ID;
        private BitSet bitSet;
        private int nextAvailable;

        public Allocator(int maxId) {
            this.MAX_ID = maxId;
            this.bitSet = new BitSet(maxId);
            this.nextAvailable = 0;
        }
        public int allocate() {
            if (nextAvailable == MAX_ID) return -1;
            int num = nextAvailable;
            bitSet.set(num);
            nextAvailable = bitSet.nextClearBit(num);
            return num;
        }
        public void release(int id) {
            if (id < 0 || id >= MAX_ID) return;
            if (bitSet.get(id)) {
                bitSet.clear(id);
                nextAvailable = Math.min(nextAvailable, id);
            }
        }
        public boolean isAvailable(int id) {
            if(id<0 || id>=MAX_ID) return false;
            return !bitSet.get(id);
        }
    }

    /*
    Using the idea of binary tree (in heap array representation in bitset):  segment tree?
    Space: 2 * space used in bitset
    Time: O(log n) time in allocate and release
     */

    public class Allocator2{
        private final int MAX_ID;
        private BitSet bitSet;

        public Allocator2(int maxId) {
            this.MAX_ID = maxId;
            this.bitSet = new BitSet(maxId*2-1);
        }

        public int allocate() {
            int index=0;
            while(index<MAX_ID-1) {
                if(!bitSet.get(index*2+1)) {
                    index = index*2+1;
                } else if(!bitSet.get(index*2+2)) {
                    index = index*2+2;
                } else {
                    return -1;
                }
            }

            bitSet.set(index);
            updateTree(index);

            return index-MAX_ID+1;
        }

        public void updateTree(int index) {
            while(index>0) {
                int parent = (index-1)/2;
                if(index%2==1) { //left child
                    if(bitSet.get(index) && bitSet.get(index+1)) {
                        bitSet.set(parent);
                    } else {
                        bitSet.clear(parent); //it is required for release id
                    }
                } else {
                    if(bitSet.get(index) && bitSet.get(index-1)) {
                        bitSet.set(parent);
                    } else {
                        bitSet.clear(parent);
                    }
                }
                index = parent;
            }
        }

        public void release(int id) {
            if(id<0 || id>=MAX_ID) return;
            if(bitSet.get(id+MAX_ID-1)) {
                bitSet.clear(id+MAX_ID-1);
                updateTree(id+MAX_ID-1);
            }
        }

        public boolean check(int id) {
            if(id<0 || id>=MAX_ID) return false;
            return !bitSet.get(id+MAX_ID-1);
        }
    }
}
