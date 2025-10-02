package lab6;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PolylineFileParser {

    public static Polyline parseOneLine(String line) {
        Scanner scanner = new Scanner(line);
        int lineWidth = 1;
        String color = "black"; // Default color

        if (scanner.hasNextInt()) {
            lineWidth = scanner.nextInt();
            color = scanner.next();
        } else {
            color = scanner.next();
        }

        Polyline polyline = new Polyline(color, lineWidth);

        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            polyline.addPoint(new Point(x, y));
        }

        scanner.close();
        return polyline;
    }

    public static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException {
        ArrayList<Polyline> polylines = new ArrayList<>();
        Scanner fileScanner = new Scanner(new File(filename));

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                polylines.add(parseOneLine(line));
            }
        }

        fileScanner.close();
        return polylines;
    }
}
