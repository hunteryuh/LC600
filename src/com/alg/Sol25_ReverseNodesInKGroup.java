package com.alg;

/**
 * Created by HAU on 12/12/2017.
 */
/*Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5*/
public class Sol25_ReverseNodesInKGroup {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while(cur!=null && count!=k){
            cur = cur.next;
            count++;
        }
        if ( count == k){
            cur = reverseKGroup(cur,k); // reverse list whose head is ((k+1)th node
            while(count >0){
                ListNode tmp = head.next;
                head.next = cur;
                cur = head;
                head = tmp;
                count--;
            }
            head = cur;
        }
        return head;

    }
    public static void main(String[] args) {
        ListNode h3 = new ListNode(1);
        h3.next = new ListNode(2);
        h3.next.next = new ListNode(3);
        h3.next.next.next = new ListNode(4);
        h3.next.next.next.next = new ListNode(5);
        printList(h3);
        System.out.println();
        System.out.println("after Swap k groups");
        printList(reverseKGroup(h3,3));
    }
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }


}
