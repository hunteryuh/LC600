package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
https://www.1point3acres.com/bbs/thread-911199-1-1.html
遇到新题了，题感觉也不简单, 就当攒人品 move on 了
给一个list of 同义词 和 一个list of sentences， 把相似的sentence group到一起
ie：input: [("main", "primary"), ("main", "important"), ("rating", "score")]
 ["main email" , "secondary email", "primary email", "important email", "performance rating", "performance score"]
output: [ ["main email", "primary email", "importan‍‍‌‍‍‌‍‌‌‌‌‍‍‍‌‍t email"], ["secondary email"], ["performance rating", "performance score"] ]
注意 相似的sentence是指这个sentence中每个word要么相同要么是同义词，比较的时候只要比较每个word就行了
 */
public class RP_GroupSimilarSentence {
    public List<List<String>> findSimilarSentences(String[][] wordPairs, String[] sentences) {
        Map<String, Integer> wordMap = new HashMap<>();
        int index = 0;
        for (String[] pairs : wordPairs) {
//            Set<String> set = new HashSet<>(Arrays.asList(pairs));
            if (!wordMap.containsKey(pairs[0]) && !wordMap.containsKey(pairs[1])) {
                index++;
                wordMap.put(pairs[0], index);
                wordMap.put(pairs[1], index);
            } else {
                wordMap.put(pairs[0], index);
                wordMap.put(pairs[1], index);
            }
        }
        System.out.println(wordMap);
        Map<String, List<String>> sentenceMap = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            String[] words = sentence.split(" ");
            StringBuilder key = new StringBuilder();
            for (String word: words) {
                if (wordMap.containsKey(word)) {
                    key.append(wordMap.get(word));
                } else {
                    key.append(word);
                }
            }
            sentenceMap.computeIfAbsent(key.toString(), x -> new ArrayList<>()).add(sentence);
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> values : sentenceMap.values()) {
            res.add(values);
        } //         res.addAll(sentenceMap.values());
        return res;
    }

    public static void main(String[] args) {
        RP_GroupSimilarSentence rs = new RP_GroupSimilarSentence();
        String[][] wordPairs = new String[][]{
            {"main", "primary"},
            {"main", "important"},
            {"score", "rating"}
        };
        String[] sentences = new String[]{
            "main email" , "secondary email", "primary email", "important email", "performance rating", "performance score"
        };

        List<List<String>> res = rs.findSimilarSentences(wordPairs, sentences);
        System.out.println(res);

    }
}
