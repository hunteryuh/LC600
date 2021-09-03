package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".

We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
 */
public class Sol811_SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> count = new HashMap<>();
        for (String d : cpdomains) {
            String[] ss = d.split("\\s+");
            int freq = Integer.parseInt(ss[0]);
            String dom = ss[1];
            //"www.google.com"
            count.put(dom, count.getOrDefault(dom, 0) + freq);
            for (int i = 0; i < dom.length(); i++) {
                if (dom.charAt(i) == '.') {
                    String sub = dom.substring(i + 1);
                    count.put(sub, count.getOrDefault(sub, 0) + freq);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (String d : count.keySet()) {
            res.add(count.get(d) + " " + d);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] inputs = {"900 mail.google.com", "50 yahoo.com", "9 wiki.org"};
        Sol811_SubdomainVisitCount s8 = new Sol811_SubdomainVisitCount();
        List<String> r = s8.subdomainVisits(inputs);
        System.out.println(r);
    }
}
