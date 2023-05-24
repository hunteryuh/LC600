package com.alg;

/**
 * Created by HAU on 10/1/2017.
 */
/*Write a program to find the node at which the intersection of two singly linked lists begins.
*
For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3*/
public class Sol160_IntersectionOfTwoLinkedLists {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        if (headA == null || headB == null)
            return null;
        int len1 = getL(headA);
        int len2 = getL(headB);  //Get the length of the two lists.
        // move headA and headB to the same start point
        while(len1 > len2){
                headA = headA.next;
                len1--;
        }
        while(len2 > len1){
            headB = headB.next;
            len2--;
        }
        // find the intersection until end
        while(headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
    private static int getL(ListNode head){
        int c = 0;
        while (head != null){
            head = head.next;
            c++;
        }
        return c;
    }

    //method 2, no length calculation
    public ListNode getIntersection2(ListNode headA, ListNode headB){
        if (headA == null || headB == null)
            return null;
        ListNode pa = headA;
        ListNode pb = headB;
        ListNode ea = null; // last node in list a
        ListNode eb = null;
        while (true){
            if (pa == null){
                pa = headB;
            }
            if (pb == null){
                pb = headA;
            }
            if ( pa.next == null){
                ea = pa;
            }
            if (pb.next == null){
                eb = pb;
            }

            if (ea != null && eb != null && ea != eb){
                return null;
            }
            if (pa == pb) return pa;  // meet at the 2nd traverse
            pa = pa.next;
            pb = pb.next;

        }
    }
}
