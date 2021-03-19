/******************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        if (!checkNullPoints(points)) {
            throw new IllegalArgumentException();
        }
        if (!checkRepeatedPoints(points)) {
            throw new IllegalArgumentException();
        }
        Point[] pointClone = points.clone();
        ArrayList<LineSegment> list = new ArrayList<LineSegment>();
        addSegments(list, pointClone);
        segments = list.toArray(new LineSegment[0]);
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

    private void addSegments(ArrayList<LineSegment> list, Point[] pointClone) {
        for (Point p : pointClone) {
            Arrays.sort(pointClone, p.slopeOrder());
            for (int q = 0; q < pointClone.length; q++) {
                list.add(new LineSegment(p, pointClone[q]));
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        if (segments == null) {
            return new LineSegment[0];
        }
        return segments.clone();
    }

    public static void main(String[] args) {
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
