package com.alg;
/*
Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.

The operation of drinking a full water bottle turns it into an empty bottle.

Return the maximum number of water bottles you can drink.

Example 1:



Input: numBottles = 9, numExchange = 3
Output: 13
Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
Number of water bottles you can drink: 9 + 3 + 1 = 13.

 */
public class Sol1518_WaterBottles {
    public static int numWaterBottles(int numBottles, int numExchange) {
        int count = 0;
        int extra = 0;
        while (numBottles > 0 ) {
            count += numBottles;
            int curNumbottle = numBottles + extra;
            extra = curNumbottle % numExchange;
            numBottles = curNumbottle / numExchange;
        }
        return count;
    }

    public static void main(String[] args) {
        int a = 9, exc = 3;
        System.out.println(numWaterBottles(a, exc));
        System.out.println(numWaterBottles(15, 4));
    }

    //O(1):
    //Key observation: A full bottle of water = An empty bottle + One (bottle) unit of water.

    // https://leetcode-cn.com/problems/water-bottles/solution/zhi-xin-meng-nan-tu-jie-yi-xia-huan-jiu-keiqk/
    //由于换酒过程本质是：我们只在乎喝到 [酒水]
    //
    //1 瓶酒 等价于 空瓶加酒水
    //
    //换酒的过程需要我们必须用瓶子装酒水，而我们不能借酒瓶，3 空瓶换 1 瓶酒，本质是总保留 1 个空瓶用于装酒
    //
    //2 空瓶 等价于 1 酒水
    //
    //所以我们开始的 9 瓶酒 就是有 9 个空瓶，手里要保留 1 个空瓶装酒，所以用于交换的只有 8 个空瓶。而 2 个空瓶等价于 1 酒水，所以我们能换到 8/2 = 4 瓶酒水。答案就是 9+4 = 13。写成公式就是：
    //
    //作者：mochi-ds
    //链接：https://leetcode-cn.com/problems/water-bottles/solution/zhi-xin-meng-nan-tu-jie-yi-xia-huan-jiu-keiqk/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int numWaterBottles2(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }
}
