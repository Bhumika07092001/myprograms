
        import java.io.*;
        import org.jfree.chart.*;
        import org.jfree.data.time.*;
        import org.jfree.data.xy.*;

public class Chart {
    public static void main(String[] args) throws IOException {
        // Step 1: Import the necessary libraries
        // java.io.* for reading CSV file
        // org.jfree.chart.* for creating line chart
        BufferedReader br = new BufferedReader(new FileReader("data.csv"));
        String line = "";

        // Step 3: Create arrays to store data
        double[] prices = new double[1000];
        double[] volumes = new double[1000];

        // Step 4: Loop through each line of CSV file and parse data
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            prices[i] = Double.parseDouble(values[0]);
            volumes[i] = Double.parseDouble(values[1]);
            i++;
        }
        br.close();

        // Step 5: Calculate moving average
        int windowSize = 20;
        double[] movingAverage = new double[prices.length - windowSize];
        for (i = 0; i < movingAverage.length; i++) {
            double sum = 0;
            for (int j = 0; j < windowSize; j++) {
                sum += prices[i + j];
            }
            movingAverage[i] = sum / windowSize;
        }


