package com.alg;

import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < words.length ; i++) {
            if (count + words[i].length() + i - last > L) {
                int spacenum = 0;
                int extraspace = 0;
                if (i - last - 1 > 0) {
                    spacenum = (L - count )/(i - last - 1);
                    extraspace = (L-count)%(i - last - 1);
                }

                StringBuilder sb = new StringBuilder();
                for (int j = last; j < i; j++) {
                    sb.append(words[j]);
                    if (j < i-1) {
                        for(int k = 0; k < spacenum; k++){
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
        for (int i =ll.length() ; i < L; i++) {
            ll.append(" "); // needed?
        }
        res.add(ll.toString());
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int t = 16;
        List<String> result = justifyText(words,t);
        for (String s: result) {
            System.out.println(s);
        }
    }

    public static List<String> justifyText(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int width = words[i].length();
            // position of last word in line
            int last = i + 1;
            while (last < words.length && width + words[last].length() + 1 <= maxWidth) {
                width = width + words[last].length() + 1; // here with one space between words
                last++;
            }
            StringBuilder sb = new StringBuilder();
            int numofSpacer = last - i - 1;
            System.out.println("last is :" + last);
            System.out.println("num of spacers: " + numofSpacer);
            // last line or only one word in this line, left-justified
            if (last == words.length || numofSpacer == 0) {
                sb.append(words[i]);
                for (int j = i + 1; j < last; j++) {
                    sb.append(" ").append(words[j]);  // if there is more than one word in the line
                }
                for (int j = sb.length(); j< maxWidth; j++) {
                    sb.append(" ");
                }
            } else {
                System.out.println("width is: " + width); // 4 + 1 + 2 + 1 + 2  = 10
                int numOfSpaces = (maxWidth - width) / numofSpacer;  // (16 - 10) / 2 = 3 without basic one-space separator
                System.out.println("num of spaces: " + numOfSpaces); // 3
                // extra space
                int numOfExtra = (maxWidth - width) % numofSpacer;  // 6 % 2 == 0
                System.out.println("num of numOfExtra: " + numOfExtra);
                for (int j = i; j < last; j++) {
                    sb.append(words[j]);
                    if (j < last - 1) {
                        for (int k = 0; k <= numOfSpaces; k++) { // actual space: numOfSpaces + 1
                            sb.append(" ");
                        }
                        if (numOfExtra > 0) {
                            sb.append(" ");
                            numOfExtra--;
                        }
                    }

                }
            }
            res.add(sb.toString());
            i = last;
        }
        return res;
    }
}
