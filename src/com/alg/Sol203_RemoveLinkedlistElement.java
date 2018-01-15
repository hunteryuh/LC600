package com.alg;

/**
 * Created by HAU on 11/25/2017.
 */
/*Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5*/
public class Sol203_RemoveLinkedlistElement {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public static ListNode removeElements(ListNode head, int val) {
        if ( head == null) return null;
        ListNode cur = head;
        while (cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head.val == val? head.next: head;
    }

    public static ListNode removeEle(ListNode head, int val){
        if ( head == null) return null;
        head.next = removeEle(head.next,val);
        return head.val == val? head.next : head;
    }
}
