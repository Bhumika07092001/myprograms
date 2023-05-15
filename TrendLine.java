import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;



public class TrendLine {
    private static List<Double> loadCSVData(String filePath) {
        List<Double> closingPrices = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double closingPrice = Double.parseDouble(values[4]); // Assuming closing price is in the 5th column
                closingPrices.add(closingPrice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return closingPrices;
    }
    String csvFilePath = "src\\StockDataHDFCBANK.csv";
    List<Double> closingPrices = loadCSVData(csvFilePath);



    



}
