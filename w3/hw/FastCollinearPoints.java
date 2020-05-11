package w3.hw;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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
}