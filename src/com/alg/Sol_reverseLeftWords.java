package com.alg;
/*
https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/

字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sol_reverseLeftWords {
    public String reverseLeftWords(String s, int n) {
        //abcdefg bagfedc  cdefgab
        StringBuilder sb = new StringBuilder(s);
        reverse(sb, 0, n - 1);
        reverse(sb, n, s.length() - 1);
        reverse(sb, 0, sb.length() - 1);
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int i, int j) {
        while (i < j) {
            char c = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, c);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        Sol_reverseLeftWords ss = new Sol_reverseLeftWords();
        String s0= "abcdefg";
        int k = 3;
        System.out.println(ss.reverseLeftWords(s0, k));
    }
}
