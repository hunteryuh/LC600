package com.alg.other;
/*
Change in a Foreign Currency
You likely know that different currencies have coins and bills of different denominations. In some currencies, it's actually impossible to receive change for a given amount of money. For example, Canada has given up the 1-cent penny. If you're owed 94 cents in Canada, a shopkeeper will graciously supply you with 95 cents instead since there exists a 5-cent coin.
Given a list of the available denominations, determine if it's possible to receive exact change for an amount of money targetMoney. Both the denominations and target amount will be given in generic units of that currency.
Signature
boolean canGetExactChange(int targetMoney, int[] denominations)
Input
1 ≤ |denominations| ≤ 100
1 ≤ denominations[i] ≤ 10,000
1 ≤ targetMoney ≤ 1,000,000
Output
Return true if it's possible to receive exactly targetMoney given the available denominations, and false if not.
Example 1
denominations = [5, 10, 25, 100, 200]
targetMoney = 94
output = false
Every denomination is a multiple of 5, so you can't receive exactly 94 units of money in this currency.
Example 2
denominations = [4, 17, 29]
targetMoney = 75
output = true
You can make 75 units with the denominations [17, 29, 29].

 */
public class FB_ChangeInAForeignCurrency {
    public boolean canGetExactChange(int targetMoney, int[] denominations) {
        if (targetMoney < 0) {
            return false;
        }
        if (targetMoney == 0) {
            return true;
        }
        for (int den: denominations) {
            if (canGetExactChange(targetMoney - den, denominations)) {
                return true;
            }
        }
        return false;
    }

    // backtrack solution 2
    public boolean canGetExactChange2(int targetMoney, int[] denominations) {
        return dfs(targetMoney, denominations, 0);
    }

    private boolean dfs(int targetMoney, int[] denominations, int k) {
        if (targetMoney == 0) return true;
        for (int i = k; i < denominations.length; i++) {
            if (targetMoney >= denominations[i]) {
                if(dfs(targetMoney - denominations[i], denominations, i)) {
                    return true;
                };
            }
        }
        return false;
    }
}
