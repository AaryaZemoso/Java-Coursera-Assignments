package Course1.Week1;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;

        for(Point p : s.getPoints())
            count++;

        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here

        double longestSide = 0.0;

        Point prev = s.getLastPoint();
        for(Point curr : s.getPoints())
        {
            double distance = curr.distance(prev);
            if(distance > longestSide)
                longestSide = distance;
        }

        return longestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here

        double x = Double.MIN_VALUE;

        for(Point p : s.getPoints())
            if(p.getX() > x)
                x = p.getX();

        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;

        for(File f : dr.selectedFiles())
        {
            Shape s = new Shape(new FileResource(f));
            double perimeter = getPerimeter(s);

            if(perimeter > largestPerimeter)
                largestPerimeter = perimeter;
        }

        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();

        File largestFileWithPerimeter = null;
        double largestPerimeter = 0.0;

        for(File f : dr.selectedFiles())
        {
            if(largestFileWithPerimeter == null)
            {
                largestFileWithPerimeter = f;
                largestPerimeter = getPerimeter(new Shape(new FileResource(f)));
            }

            else
            {
                Shape s = new Shape(new FileResource(f));
                double perimeter = getPerimeter(s);

                if(perimeter > largestPerimeter)
                {
                    largestFileWithPerimeter = f;
                    largestPerimeter = perimeter;
                }
            }
        }
        return largestFileWithPerimeter.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();

        Shape s = new Shape(fr);

        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);

        System.out.println("perimeter = " + length);
        System.out.println("num points = " + numPoints);
        System.out.println("avg length = " + avgLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest x = " + largestX);

    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter in multiple files = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String nameOfFile = getFileWithLargestPerimeter();
        System.out.println("File with largest Perimter : " + nameOfFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }
}