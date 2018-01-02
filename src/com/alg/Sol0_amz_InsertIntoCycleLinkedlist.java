package com.alg;

/**
 * Created by HAU on 1/2/2018.
 */
/*插入一个新的节点到一个sorted cycle linkedlist，返回新的节点。给的list节点不一定是最小节点

所以先要找到最小的点*/
public class Sol0_amz_InsertIntoCycleLinkedlist {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public static ListNode insertCycle(ListNode head, int val) {
        if ( head == null){
            ListNode res = new ListNode(val);
            res.next = res;
            return res;
        }
        ListNode cur = head;
        do{
            if(val >= cur.val && val <= cur.next.val) break;
            if( cur.val > cur.next.val && ( val > cur.val) || val <cur.next.val){
                break; // largest or smallest
            }
            cur = cur.next;
        } while(cur != head);

        ListNode newNode = new ListNode(val);
        cur.next = newNode;
        newNode.next = cur.next;
        cur.next = newNode;
        return newNode;
    }
}
