/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int count;
    private ArrayList<LineSegment> segments;
    private Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        count = 0;
        segments = new ArrayList<>();
        this.points = points.clone();
    }

    public void addSegments() {
        int length = points.length;
        for (int idxP = 0; idxP < length - 3; idxP++) {
            for (int idxQ = idxP + 1; idxQ < length - 2; idxQ++) {
                for (int idxR = idxQ + 1; idxR < length - 1; idxR++) {
                    for (int idxS = idxR + 1; idxS < length; idxS++) {
                        double slopePQ = points[idxP].slopeTo(points[idxQ]);
                        double slopePR = points[idxP].slopeTo(points[idxR]);
                        double slopePS = points[idxP].slopeTo(points[idxS]);
                        if (slopePQ == slopePR && slopePR == slopePS) {
                            count++;
                            segments.add(new LineSegment(points[idxP], points[idxS]));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return count;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[segments.size()];
        for (int idx = 0; idx < lineSegments.length; idx++) {
            lineSegments[idx] = segments.get(idx);
        }
        return lineSegments;
    }

    public static void main(String[] args) {
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 2);
        points[3] = new Point(3, 3);
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        StdOut.println(brute.numberOfSegments());
        StdOut.println(Arrays.toString(brute.segments()));
    }
}

