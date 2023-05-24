package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HAU on 8/16/2017.
 */

/*In English, we have a concept called root, which can be followed by some other words to form another longer word
        - let's call this word successor. For example, the root an, followed by other, which can form another word another.

        Now, given a dictionary consisting of many roots and a sentence.
        You need to replace all the successor in the sentence with the root forming it.
        If a successor has many roots can form it, replace it with the root with the shortest length.

        You need to output the sentence after the replacement.

        \s	Matches the whitespace. Equivalent to [\t\n\r\f].*/

//solution 0: can use Trie
    // solution without using Trie below
public class Sol648_replaceWords {
    public static String replaceWords(List<String> dict, String sentence){
        if (dict == null || dict.size() == 0) return sentence;
        Set<String> set = new HashSet<>();
        for (String s : dict) set.add(s);

        StringBuilder sb  = new StringBuilder();
        String[] words = sentence.split("\\s+");
        //String[] words = sentence.split(" ");

        for ( int i = 0; i < words.length; i++){
            String prefix = "";
            for (int j = 0; j < words[i].length();j++){
                prefix = words[i].substring(0,j+1);
                if (set.contains(prefix)) {
                    break;
                }

            }
            sb.append(" " + prefix);
        }
        return sb.deleteCharAt(0).toString();


    }

    public static void main(String[] args) {
        String sentence = "the cattle was rattled by the battery";
        List<String> dict = Arrays.asList("cat", "bat", "rat");
        System.out.println(replaceWords(dict,sentence));
    }

}
