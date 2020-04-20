package w2.hw;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int n; //下一个载入的结点

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == s.length) resize(2 * s.length);
        s[n++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(n);
        swap(index, n-1);
        Item item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length/4) resize(s.length/2);
        return item;
    }

    private void swap(int i, int j) {
        Item tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return s[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i = 0;

        public RandomArrayIterator() {
            StdRandom.shuffle(s, 0, n-1);
        }
        
        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[i++];
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("1");
        randomizedQueue.enqueue("2");
        randomizedQueue.enqueue("3");
        randomizedQueue.enqueue("4");

        for (String s : randomizedQueue) StdOut.print(s);
        StdOut.println();

        StdOut.println(randomizedQueue.sample());

        StdOut.println(randomizedQueue.dequeue());
        
        for (String s : randomizedQueue) StdOut.print(s);
        StdOut.println();
    }  

}