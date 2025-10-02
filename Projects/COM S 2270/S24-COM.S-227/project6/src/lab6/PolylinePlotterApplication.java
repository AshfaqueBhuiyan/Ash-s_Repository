package lab6;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PolylinePlotterApplication {
    public static void main(String[] args) {
        try {
            ArrayList<Polyline> list = PolylineFileParser.readFile("hello.txt");
            Plotter plotter = new Plotter();

            for (Polyline polyline : list) {
                plotter.plot(polyline);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
