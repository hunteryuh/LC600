package com.alg;

import java.util.ArrayList;
/*
Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.


Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 */
public class Sol68_TextJustification {
    public static ArrayList<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> res = new ArrayList<>();
        int L = maxWidth;
        if (words == null || words.length == 0) return res;
        int count = 0; // length of word[i-1]
        int last = 0;

        for (int i = 0; i < words.length ; i++){
            if ( count + words[i].length() + i - last > L){
                int spacenum = 0;
                int extraspace = 0;
                if ( i - last -1 > 0){
                    spacenum = (L - count )/(i - last - 1);
                    extraspace = ( L-count)%(i - last - 1);
                }
                StringBuilder sb = new StringBuilder();
                for ( int j = last; j < i; j++){
                    sb.append(words[j]);
                    if ( j< i-1){
                        for( int k = 0; k < spacenum; k++){
                            sb.append(" ");
                        }
                        if (extraspace > 0){
                            sb.append(" ");
                            extraspace--;
                        }
                    }
                }
                // if at most one word in the line
                for (int j = sb.length(); j<L; j++){
                    sb.append(" ");
                }
                res.add(sb.toString());
                count = 0;
                last = i;
            }
            count += words[i].length();

        }
        StringBuilder ll = new StringBuilder();
        for (int i = last; i < words.length; i++){
            ll.append(words[i]);
            if (ll.length() < L){
                ll.append(" ");
            }
        }
        for ( int i =ll.length() ; i < L; i++){
            ll.append(" "); // needed?
        }
        res.add(ll.toString());
        return res;

    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int t = 16;
        ArrayList<String> result = fullJustify(words,t);
        for (String s: result){
            System.out.println(s);
        }
    }
}
