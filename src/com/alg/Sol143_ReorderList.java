package com.alg;
/*
You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

1 2 3 4 5 6
 */
public class Sol143_ReorderList {
    // This problem is a combination of these three easy problems:
    //
    //    Middle of the Linked List.
    //
    //    Reverse Linked List.
    //
    //    Merge Two Sorted Lists.
    public void reorderList(ListNode head) {
        // find middle
        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // reverse 2nd half
        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        ListNode pre = null;
        ListNode cur = slow;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // now head is at pre
        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head;
        ListNode second = pre;
        while (second.next != null) {
            ListNode temp = first.next;
            first.next = second;
            first = temp;

            ListNode tmp2 = second.next;
            second.next = first;
            second = tmp2;
        }
    }

    // 2 pointer https://leetcode.com/problems/reorder-list/discuss/1640806/Java-or-2-Approach-or-2-Pointer-Approach
    public ListNode midNode(ListNode head){
        ListNode fast =  head, slow  =  head;
        while(fast.next!=null && fast.next.next!=null) {
            fast =  fast.next.next;
            slow =  slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head){
        ListNode curr =  head, prev=  null, next = null;
        while(curr!=null){
            next  =  curr.next;
            curr.next =  prev ;
            prev =  curr;
            curr  =  next;
        }
        return prev;
    }


    public void reorderList2(ListNode head) {

        ListNode midNode =  midNode(head);
        ListNode nextToMid =  midNode.next;
        midNode.next = null; // set an end for first half
        ListNode p2 =  reverse(nextToMid);

        ListNode p1 = head, p1Next;
        while(p1!=null && p2!=null){
            p1Next =  p1.next;
            p1.next =  p2;

            p1 =  p2;
            p2=p1Next;
        }
    }
}
