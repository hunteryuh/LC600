package com.alg;

/**
 * Created by HAU on 6/10/2017.
 */
public class Sol28_Implement_strStr {
    //best algorithm : KMP
    //can study an easier algorithm than KMP, which is Rabin-Karp , based on hash function

    // below is brute force method, works for most interviews
    public static int strStr(String source, String target){
        if (source == null || target == null)  // error check
            return -1;
        int i,j;
        for (i = 0; i <= source.length() - target.length(); i++){  // leave a space before and after two variable operator
            for (j = 0; j < target.length(); j++){  // leave a space after the ;
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
        System.out.println(strReplace(source,target,toString));
    }
}
