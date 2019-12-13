package com.zatsweb.jcache;

import java.util.HashMap;


// cache entries that we keep in the linked list and in the hashmap
class Node {
    int key;
    Object value;
    Node next;
    Node prev;

    public Node (int key, Object value) {
        this.key = key;
        this.value=value;
    }
}

public class LRUCache implements ICache {

    private int capacity;   // cache capacity
    private int size;       // cache size
    private HashMap<Integer, Node> map; // hashmap of objects
    Node head; // front of the linked list; entries that are recently used go to front
    Node tail; // tail of the linked list; entries that need to be replaced are take from tail

    public LRUCache (int capacity) {
       map = new HashMap<Integer, Node>(); 
       this.capacity=capacity;

        // add filler head/tail nodes to simplify ll operations
        head = new Node(0,null);
        tail = new Node(0,null);
        tail.prev = head;
        head.next = tail;
    }

    public void add(int key, final Object value) {

        Node node = map.get(key);

        if (node==null && size==capacity) {
            // if we're at capacity and need to add, remove the node located at tail
            node=tail.prev;
            map.remove(node.key);
        }

        if (node!=null) {
            // remove node from the list. 
            // this is either the one we found in cache or last one if we're at capacity
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            if (node.key!=key) {
                node=null;
            }
        }

        if (node==null) {
            // alloc new node to add to the front
            node = new Node(key, value);
            map.put(key, node);
        } else {
            // node with this key already exists, but value may be different
            node.value=value;
        }

        // add node to the front of ll
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;

        size++;
    }

    public int size() {
        return size;
    }

    public Object get(int key) {
        Node node = map.get(key);
       
        // move to head of the list
        if (node!=null) {
            // remove from existing location
            node.prev.next = node.next;
            node.next.prev = node.prev;

            // add it to front
            node.next=head.next;
            node.prev=head;
            head.next.prev=node;
            head.next=node;
            return node.value;
        }
        return null;

        
    }

    public static void main(String[] args) {
        System.out.println ("Hello world");
    }
}