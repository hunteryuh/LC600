package com.alg;

import apple.laf.JRSUIUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.

Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.

Design an algorithm that:

    Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
    Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
    Finds the maximum price the stock has been based on the current records.
    Finds the minimum price the stock has been based on the current records.

Implement the StockPrice class:

    StockPrice() Initializes the object with no price records.
    void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
    int current() Returns the latest price of the stock.
    int maximum() Returns the maximum price of the stock.
    int minimum() Returns the minimum price of the stock.



Example 1:

Input
["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
Output
[null, null, null, 5, 10, null, 5, null, 2]

Explanation
StockPrice stockPrice = new StockPrice();
stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                          // Timestamps are [1,2] with corresponding prices [3,5].
stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.



Constraints:

    1 <= timestamp, price <= 109
    At most 105 calls will be made in total to update, current, maximum, and minimum.
    current, maximum, and minimum will be called only after update has been called at least once.


 */
public class Sol2034_StockPriceFluctuation {
    class StockPrice {
        int latestTime;
        Map<Integer, Integer> timestampPriceMap;
        TreeMap<Integer, Integer> priceFreq;

        public StockPrice() {
            latestTime = 0;
            timestampPriceMap = new HashMap<>();
            priceFreq = new TreeMap<>();
        }

        // time: O(logn) for each call
        public void update(int timestamp, int price) {
            latestTime = Math.max(latestTime, timestamp);
            if (timestampPriceMap.containsKey(timestamp)) {
                int oldPrice = timestampPriceMap.get(timestamp);
                priceFreq.put(oldPrice, priceFreq.get(oldPrice) - 1);
                if (priceFreq.get(oldPrice) == 0) {
                    priceFreq.remove(oldPrice);
                }
            }
            timestampPriceMap.put(timestamp, price);
            priceFreq.put(price, priceFreq.getOrDefault(price, 0) + 1);
        }

        // constant time
        public int current() {
            return timestampPriceMap.get(latestTime);
        }

        // constant time
        public int maximum() {
            return priceFreq.lastKey();
        }

        // constant time
        public int minimum() {
            return priceFreq.firstKey();
        }
    }

    // approach 2 ,using heap
    class StockPrice2 {
        private int latestTime;
        // Store price of each stock at each timestamp.
        private Map<Integer, Integer> timestampPriceMap;

        // Store stock prices in sorted order to get min and max price.
        private PriorityQueue<int[]> minHeap, maxHeap;

        public StockPrice2() {
            latestTime = 0;
            timestampPriceMap = new HashMap<>();
            minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        }

        public void update(int timestamp, int price) {
            // Update latestTime to latest timestamp.
            latestTime = Math.max(latestTime, timestamp);

            // Add latest price for timestamp.
            timestampPriceMap.put(timestamp, price);

            minHeap.add(new int[]{ price, timestamp });
            maxHeap.add(new int[]{ price, timestamp });
        }

        public int current() {
            // Return latest price of the stock.
            return timestampPriceMap.get(latestTime);
        }

        public int maximum() {
            int[] top = maxHeap.peek();
            // Pop pairs from heap with the price doesn't match with hashmap.
            while (timestampPriceMap.get(top[1]) != top[0]) {
                maxHeap.poll();
                top = maxHeap.peek();
            }

            return top[0];
        }

        public int minimum() {
            int[] top = minHeap.peek();
            // Pop pairs from heap with the price doesn't match with hashmap.
            while (timestampPriceMap.get(top[1]) != top[0]) {
                minHeap.remove();
                top = minHeap.peek();
            }

            return top[0];
        }
    }
}
