package com.alg;

import java.util.HashMap;
import java.util.Map;

/*In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them. Those answers are placed in an array.

Return the minimum number of rabbits that could be in the forest.

Examples:
Input: answers = [1, 1, 2]
Output: 5
Explanation:
The two rabbits that answered "1" could both be the same color, say red.
The rabbit than answered "2" can't be red or the answers would be inconsistent.
Say the rabbit that answered "2" was blue.
Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.

Input: answers = [10, 10, 10]
Output: 11

Input: answers = []
Output: 0*/
public class Sol781_RabbitsInForest {
    public static int numRabbits(int[] answers) {
        int res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int n: answers){
            map.put(n,map.getOrDefault(n,0) + 1);
        }
        for(Integer i: map.keySet()){
            int group = map.get(i)/(i+1);
            res += map.get(i)%(i+1) != 0 ? (group + 1) *(i+1) : group*(i+1);
            /*If x+1 rabbits have same color, then we get x+1 rabbits who all answer x.
now n rabbits answer x.
If n%(x+1)==0, we need n/(x+1) groups of x+1 rabbits.
If n%(x+1)!=0, we need n/(x+1) + 1 groups of x+1 rabbits.*/

        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1,1,2};
        System.out.println(numRabbits(a));//5
        int[] b = {1,1,1};
        System.out.println(countRabits(b));//4
    }
    // approach 2
    public static int countRabits(int[] answers){
        if( answers.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        for(int i : answers){
            if(i == 0){
                sum += 1;
                continue;
            }
            if(!map.containsKey(i)){
                // haven't counted this rabit color, count it plus itself 1
                map.put(i,0);
                sum += i + 1;
            }else{
                map.put(i,map.get(i) + 1);
                // if there are k of each color and they are all present, remove them to allow the change to account for others
                if(map.get(i) == i){
                    map.remove(i); // if all present
                }
            }
        }
        return sum;
    }
}
