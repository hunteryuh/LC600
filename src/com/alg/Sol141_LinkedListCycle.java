package com.alg;

import java.util.HashSet;
import java.util.Set;


/*Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?*/
public class Sol141_LinkedListCycle {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public static boolean hasCycle(ListNode head){
        // using hashtable, O(n) time, O(n) space
        // may need to override the equals and hashcode to compare object in the set
// Java Hashset 默认比reference， 如果内部一样但是保存位置不一样也就是重新new过，需要重写equals和hashcode
        Set<ListNode> set = new HashSet<>();
        while (head!= null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle_1(ListNode head) {
        if (head == null || head.next == null) return false;
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public static boolean hasCycle2(ListNode head) {
        // two pointers,
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow!= fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    // two pointer, same initial pointer for both slow and fast
    public static boolean hasCycle3(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
