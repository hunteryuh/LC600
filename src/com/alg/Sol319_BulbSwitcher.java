package com.alg;

/**
 * Created by HAU on 12/2/2017.
 */
/*There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3.

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].

So you should return 1, because there is only one bulb is on.
题意：
有 n 个灯泡，初始状态是off，
第一轮：全部变成on
第二轮：每两个一组，把第二个切换一下
第三轮：每三个一组，把第三个切换一下
。。。
第n轮：全部n个一组，把第n个切换一下
在这n轮之后，找出有多少个灯泡是on
*/
/*大概意思是，要在[1,n]里头找哪些灯泡被执行了奇数次操作
 对于一个非平方数来说（比如12），因为有成对的补数（1vs12; 2vs6），所以总是会按下2的倍数次
但是对于一个平方数来说（比如36），因为其中有个数(6)它的补数是自己，所以会按被下奇数次
然后这道题就变成了找[1,n]中间有几个平方数了.

而因子都是成对出现的，之所以出现了奇数个因子，是因为有个因子的平方＝这个数
即此数为完全平方数

对n开平方，则小于等于n的 完全平方数 的个数 就是n开方后取整
*/
public class Sol319_BulbSwitcher {
    public static int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    // naive method
    public static int bulbcount(int n){
        int count = 0;
        for ( int i = 1; i <=n; i++){
            int numSwitch = helper(i);
            if (numSwitch % 2 == 1) count++;
        }
        return count;
    }
    public static int helper(int n){
        int c = 0;
        for (int i = 1 ; i <= n ;i++){
            if (n%i == 0) // is a factor of n
                c++;
        }
        return c;
    }
}
