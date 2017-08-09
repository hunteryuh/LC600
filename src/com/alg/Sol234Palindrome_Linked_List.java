package com.alg;

/**
 * Created by HAU on 5/27/2017.
 */


import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Sol234Palindrome_Linked_List {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }


    public static boolean isPalindrome(ListNode head) {
        if (head== null || head.next == null) return true;
        String ori = "";
        String rev = "";
        while (head!=null){
            ori +=head.val;
            rev = head.val + rev;
            head = head.next;
        }
        return ori.equals(rev);   //O(n) time, O(n) space
    }
    public static boolean isPld2(ListNode head){
        if (head == null || head.next == null) return true;
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        while (head!=null){
            stack.push(head.val);
            count++;
            head = head.next;
        }
        Stack<Integer> half2 = new Stack<>();
        for (int i=0;i<count/2;i++){
            half2.push(stack.pop());
        }
        if (count%2==1) stack.pop();

        for (int i=0;i<count/2;i++){
            if (!(stack.pop().equals(half2.pop() ))) return false;
        }
        return true;  //O(n) time and O(1) space
    }

    public static void main(String[] args) {
        //LinkedList list = new LinkedList();

/*        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);

        ListNode h2 = new ListNode(4);
        h2.next = new ListNode(5);
        h2.next.next = new ListNode(1);
        h2.next.next.next = new ListNode(4);

        ListNode h3 = new ListNode(4);
        h3.next = new ListNode(5);
        h3.next.next = new ListNode(1);
        h3.next.next.next = new ListNode(5);
        h3.next.next.next.next = new ListNode(4);

        System.out.println(isPalindrome(head));
        System.out.println(isPld2(h2));
        System.out.println(isPld2(h3));*/


        ListNode h4 = new ListNode(-129);
        h4.next = new ListNode(-129);
        System.out.println(isPld2(h4));


    }
}