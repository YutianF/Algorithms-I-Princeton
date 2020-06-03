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
        Point2D point;
        Node left, right;
        boolean coordinate;
        private int size;

        public Node(Point2D point, boolean coordinate, int size) {
            this.point = point;
            this.coordinate = coordinate; 
            this.size = size;
        }

    }

    private Node root;
    private boolean level;

    public KdTree() {
        
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("the arg in insert() is null");
        root = insert(root, p, X);
    }

    // 在x树中插入p结点
    private Node insert(Node node, Point2D p, boolean level) {
        if (node == null) return new Node(p, level, 1);
        
        int cmp;
        if (level == X) cmp = Point2D.X_ORDER.compare(p, node.point);
        else cmp = Point2D.Y_ORDER.compare(p, node.point);

        if (cmp < 0) node.left = insert(node.left, p, !level);
        else if (cmp > 0) node.right = insert(node.right, p, !level);
        else node.point = p;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public boolean contains(Point2D p) {
        return contains(root, p, X);

    }

    private boolean contains(Node node, Point2D p, boolean level) {
        if (p == null) throw new IllegalArgumentException("the arg in contains() is null");
        if (node == null) return false;
        
        int cmp;
        if (level == X) cmp = Point2D.X_ORDER.compare(p, node.point);
        else cmp = Point2D.Y_ORDER.compare(p, node.point);
        
        if (cmp < 0) return contains(node.left, p, !level);
        else if (cmp > 0) return contains(node.right, p, !level);
        else return true;
    }


    public void draw() {
        draw(root);
    }

    // preorder to draw the node
    private void draw(Node node) {
        if (node == null) return;
        node.point.draw();
        if (node.left != null) draw(node.left);
        if (node.right != null) draw(node.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("the arg in range() is null");
        List<Point2D> list = new ArrayList<>();
        range(root, list, rect, X);
        return list;
    }

    private void range(Node node, List<Point2D> list, RectHV rect, boolean level) {
        if (node == null) return;

        int cmplo, cmphi;
        if (level == X) {
            cmplo = Double.compare(node.point.x(), rect.xmin());
            cmphi = Double.compare(node.point.x(), rect.xmax());
        } else {
            cmplo = Double.compare(node.point.y(), rect.ymin());
            cmphi = Double.compare(node.point.y(), rect.ymax());
        }

        if (cmplo < 0) range(node.left, list, rect, !level);
        if (cmplo >= 0 && cmphi <= 0 && isInRec(node.point, rect)) list.add(node.point);
        if (cmphi > 0) range(node.right, list, rect, !level);
        
    }

    private boolean isInRec(Point2D p, RectHV rect) {
        return p.x() >= rect.xmin() && p.x() <= rect.xmax() 
                && p.y() >= rect.ymin() && p.y() <= rect.ymax();
    }

    public Point2D nearest(Point2D p) {
        Point2D nearestPoint = new Point2D(10, 10);
        nearest(root, p, new RectHV(0, 0, 1, 1), nearestPoint, X);
        return nearestPoint;
    }

    // 返回node树中离p最近的结点
    private void nearest(Node node, Point2D p, RectHV rect, Point2D nearestPoint, boolean level) {
        if (node == null) throw new NoSuchElementException("the Kd tree is empty");
        if (p == null) throw new IllegalArgumentException("the arg in nearest() is null");
        if (rect.distanceSquaredTo(p) > nearestPoint.distanceSquaredTo(p)) return;

        if (node.point.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p)) nearestPoint = node.point;

        int cmp;
        if (level == X) cmp = Double.compare(p.x(), node.point.x());
        else cmp = Double.compare(p.y(), node.point.y());

        if (level == X) {
            if (cmp < 0) {
                nearest(node.left, p, new RectHV(rect.xmin(), node.point.x(), rect.ymin(), rect.ymax()), nearestPoint, !level);
                nearest(node.right, p, new RectHV(node.point.x(), rect.xmax(), rect.ymin(), rect.ymax()), nearestPoint, !level);
            } else {
                nearest(node.right, p, new RectHV(node.point.x(), rect.xmax(), rect.ymin(), rect.ymax()), nearestPoint, !level);
                nearest(node.left, p, new RectHV(rect.xmin(), node.point.x(), rect.ymin(), rect.ymax()), nearestPoint, !level);
            }
        } else {
            if (cmp < 0) {
                nearest(node.left, p, new RectHV(rect.xmin(), rect.xmax(), rect.ymin(), node.point.y()), nearestPoint, !level);
                nearest(node.right, p, new RectHV(rect.xmin(), rect.xmax(), node.point.y(), rect.ymax()), nearestPoint, !level);
            } else {
                nearest(node.right, p, new RectHV(rect.xmin(), rect.xmax(), node.point.y(), rect.ymax()), nearestPoint, !level);
                nearest(node.left, p, new RectHV(rect.xmin(), rect.xmax(), rect.xmin(), node.point.y()), nearestPoint, !level);
            }
        }
    }

    public static void main(String[] args) {
        
    }

}