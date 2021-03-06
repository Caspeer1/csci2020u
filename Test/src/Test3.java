import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javafx.geometry.Insets;
import java.net.ServerSocket;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.Socket;

public class Test3 extends Application {

    TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        VBox main = new VBox(5);
        main.setPadding(new Insets(10, 10, 10, 10));
        /*
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(20);
         */
        // creates a textArea
        textArea = new TextArea();
        textArea.setFocusTraversable(false);
        textArea.setMouseTransparent(true);
        textArea.setEditable(false);

        // creates an exit button
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(evt -> System.exit(0));

        main.getChildren().addAll(textArea, getConsole(), exitBtn);
        /*
        gridPane.add(textArea, 0, 0);
        gridPane.add(consoleLbl, 0, 1);
        gridPane.add(consoleFld, 1, 1);
        gridPane.add(exitBtn, 0, 2);
         */

        Thread t = new Thread(new handleConnections());
        t.start();

        primaryStage.setTitle("SimpleBBS Server v1.0");
        Scene scene = new Scene(main);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox getConsole(){
        HBox console = new HBox(4);

        // creates a console chat
        Label consoleLbl = new Label("Console: ");
        TextField consoleFld = new TextField();
        Button consoleSend = new Button("Send");
        consoleSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String message = "Admin: " + consoleFld.getText();
                consoleFld.clear();
                textArea.appendText(message + "\n");
            }
        });

        console.getChildren().addAll(consoleLbl, consoleFld, consoleSend);

        return console;
    }

    public class handleConnections implements Runnable {

        private ServerSocket serverSocket;
        Thread[] threads = null;

        public handleConnections() {
            try {
                this.serverSocket = new ServerSocket(60000);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            System.out.println("Thread is Active");
            System.out.println("Now accepting socket connections");
            while (true) {
                try {
                    // accepts sockets
                    Socket clientSocket = serverSocket.accept();

                    // Launch serverGetTextJob and put it into a thread
                    Thread t = new Thread(new getText(clientSocket));
                    t.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class getText implements Runnable {
        private Socket clientSocket;
        private String message;

        public getText (Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while ((message = in.readLine()) != null) {
                    textArea.appendText(message + "\n");
                    System.out.println("Message being read from " + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}