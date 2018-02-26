package com.alg;
/*In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.*/

import java.util.Arrays;
import java.util.HashMap;

/* backtracking + memorization
* recursion + hashmap
* time: 2^n*/
public class Sol464_CanIWin {
    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1+maxChoosableInteger)*maxChoosableInteger/2 <desiredTotal){
            return false; // desiredtotal too big
        }
        if(desiredTotal <= 0) return true;
        int[] state = new int[maxChoosableInteger + 1]; // number is the same is index
        return canWin(state,desiredTotal, new HashMap<String, Boolean>());
    }

    private static boolean canWin(int[] state, int total, HashMap<String, Boolean> map) {
        String key = Arrays.toString(state);// convert the state array to a string
        if(map.containsKey(key)){
            return map.get(key);
        }
        for(int i = 1; i < state.length; i++){
            if(state[i] == 0){
                state[i] = 1;
                if(total - i <= 0 || !canWin(state, total - i, map)){ // or opponent cannot win
                    map.put(key,true);
                    state[i] = 0;
                    return true;
                }
                state[i] = 0; // did not win at this i, reset its state
            }
        }
        map.put(key,false);
        return false;
    }
}
