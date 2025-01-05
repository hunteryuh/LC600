package com.alg.other;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/discuss/interview-question/1553862/doordash-phone-screen
public class DD_getMenuPaths {

    Node root;

    class Node {
        String key;
        int val;

        Map<String, Node> children;

        Node(String key, int val) {
            this.key = key;
            this.val = val;
            children = new HashMap<>();
        }
    }

    public static void main(String[] args) throws Exception {
//        Node root = new Node("-1", -1);
//        createPath("/Sweetgreen/", 1);
//        createPath("/Sweetgreen/naan_roll", 18);
//        System.out.println(get("/Sweetgreen/naan_roll"));
//        delete("/Sweetgreen/naan_roll");
//        System.out.println(get("/Sweetgreen"));
    }

    public boolean createPath(String path, int value) {
        if (path == null || path.isEmpty()) {
            return false;
        }

        String[] pathNodes = path.split("/");
        Map<String, Node> nodes = root.children;
        for (int i = 1; i < pathNodes.length; i++) {
            if (i == pathNodes.length - 1) {
                if (nodes.containsKey(pathNodes[i])) {
                    return false;
                }
                nodes.put(pathNodes[i], new Node(pathNodes[i], value));
                return true;
            }
            if (nodes.containsKey(pathNodes[i])) {
                nodes = nodes.get(pathNodes[i]).children;
            } else {
                return false;
            }
        }
        return false;
    }


    public int get(String path) {
        String[] pathNodes = path.split("/");

        Map<String, Node> nodes = root.children;
        for (int i = 1; i < pathNodes.length; i++) {
            if (i == pathNodes.length - 1) {
                if (!nodes.containsKey(pathNodes[i])) {
                    return -1;
                }
                return nodes.get(pathNodes[i]).val;
            }
            if (nodes.containsKey(pathNodes[i])) {
                nodes = nodes.get(pathNodes[i]).children;
            } else {
                return -1;
            }
        }
        return -1;
    }

    //get(path): String -> returns the value of the node at the given path

}
