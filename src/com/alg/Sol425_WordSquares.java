package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 12/17/2017.
 */
/*Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y

Note:

    There are at least 1 and at most 1000 words.
    All words will have the exact same length.
    Word length is at least 1 and at most 5.
    Each word contains only lowercase English alphabet a-z.

Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. */
public class Sol425_WordSquares {
    static class Node{
        Node[] nodes;
        String word;
        Node(){
            this.nodes = new Node[26];
            this.word = null;
        }
    }
    static void add(Node root, String word){
        Node node = root;
        for (char c:word.toCharArray()){
            int idx = c - 'a';
            if(node.nodes[idx] == null) node.nodes[idx] = new Node();
            node = node.nodes[idx];
        }
        node.word = word;
    }
    static void helper(int row, int col, int len, Node[] rows, List<List<String>> list){
        if( col == row && row == len){
            // last char is reached
            List<String> tmp = new ArrayList<>();
            for(int i = 0; i < len; i++){
                tmp.add(new String(rows[i].word));
            }
            list.add(tmp);
        }else{
            // from left to right and then go down to the next row
            if(col < len){// left to right
                Node pre_row = rows[row];
                Node pre_col = rows[col];
                for (int i = 0; i < 26; i++){
                    if( rows[row].nodes[i] != null && rows[col].nodes[i] != null){
                        rows[row] = rows[row].nodes[i];
                        if (col != row) rows[col] = rows[col].nodes[i];
                        helper(row,col + 1, len, rows, list);
                        rows[row] = pre_row;
                        if(col != row) rows[col] = pre_col;
                    }
                }
            }else{
                // now at the end of colum, go down
                helper(row+1,row+1, len, rows,list);
            }
        }
    }
    public static List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if(words == null || words.length == 0) return res;
        Node root = new Node();
        int len = words[0].length();
        for (String word : words) add(root,word);
        Node[] rows = new Node[len];
        for(int i = 0; i< len; i++){
            rows[i] = root;
        }
        helper(0,0,len,rows,res);
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"area","lead","wall","lady","ball"};
        List<List<String>> result = wordSquares(words);
        System.out.println(result);
    }
}
