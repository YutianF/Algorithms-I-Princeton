package w3.hw;
import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
public class FastCollinearPoints {
    private final Point[] pts;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        this.pts = new Point[points.length];
        for (int i = 0; i < pts.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            pts[i] = points[i];
        }

        Arrays.sort(pts);
        for (int i = 0; i < pts.length - 1; i++) {
            if (pts[i].compareTo(pts[i+1]) == 0) throw new IllegalArgumentException();
        }

        this.segments = cache();
    }

    private LineSegment[] cache() {
        
        ArrayList<LineSegment> list = new ArrayList<>();

        for (Point p : pts) {
            Point[] pp = Arrays.copyOf(pts, pts.length);
            if (pp.length < 4) {
                continue;
            }
            Arrays.sort(pp, p.slopeOrder());
            int begin = 1;
            int end = 1;
            double last = p.slopeTo(pp[1]); // 上一个元素的slope
            for (int j = 2; j < pp.length; j++) {
                double slope = p.slopeTo(pp[j]);
                if (Double.compare(last, slope) != 0) {
                    if (end - begin >= 2) {
                        if (p.compareTo(pp[begin]) < 0){
                            list.add(new LineSegment(p, pp[end]));
                        }
                    }
                    last = slope;
                    begin = j;
                }
                end = j;
            }
            if (end - begin >= 2) {
                if (p.compareTo(pp[begin]) < 0) {
                    list.add(new LineSegment(p, pp[end]));
                }
            }
        }
        
        return list.toArray(new LineSegment[0]);
        

    }

    public int numberOfSegments() {
        return this.segments.length;
    }

    public LineSegment[] segments() {
        // 保证不可变性, 返回一份拷贝
        return Arrays.copyOf(segments, segments.length);
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