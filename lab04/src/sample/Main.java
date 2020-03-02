package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class Main extends Application {

    private DatePicker checkInDatePicker;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        //Username TextField
        TextField UsNameF = new TextField();
        UsNameF.setPromptText("Username");
        pane.add(new Label("Username:"),0,0);
        pane.add(UsNameF,1,0);

        //Password TextField
        PasswordField PsWordF = new PasswordField();
        PsWordF.setPromptText("Password");
        pane.add(new Label("Password:"), 0,1);
        pane.add(PsWordF,1,1);

        //Full Name TextField
        TextField FuNameF = new TextField();
        FuNameF.setPromptText("Full Name");
        pane.add(new Label("Full Name:"), 0 , 2);
        pane.add(FuNameF,1 ,2);

        //Email TextField
        TextField EmailF = new TextField();
        EmailF.setPromptText("Example@sample.com");
        pane.add(new Label("Email:"), 0 , 3);
        pane.add(EmailF,1 ,3);

        //Phone # TextField
        TextField PhNumF = new TextField();
        PhNumF.setPromptText("###-###-####");
        pane.add(new Label("Phone #:"), 0 , 4);
        pane.add(PhNumF,1 ,4);

        //Date of Birth TextField
        pane.add(new Label("Date of Birth:"), 0 , 5);
        checkInDatePicker = new DatePicker();
        pane.add(checkInDatePicker,1 ,5);
        String pattern = "M/d/yyyy";
        checkInDatePicker.setPromptText(pattern.toLowerCase());



        //Register button
        Button regButton = new Button("Register");
        pane.add(regButton,1,6);
        regButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                String sUsName = UsNameF.getText();
                String sPsWord = PsWordF.getText();
                String sFuName = FuNameF.getText();
                String sEmail = EmailF.getText();
                String sPhNum = PhNumF.getText();
                String bDay = checkInDatePicker.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));

                System.out.printf("Full Name: %s \n E-Mail: %s \n Phone #: %s \n Birthday: %s \n", sFuName, sEmail, sPhNum, bDay);

                //Clear TextFields

                UsNameF.setText("");
                PsWordF.setText("");
                FuNameF.setText("");
                EmailF.setText("");
                PhNumF.setText("");
                checkInDatePicker.setValue(null);
            }
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 04 Solution");
        primaryStage.show();
    }
}








