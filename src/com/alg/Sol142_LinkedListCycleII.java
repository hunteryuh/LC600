package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 11/29/2017.
 */
/*Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?*/
public class Sol142_LinkedListCycleII {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public static ListNode detectCycle(ListNode head) {
        // two pointer
        if (head == null || head.next == null) return null;
        ListNode fast = head, slow = head;
        while (fast!= null && fast.next!= null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                slow = head;  // move the slow pointer to the beginning of the list
                // now it takes same distance from the fast pointer and the head to the start of the cycle
                while (slow!= fast){  //  a+ b +c +b = 2(a+b)-->a = c
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }

        }
        return null;
    }
    // hashset
    public ListNode getCycleEntrance(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }
}
