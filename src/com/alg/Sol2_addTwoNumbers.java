package com.alg;

/**
 * Created by HAU on 8/22/2017.
 */
public class Sol2_addTwoNumbers {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode (int x) { val = x;}
    }

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
        ListNode res = addTwoNumbers(l1,l2);
        while (res!= null){
            System.out.print(res.val +"->");
            res = res.next;
        }
    }
}
