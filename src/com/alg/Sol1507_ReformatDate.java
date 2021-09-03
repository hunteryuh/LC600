package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Given a date string in the form Day Month Year, where:

Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
Year is in the range [1900, 2100].
Convert the date string to the format YYYY-MM-DD, where:

YYYY denotes the 4 digit year.
MM denotes the 2 digit month.
DD denotes the 2 digit day.

Example 1:

Input: date = "20th Oct 2052"
Output: "2052-10-20"
Example 2:

Input: date = "6th Jun 1933"
Output: "1933-06-06"
Example 3:

Input: date = "26th May 1960"
Output: "1960-05-26"


https://leetcode.com/problems/reformat-date/
 */
public class Sol1507_ReformatDate {
    public static String reformatDate(String date) {
        String[] splits = date.split("\\s+");
        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < months.length;i++) {
            map.put(months[i], i + 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(splits[2]);
        sb.append("-");
        int month = map.get(splits[1]);
        if (month < 10) {
            sb.append("0");
        }
        sb.append(month);
        sb.append("-");
        String day = splits[0];
        if (day.length() < 4) {
            sb.append("0").append(day.charAt(0));
        } else {
            sb.append(day, 0, 2);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "20th Oct 2052";
        String s2 = "6th Jun 1933";
        System.out.println(reformatDate(s));
        System.out.println(reformatDate(s2));
    }
}
