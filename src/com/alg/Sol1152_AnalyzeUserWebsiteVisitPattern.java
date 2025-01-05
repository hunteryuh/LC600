package com.alg;

import java.lang.reflect.Array;
import java.util.*;

/*
You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].

A pattern is a list of three websites (not necessarily distinct).

    For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.

The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.

    For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
    Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
    Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.

Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.

doordash onsite consolidate doordash
https://leetcode.com/discuss/interview-question/1583430/doordash-questions-consolidated
 */
public class Sol1152_AnalyzeUserWebsiteVisitPattern {
    // https://leetcode.com/problems/analyze-user-website-visit-pattern/solutions/355606/java-very-easy-understand-with-explanation/
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            map.computeIfAbsent(username[i], u -> new ArrayList<>()).add(new Pair(timestamp[i], website[i]));
        }
        Map<String, Integer> patternCount = new HashMap<>();
        String res = "";
        for (String user: map.keySet()) {
            List<Pair> patterns = map.get(user);
            // this set is to avoid visit the same 3-seq in one user
            Set<String> set = new HashSet<>();
            Collections.sort(patterns, (a, b) -> a.time - b.time);
//            patterns.sort(Comparator.comparingInt(a -> a.time));
            // brutal force O(N ^ 3)
            for (int i = 0; i < patterns.size(); i++) {
                for (int j = i + 1; j < patterns.size(); j++) {
                    for (int k = j + 1; k < patterns.size(); k++) {
                        String pattern = patterns.get(i).website + " " + patterns.get(j).website + " " + patterns.get(k).website;
                        if (!set.contains(pattern)) {
                            patternCount.put(pattern, patternCount.getOrDefault(pattern, 0) + 1);
                            set.add(pattern);
                        }
                        if (res.equals("") || patternCount.get(pattern) > patternCount.get(res) ||
                            (patternCount.get(pattern) == patternCount.get(res) && pattern.compareTo(res) < 0)) {
                            res = pattern;
                        }
                    }
                }
            }

        }
        return Arrays.asList(res.split(" "));
    }

    public static void main(String[] args) {
        Map<String, Integer> t = new HashMap<>();
        System.out.println(t.get(""));
    }

    class Pair {
        int time;
        String website;

        Pair(int time, String website) {
            this.time = time;
            this.website = website;
        }
    }
}
