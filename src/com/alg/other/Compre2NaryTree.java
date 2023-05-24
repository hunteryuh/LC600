package com.alg.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Compre2NaryTree {
    class Node {
        String key;
        int value;
        List<Node> children;

        public Node() {}
        public Node(String key, int value, List<Node> children) {
            this.key = key;
            this.value = value;
            this.children = children;
        }
    }

    public int get2NaryTreeDiff(Node r1, Node r2) {
        int count = 0;
        if (r1 == null) {
            return getNodeCount(r2);
        }
        if (r2 == null) {
            return getNodeCount(r1);
        }
        //  r1!=null r2!= null
        if (!r1.key.equals(r2.key) || r1.value != r2.value) {
            count += getNodeCount(r1) + getNodeCount(r2);
        } else {
            Map<String, Node> map1 = getChildrenMap(r1);
            Map<String, Node> map2 = getChildrenMap(r2);
            Set<String> set = new HashSet<>();
            for (String key: map2.keySet()) {
                Node n2 = map2.get(key);
                if (map1.containsKey(key))  {
                   if (map1.get(key).value == n2.value) {
                       count += get2NaryTreeDiff(n2, map1.get(key));

                   } else {
                       count += getNodeCount(n2) + getNodeCount(map1.get(key));
                   }
                    set.add(key); // map1.keySet().retainAll(map2.keySet())
                } else {  // not in map1
                    count += getNodeCount(n2);
                }
            }
            for (String key:map1.keySet()) {
                if (!set.contains(key)) {
                    count += getNodeCount(map1.get(key));
                }
            }
        }
        return count;

    }

    private Map<String, Node> getChildrenMap(Node node) {
        Map<String, Node> map = new HashMap<>();
        for (Node child: node.children) {
            map.put(child.key, child);
        }
//        node.children.stream().collect(Collectors.toMap(item -> item.key, item -> item));
        return map;
    }

    private int getNodeCount(Node root) {
        if (root == null) return 0;
        int count = 1;
        for (Node node: root.children) {
            count += getNodeCount(node);
        }
        return count;
    }
}
