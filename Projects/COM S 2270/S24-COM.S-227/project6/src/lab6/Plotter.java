package lab6;

public class Plotter {
    public void plot(Polyline polyline) {
        // Placeholder implementation.
        // Implement your plotting logic here.
        System.out.println("Plotting a polyline with color " + polyline.getColor() + " and line width " + polyline.getLineWidth());
        polyline.getPoints().forEach(point -> 
            System.out.println("Point: (" + point.x + "," + point.y + ")"));
    }
}
