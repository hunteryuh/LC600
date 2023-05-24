package com.alg.other;
/*
We have N different apps with different user growth rates. At a given time t, measured in days, the number of users using an app is
 g^t (for simplicity we'll allow fractional users), where g is the growth rate for that app.
 These apps will all be launched at the same time and no user ever uses more than one of the apps. We want to know how many total users there are when you add together the number of users from each app.
After how many full days will we have 1 billion total users across the N apps?
Signature
int getBillionUsersDay(float[] growthRates)
Input
1.0 < growthRate < 2.0 for all growth rates
1 <= N <= 1,000
Output
Return the number of full days it will take before we have a total of 1 billion users across all N apps.
Example 1
growthRates = [1.5]
output = 52
Example 2
growthRates = [1.1, 1.2, 1.3]
output = 79
Example 3
growthRates = [1.01, 1.02]
output = 1047
 */
public class FB_1BillionUsers {
    public int getBillionUsersDay(float[] growthRates) {
        // Write your code here
        int n = growthRates.length;
        int low = 1;
        int high = Integer.MAX_VALUE;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (reach1B(mid, growthRates)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if (reach1B(low, growthRates)) {
            return low;
        }
        if (reach1B(high, growthRates)) {
            return high;
        }
        return -1;
    }
    private boolean reach1B(int days, float[] rates) {
        double total = 0;
        for (int i = 0; i < rates.length; i++) {
            total += Math.pow(rates[i], days);
        }
        return total >= 1_000_000_000;
    }

    public static void main(String[] args) {
        float[] rates = {(float) 1.1, (float) 1.2, (float) 1.3};
        FB_1BillionUsers f = new FB_1BillionUsers();
        System.out.println(f.getBillionUsersDay(rates));
    }
}
