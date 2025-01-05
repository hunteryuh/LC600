package com.alg.other;

import com.sun.istack.internal.NotNull;

import java.util.*;

//https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
/*
Create an empty DLL. Also, create two arrays inDLL[] and repeated[] of size 256. In DLL is an array of pointers to DLL nodes. repeated[] is a boolean array, repeated[x] is true if x is repeated two or more times, otherwise false. inDLL[x] contains a pointer to a DLL node if character x is present in DLL, otherwise NULL.
Initialize all entries of inDLL[] as NULL and repeated[] as false.
To get the first non-repeating character, return character at the head of DLL.
Following are steps to process a new character ‘x’ in a stream.
If repeated[x] is true, ignore this character (x is already repeated two or more times in the stream)
If repeated[x] is false and inDLL[x] is NULL (x is seen the first time). Append x to DLL and store address of new DLL node in inDLL[x].
If repeated[x] is false and inDLL[x] is not NULL (x is seen a second time). Get DLL node of x using inDLL[x] and remove the node. Also, mark inDLL[x] as NULL and repeated[x] as true.
Note that appending a new node to DLL is O(1) operation if we maintain a tail pointer. Removing a node from DLL is also O(1). So both operations, addition of new character and finding first non-repeating character take O(1) time.
 */


/*
sure, one approach is to use a doubly linked list + hashmap to represent all users seen once, and another hashset to represent all users seen more than once. the solution is described here, but instead of Java's LinkedHashSet we'd use a doubly linked list + hashmap
15:41
also it's actually a pretty common problem, if you google "first unique in a stream"
white_check_mark
eyes
raised_hands





15:41
optimal solution will have O(1) time complexity, O(D) space complexity, with D being number of unique users
 */
public class FindFirstNonRepeating {
    final static int MAX_CHAR = 256;

    static void findFirstNonRepeating()
    {
        // inDLL[x] contains pointer to a DLL node if x is
        // present in DLL. If x is not present, then
        // inDLL[x] is NULL
        List<Character> inDLL = new ArrayList<Character>();

        // repeated[x] is true if x is repeated two or more
        // times. If x is not seen so far or x is seen only
        // once. then repeated[x] is false
        boolean[] repeated = new boolean[MAX_CHAR];

        // Let us consider following stream and see the
        // process
        String stream = "geeksforgeeksandgeeksquizfor";
        for (int i = 0; i < stream.length(); i++) {
            char x = stream.charAt(i);
            System.out.println("Reading " + x
                + " from stream \n");

            // We process this character only if it has not
            // occurred or occurred only once. repeated[x]
            // is true if x is repeated twice or more.s
            if (!repeated[x]) {
                // If the character is not in DLL, then add
                // this at the end of DLL.
                if (!(inDLL.contains(x))) {
                    inDLL.add(x);
                }
                else // Otherwise remove this character from
                // DLL
                {
                    inDLL.remove((Character)x);
                    repeated[x]
                        = true; // Also mark it as repeated
                }
            }

            // Print the current first non-repeating
            // character from stream
            if (inDLL.size() != 0) {
                System.out.print(
                    "First non-repeating character so far is ");
                System.out.println(inDLL.get(0));
            }
        }
    }

    /* Driver code */
    public static void main(String[] args)
    {
        findFirstNonRepeating();
    }

    // Assume no nulls in the array
    public Object getFirstUnique(Object[] objects) {
        if (objects == null || objects.length == 0) {
            return null;
        }//

        final Map<Object, Integer> countMap = new HashMap<>();
        for (final Object object : objects) {
            if (countMap.containsKey(object)) {
                countMap.put(object, countMap.get(object) + 1);
            } else {
                countMap.put(object, 1);
            }
        }

        for (final Object object : objects) {
            if (countMap.get(object) == 1) {
                return object;
            }
        }
        return null;
    }

    public class FirstUnique {
        private final Set<Object> uniques = new LinkedHashSet<>();
        private final Set<Object> duplicates = new HashSet<>();

        public synchronized void add(@NotNull final Object o) {
            if (uniques.contains(o)) {
                uniques.remove(o);
                duplicates.add(o);
            } else if (!duplicates.contains(o)) {
                uniques.add(o);
            }

        }

// Java 8-ness is optional (pun intended!)
        public synchronized Optional<Object> find() {
            return uniques.stream().findFirst();
        }
    }

    public class FirstUnique2 {
        private final Set<Object> uniques = new LinkedHashSet<>();
        private final Set<Object> seen = new HashSet<>();

        public synchronized void add(final Object o) {
            // Add returns true not yet present
            final boolean b = seen.add(o) ? uniques.add(o) : uniques.remove(o);
        }//

// Java 8-ness is optional (pun intended!)
        public synchronized Optional<Object> find() {
            return uniques.stream().findFirst();
        }
    }


    public class LinkedHashSet2<E> {
        private final HashMap<E, Node<E>> map;
        private final LinkedList<Node<E>> list;

        private class Node<E> {
            E value;
            Node<E> next;
            Node<E> prev;

            Node(E value) {
                this.value = value;
            }
        }

        // implement linkedhashset using hashmap and linkedlist
        // add O(1), contains O(1), remove O(1)

        public LinkedHashSet2() {
            map = new HashMap<>();
            list = new LinkedList<>();
        }

        public boolean add(E element) {
            if (map.containsKey(element)) {
                return false; // Element already exists
            }

            Node<E> newNode = new Node<>(element);
            map.put(element, newNode);
            list.addLast(newNode);
            return true;
        }

        public boolean contains(E element) {
            return map.containsKey(element);
        }

        public boolean remove(E element) {
            Node<E> nodeToRemove = map.get(element);

            if (nodeToRemove == null) {
                return false; // Element not found
            }

            map.remove(element);
            list.remove(nodeToRemove);  // only the neighbors needs to be updated node.prev.next = node.next
            return true;
        }
    }
}
