/*
  Eric CB-Lamontagne
  100700304
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.application.Application;
import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileReader;

public class PieChart_csv extends Application {

    private static String[] names = {"FLASH FLOOD", "SEVERE THUNDERSTORM", "SPECIAL MARINE", "TORNADO"};
    private static int[] amounts = {0, 0, 0, 0};
    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.PLUM};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Canvas circle = new Canvas(520,300);
        GraphicsContext gcb = circle.getGraphicsContext2D();


        BufferedReader csvReader = new BufferedReader(new FileReader("src/data/weatherwarnings-2015.csv"));
        String[] data = null;
        String row = "";
        try {
            while((row = csvReader.readLine()) != null){
                data = row.split(",");
                if (names[0].equals(data[5])){
                    amounts[0]++;
                }else if(names[1].equals(data[5])){
                    amounts[1]++;
                } else if(names[2].equals(data[5])){
                    amounts[2]++;
                }else if(names[3].equals(data[5])){
                    amounts[3]++;
                }

            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        for(int i = 0; i < names.length; i++){
            gcb.setFill(Color.AQUA);
            gcb.fillRect(75,75,50,25);
            gcb.strokeText("FLASH FLOOD", 150, 90);

            gcb.setFill(Color.GOLD);
            gcb.fillRect(75,125,50,25);
            gcb.strokeText("SEVERE THUNDERSTORM", 150, 140);

            gcb.setFill(Color.DARKORANGE);
            gcb.fillRect(75,175,50,25);
            gcb.strokeText("SPECIAL MARINE",150,190);

            gcb.setFill(Color.PLUM);
            gcb.fillRect(75,225,50,25);
            gcb.strokeText("TORNADO",150,240);
        }

        double total = 0;
        for(int i = 0; i < amounts.length; i++) {total += amounts[i];}
        double currentArc = total;
        for(int i = names.length - 1; i >= 0 ;i--){
            gcb.setFill(pieColours[i]);
            gcb.fillArc(300.0,45.0,200.0,200.0,0.0,(currentArc/total)*360, ArcType.ROUND);
            currentArc -= amounts[i];
        }

        root.getChildren().add(circle);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Lab 7");
        primaryStage.show();
    }
}
