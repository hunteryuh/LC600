package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by HAU on 11/5/2017.
 */
/*Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
then the word with the lower alphabetical order comes first.*/
/*哈希表 + 优先队列（堆）*/
public class Sol692_TopKFrequentWords {
    public static List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> count = new HashMap<>();
        for (String word : words){
            count.put(word, count.getOrDefault(word,0) + 1);
        }
        // min heap
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a,b)-> count.get(a) == count.get(b)?
                        b.compareTo(a) : count.get(a) - count.get(b)
        );
        //出现频率低、字典序大的单词优先弹出,  i.e min-heap, the item needs to be polled first at the head of the queue, which are lower frequency, or higher alphabetical
        // After reading others' comments, I realized that the reason is a word that has a higher alphabetical order can stay higher in the min-heap.
        //For example, when min-heap has "abc", "abd", "bun", min-heap should have them in this order to be removed: "bob", "bun", "ban".
        for (String word: count.keySet()) {
            pq.add(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        LinkedList<String> list = new LinkedList<>();
//        ArrayList<String> list2 = new ArrayList<>();
        while (!pq.isEmpty()){
            list.addFirst(pq.poll());
        }
        return list;
    }

    public static void main(String[] args) {
        int k = 4;
        String[] arr = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        System.out.println(topKFrequent(arr,4));
    }
}
