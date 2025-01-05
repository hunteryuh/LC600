package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*Given an absolute path for a file (Unix-style), simplify it.

        For example,
        path = "/home/", => "/home"
        path = "/a/./b/../../c/", => "/c"*/
/*
The problem requires some background of the UNIX command. The string is partitioned by  / .... /. So the basic idea is to check the substring between two / /.
        If the substring equals to '.', simply bypass it because it means the under the current path
        If the substring equals to '..', pop the stack
        If the substring equals to 'abc', push it into the stack.*/
public class Sol71_SimplifyPath {
    public static String simplifyPath_wrong(String path){
        int n = path.length();
        if (n==1) return "/";
        int i;
        for (i = n-1; i >= 0; i--){
            if (path.charAt(i) == '/')
                break;
        }

        int j ;
        for (j = i-1; j >=0; j--){
            if (path.charAt(j) =='/'){
                break;
            }
        }
        if(i>=2 && path.charAt(i-1) =='.' && path.charAt(j+1)=='.')
            return "/";
        if(j>=2 && path.charAt(j-1) =='/'){
            int k = j-2;
            while (path.charAt(k)!='/') k--;
            return path.substring(k,j) + path.substring(j+1,n-1);
        }
        return path.substring(j,i);
    }

    public static String simplifyPath(String path){
        //String result = "/";
        String[] arr = path.split("/+"); // will split blank if use "/"
        ArrayList<String> p = new ArrayList<>();
        for (String s : arr) {
            if (s.equals("..")) {
                if (p.size() > 0) {
                    p.remove(p.size() - 1);
                }
            }
            else if(!s.equals(".") && !s.equals("")){
                p.add(s);
            }
        }
//        for (String s: p){
//            result += s + "/";
//        }
        StringBuilder res = new StringBuilder();
        if (p.size() == 0){
            return "/";
        }
        for (String s: p) {
            res.append("/").append(s);
        }
        return res.toString();
//        if (result.length() > 1) {
//            result = result.substring(0,result.length() - 1);
//        }
//
//        return result;
    }

    public String simplifyPath_faster(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }

        Stack<String> stack = new Stack<>();

        int i = 0;
        while (i < path.length()) {
            // bypass the first /
            while (i < path.length() && path.charAt(i) == '/') {
                i++;
            }

            int start = i;

            if (i == path.length()) {
                break;
            }

            // reach the end /
            while(i < path.length() && path.charAt(i) != '/') {
                i++;
            }

            int end = i;

            String curr = path.substring(start, end);
            if (curr.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!curr.equals(".")) {
                stack.push(curr);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();

        String[] arr = stack.toArray(new String[stack.size()]);
        for (int j = 0; j < arr.length; j++) {
            sb.append('/');
            sb.append(arr[j]);
        }

        return sb.toString();
    }

    public static String simplifyPathWithStack(String path) {
        String[] splits = path.split("/+");
        Stack<String> stack = new Stack<>();
        for (String s: splits) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!s.equals(".") && !s.equals("")) {
                stack.push(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            sb.append("/");
            // return "/";
        }
        for (String a: stack) {
            sb.append("/").append(a);
        }
        return sb.toString();
//        return "/" + String.join("/", stack);
    }


    public static void main(String[] args) {
        String p1 = "/a/./b/../../c/";
        System.out.println(simplifyPath(p1));
        System.out.println(simplifyPathWithStack(p1));

//        System.out.println(simplifyPath(p1).equals(simplifyPathWithStack(p1)));
        String p2 = "/../../";
        System.out.println(simplifyPath(p2));
        System.out.println(simplifyPathWithStack(p2));
        String p3 = "/home//foo/";
        System.out.println(simplifyPath(p3));
        System.out.println(simplifyPathWithStack(p3));

        String p = "/";
        System.out.println(simplifyPath(p));
        System.out.println(simplifyPathWithStack(p));
        String p4 = "/..";
        System.out.println(simplifyPath(p4));
        System.out.println(simplifyPathWithStack(p4));
    }
}
