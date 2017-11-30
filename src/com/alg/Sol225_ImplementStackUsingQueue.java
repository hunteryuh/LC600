package com.alg;

import java.util.LinkedList;

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

}
