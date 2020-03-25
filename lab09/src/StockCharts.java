import javafx.scene.canvas.GraphicsContext;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.net.URLConnection;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import com.google.gson.*;
import java.net.URL;
import java.util.*;

public class StockCharts extends Application {
    ArrayList<Double> stockOnePrices = new ArrayList<>();
    ArrayList<Double> stockTwoPrices = new ArrayList<>();
    private Canvas canvas;
    private Group root = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception{
        canvas = new Canvas(1000, 700);
        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 9");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        downloadStockPrices();
    }

    private void downloadStockPrices() {
        stockOnePrices = dataReader("MSFT");

        stockTwoPrices = dataReader("AAPL");

        drawPlotLine(root, stockOnePrices, stockTwoPrices);
    }
    private ArrayList<Double> dataReader(String stockName){
        ArrayList<Double> stockPrices = new ArrayList<>();
        String line = "";
        try {
            URL urlStockOne = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + stockName + "&interval=daily&apikey=UAJB3UOHEN4NW52J");
            URLConnection connStockOne = null;
            connStockOne = urlStockOne.openConnection();

            Scanner dataToParse = new Scanner(connStockOne.getInputStream());

            while (dataToParse.hasNextLine()) {
                line += dataToParse.nextLine();
            }
            dataToParse.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject document;
        JsonParser parser = new JsonParser();
        document = parser.parse(line).getAsJsonObject();
        if(document != null){
            JsonObject Monthly_Time_Series = document.get("Monthly Time Series").getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry: Monthly_Time_Series.entrySet()) {
                JsonObject eObj = entry.getValue().getAsJsonObject();
                stockPrices.add(eObj.get("2. high").getAsDouble());
            }
        }
        return stockPrices;
    }

    private void drawPlotLine(Group root, ArrayList<Double> stockOnePrices, ArrayList<Double> stockTwoPrices) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.BLACK);
        // y-axis line
        gc.strokeLine(50,50,50,650);
        // x-axis line
        gc.strokeLine(50,650,900,650);

        gc.setStroke(Color.RED);
        plotLine(stockOnePrices, gc);
        gc.setStroke(Color.BLUE);
        plotLine(stockTwoPrices, gc);
    }

    private void plotLine(ArrayList<Double> stockPrices, GraphicsContext gc) {
        double baseX = 50;
        double baseY = 650;
        double oldPrice = baseY - (stockPrices.get(239) / 100) * 50;
        double newPrice;
        double oldDate = baseX;
        double newDate = baseX;


        for (int i = stockPrices.size()-2; i >= 0; i--) {
            double price = stockPrices.get(i);

            newPrice = baseY - ((price / 100) * 50);
            newDate += 3;
            gc.strokeLine(oldDate, oldPrice, newDate, newPrice);
            oldDate = newDate;
            oldPrice = newPrice;
        }
    }
}