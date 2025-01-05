package com.alg.other;

import jdk.internal.util.xml.impl.Pair;

public class FB_LinkedList {
    public class Node {
        Node next;
        int data;
        public Node(int data) {
            this.data = data;
        }
    }

    public class LinkedList {
        Node head;
        public void append(int data) {
            if (head == null) {
                head = new Node(data);
                return;
            }
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(data);
        }
        public void prepend(int data) {
            Node newHead = new Node(data);
            newHead.next = head;
            head = newHead;
        }
        public void deleteWithValue(int data) {
            if (head == null) return;
            if (head.data == data) {
                head = head.next;
                return;
            }
            Node current = head;
            while (current.next != null) {
                if (current.next.data == data) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }
        public Node insertNodeAtPosition(Node headNode, int pos, int data) {
            Node node = headNode;
            if (pos == 0) {
                node = new Node(data);
                node.next = headNode;
                return node;
            }
            while (pos-- > 0) {
                node = node.next;
            }
            Node i = node.next;
            node.next = new Node(data);
            node.next.next = i;
            return headNode;
        }
    }
}
