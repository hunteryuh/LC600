package com.alg.other;

/*
Design and implement an in-memory key value datastore. This datastore should be able to support some basic operations such as Get, Set and Delete for string keys and values.

I would like to see test cases as well as implementation code.

You can assume that the input operations are always valid, but the key to operate on may be non-existent. We won’t worry about concurrent access to the database. You can handle errors however you think is best.

Let’s start with the data structure of this key value store. Implement methods for Get, Set and Delete.

db.set("key1", "val1")
print(db.get("key1"))               # returns val1
db.delete("key1")
db.get("key1")                      # error case as key1 is not set

****************** part 2 *******************

Add support for transactions - Begin, Commit and Rollback.

A transaction is created with the Begin command and creates a context for the other operations to happen. Until the active transaction is committed using the Commit command, those operations do not persist. And, the Rollback command throws away any changes made by those operations in the context of the active transaction.

Every Begin() will always come with a Commit() or Rollback().

db.set("key0", "val0")
db.get("key0")               # returns val0 which is set outside of a transaction
db.begin()                   # start tx
db.set("key1", "val1")
db.get("key1")               # returns val1
db.commit()                  # commit tx
db.get("key1")               # returns val1

rippling , phone interview
*/
import java.util.*;

public class RP_KeyValue {
    private Map<String, String> map;
    private Stack<Map<String, String>> transactionStack;

    RP_KeyValue() {
        this.map = new HashMap<>();
        this.transactionStack = new Stack<>();
    }

    public String get(String key) {
        if (transactionStack.isEmpty()) {
            return map.get(key);
        } else {
            return transactionStack.peek().get(key);
        }
    }

    void set(String key, String value) {
        if (transactionStack.isEmpty()) {
            map.put(key, value);
        } else {
            transactionStack.peek().put(key, value);
        }
    }

    void delete(String key) {
        if (transactionStack.isEmpty()) {
            map.remove(key);
        } else {
            transactionStack.peek().remove(key);
        }
    }

    void begin() {
        transactionStack.push(new HashMap<>(map));
    }

    void commit() {
        if (transactionStack.isEmpty()) {
            throw new IllegalStateException("No active transaction");
        }
        map = transactionStack.pop();
    }

    void rollback() {
        if (transactionStack.isEmpty()) {
            throw new IllegalStateException("No active transaction");
        }
        transactionStack.pop();
    }

    public static void main(String[] args) {
        RP_KeyValue kv = new RP_KeyValue();

        kv.set("k1", "v1");
        System.out.println(kv.get("k1"));
        kv.set("k2", "v2");
        kv.set("k3", "v3");
        try {
            kv.commit(); // should throw IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // should print "No active transaction"
        }
        kv.begin(); System.out.println("transaction started");
        kv.set("k1", "k12");
//        kv.delete("k1");
        System.out.println(kv.get("k1"));
//        System.out.println(kv.map);
//        System.out.println(kv.transactionStack.peek());
//        System.out.println(kv.get("k2")); // v2
        kv.rollback();
        System.out.println("transaction rolled back");

        System.out.println(kv.get("k1")); // failed

    }
}
