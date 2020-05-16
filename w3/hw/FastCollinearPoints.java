package w3.hw;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
public class FastCollinearPoints {
    private Point[] pts;
    private int size;
    private List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        this.pts = new Point[points.length];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = points[i];
        }

        Arrays.sort(pts);
        for (int i = 0; i < pts.length - 1; i++) {
            if (pts[i] == null || pts[i].compareTo(pts[i+1]) == 0) throw new IllegalArgumentException();
        }

        segments = new ArrayList<>();
        size = 0;

        for (int i = 0; i < pts.length; i++) {
            Arrays.sort(pts, )
        }

    }

    public int numberOfSegments() {
        return this.size;
    }

    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[0]);
    } 

    public static void main(String[] args){
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}