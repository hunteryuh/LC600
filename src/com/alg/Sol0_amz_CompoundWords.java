package com.alg;
/*
You are given a potentially large list of words. Some of these are compounds, where all parts are also in the list. Identify all combinations where one words can be composed of two or more words of the same list and print or return them.

Example
Input:

[rockstar, rock, star, rocks, tar, stars, rockstars, super, highway, high, way, superhighway]
Output:

rockstar -> rock + star
rockstar -> rocks + tar
superhighway -> super + highway
superhighway -> super + high + way
If returning, the output would be a list of lists, e.g.

[[rock, star], [rocks, tar], [super, highway], [super, high, way],...]
Solution
The task is to identify all combinations where one word is a composite of two or more words from the same list and print or return them.

The easiest solution is probably to have three nested loops, one for the prefix one for the suffix, one for the complete word. While this detects all possible combinations, it has very bad runtime complexity (O(n3)). Every SDE1 should be able to come up with this and also hopefully see how bad it is. It can be improved slightly by introducing a HashSet, replacing one of the loops with a contains() check. This reduces the time complexity to O(n^2^) because HashSet lookups are constant. Red flag if the candidate does the contains on a list, not a HashSet.

The optimal solution is O(n * m2), where n is the list size and m is the max word length. To achieve it, we need a HashSet for lookup:

copy all words to a HashSet
iterate over all words
then recursively:
for any given word, iterate over its length, splitting into prefix and suffix
if HashSet contains prefix, move prefix into a prefix list and recurse with the suffix
Other solutions that seem more intuitive, specifically all Tree- or Trie-based solutions, perform a lot worse and are usually not worth implementing.
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Tech Bar
As the Bar for Data Structures & Algorithms is similar for SDE 2 & 3, the meets criteria is to achieve a complete solution for n words. To raise the bar, the candidate needs to handle the standard solution exceptionally well, or come up with a non-standard algorithm with similar efficiency (e.g. involving dynamic programming), or master additional requirements in follow-up questions.

For SDE1, getting to an O(n^2) solution with 2 words meets, any solution for n words raises.

SDE 1:

Meets: O(n^2) solution with 2 words,
Raises: any solution for n words
SDE 2:

Meets: O(n * m2) n-way split with some guidance
Raises: O(n * m2) n-way split and with no guidance
SDE 3:

Meets: O(n * m2) n-way split and with no guidance
Raises: Non-standard algorithm with similar efficiency (e.g. involving dynamic programming), or master additional requirements in follow-up questions
 */
public class Sol0_amz_CompoundWords {
    public static List<List<String>> findComposites(List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        return wordSet.stream()
                .flatMap(word -> findCompositesForWord(word, wordSet, Collections.<String>emptyList()))
                .collect(Collectors.toList());
    }

    private static Stream<List<String>> findCompositesForWord(String word, Set<String> wordSet, List<String> prefixes) {
        if (word.isEmpty() && prefixes.size() > 1) {
            return Stream.of(prefixes);
        }

        return IntStream.rangeClosed(1, word.length())
                .filter(i -> wordSet.contains(word.substring(0, i)))
                .mapToObj(i -> new Split(word, i))
                .flatMap(s -> findCompositesForWord(s.suffix, wordSet, copyAndAdd(prefixes, s.prefix)));
    }

    private static List<String> copyAndAdd(List<String> list, String element) {
        if (list.isEmpty()) {
            return Collections.singletonList(element);
        }

        List<String> newList = new ArrayList<>(list.size() + 1);
        newList.addAll(list);
        newList.add(element);
        return newList;
    }


    private static class Split {

        final String prefix;
        final String suffix;

        Split(String base, int position) {
            prefix = base.substring(0, position);
            suffix = base.substring(position);
        }
    }

    // Java 7
    public class CompoundWords {

        private int count = 0;

        public List<List<String>> findCompounds(List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);

            List<List<String>> result = new ArrayList<>();
            for (String word : wordSet) {
                findCompoundsForWord(word, wordSet, Collections.emptyList(), result);
            }
            return result;
        }

        private void findCompoundsForWord(String word, Set<String> wordSet, List<String> prefixes,
                                          List<List<String>> result) {
            if (word.isEmpty() && prefixes.size() > 1) {
                result.add(prefixes);
                return;
            }

            for (int pos = 1; pos <= word.length(); pos++) {
                String prefix = word.substring(0, pos);
                count++;
                if (wordSet.contains(prefix)) {
                    String suffix = word.substring(pos);
                    findCompoundsForWord(suffix, wordSet, copyAndAdd(prefixes, prefix), result);
                }
            }
        }

        private List<String> copyAndAdd(List<String> list, String element) {
            if (list.isEmpty()) {
                return Collections.singletonList(element);
            }
            List<String> newList = new ArrayList<>(list.size() + 1);
            newList.addAll(list);
            newList.add(element);
            return newList;
        }

        public void main(String[] args) {
            // Example 1
            List<String> wordList = Arrays.asList("rockstar", "rock", "star", "rocks", "tar", "stars", "rockstars", "super",
                    "highway", "high", "way", "superhighway");
            List<List<String>> compounds = findCompounds(wordList);
            System.out.println(compounds);
            System.out.println(compounds.size());
            System.out.println(count);

            System.out.println();

            // Example 2
            count = 0;
            List<String> wordList2 = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa",
                    "aaaaaaaaa", "aaaaaaaaaa");
            List<List<String>> compounds2 = findCompounds(wordList2);
            System.out.println(compounds2);
            System.out.println(compounds2.size());
            System.out.println(count);
        }

    }

    // Implementation with memoization:

//    public class CompoundWords2 {

        private int count = 0;

        public List<List<String>> findCompounds2(List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);

            Map<String, List<List<String>>> memoizedSplits = new HashMap<>();
            List<List<String>> result = new ArrayList<>();
            for (String word : wordSet) {
                result.addAll(findWordSplits(word, wordSet, memoizedSplits));
            }

            return result;
        }

        private List<List<String>> findWordSplits(String word, Set<String> wordSet,
                                                         Map<String, List<List<String>>> memoizedSplits) {
            if (memoizedSplits.containsKey(word)) {
                return memoizedSplits.get(word);
            }
            List<List<String>> wordSplits = new ArrayList<>();
            for (int i = 1; i < word.length(); i++) {
                String prefix = word.substring(0, i);
                count++;
                if (wordSet.contains(prefix)) {
                    String suffix = word.substring(i);
                    if (wordSet.contains(suffix)) {
                        List<String> split = new ArrayList<>();
                        split.add(prefix);
                        split.add(suffix);
                        wordSplits.add(split);
                    }
                    List<List<String>> suffixSplits = findWordSplits(suffix, wordSet, memoizedSplits);
                    for (List<String> suffixSplit : suffixSplits) {
                        count++;
                        List<String> wordSplit = copyAndAdd2(suffixSplit, prefix);
                        wordSplits.add(wordSplit);
                    }
                }
            }
            memoizedSplits.put(word, wordSplits);
            return wordSplits;
        }

        private List<String> copyAndAdd2(List<String> list, String element) {
            if (list.isEmpty()) {
                return Collections.singletonList(element);
            }
            List<String> newList = new ArrayList<>(list.size() + 1);
            newList.add(element);
            newList.addAll(list);
            return newList;
        }

        public static void main(String[] args) {
            // Example 1
            List<String> wordList = Arrays.asList("rockstar", "rock", "star", "rocks", "tar", "stars", "rockstars", "super",
                    "highway", "high", "way", "superhighway");

            Sol0_amz_CompoundWords ss = new Sol0_amz_CompoundWords();
            List<List<String>> compounds = ss.findCompounds2(wordList);
            System.out.println(compounds);
            System.out.println(compounds.size());
            System.out.println(ss.count);

            System.out.println();

            // Example 2
            ss.count = 0;
            List<String> wordList2 = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa",
                    "aaaaaaaaa", "aaaaaaaaaa");
            List<List<String>> compounds2 = ss.findCompounds2(wordList2);
            System.out.println(compounds2);
            System.out.println(compounds2.size());
            System.out.println(ss.count);
        }
//    }
}
