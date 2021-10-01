package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 11/28/2017.
 */
/*Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.*/
public class Sol232_ImplementQueueUsingStacks {
    class MyQueue {
        private Stack<Integer> stack = new Stack<>();
        // push - O(n)， pop - O(1)， peek - O(1)， isEmpty - O(1).  Space Complexity - O(n)
        /** Initialize your data structure here. */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            Stack<Integer> tmp = new Stack<>();
            while (!stack.isEmpty()) tmp.push(stack.pop());
            stack.push(x);
            while(!tmp.isEmpty()) stack.push(tmp.pop());
            
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }

    class MyQueue2 {

        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public MyQueue2() {

        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else{
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }

        public int peek() {
            if (!stack2.isEmpty()) {
                return stack2.peek();
            } else {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.peek();
            }
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
