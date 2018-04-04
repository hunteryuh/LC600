package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HAU on 12/1/2017.
 */
/*Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/
public class Sol170_TwoSumIII_DataStructureDesign {
    class TwoSum {

        //O1 time for add, O(n) time for find, O(n) space
        private List<Integer> list = new ArrayList<>();
        private Map<Integer,Integer> map = new HashMap<>();
        /** Initialize your data structure here. */
        public TwoSum() {

        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            if(map.containsKey(number)){
                map.put(number,map.get(number) + 1);
            }else{
                map.put(number,1);
                list.add(number);
            }
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for (int i = 0; i<list.size(); i++){
                int num1 = list.get(i), num2 = value - num1;
                if( (num1 == num2 && map.get(num1)> 1) || (num1!=num2 && map.containsKey(num2)) )
                    return true;
            }
            return false;
        }

        // method 2, jus tone hashmap
        public void add2(int number){
            map.put(number, map.containsKey(number)? map.get(number) + 1 : 1);
        }
        public boolean find2(int val){
            for (Map.Entry entry: map.entrySet()){
                int n1 = (int)entry.getKey();
                int n2 = val - n1;
                if ( (n1 == n2 && (int)entry.getValue() > 1) || (n1!=n2) && map.containsKey(n2)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Your TwoSum object will be instantiated and called as such:
     * TwoSum obj = new TwoSum();
     * obj.add(number);
     * boolean param_2 = obj.find(value);
     */


}
