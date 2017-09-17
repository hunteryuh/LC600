package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 9/17/2017.
 */

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
        Set<ListNode> set = new HashSet<>();
        while (head!= null){
            if (set.contains(head)){
                return true;
            }else{
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }
    public static boolean hasCycle2(ListNode head){
        if ( head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while ( slow!= fast){
            if ( fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
