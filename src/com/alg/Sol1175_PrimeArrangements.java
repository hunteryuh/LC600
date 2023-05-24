package com.alg;
/*
Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)

(Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)

Since the answer may be large, return the answer modulo 10^9 + 7.



Example 1:

Input: n = 5
Output: 12
Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.

Example 2:

Input: n = 100
Output: 682289015



Constraints:

    1 <= n <= 100


 */
// put prime number in prime location
public class Sol1175_PrimeArrangements {
    public int numPrimeArrangements(int n) {
        if(n < 2){
            return 1;
        }
        long res = 1;
        int prime = 1;
        int notPrime = 1;
        for(int i = 3; i <= n; i++){
            if(isPrime(i)){
                prime++;
            }else{
                notPrime++;
            }
        }
        for(int i = prime; i > 0; i--){
            res *= i;
            res %= 1000000007;
        }
        for(int i = notPrime; i > 0; i--){
            res *=i;
            res %= 1000000007;
        }
        return (int)res;
    }
    public boolean isPrime(int num){
        for(int i = 2; i <= num / 2; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
