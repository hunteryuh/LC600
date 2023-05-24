package com.alg.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
// not correct
// https://leetcode.com/discuss/interview-question/279913/Bloomberg-or-Onsite-or-Key-Value-Store-with-transactions/894813
public class KeyValueStore {
    // get -> just return from the store
    // begin -> just adds a new hashMap to the stack
    // Commit -> take all the stuff in transactional Stack and dump it into store
    // Rollback -> clears transactional stack
    // Set -> updates the transactional stack but not the store.
    // delete -> remove from store if not part of transaction, else remove the key from curr transaction.

    Map<String, String> store;
    Stack<Map<String, String>> transactionalStack;

    KeyValueStore () {
        store = new HashMap<>();
        transactionalStack = new Stack<>();
    }

    void begin() {
        transactionalStack.push(new HashMap<>());
    }

    String get(String key) {
        if (store.isEmpty() || !store.containsKey(key)) {
            return transactionalStack.peek().get(key);
        }

        return store.get(key);
    }

    void set(String key, String val) {
        if (transactionalStack.isEmpty()) {
            store.put(key, val);
            return;
        }

        Map<String, String> curr = transactionalStack.peek();
        curr.put(key, val);
    }

    void commit() {
        if (transactionalStack.isEmpty()) {
            throw new RuntimeException("Stack is empty nothing to commit");
        }
        Map<String, String> curr = transactionalStack.pop();
        store.putAll(curr);
    }

    void rollback() {
        if (transactionalStack.isEmpty()) {
            throw new RuntimeException("Nothing to rollback");
        }
        transactionalStack.pop();
    }

    void delete (String key) {
        if (transactionalStack.isEmpty()) {
            store.remove(key);
            return;
        }
        transactionalStack.peek().remove(key);
    }

    public static void main(String[] args) {
        KeyValueStore db = new KeyValueStore();
        db.set("key1", "val1");
        System.out.println(db.get("key1")); // should print "val1"
        db.delete("key1");
        System.out.println(db.get("key1")); // should print null
        try {
            db.commit(); // should throw IllegalStateException
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); // should print "nothing to commit"
        }

        db.set("key0", "val0");
        System.out.println(db.get("key0")); // should print "val0"
        db.begin();
        db.set("key1", "val1");
        System.out.println(db.get("key1")); // should print "val1"
        db.commit();
        System.out.println(db.get("key1")); // should print "val1"
        db.begin();
        db.set("key1", "val2");
        System.out.println(db.get("key1")); // should print "val2"
        db.rollback();
        System.out.println("rolling back");
        System.out.println(db.get("key1")); // should print "val1"
    }
}
