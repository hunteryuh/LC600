package com.alg.other;
import java.util.ArrayList;
import java.util.List;
/*
Build a queue class with the enqueue and dequeue methods.
The queue can store an UNLIMITED number of elements.
However, the language you are using has a bug which does not allow arrays to store more than 5 elements,
how would you build that?



 */
//https://github.com/allaboutjst/airbnb/blob/master/src/main/java/implement_queue_with_fixed_size_of_arrays/ImplementQueuewithFixedSizeofArrays.java
public class Airbnb_ImplementQueueWithFixedSizeOfArrays {
    public class QueueWithFixedArray {
        private int fixedSize;

        private int count;
        private int head;
        private int tail;
        private List<Object> headList;
        private List<Object> tailList;

        public QueueWithFixedArray(int fixedSize) {
            this.fixedSize = fixedSize;
            this.count = 0;
            this.head = 0;
            this.tail = 0;
            this.headList = new ArrayList<>();
            this.tailList = this.headList;
        }

        public void offer(int num) {
            //// if we reach the capacity, store a new array at the 5th slot and push the element in the new array
            if (tail == fixedSize - 1) {
                List<Object> newList = (List<Object>) new ArrayList<Object>();
                newList.add(num);
                tailList.add(newList);
                tailList = (List<Object>) tailList.get(tail);
                tail = 0;
            } else {
                tailList.add(num); // enqueue element in the array,
            }
            count++;
            tail++;
        }

        public Integer poll() {
            if (count == 0) {
                return null;
            }

//            int num = (int) headList.get(head);

            int num;
            if (head == fixedSize - 1) {
                List<Object> newList = (List<Object>) headList.get(head);
                num = (int) newList.get(0);
                headList.clear();
                headList = newList;
                head = 0;
            } else {
                num = (int) headList.get(head);
                head++;
            }
            count--;

            return num;
        }

        public int size() {
            return count;
        }
    }
}
