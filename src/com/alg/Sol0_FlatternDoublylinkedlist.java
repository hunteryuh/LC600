package com.alg;

/**
 * Created by HAU on 12/2/2017.
 */
public class Sol0_FlatternDoublylinkedlist {
    public class MultiListToDoubleList {
         class MultiListNode {
            int val;
            MultiListNode pre;
            MultiListNode next;
            MultiListNode up;
            MultiListNode down;
        }

        public void convert(MultiListNode head) {
            if (head == null) {
                return;
            }

            MultiListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }

            MultiListNode cur = head;
            while (cur != null) {
                if (cur.up != null) {
                    tail.next = cur.up;
                    cur.up.pre = tail;
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    cur.up = null;
                }

                if (cur.down != null) {
                    tail.next = cur.down;
                    cur.down.pre = tail;
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    cur.down = null;
                }
                cur = cur.next;
            }
        }

    }
}
