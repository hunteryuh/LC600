package com.alg.other;

import java.util.*;
import java.util.ArrayList;

/*
I have a list, return RLE (run length encoding) representation of the list.
Example:
Input: [10, 10, 10, 10, 10]
Output: [ (5, 10)]
Input: [10, 10, 10, 10, 10, 20, 20, 20]
Output: [ (5, 10), (3, 20)]
Question 2:
Given a list, return RLE representation of the list, using the variation. (count, initialValue, diff)
Example:~
Input: [10, 10, 10, 10, 10]
Output: [ (5, 10, 0)]
Input: [10, 11, 12, 10, 10]
Output: [ (3, 10, 1), (2, 10, 0)].

https://www.1point3acres.com/bbs/thread-1096683-1-1.html
 */
public class Airbnb_RunLengthEncoding {
    public static List<int[]> rleEncode(int[] nums) {
        List<int[]> result = new ArrayList<>();
        if (nums.length == 0) return result;

        int count = 1;
        int value = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == value) {
                count++;
            } else {
                result.add(new int[]{count, value});
                count = 1;
                value = nums[i];
            }
        }
        result.add(new int[]{count, value}); // add the last segment

        return result;
    }

    public static List<int[]> runLengthEncodeWithDiff(int[] input) {
        List<int[]> encodedList = new ArrayList<>();
        if (input == null || input.length == 0) {
            return encodedList;
        }

        int count = 1;
        int initialValue = input[0];
        int diff = (input.length > 1) ? input[1] - initialValue : 0;

        for (int i = 1; i < input.length; i++) {
            int currentDiff = input[i] - input[i - 1];
            if (currentDiff == diff) {
                count++;
            } else {
                encodedList.add(new int[]{count, initialValue, diff});
                initialValue = input[i];
                diff = (i + 1 < input.length) ? input[i+1]- initialValue : 0;
                count = 1;
            }
        }

        // Add the last sequence
        encodedList.add(new int[]{count, initialValue, diff});

        return encodedList;
    }

    public static void main(String[] args) {
        int[] input1 = {10, 10, 10, 10, 10};
        int[] input11 = {1,1,1,3,6,6,1,1};

        List<int[]> result1 = rleEncode(input11);
        for (int[] a: result1) {
            System.out.print(Arrays.toString(a));
        }
        System.out.println();

//
        int[] input2 = {10, 11, 12, 10, 10};
        List<int[]> result2 = runLengthEncodeWithDiff(input2); // Output: [(3, 10, 1), (2, 10, 0)]
        for (int[] b: result2) {
            System.out.println(Arrays.toString(b));
        }

        RLEDiffDecoder diffDecoder = new RLEDiffDecoder(result2);
        while (diffDecoder.hasNext()) {
            System.out.print(diffDecoder.next() + " ");
        }


    }

    // decoder of rle with diff
    public static class RLEDiffDecoder implements Iterator<Integer> {
        private final List<int[]> encodedList;
        private int currentIndex;
        private int currentCount;
        private int currentValue;
        private int currentDiff;

        public RLEDiffDecoder(List<int[]> encodedList) {
            this.encodedList = encodedList;
            this.currentIndex = 0;
            this.currentCount = 0;
            if (!encodedList.isEmpty()) {
                int[] firstGroup = encodedList.get(0);
                this.currentCount = firstGroup[0];
                this.currentValue = firstGroup[1];
                this.currentDiff = firstGroup[2];
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < encodedList.size() && currentCount > 0;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int valueToReturn = currentValue;
            currentCount--;
            currentValue += currentDiff;

            if (currentCount == 0 && currentIndex < encodedList.size() - 1) {
                currentIndex++;
                int[] nextGroup = encodedList.get(currentIndex);
                currentCount = nextGroup[0];
                currentValue = nextGroup[1];
                currentDiff = nextGroup[2];
            }

            return valueToReturn;
        }
    }
}
