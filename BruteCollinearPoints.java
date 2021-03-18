/******************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        // int count = 0;
        ArrayList<LineSegment> list = new ArrayList<LineSegment>();
        Point[] pointsClone = Arrays.copyOf(points, points.length);
        addSegments(list, pointsClone);
    }

    private void addSegments(ArrayList<LineSegment> list, Point[] pointsClone) {
        Arrays.sort(pointsClone);
        int length = pointsClone.length;
        for (int idxP = 0; idxP < length - 3; idxP++) {
            for (int idxQ = idxP + 1; idxQ < length - 2; idxQ++) {
                for (int idxR = idxQ + 1; idxR < length - 1; idxR++) {
                    for (int idxS = idxR + 1; idxS < length; idxS++) {
                        double slopePQ = pointsClone[idxP].slopeTo(pointsClone[idxQ]);
                        double slopePR = pointsClone[idxP].slopeTo(pointsClone[idxR]);
                        double slopePS = pointsClone[idxP].slopeTo(pointsClone[idxS]);
                        if (slopePQ == slopePR && slopePR == slopePS) {
                            // count++;
                            list.add(new LineSegment(pointsClone[idxP], pointsClone[idxS]));
                        }
                    }
                }
            }
        }
        segments = list.toArray(new LineSegment[list.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        // return count;
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
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

