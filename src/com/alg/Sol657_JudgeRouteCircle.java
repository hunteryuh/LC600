package com.alg;

/**
 * Created by HAU on 8/14/2017.
 */
/*
Initially, there is a Robot at position (0, 0).
        Given a sequence of its moves, judge if this robot
        makes a circle, which means it moves back to the original place.

        The move sequence is represented by a string.
        And each move is represent by a character.
        The valid robot moves are R (Right), L (Left), U (Up) and D (down).
        */
public class Sol657_JudgeRouteCircle {
    public static boolean judgeCircle(String moves){
        int x = 0, y = 0;
        for (int i = 0; i< moves.length();i++) {
            char c = moves.charAt(i);
            switch (c){
                case 'U': y++;break;
                case 'D': y--; break;
                case 'R': x++;break;
                case 'L': x--; break;
            }
        }
        return x == 0 && y ==0;
    }

    public static void main(String[] args) {
        String m = "ULDR";
        System.out.println(judgeCircle(m));
        String n = "DR";
        System.out.println(judgeCircle(n));
    }

}
