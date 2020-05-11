package w3.hw;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class BruteCollinearPoints {
    private Point[] pts;
    private int size;
    private List<LineSegment> segments;
    public BruteCollinearPoints(Point[] points) {
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
            for (int j = i + 1; j < pts.length; j++) {
                for (int k = j + 1; k < pts.length; k++) {
                    for (int m = k + 1; m < pts.length; m++) {
                        double slopeA = pts[i].slopeTo(pts[j]);
                        double slopeB = pts[j].slopeTo(pts[k]);
                        double slopeC = pts[k].slopeTo(pts[m]);
                        if (slopeA == slopeB && slopeB == slopeC) {
                            segments.add(new LineSegment(pts[i], pts[m]));
                        }
                    }
                }
            }
        }

        size = segments.size();

    }

    public int numberOfSegments(){
        return this.size;
    }

    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[0]);
    }
}