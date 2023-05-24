package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/22/2018.
 */
/*Count the number of prime numbers less than a non-negative number, n.*/
public class Sol204_CountPrimes {
    /*埃拉托斯特尼筛法 Sieve of Eratosthenes
复杂度

时间 O(NloglogN) 空间 O(N)
思路

如果一个数是另一个数的倍数，那这个数肯定不是素数。利用这个性质，我们可以建立一个素数数组，从2开始将素数的倍数都标注为不是素数。
第一轮将4、6、8等表为非素数，然后遍历到3，发现3没有被标记为非素数，则将6、9、12等标记为非素数，一直到N为止，再数一遍素数数组中有多少素数。*/
    public static int countPrimes(int n){
        boolean[] prime = new boolean[n];
        Arrays.fill(prime,true);
        for(int i = 2; i < n; i++){
            if(prime[i]){
                // 将i的2倍、3倍、4倍...都标记为非素数
                for(int j = i *2; j < n; j = j + i){
                    prime[j] = false;
                }
            }
        }
        int count = 0;
        for(int i = 2; i < n ;i++){
            if(prime[i]) count++;
        }
        return count;
    }
    // same method, writing 2
    public int countPrimes2(int n) {
        boolean notPrime[] = new boolean[n + 2];
        notPrime[0] = notPrime[1] = true;
        for (int i = 2; i * i < n; i++) {
            if (!notPrime[i]) {
                int c = i * i;
                while (c < n) {
                    notPrime[c] = true;
                    c += i;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!notPrime[i])
                ans ++;
        }
        return ans;
    }
}
