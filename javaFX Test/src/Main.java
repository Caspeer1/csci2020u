import javafx.application.Application;
        import javafx.geometry.Insets;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Rectangle;
        import javafx.stage.Stage;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.HashMap;
        import java.util.Scanner;

public class Histogram extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox graph = getVBox();

        //Scene scene = new Scene(histogram, 500, 500, Color.WHITE);//Scene scene = new Scene(histogram, 500, 500, Color.WHITE);
        Scene scene = new Scene(graph);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Histogram");
        primaryStage.show();
    }

    private VBox getVBox() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(15, 15, 15, 15));

        //vbox.getChildren().add(Histogram());
        //vbox.getChildren().add(letterSpacing());
        vbox.getChildren().add(fileLocation());
        return vbox;
    }
    private HBox Histogram(){
        HBox hbox = new HBox(15);
        hbox.setPadding(new Insets(15,15,15,15));

        return hbox;
    }

    private HBox letterSpacing(){
        HBox hbox = new HBox(15);
        hbox.setPadding(new Insets(15,15,15,15));
        for (int i = 0; i < 26; i++){

        }
        /*Group histogram = new Group();
        int max = findMax(letters);

        for (int i = 0; i < 26; i++){
            //26 Rectangle's
            int height = 200 * (letters.get(Character.toChars(i + 97)[0]) / max);

            Rectangle rect = new Rectangle(10+15*i, 10+(200-height), 10, height);
            rect.setFill(Color.WHITE);
            rect.setStroke(Color.BLACK);

            histogram.getChildren().addAll(rect);
        }
        return histogram;
         */
        return hbox;
    }

    private HBox fileLocation(){
        HBox hbox = new HBox(15);
        hbox.setPadding(new Insets(15,15,15,15));

        //Filename TextField
        TextField filename = new TextField();
        filename.setPromptText("Filename");
        hbox.getChildren().add(new Label("Filename:"));
        hbox.getChildren().add(filename);

        //View button
        Button viewBtn = new Button("View");
        viewBtn.setDefaultButton(true);
        viewBtn.setOnAction(actionEvent -> {
            //getHistogram(getMap(filename.getText()));
            hbox.getChildren().add(getHistogram(getMap(filename.getText())));

        });

        hbox.getChildren().add(viewBtn);

        return hbox;
    }

    private HashMap<Character, Integer> getMap(String filename){
        File file = new File(filename);
        if (file.exists()){
            HashMap<Character, Integer> letters = new HashMap<>();
            Scanner scanFile = null;
            try { scanFile = new Scanner(file);
            } catch (FileNotFoundException e) { e.printStackTrace(); }

            while (scanFile.hasNext()){
                String words = scanFile.next();
                for (int i = 0; i < words.length(); i++){
                    char letter = words.charAt(i);
                    if (letters.get(letter) != null){
                        letters.put(letter, letters.get(letter) + 1);
                    } else {
                        letters.put(letter, 1);
                    }
                }
            }
            scanFile.close();
            return letters;
        }
        else{
            System.out.println("No file found at " + filename);
            System.exit(-1);
            return null;
        }
    }

    private int findMax(HashMap<Character, Integer> letters){
        char max = 'a';

        for (char letter: letters.keySet()) {
            if(letters.get(max) < letters.get(letter)) {
                max = letter;
            }
        }

        return letters.get(max);
    }

    private HBox getHistogram(HashMap<Character, Integer> letters){
        HBox hbox = new HBox(15);
        hbox.setPadding(new Insets(15,15,15,15));

        int max = findMax(letters);

        for (int i = 0; i < 26; i++){
            VBox vbox = new VBox(15);

            //26 Rectangle's
            int height = 200 * (letters.get(Character.toChars(i + 97)[0]) / max);

            Rectangle rect = new Rectangle(10+15*i, 10+(200-height), 10, height);
            rect.setFill(Color.WHITE);
            rect.setStroke(Color.BLACK);

            vbox.getChildren().addAll(rect);
            Label letter = new Label(Character.toString(i + 97));
            vbox.getChildren().add(letter);
            hbox.getChildren().add(vbox);

        }
        return hbox;
    }
}