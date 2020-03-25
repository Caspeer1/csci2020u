import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Test3 extends Application {
    GridPane addPane = new GridPane();
    HashMap<String, Integer> accounts = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //on button press call Function btn(accounts);
        btnS();

        Scene scene = new Scene(addPane, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Text");
        primaryStage.show();
    }
    private void btnS(){
        Button a = new Button("Add account");
        Button b = new Button("Check Balance");
        addPane.add(a, 0, 1);
        a.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addAccount(accounts);
            }
        });

        addPane.add(b, 0, 2);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkBal(accounts);
            }
        });
    }

    private HashMap<String, Integer> addAccount(HashMap<String, Integer> accounts){
        Label nameLbl = new Label("Name: ");
        TextField nameFld = new TextField();
        Label balanceLbl = new Label("Balance: ");
        TextField balanceFld = new TextField();
        balanceFld.setStyle("-fx-control-inner-background: lightgrey;");
        balanceFld.setFocusTraversable(false);
        balanceFld.setMouseTransparent(true);
        balanceFld.setEditable(false);
        Button addBtn = new Button("Add   ");

        addPane.add(nameLbl, 0, 0);
        addPane.add(nameFld, 1, 0);
        addPane.add(addBtn, 1, 3);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Name = nameFld.getText();
                accounts.put(Name, 300);
            }
        });
        return accounts;
    }

    private void checkBal(HashMap<String, Integer> accounts){
        Label nameLbl = new Label("Name: ");
        TextField nameFld = new TextField();
        Label balanceLbl = new Label("Balance: ");
        TextField balanceFld = new TextField();
        balanceFld.setStyle("-fx-control-inner-background: lightgrey;");
        balanceFld.setFocusTraversable(false);
        balanceFld.setMouseTransparent(true);
        balanceFld.setEditable(false);
        Button addBtn = new Button("Check");

        addPane.add(nameLbl, 0, 0);
        addPane.add(nameFld, 1, 0);
        addPane.add(balanceLbl, 2, 0);
        addPane.add(balanceFld, 3, 0);
        addPane.add(addBtn, 1, 3);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                balanceFld.clear();
                String Name = nameFld.getText();

                DecimalFormat df = new DecimalFormat("#.00");
                for (int i = 0; i < accounts.size(); i++){
                    balanceFld.setText(df.format(accounts.get(Name)));
                }
            }
        });
    }

    private void clearBtn(){

    }
}