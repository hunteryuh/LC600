package com.alg;
/*
We have two special characters:

The first character can be represented by one bit 0.
The second character can be represented by two bits (10 or 11).
Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.



Example 1:

Input: bits = [1,0,0]
Output: true
Explanation: The only way to decode it is two-bit character and one-bit character.
So the last character is one-bit character.
Example 2:

Input: bits = [1,1,1,0]
Output: false
Explanation: The only way to decode it is two-bit character and two-bit character.
So the last character is not one-bit character.


Constraints:

1 <= bits.length <= 1000
bits[i] is either 0 or 1.
 */
public class Sol717_OneBitCharacter {
    public boolean isOneBitCharacter(int[] bits) {
        return false;
    }
    /**
 * The problem:
 *
 * We have a need to process text files that are encoded in a unique fashion. Inside
 * each file, a character is encoded as either a single byte or as a sequence of two
 * bytes using the following rules:
 *   - If the initial byte in a character is <= 127, then that byte represents the entire
 *     character.
 *   - If the initial byte in a character is > 127, then that byte is the first in a
 *     double-byte character.
 *   - The second byte of a double-byte character can have any 8-bit value (0-255).
 *
 * It's of interest to be able to examine the last character in such a file.
 *
 * Your task is to write a method that can compute, for a given file, the size of the
 * last character: Is the last character a 1-byte character or a 2-byte character?
 *
 * For example, the method signature might be:
 *
 *   int getSizeOfLastChar(byte[] data)
 *
 * where the data array represents the contents of the file.
 *
 * In your implementation, you can assume that the file is "well-formed" according to
 * the encoding described above.
 */

    // q -> 222, 223
    // abcF
    // a -> 210
    // b -> 220, 210
    // abba : 110 220 210 220 210 110
    // 110, 128, 200, 129
    public int getSizeOfLastChar2(byte[] data) {
        int size = data.length;
        if (size == 0) return 1; // empty data
        int i = 0; // cursor to move from the beginning to the end
        while (i < size) {
            byte cur = data[i];
            if (cur <= 127) { // get ASCII value of the byte
                i++;
            } else {
                i += 2;
            }
            if (i == size - 1) { // only 1 left
                return 1;
            }
        }
        // i == size assume it is well-formed
        return 2;
    }

    // assume the file is well encoded.
    // 222, 222  2
    //  0, 222   x
    // 0, 222, 111  2
    // 222, 222, 111  ??
    // 0, 111, 111
    // 222, 111, 111

    public int getSizeOfLastChar(byte[] data) {
        int size = data.length;
        if (size == 1) return 1;
        int i = size - 2;
        int count = 0;
        while (i >= 0 && data[i] > 127) {
            i--;
            count++;
        }
        if (count % 2 == 0) {
            return 1;
        }
        return 2;
    }
}
