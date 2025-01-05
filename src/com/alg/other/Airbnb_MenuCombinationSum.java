package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Airbnb_MenuCombinationSum {
    public List<List<Double>> getCombos(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        if (prices == null || prices.length == 0) {
            return res;
        }
        int centsTarget = (int) Math.round(target * 100);
        Arrays.sort(prices);
        int[] centPrices = new int[prices.length];
        for (int i = 0; i < centPrices.length; i++) {
            centPrices[i] = (int) Math.round((prices[i] * 100));
        }
        search(res, centPrices, 0, centsTarget, new ArrayList<>(), prices);
        return res;
    }

    private void search(List<List<Double>> res, int[] centPrices, int start, int centsTarget,
                        List<Double> combo, double[] prices) {
        if (centsTarget == 0) {
            res.add(new ArrayList<>(combo));
            return;
        }
        for (int i = start; i < centPrices.length; i++) {
            if (i > start && centPrices[i] == centPrices[i - 1]) {
                continue;
            }
            if (centPrices[i] > centsTarget) break;
            combo.add(prices[i]);
            search(res, centPrices, i + 1, centsTarget - centPrices[i], combo, prices);
            // the new start is i + 1, not start + 1, 每个数字在每个组合中只能使用一次
            combo.remove(combo.size() - 1);
        }
    }

    public static void main(String[] args) {
        Airbnb_MenuCombinationSum sol = new Airbnb_MenuCombinationSum();
        double[] prices = {10.02, 1.11, 2.22, 3.01, 4.02, 2.00, 5.03};
        List<List<Double>> combos = sol.getCombos(prices, 7.03);
        System.out.println(combos);
    }

}
