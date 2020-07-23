package w5.hw;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;


public class KdTree {

    private static final boolean X = true;
    private static final boolean Y = false;

    private class Node {
        private final Point2D p;
        private final boolean level;
        private final RectHV rect;
        private Node left;
        private Node right;

        public Node(Point2D p, boolean level, 
                    double xmin, double ymin, double xmax, double ymax) {
            this.p = p;
            this.level = level;
            this.rect = new RectHV(xmin, ymin, xmax, ymax);
        }

        public Node(Point2D p) {
            this(p, X, 0, 0, 1, 1);
        }

    
    }

    private Node root;
    private int size;

    public KdTree() {

    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public void insert(Point2D p) {

    }

    public boolean contains(Point2D p) {
        return false;
    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    public Point2D nearest(Point2D p) {
        return null;
    }

    public static void main(String[] args) {
        
    }

}