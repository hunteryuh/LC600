package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/8/2018.
 */
/*有一个容器double capacity, 還有一個array(double[] weights), 和int numOfContainers。
要求在array中选出两个weights總总和小于等于capacity但最接近capacity 然後指定到一個Container object並且return。
first和second的顺序不做要求，numOfContainer在java里好像没用,因为double[]本身就自带length信息。
*/
public class Sol0_amz_FindOptimalWeights {
    static class Container {
        public double first;
        public double second;
        public Container(double first, double second){
            this.first = first;
            this.second = second;
        }
    }

    public static Container findOptimalWeights(double capacity, double[] weights, int numOfContainers){
        Arrays.sort(weights);
        double min = 0;
        double first = 0, second = 0;
        int i = 0, j = weights.length - 1;
        while ( i < j ){
            double sum = weights[i] + weights[j];
            if (sum == capacity){
                return new Container(weights[i],weights[j]);
            }else if(  sum < capacity){
                if(sum > min) {
                    min = sum;
                    first = weights[i];
                    second = weights[j];

                }
                i++;

            }else{
                j--;
            }
        }
        return new Container(first,second);
    }

}
