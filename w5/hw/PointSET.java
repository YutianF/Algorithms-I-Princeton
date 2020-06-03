package w5.hw;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;

import java.util.List;
import java.util.ArrayList;

public class PointSET {
    private TreeSet<Point2D> set;
    public PointSET() {
        set = new TreeSet<>(); 
    }

    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    public int size() {
        return this.set.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("the arg in insert() is null");
        this.set.add(p);
    }

    public boolean contains(Point2D p) {
        return this.set.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> list = new ArrayList<>();
        for (Point2D p : set) {
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax() 
            && p.y() >= rect.ymin() && p.y() <= rect.ymax()) {
                list.add(p);
            }
        }
        return list;

    }

    public Point2D nearest(Point2D that) {
        double minDis = Double.MAX_VALUE;
        Point2D minPnt = null;
        for (Point2D p : set) {
            if (that.distanceSquaredTo(p) < minDis) {
                minDis = that.distanceSquaredTo(p);
                minPnt = p;
            }
        }
        return minPnt;
    }

    public static void main(String[] args) {

    }

    
}