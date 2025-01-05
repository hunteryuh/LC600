package com.alg;

/**
 * Created by HAU on 6/10/2017.
 */
public class Sol28_FindIndexOfFirstOccurenceInAString {
    //best algorithm : KMP
    //can study an easier algorithm than KMP, which is Rabin-Karp , based on hash function

    // below is brute force method, works for most interviews
    public static int strStr(String source, String target){
        if (source == null || target == null)  // error check
            return -1;
        int i,j;
        for (i = 0; i <= source.length() - target.length(); i++) {  // leave a space before and after two variable operator
            for (j = 0; j < target.length(); j++) {  // leave a space after the ;
                if (target.charAt(j) != source.charAt(i+j))
                    break;
            }
            if (j == target.length())
                return i;   // always start a new line for if
        }
        return -1;
    }
    public static String strReplace(String source, String target, String tostring){ // replace "from" to "to"
        if (source == null || target == null)  // error check
            return null;
        int i,j;
        for (i = 0; i <= source.length() - target.length(); i++){  // leave a space before and after two variable operator
            for (j = 0; j < target.length(); j++){  // leave a space after the ;
                if (target.charAt(j) != source.charAt(i+j))
                    break;
            }
            if (j == target.length())
                source = source.substring(0,i) + tostring + source.substring(i+j);
        }
        return source;
    }

    public static void main(String[] args) {
        String source = "abcefgbcd---ee---bcd222";
        String target = "bcd";
        String toString = "testnew";
        System.out.println(strStr(source,target));

        Sol28_FindIndexOfFirstOccurenceInAString ss = new Sol28_FindIndexOfFirstOccurenceInAString();
        System.out.println(ss.strStr2(source, target));
        System.out.println(strReplace(source,target,toString));
    }

    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int n = haystack.length();
        int m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
//            if (haystack.charAt(i) != needle.charAt(j)) {
//                continue;
//            }
//            for (j = 0; j < m; j++) {
//                if (haystack.charAt(i + j) != needle.charAt(j)) {
//                    break;
//                }
//            }
            // start one-by-one character comparison only if first and last characters of sliding window
            // match the needle's first and last characters - it improves performance a lot!
            if (haystack.charAt(i) == needle.charAt(j) && haystack.charAt(i + m -1) == needle.charAt(m-1)) {
                while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                    j++;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    // kmp
    public void getNext(int[] next, String s){
        int j = -1;
        next[0] = j;
        for (int i = 1; i<s.length(); i++){
            while(j>=0 && s.charAt(i) != s.charAt(j+1)){
                j=next[j];
            }

            if(s.charAt(i)==s.charAt(j+1)){
                j++;
            }
            next[i] = j;
        }
    }
    public int strStrKMP(String haystack, String needle) {
        if (needle.length()==0){
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for(int i = 0; i<haystack.length();i++){
            while(j>=0 && haystack.charAt(i) != needle.charAt(j+1)){
                j = next[j];
            }
            if(haystack.charAt(i)==needle.charAt(j+1)){
                j++;
            }
            if(j==needle.length()-1){
                return (i-needle.length()+1);
            }
        }

        return -1;
    }
}
