package com.alg;

/**
 * Created by HAU on 7/4/2017.
 */
/*Some hints:
        Could negative integers be palindromes? (ie, -1)

        If you are thinking of converting the integer to string, note the restriction of using extra space.

        You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

        There is a more generic way of solving this problem.*/
//Determine whether an integer is a palindrome. Do this without extra space.
public class Sol9_PalindromeNumber {
    public static boolean isPalindrome(int x) {
        boolean res = true;
        if ( x == 0) res = true;
        else if ( x < 0) res = false;
        else {
            int count = 0;
            int t = x;
            while ( x != 0) {
                count++;
                x /= 10;
            }
            x = t;
            while ( x != 0){
                int digit = x % 10;
                int front = x / (int) Math.pow(10,count - 1);
                res = digit == front;
                if ( res == false) {
                    return res;
                }else {
                    x = (x - front * (int) Math.pow(10,count - 1) ) / 10;
                    count -= 2;
                }
                if ( count == 1 || count == 0) return true;
            }
        }
        return res;
    }

    // below will cause overflow, but overflow still returns false anyway
    public boolean isPanlindrome3(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return reverse == x || x == reverse / 10;   // even || odd
    }
    // no overflow
    public static boolean isPalindrome_2(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev * 10 + x % 10;
            x = x/10;
        }
        return (x==rev || x==rev/10);
        // 12321
        // 1232 1
        // 123 12
        // 12 123
        //12 == 123/10
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(10101));
        //System.out.println(isPalindrome(12321));
        //System.out.println(isPalindrome(29));
        System.out.println(isPalindrome_2(10));
    }
}
