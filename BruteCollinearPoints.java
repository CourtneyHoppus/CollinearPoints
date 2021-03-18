/******************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    public LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        if (!checkNullPoints(points)) {
            throw new IllegalArgumentException();
        }
        if (!checkRepeatedPoints(points)) {
            throw new IllegalArgumentException();
        }
        ArrayList<LineSegment> list = new ArrayList<LineSegment>();
        Point[] pointsClone = Arrays.copyOf(points, points.length);
        addSegments(list, pointsClone);
        segments = list.toArray(new LineSegment[0]);
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
    }

    private boolean checkNullPoints(Point[] points) {
        for (Point point : points) {
            if (point == null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRepeatedPoints(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // the number of line segments
    public int numberOfSegments() {
        // return count;
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        if (segments == null) {
            return new LineSegment[0];
        }
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

