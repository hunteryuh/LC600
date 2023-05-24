package com.alg;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by HAU on 10/30/2017.
 */
public class Sol388_LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int maxLen = 0;
        for(String s : input.split("\n")){
            int level = s.lastIndexOf("\t") + 1; // number of "\t"
            while ( level + 1 < stack.size()) stack.pop(); // if same level, remove the previous one
            int len = stack.peek() + s.length() - level + 1; // add 1 to represent the length of "/"
            stack.push(len);

            if (s.contains(".")) maxLen = Math.max(maxLen, len - 1);  // len -1 to remove the "/" in the end

        }
        return maxLen;
    }
}
