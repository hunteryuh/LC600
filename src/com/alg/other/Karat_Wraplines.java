package com.alg.other;

import java.util.ArrayList;
import java.util.List;
/*
实现函数wrapLines(String[] words, int maxLen): 给一个string数组 string[] words，要求把word依次用-连接拼凑成n个句子，每个句子长度不超过maxLen。
例如：
输入words = {"a", "b", "cd", "e"}, maxLen = 4, 输出 {"a-b", "cd-e"}
输入words = {"a", "b", "cd", "e"}, maxLen = 10, 输出 {"a-b-cd-e"}
 */

public class Karat_Wraplines {
    public List<String> wrapLines(String[] words, int maxLen) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (sb.length() + word.length() + 1 <= maxLen) {
                if (sb.length() > 0) {
                    sb.append("-");
                }
                sb.append(word);
            } else {
                result.add(sb.toString());
//                sb.setLength(0);
//                sb.append(word);
                sb = new StringBuilder(word);
            }
        }

        if (sb.length() > 0) {
            result.add(sb.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words1 = {"a", "b", "cd", "e"};
        int maxLen1 = 4;
        Karat_Wraplines ss = new Karat_Wraplines();
        List<String> result1 = ss.wrapLines(words1, maxLen1);
        System.out.println(result1);

        String[] words2 = {"a", "b", "cd", "e"};
        int maxLen2 = 10;
        List<String> result2 = ss.wrapLines(words2, maxLen2);
        System.out.println(result2);
    }
}
