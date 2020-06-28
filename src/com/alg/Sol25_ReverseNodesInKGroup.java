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

    // method 1
    public static ListNode reverseKGroup1(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while(cur!=null && count!=k){
            cur = cur.next;
            count++;
        }
        if ( count == k){
            cur = reverseKGroup1(cur,k); // reverse list whose head is ((k+1)th node
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

    // method 2
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = dummy;
        while (node != null) {
            node = reverseNextK(node, k);
        }
        return dummy.next;
    }

    // n0 -> n1 -> n2 ... -> nk -> nkplus
    // n0 -> nk -> nk-1 ...-> n1 -> nkplus

    private static ListNode reverseNextK(ListNode node0, int k) {
        // are there k or more nodes to reverse
        ListNode nk = node0;
        for (int i = 0; i < k; i++) {
            nk = nk.next;
            if (nk == null) return null;
        }
        // now nk = nk;
        // reverse list from n1 to nk
        ListNode nkplus = nk.next;
        ListNode n1 = node0.next;
        ListNode pre = null;
        ListNode curr = node0.next;
        for (int i = 0; i < k; i++) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        node0.next = pre; // node0.next = nk;
        n1.next = curr;  // n1.next = nkplus;
        return n1;

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
        printList(reverseKGroup(h3,2));
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }


}
