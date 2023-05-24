package com.alg;

/**
 * Created by HAU on 8/22/2017.
 */
/*You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.*/
public class Sol2_addTwoNumbers {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode (int x) { val = x;}
    }
    // time: O(max(m,n)) , m,n the length of two lists respectively
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        ListNode cur = head;
        int carry = 0;
        while (p!= null || q!= null){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if ( p != null) p = p.next;
            if ( q != null) q = q.next;
        }
        if (carry > 0){
            cur.next = new ListNode(carry);
        }
        return head.next;

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(8);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(5);
        //ListNode res = addTwoNumbers(l1,l2);
        //592+558 = 1150
        ListNode res = add2Num(l1,l2);
        while (res!= null){
            System.out.print(res.val +"->");
            res = res.next;
        }
    }

    // similar way as above
    public static ListNode add2Num(ListNode l1, ListNode l2){
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1!= null || l2!= null || carry != 0){
            ListNode cur = new ListNode(0);
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ?0:l2.val )+ carry;
            cur.val = sum % 10;
            carry = sum /10;
            prev.next = cur;
            prev = cur;
            if (l1!= null) l1 = l1.next;
            if (l2!= null) l2 = l2.next;
        }

        return head.next;
    }
}
