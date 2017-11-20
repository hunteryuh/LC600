package com.alg;

/**
 * Created by HAU on 11/19/2017.
 */
/*Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.

Input:
['a','b','b','b','b','b','b','b','b','b','b','b','b']

Output:
Return 4, and the first 4 characters of the input array should be: ['a','b','1','2'].

Follow up:
Could you solve it using only O(1) extra space?*/
public class Sol443_StringCompression {
    public static int compress(char[] chars) {
        // main three pointer
        // read: where we are reading
        // write: where we are writing
        // anchor: the start position of the contiguous group of characters we are currently reading
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++){
            if (read + 1 == chars.length || chars[read + 1] != chars[read]){
                chars[write++] = chars[anchor];
                if (read > anchor){
                    for (char c:("" + (read - anchor + 1)).toCharArray()){
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        char[] c1= {'a','b','b','k','k','k'};
        char[] c2= {'k','t'};
        char[] c3= {'k','k','k','k','k','k','k','k','k','k','k','k','k','k','k','k','t'};
        System.out.println(compress(c1));
        System.out.println(compress(c2));
        System.out.println(compress(c3));

    }
}
