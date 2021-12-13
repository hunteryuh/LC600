package com.alg.greedy;
/*
At a lemonade stand, each lemonade costs $5.
Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
You must provide the correct change to each customer so that the net transaction is that the customer pays $5.

Note that you don't have any change in hand at first.

Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide every customer with correct change, or false otherwise.



Example 1:

Input: bills = [5,5,5,10,20]
Output: true
Explanation:
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.
Example 2:

Input: bills = [5,5,10,10,20]
Output: false
Explanation:
From the first two customers in order, we collect two $5 bills.
For the next two customers in order, we collect a $10 bill and give back a $5 bill.
For the last customer, we can not give change of $15 back because we only have two $10 bills.
Since not every customer received correct change, the answer is false.
Example 3:

Input: bills = [5,5,10]
Output: true
Example 4:

Input: bills = [10,10]
Output: false
 */
public class Sol860_LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) {
            return true;
        }

        int fives = 0, tens = 0;
        for (int bill: bills) {
            if (bill == 5) {
                fives++;
            } else if (bill == 10) {
                tens++;
                if (fives > 0) {
                    fives--;
                } else {
                    return false;
                }
            } else /*bill == 20*/ {
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives>=3) {
                    fives -=3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
