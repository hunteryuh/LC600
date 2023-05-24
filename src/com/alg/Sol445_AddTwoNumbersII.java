package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 9/19/2017.
 */

/*
* You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists?
In other words, reversing the lists is not allowed.*/
public class Sol445_AddTwoNumbersII {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while (l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);

        while (!s1.empty() || !s2.empty()){
            if(!s1.empty()) sum += s1.pop();
            if(!s2.empty()) sum += s2.pop();
            //list.val saves  the < 10 digit
            list.val = sum % 10;
            // head saves the carrier 进位
            ListNode head = new ListNode( sum / 10);
            head.next = list;
            list = head; // current node moving forward
            sum /= 10; // for the next calculation
        }
        return list.val == 0? list.next: list;
    }
    public static ListNode addTwo(ListNode l1, ListNode l2){
        int size1 = getL(l1);
        int size2 = getL(l2);
        if (size1 > size2){
            helper(l1,l2,size1-size2);
        }else{
            helper(l2,l1,size2-size1);
        }
        return null;
    }
    private static int getL(ListNode h){
        int count = 0;
        while (h!= null){
            h = h.next;
            count++;
        }
        return count;
    }
    private static ListNode helper(ListNode l1, ListNode l2, int offset){
        if ( l1 == null) return  null;
        // check if l1 has the same length as l2
        ListNode result = offset == 0? new ListNode(l1.val +l2.val): new ListNode(l1.val);
        ListNode post = offset == 0? helper(l1.next,l2.next,0) : helper(l1.next, l2.next, offset -1);
        // carrier
        if (post != null && post.val > 9){
            result.val += 1;
            post.val %= 10;
        }
        result.next = post;
        return result;
    }

}
