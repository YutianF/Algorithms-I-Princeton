package w2.hw;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        } 
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        } 
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item;
        if (first == last) {
            item = first.item;
            first = null;
            last = null;
        } 
        else {
            Node oldFirst = first;
            first = oldFirst.next;
            oldFirst.next = null;
            item = oldFirst.item;
            oldFirst = null;
        }

        size--;
        return item;
        
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item;
        if (first == last) {
            item = first.item;
            first = null;
            last = null;
        }
        else {
            Node oldLast = last;
            last = oldLast.prev;
            last.next = null;
            item = oldLast.item;
            oldLast = null;
        }

        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        public Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        for (Integer s: deque) StdOut.print(s);
        StdOut.println();

        deque.addFirst(0);
        for (Integer s: deque) StdOut.print(s);
        StdOut.println();

        deque.addLast(9);
        for (Integer s: deque) StdOut.print(s);
        StdOut.println();

        StdOut.println(deque.removeFirst());

        StdOut.println(deque.removeLast());

        for (Integer s: deque) StdOut.print(s);
        StdOut.println();

        StdOut.println(deque.size());
    }

}