package com.alg;

/**
 * Created by HAU on 6/13/2017.
 */

/*
* A linked list is given such that each node contains an additional random pointer
* which could point to any node in the list or null.

Return a deep copy of the list.*/
public class Sol138CopyListWithRandomPointer {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode random;

        ListNode(int x) {
            val = x;
            next = null;
            random = null;
        }
    }
    private static void copyNext(ListNode head){
        while (head != null){
            ListNode newNode = new ListNode(head.val);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }

    }
    private static void copyRandom(ListNode head){
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }
    private static ListNode splitList(ListNode head) {
        ListNode newHead = head.next;
        while (head != null) {
            ListNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = head.next; // temp.next = temp.next.next
            }
        }
        return newHead;
    }
    public static ListNode copyRandomList(ListNode head){
        if (head == null) return null;
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }


    public static void main(String[] args) {
        ListNode h3 = new ListNode(1);
        h3.next = new ListNode(2);
        h3.next.next = new ListNode(3);
        h3.next.next.next = new ListNode(4);
        h3.next.next.next.next = new ListNode(5);

        h3.random = h3.next.next;  //1->3
        h3.next.random = h3; // 2->1
        h3.next.next.random = h3.next.next.next.next;  //3->5
        h3.next.next.next.random = null;  //4->null
        h3.next.next.next.next.random = h3.next;  //5->2

        System.out.println("original linked list");
        printList(h3);
        System.out.println();
        System.out.println("after copy random");
        printList(copyRandomList(h3));
    }
    public static void printList(ListNode node) {
        while (node != null) {
            //System.out.print(node.val + "->");
            if(node.random == null) System.out.print("val: " + node.val +", random = " + "null");
                else System.out.print("val: " + node.val +", random = " + node.random.val);
            System.out.println();
            node = node.next;

        }
    }
}
