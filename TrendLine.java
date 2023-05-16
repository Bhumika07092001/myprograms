import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;


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

    // Assuming you have a JFrame or any other container to display the chart
    DefaultXYDataset dataset = new DefaultXYDataset();
    double[][] data = new double[2][closingPrices.size()];
                for (int i = 0; i < closingPrices.size(); i++) {
        data[0][i] = i;
        data[1][i] = closingPrices.get(i);
    }
dataset.addSeries("Closing Prices", data);

    JFreeChart chart = ChartFactory.createXYLineChart(
            "HDFCBANK Stock", // Chart title
            "Time", // X-axis label
            "Closing Price", // Y-axis label
            dataset, // Dataset
            PlotOrientation.VERTICAL,
            true, // Include legend
            true, // Include tooltips
            false // Include URLs
    );
    // Create a new JFrame
    JFrame frame = new JFrame("Chart Display");

// Set the size and close operation of the frame
              frame.setSize(800, 600);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Add the chart to the frame
frame.getContentPane().add(chartPanel); // Assuming you have a chartPanel that holds your chart

// Display the frame
frame.setVisible(true);
// Save the chart as an image (optional)
try {
        ChartUtilities.saveChartAsPNG(new File("chart.png"), chart, 800, 600);
    } catch (IOException e) {
        e.printStackTrace();
    }







}
