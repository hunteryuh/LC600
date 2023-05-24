package com.alg;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
Design a Snake game that is played on a device with screen size height x width. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.

You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.

Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.

When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).

Implement the SnakeGame class:

    SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width and the positions of the food.
    int move(String direction) Returns the score of the game after applying one direction move by the snake. If the game is over, return -1.



Example 1:

Input
["SnakeGame", "move", "move", "move", "move", "move", "move"]
[[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
Output
[null, 0, 0, 1, 1, 2, -1]

Explanation
SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
snakeGame.move("R"); // return 0
snakeGame.move("D"); // return 0
snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).
snakeGame.move("U"); // return 1
snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.
snakeGame.move("U"); // return -1, game over because snake collides with border



Constraints:

    1 <= width, height <= 104
    1 <= food.length <= 50
    food[i].length == 2
    0 <= ri < height
    0 <= ci < width
    direction.length == 1
    direction is 'U', 'D', 'L', or 'R'.
    At most 104 calls will be made to move.


 */
public class Sol353_DesignSnakeGame {
    // https://leetcode.com/problems/design-snake-game/solutions/82668/java-deque-and-hashset-design-with-detailed-comments/
    class SnakeGame {
        int width;
        int height;
        int[][] food;
        int k = 0;
        Point cur;
        LinkedList<Point> snake;
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            this.k = 0;
            this.snake = new LinkedList<>();
            snake.addFirst(new Point(0,0));
        }

        public int move(String direction) {
            // the new head is based on the current head
            // the tail is removed, but can be returned back if food is found
            Point head = snake.getFirst();
            Point newHead = new Point(head.i, head.j);
            Point tail = snake.removeLast();
            if (direction.equals("U")) {
                newHead.i--;
            } else if (direction.equals("D")) {
                newHead.i++;
            } else if (direction.equals("L")) {
                newHead.j--;
            } else {
                newHead.j++;
            }
            if (newHead.i < 0 || newHead.i == height || newHead.j < 0 || newHead.j == width
                || snake.contains(newHead)) { //
                return -1;
            }
            snake.addFirst(newHead);
            if (k < food.length && food[k][0] == newHead.i && food[k][1] == newHead.j) {
                snake.addLast(tail); // add tail back
                k++;
            }
            return k;
        }

        class Point {
            int i, j;
            Point(int i, int j) {
                this.i = i;
                this.j = j;
            }
            public boolean equals(Point o) {
                return this.i == o.i && this.j == o.j;
            }
            public int hashCode() {
                return i * 31 + j;
            } // todo: implement

        }
    }

    class SnakeGame2 {
        int width;
        int height;
        int[][] food;
        int score;
        int foodIndex = 0;
        Set<Integer> set;
        Deque<Integer> body;

        public SnakeGame2(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            set = new HashSet<>();
            set.add(0);
            body = new LinkedList<>();
            body.offerLast(0);
        }

        public int move(String direction) {
            if (score == -1) return -1;
            int rowHead = body.peekFirst() / width;
            int colHead = body.peekFirst() % width;
            switch (direction) {
                case "U":
                    rowHead--;
                    break;
                case "D":
                    rowHead++;
                    break;
                case "L":
                    colHead--;
                    break;
                case "R":
                    colHead++;
                    break;
                // default: colHead++;
            }
            int head = rowHead * width + colHead;
            // case 1: out of boundary or eating body
            set.remove(body.peekLast());
            if (rowHead < 0 || rowHead == height
                || colHead < 0 || colHead == width || set.contains(head)) {
                return -1;
            }
            // add head
            set.add(head);
            body.offerFirst(head);

            // if eating food, need to keep tail, add head
            if (foodIndex < food.length &&
                rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
                set.add(body.peekLast());
                foodIndex++;
                score++;
                return score;
            }
            // normal move, remove tail, add head
            body.pollLast();
            return score;
        }
    }

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
}
