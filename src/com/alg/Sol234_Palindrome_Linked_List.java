package com.alg;

/**
 Given the head of a singly linked list, return true if it is a
 palindrome
 or false otherwise.



 Example 1:


 Input: head = [1,2,2,1]
 Output: true
 Example 2:


 Input: head = [1,2]
 Output: false


 Constraints:

 The number of nodes in the list is in the range [1, 105].
 0 <= Node.val <= 9


 Follow up: Could you do it in O(n) time and O(1) space?
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Sol234_Palindrome_Linked_List {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }


    public static boolean isPalindrome(ListNode head) {
        if (head== null || head.next == null) return true;
        String ori = "";
        String rev = "";
        while (head != null) {
            ori += head.val;
            rev = head.val + rev;
            head = head.next;
        }
        return ori.equals(rev);   //O(n) time, O(n) space  string usage
        //    // 反向字串與正向字串相等就是回文陣列
    }

    public boolean isPalindromeList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int n = list.size();
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;

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
            if (!(stack.pop().equals(half2.pop() ))) return false; // for string or char
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
    public static boolean isPanld(ListNode head){
        ListNode fast = head, slow = head;
        while (fast!= null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if ( fast != null){
            slow = slow.next; // there are odd number of nodes, slow now is the node after the middle
        }
        slow = reverse(slow); // the node at the end
        fast = head; // fast is the original head
        while (slow!= null){
            if (slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    // O(n) time, O(1) space, constant space
    public boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode middle = findMiddle(head);
        ListNode halfstart = reverse(middle.next);
        ListNode p1 = head;
        while (halfstart != null) {
            if (p1.val != halfstart.val) return false;
            p1 = p1.next;
            halfstart = halfstart.next;
        }
        return true;
    }
    public static ListNode reverse(ListNode head){
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    //find middle way 2
    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // end of first half: if even, the end of half; if odd, the middle of the whole
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // 1-2-3   1-2-3-4
    }
}