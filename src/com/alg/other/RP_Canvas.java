package com.alg.other;

import com.sun.tools.javac.util.Pair;

import java.util.Arrays;
import java.util.Stack;

public class RP_Canvas {
    // https://leetcode.com/discuss/interview-question/2119181/rippling-or-technical-phone-screen/1426833
    static class Canvas {
        int length;
        int height;
        char[][] g;
        char[][] l;
        Canvas(int length, int height) {
            this.length = length;
            this.height = height;
            g = new char[length][height];
            for (char[] row: g) {
                Arrays.fill(row, '.');
            }
            l = new char[length][height];
            for (char[] row: l) {
                Arrays.fill(row, '.');
            }
        }



        private void display() {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < height; j++) {
                    System.out.print(g[i][j]);
                }
                System.out.println();
            }
        }

        void draw(Character shape, Pair<Integer, Integer> from, Pair<Integer, Integer> size) { // size.first - length number of rows; size.snd nums of columns
            if (from.fst < 0 || from.snd < 0 || size.fst < 0 || size.snd < 0) return;
            int ri = from.fst;
            int ci = from.snd;
            for (int i = 0; i< size.fst; i++) {
                for (int j = 0; j < size.snd; j++) {
                    if (ri + i >= g.length || ci + j >= g[0].length) continue;
                    if (g[ri+i][ci+j] != '.') {
                        l[ri+i][ci+j] = g[ri+i][ci+j]; // save the layers
                    }
                    g[ri+i][ci+j] = shape;
                }
            }
        }
        void move(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
            if (from.fst < 0 || from.snd < 0 || to.fst < 0 || to.snd < 0) return;
            char shape = g[from.fst][from.snd];
            int col_end = from.snd;
            while (col_end < g[from.fst].length && g[from.fst][col_end] == shape) {
                col_end++;
            }
            int length = col_end - from.snd;
            int row_last = from.fst;
            while (row_last < g.length && g[row_last][from.snd] == shape) {
                row_last++;
            }
            int height = row_last - from.fst;
            System.out.println(length);
            System.out.println(height);

            // not working; old cell is not deleted
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < length; j++) {
                    int tox = to.fst + i;
                    int toy = to.snd + j;
                    if (tox >= g.length || toy >= g[0].length) continue;
                    // handle overlap
                    g[tox][toy] = shape;
                    if (l[from.fst+i][from.snd + j] != '.') {
                        g[from.fst + i][from.snd + j] = l[from.fst+i][from.snd + j];
                        l[from.fst + i][from.snd + j] = '.';
                    } else if (from.fst +i < to.fst || from.snd + j < to.snd) { // restore the original locations
                        g[from.fst + i][from.snd + j] = '.';
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        RP_Canvas rp_canvas = new RP_Canvas();
        Canvas canvas = new Canvas(10, 25);
        canvas.draw('a', new Pair(0,0), new Pair(4,6));
        canvas.draw( 'b', new Pair(4,21), new Pair(6,4));
        canvas.draw( 'c', new Pair(2,5), new Pair(5,5));
        canvas.display();
        System.out.println("Hello");
        canvas.move(new Pair(2,5), new Pair(2,7));
        canvas.display();
    }



    class Square {
        private final char c;
        private final int id;

        public Square(char c, int id) {
            this.c = c;
            this.id = id;
        }

        public char getC() {
            return c;
        }
    }

    private int id;
    private final int length;
    private final int width;
    private final Stack<Square>[][] board;
    public RP_Canvas(int width, int length) {
        this.id = 0;
        this.length = length;
        this.width = width;
        this.board = new Stack[width][length];
        this.draw('0', width, length, 0, 0);
    }

    public  void draw(char c,int sizeX, int sizeY, int posX, int posY) {
        for (int i=0; i<sizeX; i++) {
            if (i+posX<0 || i+posX >= width) {
                break;
            }
            for (int j=0; j<sizeY; j++) {
                if (j+posY<0 || j+posY >= length) {
                    break;
                }
                int newI = posX + i;
                int newJ = posY + j;
                if (board[newI][newJ] == null) {
                    board[newI][newJ] = new Stack<>();
                }
                board[newI][newJ].push(new Square(c, id));
            }
        }

        id++;
    }

    public void move(int fromX, int fromY, int toX, int toY) {
        int currId = board[fromX][fromY].peek().id;
        char c = board[fromX][fromY].peek().c;
        int sizeX = 0;
        int sizeY = 0;
        while (board[fromX + sizeX][fromY + sizeY].peek().id == currId) {
            sizeX++;
        }
        while (board[fromY + sizeY][fromY + sizeY].peek().id == currId) {
            sizeY++;
        }
        for (int i = fromX; i < fromX + sizeX; i++) {
            for (int j = fromY; j < fromY + sizeY; j++) {
                board[i][j].pop();
            }
        }
        this.draw(c, sizeX, sizeY, toX, toY);
    }
}
