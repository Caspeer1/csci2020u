/*import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;


public class lab06 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        var main = new VBox();

        var canvas = new Canvas(300, 300);
        var gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < avgHousingPricesByYear.length; i++){
            double height = 200 * (avgHousingPricesByYear[i] / getMax(avgHousingPricesByYear));
            gc.strokeRect(10+15*i, 10+ (200 - height), 10, height);
        }

        main.getChildren().add(canvas);

        var scene = new Scene(main);

        primaryStage.setTitle("lab06");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private double getMax(double[] x){
        double max = 0;
        for (int i = 0; i < avgHousingPricesByYear.length; i++) {
            if (avgHousingPricesByYear[i] > avgHousingPricesByYear[i + 1]) {
                avgHousingPricesByYear[i + 1] = avgHousingPricesByYear[i];
            }
            max = avgHousingPricesByYear[i];
            System.out.println(max);
        }
        System.out.println(max);

        return max;
    }

    private static double[] avgHousingPricesByYear = {24.73810, 26.41714, 28.77153, 29.47361, 30.84314, 32.26359, 34.02530, 36.31537};
}

 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class lab06 extends Application {
    //Bar Chart Data
    private static double[] avgHousingPricesByYear =
            {247381.0,264171.4,287715.3,294736.1,308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear =
            {1121585.3,1219479.5,1246354.2,1295364.8,1335932.6,1472362.0,1583521.9,1613246.3};

    //Pie Chart Data
    private static String[] ageGroups = {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static float[] purchasesByAgeGroup = {648f, 1021f, 2453f, 3173f, 1868f, 2247f};
    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.PLUM, Color.LAWNGREEN, Color.DARKSALMON};

    @Override
    public void start(Stage primaryStage) throws Exception{
        var main = new Group();
        Canvas barGraph = new Canvas(450,350);
        Canvas circleChart = new Canvas(450,350);
        GraphicsContext circleGc = circleChart.getGraphicsContext2D();
        GraphicsContext barGc = barGraph.getGraphicsContext2D();
        barGc.setStroke(Color.GREEN);
        barGc.setLineWidth(10);

        for (int i = 0; i < avgCommercialPricesByYear.length; i++) {
            int x = i*30;
            //Housing price by year
            barGc.setStroke(Color.BLACK);
            barGc.strokeLine(x,300, x,300 - avgHousingPricesByYear[i] / 4700);

            //Commercial price by year
            barGc.setStroke(Color.BLUE);
            barGc.strokeLine(x + 10,300,x + 10, (300 - avgCommercialPricesByYear[i] / 6000));
        }

        float[] angles = getAngles();
        for (int i=0; i<purchasesByAgeGroup.length; i++){
            circleGc.setFill(pieColours[i]);
            if (i > 0) {
                circleGc.fillArc(250, 100, 175, 175, angles[i-1], angles[i], ArcType.ROUND);
            }
            else {
                circleGc.fillArc(250, 100, 175, 175, 0, angles[i], ArcType.ROUND);
            }
        }

        main.getChildren().addAll(barGraph, circleChart);
        primaryStage.setScene(new Scene(main));
        primaryStage.setTitle("Graphs");
        primaryStage.show();

    }

    private float total() {
        float temp = 0f;
        for (int i = 0; i < purchasesByAgeGroup.length; i++) {
            temp += purchasesByAgeGroup[i];
        }
        return temp;
    }

    private float[] getAngles() {
        float[] angleArray = new float[ageGroups.length];
        float total = total();
        for (int i = 0; i < purchasesByAgeGroup.length; i++) {
            angleArray[i] = ((purchasesByAgeGroup[i]*100) / total)*3.65f*2f;
            System.out.println(angleArray[i]);
        }
        return angleArray;
    }
}