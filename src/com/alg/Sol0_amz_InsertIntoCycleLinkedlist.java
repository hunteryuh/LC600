package com.alg;

/**
 * Created by HAU on 1/2/2018.
 */
/*插入一个新的节点到一个sorted cycle linkedlist，返回新的节点。给的list节点不一定是最小节点

所以先要找到最小的点*/
/*Solution:
Basically, you would have a loop that traverse the cyclic sorted list and
find the point where you insert the value (Let’s assume the value being inserted called x).
list may have duplicates
You would only need to consider the following three cases:

case 1 prev→val ≤ x ≤ current→val:
Insert between prev and current.
case 2 x is the maximum or minimum value in the list:
Insert before the head. (ie, the head has the smallest value and its prev→val > head→val.
case 3 Traverses back to the starting point:
Insert before the starting point.
Q: What if the list has only one value?
A: Handled by case 3).
Q: What if the list is passed in as NULL?
A: Then handle this special case by creating a new node pointing back to itself and return.
Q: What if the list contains all duplicates?
A: Then it has been handled by case 3).*/
public class Sol0_amz_InsertIntoCycleLinkedlist {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public static ListNode insertCycle(ListNode head, int val) {
        if (head == null) {
            ListNode res = new ListNode(val);
            res.next = res;
            return res;
        }
        ListNode cur = head;
        do{
            ////如果val的值正好介于两个节点之间，结束循环，等号是有必要的
            if(val >= cur.val && val <= cur.next.val) break; // case 1
            if( cur.val > cur.next.val && ( val > cur.val) || val <cur.next.val){
                break; // largest or smallest  , case 2
            }

            // // 否则继续往下找
            cur = cur.next;
        } while(cur != head);  // case 3, back to starting point

        ListNode newNode = new ListNode(val);
        newNode.next = cur.next;
        cur.next = newNode;
        return newNode;
    }
}
