package com.alg;
/*
You are asked to design a file system that allows you to create new paths and associate them with different values.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

Implement the FileSystem class:

    bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
    int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.



Example 1:

Input:
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output:
[null,true,1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1

Example 2:

Input:
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output:
[null,true,true,2,false,-1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.



Constraints:

    The number of calls to the two functions is less than or equal to 104 in total.
    2 <= path.length <= 100
    1 <= value <= 109


 */

import java.util.HashMap;
import java.util.Map;

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */

public class Sol1166_DesignFileSystem {
    // time O(M): m is length of path
    // space O(K): k : the number of unique paths that we add
    class FileSystem {
        Map<String, Integer> paths;
        public FileSystem() {
            paths = new HashMap<>();
        }

        public boolean createPath(String path, int value) {
            // path validation
            if (path.isEmpty() || ( path.length() == 1 && path.equals("/") ) || paths.containsKey(path)) {
    return false;
            }
            int delimIndex = path.lastIndexOf("/");
            String parent = path.substring(0, delimIndex);
            if (parent.length() > 1 && !paths.containsKey(parent)) {
                return false;
            }
            // add the new path
            paths.put(path, value);
            return true;
        }

        public int get(String path) {
            return paths.getOrDefault(path, -1);
        }
    }

    // approach 2: trie based approach
    class FileSystem2 {
        class TrieNode {
            String name;
            int val = -1;
            Map<String, TrieNode> map = new HashMap<>();
            TrieNode(String name) {
                this.name = name;
            }
        }

        TrieNode root;

        public FileSystem2() {
            this.root = new TrieNode("");
        }
        // Time O(T), t is the length of the path in the trie
        public boolean createPath(String path, int value) {
            // get all components
            String[] components = path.split("/");
            // start "cur" from root node
            TrieNode cur = root;
            // iterate over all components
            for (int i = 1; i < components.length; i++) {
                String component = components[i];
                // check if it exists in current node's dictionary
                if (!cur.map.containsKey(component)) {
                    if (i == component.length() - 1) {
                        cur.map.put(component, new TrieNode(component));
                    } else {
                        return false;
                    }
                }
                cur = cur.map.get(component);
            }
            if (cur.val != -1) {
                return false;
            }
            cur.val = value;
            return true;
        }

        // Time: O(T)
        public int get(String path) {
            String[] components = path.split("/");
            TrieNode cur = root;
            for (int i = 1; i < components.length; i++) {
                String comp = components[i];
                if (!cur.map.containsKey(comp)) {
                    return -1;
                }
                cur = cur.map.get(comp);
            }
            return cur.val;
        }

        // not verified

        public void delete(String path) throws Exception {
            if (path == null || path.length() == 0) return;
            TrieNode cur = root;
            String[] components = path.split("/");
            for (int i = 1; i < components.length; i++) {
                String comp = components[i];
                if (!cur.map.containsKey(comp)) {
                    throw new Exception("Parent does not exist");
                } else {
                    TrieNode pre = cur;
                    cur = cur.map.get(comp);
                    if (i == components.length - 1) {
                        if (cur.map.isEmpty()) {
                          pre.map.remove(comp);
                        } else {
                            throw new Exception("Path has children, cannot be deleted.");
                        }
                    }
                }

            }
        }
    }

}
