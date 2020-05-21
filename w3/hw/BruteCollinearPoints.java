package w3.hw;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class BruteCollinearPoints {
    private final Point[] pts;
    private final LineSegment[] segments;
    public BruteCollinearPoints(Point[] points) {
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

        List<LineSegment> list = new ArrayList<>();

        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                for (int k = j + 1; k < pts.length; k++) {
                    for (int m = k + 1; m < pts.length; m++) {
                        double slopeA = pts[i].slopeTo(pts[j]);
                        double slopeB = pts[j].slopeTo(pts[k]);
                        double slopeC = pts[k].slopeTo(pts[m]);
                        if (Double.compare(slopeA, slopeB) == 0 && Double.compare(slopeB,slopeC) == 0) {
                            list.add(new LineSegment(pts[i], pts[m]));
                        }
                    }
                }
            }
        }

        this.segments = list.toArray(new LineSegment[0]);

    }

    public int numberOfSegments(){
        return this.segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, this.segments.length);
    }
}