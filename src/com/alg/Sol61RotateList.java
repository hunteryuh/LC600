package com.alg;

/**
 * Created by HAU on 5/28/2017.
 */

/*Given a list, rotate the list to the right by k places, where k is non-negative.

        For example:
        Given 1->2->3->4->5->NULL and k = 2,
        return 4->5->1->2->3->NULL.*/
public class Sol61RotateList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int count = 1;
        ListNode last = head;
        ListNode pre;
        ListNode newhead = head;
        while(last.next!=null){
            count++;
            last = last.next;

        }
        if (count==1 || k==0) return head;
        if (k>= count ) k %= count;
        last.next = head;
        for(int i=0;i<count-k;i++){
            pre = newhead;
            newhead = newhead.next;
            if(i==count-k-1) pre.next = null;


        }

        return newhead;


    }
        // a reference below
/*    if (head == null)
            return head;

    ListNode copyHead = head;

    int len = 1;
	while (copyHead.next != null) {
        copyHead = copyHead.next;
        len++;
    }

    copyHead.next = head;

	for (int i = len - k % len; i > 1; i--)
    head = head.next;

    copyHead = head.next;
    head.next = null;

	return copyHead;
}*/
    public static void main(String[] args) {

        ListNode h3 = new ListNode(4);
        h3.next = new ListNode(5);
        h3.next.next = new ListNode(1);
        h3.next.next.next = new ListNode(2);
        h3.next.next.next.next = new ListNode(3);

        int k =2;
        ListNode newh3 = rotateRight(h3,k);
        printList(newh3);
        System.out.println("");
        ListNode h1 = new ListNode(9);
        printList(rotateRight(h1,1));

        System.out.println("");

        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(2);
        printList(rotateRight(h2,2));
        //printList(rotateRight(h2,3));

    }
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }
}
