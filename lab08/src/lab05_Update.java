/*
  Eric CB-Lamontagne
  100700304
 */
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

public class lab05_Update extends Application {
    private BorderPane layout;
    private TableView<StudentRecord> table;
    private File currentFilename;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 08");
        currentFilename = new File("test.csv");

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        fileMenu.getItems().add(newMenuItem);
        newMenuItem.setOnAction(evt -> table.setItems(null));
        MenuItem openMenuItem = new MenuItem("Open");
        fileMenu.getItems().add(openMenuItem);
        openMenuItem.setOnAction(evt -> load(primaryStage));
        MenuItem saveMenuItem = new MenuItem("Save");
        fileMenu.getItems().add(saveMenuItem);
        saveMenuItem.setOnAction(evt -> save());
        MenuItem saveAsMenuItem = new MenuItem("Save As");
        fileMenu.getItems().add(saveAsMenuItem);
        saveAsMenuItem.setOnAction(evt -> saveAs(primaryStage));
        MenuItem exitMenuItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitMenuItem);
        exitMenuItem.setOnAction(evt -> System.exit(0));


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        table = new TableView<>();
        table.setItems(DataSource.getAllMarks());

        TableColumn<StudentRecord, String> sidColumn = new TableColumn<>("SID");
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("SID"));
        sidColumn.setMinWidth(100);

        TableColumn<StudentRecord, Float> assignColumn = new TableColumn<>("Assignments");
        assignColumn.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        assignColumn.setMinWidth(100);

        TableColumn<StudentRecord, Float> midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midtermColumn.setMinWidth(100);

        TableColumn<StudentRecord, Float> finalExamColumn = new TableColumn<>("Final Exam");
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalExamColumn.setMinWidth(100);

        TableColumn<StudentRecord,Float> finalMarkColumn = new TableColumn<>("Final Mark");
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        finalExamColumn.setMinWidth(100);

        TableColumn<StudentRecord, String> letterGradeColumn = new TableColumn<>("Letter Grade");
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        letterGradeColumn.setMinWidth(100);


        table.getColumns().add(sidColumn);
        table.getColumns().add(assignColumn);
        table.getColumns().add(midtermColumn);
        table.getColumns().add(finalExamColumn);
        table.getColumns().add(finalMarkColumn);
        table.getColumns().add(letterGradeColumn);


        // GridPane fng a new student
        Label sidLbl = new Label("SID: ");
        TextField sidFld = new TextField();
        Label midtermLbl = new Label("Midterm: ");
        TextField midtermFld = new TextField();
        Label assignLbl = new Label("Assignments: ");
        TextField assignFld = new TextField();
        Label finalExamLbl = new Label("Final Exam: ");
        TextField finalExamFld = new TextField();
        Button addBtn = new Button("Add");

        GridPane addPane = new GridPane();
        addPane.setHgap(20);
        addPane.setVgap(20);

        addPane.add(sidLbl, 0, 0);
        addPane.add(sidFld, 1, 0);
        addPane.add(midtermLbl, 0, 1);
        addPane.add(midtermFld, 1, 1);
        addPane.add(assignLbl, 2, 0);
        addPane.add(assignFld, 3, 0);
        addPane.add(finalExamLbl, 2, 1);
        addPane.add(finalExamFld, 3, 1);
        addPane.add(addBtn, 1, 3);

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String SID = sidFld.getText();
                float assignment = Float.parseFloat(assignFld.getText());
                float midterm = Float.parseFloat(midtermFld.getText());
                float finalExam = Float.parseFloat(finalExamFld.getText());

                if (table.getItems() == null) {
                    ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
                    table.setItems(marks);
                }

                table.getItems().add(new StudentRecord(SID, assignment, midterm, finalExam));

                sidFld.setText("");
                assignFld.setText("");
                midtermFld.setText("");
                finalExamFld.setText("");
            }
        });


        layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(table);
        layout.setBottom(addPane);
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveAs(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Student Records file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        currentFilename = fileChooser.showSaveDialog(primaryStage);
        if (currentFilename != null) {
            save();
        }
    }

    private void save() {
        try {
            PrintWriter fOut = new PrintWriter(currentFilename);
            ObservableList<StudentRecord> myList = table.getItems();
            for (StudentRecord studentRecord : myList) {
                fOut.println(studentRecord.SID + "," + studentRecord.assignment + "," + studentRecord.midterm + ","
                        + studentRecord.finalExam);
                System.out.println(studentRecord.SID + "," + studentRecord.assignment + "," + studentRecord.midterm + ","
                        + studentRecord.finalExam);
            }
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Student Records file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        currentFilename = fileChooser.showOpenDialog(primaryStage);
        if (currentFilename != null) {
            String SID;
            float assignment = 0;
            float midterm = 0;
            float finalExam = 0;
            ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

            try {
                BufferedReader in = new BufferedReader(new FileReader(currentFilename));
                String line;
                while ((line = in.readLine()) != null) {
                    String[] data = line.split(",");
                    SID = data[0];
                    assignment = Float.parseFloat(data[1]);
                    midterm = Float.parseFloat(data[2]);
                    finalExam = Float.parseFloat(data[3]);
                    marks.add(new StudentRecord(SID, assignment, midterm, finalExam));
                }
                table.setItems(marks);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}