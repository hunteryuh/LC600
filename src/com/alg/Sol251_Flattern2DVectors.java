package com.alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.

Implement the Vector2D class:

    Vector2D(int[][] vec) initializes the object with the 2D vector vec.
    next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
    hasNext() returns true if there are still some elements in the vector, and false otherwise.



Example 1:

Input
["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
[[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
Output
[null, 1, 2, 3, true, true, 4, false]

Explanation
Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
vector2D.next();    // return 1
vector2D.next();    // return 2
vector2D.next();    // return 3
vector2D.hasNext(); // return True
vector2D.hasNext(); // return True
vector2D.next();    // return 4
vector2D.hasNext(); // return False



Constraints:

    0 <= vec.length <= 200
    0 <= vec[i].length <= 500
    -500 <= vec[i][j] <= 500
    At most 105 calls will be made to next and hasNext.



Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.

 */
public class Sol251_Flattern2DVectors {
    class Vector2D {
        // two pointers approach

        private int[][] vector;
        private int inner = 0;
        private int outer = 0;
        // implement remove() method to remove element that is visited by next() method for the Vector2D
        private int lastRow;
        private int lastCol;
        private boolean canRemove;
        public Vector2D(int[][] vec) {
            vector = vec;
            this.lastCol = -1;
            this.lastRow = -1;
            this.canRemove = false;
        }

        public int next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastRow = outer;
            lastCol = inner;
            canRemove = true;
            return vector[outer][inner++]; // return current element and move inner to next
        }

        public boolean hasNext() {
            moveToNextInteger();
            return outer < vector.length;
        }
        private void moveToNextInteger() {
            // check if the current position points to an integer
            while (outer < vector.length && inner == vector[outer].length) {
                inner = 0;
                outer++;
            }

        }

        // if there is a remove(), this is to skip the nullified element for subsequent traversal
        private void moveToNextAvailable() {
            // Skip to the next valid element or row, skipping over marked "removed" elements
            while (outer < vector.length && (inner >= vector[outer].length || vector[outer][inner] == Integer.MIN_VALUE)) {
                inner = 0;
                outer++;
//                inner++;
//                if (inner >= vector[outer].length) {
//                    outer++;
//                    inner = 0;
//                }
            }
        }

        public void remove() {
            if (!canRemove) {
                throw new IllegalArgumentException("next() has not been called" +
                        "or remove() has been called after last call to next()");
            }
            //shift elements to the left;
            for (int i = lastCol; i < vector[lastRow].length - 1; i++) {
                vector[lastRow][i] = vector[lastRow][i + 1];
            }
            // Nullify the last element (optional depending on your use case)
            vector[lastRow][vector[lastRow].length - 1] = Integer.MIN_VALUE;
            canRemove = false;
        }
     }
    /**
     * Your Vector2D object will be instantiated and called as such:
     * Vector2D obj = new Vector2D(vec);
     * int param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */

//    public class Vector2D2 implements Iterator<Integer> {
//        private
//    }
}
