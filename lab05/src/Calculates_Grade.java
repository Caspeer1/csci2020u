/*
  Eric CB-Lamontagne
  100700304
 */
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.application.Application;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calculates_Grade extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    TableView<StudentRecord> data;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Stage window = primaryStage;
        window.setTitle("Lab05 Solutions");

        TableColumn<StudentRecord, String> sidColumn = new TableColumn<>("StudentID");
        sidColumn.setMinWidth(200);
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("StudentID"));

        TableColumn<StudentRecord,Float> midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setMinWidth(100);
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("Midterm"));

        TableColumn<StudentRecord,Float> assignmentColumn = new TableColumn<>("Assignment");
        assignmentColumn.setMinWidth(100);
        assignmentColumn.setCellValueFactory(new PropertyValueFactory<>("Assignment"));

        TableColumn<StudentRecord,Float> examColumn = new TableColumn<>("finalExam");
        examColumn.setMinWidth(100);
        examColumn.setCellValueFactory(new PropertyValueFactory<>("exam"));

        TableColumn<StudentRecord,Float> letterGradeColumn = new TableColumn<>("Letter Grade");
        letterGradeColumn.setMinWidth(100);
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        TableView table = new TableView<>();
        table.setItems(getAllStudents());
        table.getColumns().addAll(sidColumn,midtermColumn,assignmentColumn,examColumn,letterGradeColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public ObservableList<StudentRecord> getAllStudents(){
        ObservableList<StudentRecord> records = FXCollections.observableArrayList();
        records.add(new StudentRecord("100100100", 80.0f, 64.0f, 56.25f));
        records.add(new StudentRecord("100100101", 70.0f, 66.55f, 51.5f));
        records.add(new StudentRecord("100100102", 10.0f, 58.0f, 93.5f));
        records.add(new StudentRecord("100100103", 90.0f, 84.5f, 68.75f));
        records.add(new StudentRecord("100100104", 72.25f, 74.75f, 68.25f));
        records.add(new StudentRecord("100100105", 80.0f, 56.0f, 62.5f));
        records.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        records.add(new StudentRecord("100100107", 58.0f, 47.0f, 50.5f));
        records.add(new StudentRecord("100100108", 90.0f, 92.5f, 97.75f));
        records.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));
        return records;
    }
}
