package com.alg;

import java.util.*;
/*
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

    FileSystem() Initializes the object of the system.
    List<String> ls(String path)
        If path is a file path, returns a list that only contains this file's name.
        If path is a directory path, returns the list of file and directory names in this directory.
    The answer should in lexicographic order.
    void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
    void addContentToFile(String filePath, String content)
        If filePath does not exist, creates that file containing given content.
        If filePath already exists, appends the given content to original content.
    String readContentFromFile(String filePath) Returns the content in the file at filePath.



Example 1:

Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"



Constraints:

    1 <= path.length, filePath.length <= 100
    path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
    You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
    You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
    1 <= content.length <= 50
    At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.


 */
/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
public class Sol588_DesignInMemoryFileSystem {
    // https://leetcode.com/problems/design-in-memory-file-system/editorial/
    public static class FileSystem {
        class Dir {
            HashMap<String, Dir> dirs = new HashMap<>();
            HashMap<String, String> files = new HashMap<>();
        }
        Dir root;
        public FileSystem() {
            root = new Dir();
        }
        public List<String> ls(String path) {
            Dir t = root;
            List<String> files = new ArrayList<>();
            if (!path.equals("/")) {
                String[] d = path.split("/");
                System.out.println(Arrays.asList(d));
                for (int i = 1; i < d.length - 1; i++) {
                    t = t.dirs.get(d[i]);
                }
                if (t.files.containsKey(d[d.length - 1])) {
                    files.add(d[d.length - 1]);
                    return files;
                } else {
                    t = t.dirs.get(d[d.length - 1]);
                }
            }

            files.addAll(new ArrayList<>(t.dirs.keySet()));
            files.addAll(new ArrayList<>(t.files.keySet()));
            Collections.sort(files);
            return files;
        }

        public void mkdir(String path) {
            Dir t = root;
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                if (!t.dirs.containsKey(d[i])) {
                    t.dirs.put(d[i], new Dir());
                }
                t = t.dirs.get(d[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            Dir t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.dirs.get(d[i]);
            }
            t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
        }

        public String readContentFromFile(String filePath) {
            Dir t = root;
            String[] d = filePath.split("/");  // /a/b/cc   a, b, cc
            for (int i = 1; i < d.length -1 ; i++) {
                t = t.dirs.get(d[i]);
            }
            return t.files.get(d[d.length - 1]);
        }
    }


    public class FileSystem2 {
        class File {
            boolean isfile = false;
            HashMap<String, File> files = new HashMap<>();
            String content = "";
        }

        File root;

        public FileSystem2() {
            root = new File();
        }

        public List<String> ls(String path) {
            File t = root;
            List<String> files = new ArrayList<>();
            if (!path.equals("/")) {
                String[] d = path.split("/"); // there is an empty string as first element if the path starts with /
                for (int i = 1; i < d.length; i++) {
                    t = t.files.get(d[i]);
                }
                if (t.isfile) {
                    files.add(d[d.length - 1]);
                    return files;
                }
            }
            List<String> res_files = new ArrayList<>(t.files.keySet());
            Collections.sort(res_files);
            return res_files;
        }

        public void mkdir(String path) {
            File t = root;
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                if (!t.files.containsKey(d[i]))
                    t.files.put(d[i], new File());
                t = t.files.get(d[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            File t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.files.get(d[i]);
            }
            if (!t.files.containsKey(d[d.length - 1]))
                t.files.put(d[d.length - 1], new File());
            t = t.files.get(d[d.length - 1]);
            t.isfile = true;
            t.content = t.content + content;
        }

        public String readContentFromFile(String filePath) {
            File t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.files.get(d[i]);
            }
            return t.files.get(d[d.length - 1]).content;
        }
    }

    public static void main(String[] args) {
        String xx = "/a/b/c";
        String[] afterxx = xx.split("/");
        System.out.println(afterxx.length);
        System.out.println(Arrays.deepToString(afterxx));

//        FileSystem fs = new FileSystem();
//        List<String> listString = fs.ls("/a/b/");
//        System.out.println(listString);

    }

}
