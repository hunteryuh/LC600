package com.alg;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 11/29/2017.
 */
/*mplement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.*/
public class Sol225_ImplementStackUsingQueue {
    class MyStack {
        //(One Queue, push - O(n), pop O(1) )
        private LinkedList<Integer> q1 = new LinkedList<>();
        /** Initialize your data structure here. */
        public MyStack() {

        }

        /** Push element x onto stack. */
        public void push(int x) {
            q1.add(x);
            int qsize = q1.size();
            while (qsize > 1){
                q1.add(q1.remove());
                qsize--;
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return q1.remove();
        }

        /** Get the top element. */
        public int top() {
            return q1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.isEmpty();
        }
    }

    class MyStack2 {

        private Deque<Integer> q = new ArrayDeque<>();
        public MyStack2() {

        }

        public void push(int t) {
            q.addLast(t);
            int n = q.size();
            while (n-- > 1) {
                q.addLast(q.pollFirst());
            }
        }
        public int pop() {
            return q.pollFirst();
        }

        public int top() {
            return q.peek();
        }

        public boolean empty() {
            return q.isEmpty();
        }
    }
}
