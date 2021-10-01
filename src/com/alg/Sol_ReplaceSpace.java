package com.alg;

public class Sol_ReplaceSpace {
    public String replaceSpace(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
//                " ".equals(String.valueOf(str.charAt(i)))
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "I am happy !";
        Sol_ReplaceSpace s = new Sol_ReplaceSpace();
        System.out.println(s.replaceSpace(str));
    }
}
