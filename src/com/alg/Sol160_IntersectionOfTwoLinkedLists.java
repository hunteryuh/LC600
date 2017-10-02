package com.alg;

/**
 * Created by HAU on 10/1/2017.
 */
/*Write a program to find the node at which the intersection of two singly linked lists begins.*/
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
        //ListNode cur = headA;
        int len1 = getL(headA);
        int len2 = getL(headB);
        int dif = Math.abs(len1  - len2);
        if (len1 > len2){
            while (dif > 0){
                headA = headA.next;
                dif--;
            }
        }else{
            while (dif > 0){
                headB = headB.next;
                dif--;
            }
        }

        while (headA != null){
            if (headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;

        }
        return null;
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
