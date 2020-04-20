package w2.hw;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            randomizedQueue.enqueue(str);
        }

        for (String str: randomizedQueue) {
            k--;
            if (k < 0) break;
            StdOut.println(str);
        }
    }

}