import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;





    public class LineChart {

        public static void main(String[] args) throws IOException, CsvValidationException {
            String csvFile = "src\\StockDataBANKBARODA_1.csv";
            CSVReader csvReader = new CSVReader(new FileReader(csvFile));

            List<Double> closingPrices = new ArrayList<>();
            List<Double> volumes = new ArrayList<>();
            List<Day> dates = new ArrayList<>();

            String[] header = csvReader.readNext(); // Read the header row

            int dateIndex = -1;
            int closeIndex = -1;
            int volumeIndex = -1;

            // Find the indices of the "Date", "Close" and "Volume" columns
            for (int i = 0; i < header.length; i++) {
                if (header[i].equals("Date")) {
                    dateIndex = i;
                } else if (header[i].equals("Close")) {
                    closeIndex = i;
                } else if (header[i].equals("Volume")) {
                    volumeIndex = i;
                }
            }

            if (dateIndex == -1 || closeIndex == -1 || volumeIndex == -1) {
                throw new IllegalArgumentException("The CSV file does not contain the required columns.");
            }

            String[] line;

            while ((line = csvReader.readNext()) != null) {
                double closingPrice = Double.parseDouble(line[closeIndex]);
                double volume = Double.parseDouble(line[volumeIndex]);
                String dateStr = line[dateIndex];

                // Parse the date string to a Day object using JFreeChart's Day class
                String[] dateParts = dateStr.split("-");
                Day date = new Day(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));

                closingPrices.add(closingPrice);
                volumes.add(volume);
                dates.add(date);
            }

            csvReader.close();

            // Create a TimeSeriesCollection containing the closing prices and volumes
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries closingPriceSeries = new TimeSeries("Closing Prices");
            TimeSeries volumeSeries = new TimeSeries("Volumes");

            for (int i = 0; i < closingPrices.size(); i++) {
                closingPriceSeries.addOrUpdate(dates.get(i), closingPrices.get(i));
                volumeSeries.addOrUpdate(dates.get(i), volumes.get(i));
                if (i >= 49) {
                    double[] closingPriceArray = closingPrices.subList(i - 49, i + 1).stream().mapToDouble(Double::doubleValue).toArray();
                    double movingAverage = Moving}

            }

            dataset.addSeries(closingPriceSeries);
            dataset.addSeries(volumeSeries);

            // Create the chart using JFreeChart's ChartFactory
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Closing Prices and Volumes",
                    "Date",
                    "Closing Price",
                    dataset,
                    true,
                    true,
                    false
            );
            ChartFrame frame = new ChartFrame("Closing Prices and Volumes", chart);
            frame.pack();
            frame.setVisible(true);

            // Customize the chart
            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.white);
            plot.setDomainGridlinePaint(Color.lightGray);
            plot.setRangeGridlinePaint(Color.lightGray);


        }}
