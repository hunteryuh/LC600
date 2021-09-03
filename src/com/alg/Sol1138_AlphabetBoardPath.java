package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].

Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.



We may make the following moves:

'U' moves our position up one row, if the position exists on the board;
'D' moves our position down one row, if the position exists on the board;
'L' moves our position left one column, if the position exists on the board;
'R' moves our position right one column, if the position exists on the board;
'!' adds the character board[r][c] at our current position (r, c) to the answer.
(Here, the only positions that exist on the board are positions with letters on them.)

Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.



Example 1:

Input: target = "leet"
Output: "DDR!UURRR!!DDD!"
Example 2:

Input: target = "code"
Output: "RR!DDRR!UUL!R!"
 */
public class Sol1138_AlphabetBoardPath {
    public static String alphabetBoardPath(String target) {
//        Map<Character, Integer> map = new HashMap<>();
//        for(int i = 0; i < 26; i++) {
//            map.put((char) ('a' + i), i);
//        }
        int rowi = 0;
        int coli = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< target.length();i++) {
            char c = target.charAt(i);
            int rowc = (c - 'a') / 5;
            int colc = (c - 'a') % 5;
            int rowdiff = rowc - rowi;
            int coldiff = colc - coli;
            // need to make sure the order works for z going to and going from
            // from z: U then R
            // to z: L then D
            // cannot do LR together before UD;
            while (rowdiff < 0) {
                sb.append('U');
                rowdiff++;
            }
            while (coldiff > 0) {
                sb.append('R');
                coldiff--;
            }
            while (coldiff < 0) {
                sb.append('L');
                coldiff++;
            }
            while (rowdiff > 0) {
                sb.append('D');
                rowdiff--;
            }
            sb.append('!');
            rowi = rowc;
            coli = colc;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = alphabetBoardPath("code");
        String t = alphabetBoardPath("zdz");
        System.out.println(s);
        System.out.println(t);

    }
}
