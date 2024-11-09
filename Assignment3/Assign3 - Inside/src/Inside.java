/**
 * Assignment 3 for CS 1410
 * This program determines if points are contained within circles or rectangles.
 */
public class Inside {
    public static void reportPoint(double x, double y) {
        System.out.print("Point (" + x + ", " + y + ")");
    }

    public static void reportCircle(double x, double y, double r) {
        System.out.print("Circle (" + x + ", " + y + ")  Radius: " + r);
    }

    public static void reportRectangle(double left, double top, double width, double height) {
        System.out.print("Rectangle (" + left + ", " + top + ", " + (left + width) + ", " + (top - height) + ")");
    }

    public static boolean isPointInsideCircle(double ptX, double ptY, double circleX, double circleY, double circleRadius) {
        // Using distance formula to determine whether point is within circle.
        double ptRadius = Math.sqrt((Math.pow((ptX - circleX), 2) + Math.pow((ptY - circleY), 2)));
        return ptRadius <= circleRadius;
    }

    public static boolean isPointInsideRectangle(double ptX, double ptY, double rLeft, double rTop, double rWidth, double rHeight) {
        // Creating Variables for readability
        double ptWidth = ptX - rLeft;
        double ptHeight = rTop - ptY;

        // Ensuring point is between Left and Right and Top and Bottom of Rectangle
        boolean heightBool = ptHeight <= rHeight && ptHeight >= 0;
        boolean widthBool = ptWidth <= rWidth && ptWidth >= 0;
        return heightBool && widthBool;
    }

    public static void main(String[] args) {
        double[] ptX = {1, 2, 3, 4};
        double[] ptY = {1, 2, 3, 4};
        double[] circleX = {0, 5};
        double[] circleY = {0, 5};
        double[] circleRadius = {3, 3};
        double[] rectLeft = {-2.5, -2.5};
        double[] rectTop = {2.5, 5.0};
        double[] rectWidth = {6.0, 5.0};
        double[] rectHeight = {5.0, 2.5};

        // Circles
        System.out.println("--- Report of Points and Circles ---");
        System.out.println();
        for (int i = 0; i < circleX.length; i++) { /* Looping through circle centers*/
            for (int j = 0; j < ptX.length; j++) { /* Looping through points */

                reportPoint(ptX[j], ptY[j]);
                System.out.print(" is ");
                if (isPointInsideCircle(ptX[j], ptY[j], circleX[i], circleY[i], circleRadius[i])) {
                    System.out.print("inside ");
                } else {
                    System.out.print("outside ");
                }
                reportCircle(circleX[i], circleY[i], circleRadius[i]);
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("--- Report of Points and Rectangles ---");
        System.out.println();
        for (int i = 0; i < rectLeft.length; i++) {
            for (int j = 0; j < ptX.length; j++) {
                reportPoint(ptX[j], ptY[j]);
                System.out.print(" is ");
                if (isPointInsideRectangle(ptX[j], ptY[j], rectLeft[i], rectTop[i], rectWidth[i], rectHeight[i])) {
                    System.out.print("inside ");
                } else {
                    System.out.print("outside ");
                }
                reportRectangle(rectLeft[i], rectTop[i], rectWidth[i], rectHeight[i]);
                System.out.println();
            }
        }
    }

}
