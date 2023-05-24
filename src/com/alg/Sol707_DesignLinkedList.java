package com.alg;

public class Sol707_DesignLinkedList {
    class MyLinkedList {
        class Node {
            int val;
            Node next;
            Node(int x) {
                this.val = x;
            }
        }
        int size;
        Node head;

        /** Initialize your data structure here. */
        public MyLinkedList() {
            size = 0;
            head = new Node(0);
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            Node node = head;
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
            return node.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if (index > size) return;
            Node node = new Node(val);
            Node pre = head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            node.next = pre.next;
            pre.next = node;
            size++;

        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return;
            Node pre = head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
            size--;
        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
