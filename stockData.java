
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

        import com.opencsv.CSVReader;

public class StockDataReader {
    public static void main(String[] args) {
        String csvFile = "stock_data.csv";
        List<StockData> data = new ArrayList<StockData>();
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            String[] header = reader.readNext(); // Read header row
            String[] row;
            while ((row = reader.readNext()) != null) {
                StockData stock = new StockData(row[0], row[1], Double.parseDouble(row[2]), Double.parseDouble(row[3]), Double.parseDouble(row[4]), Double.parseDouble(row[5]), Long.parseLong(row[6]));
                data.add(stock);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Analyze the stock market data as needed
        // For example, calculate the average closing price for AAPL over the past year
        double sum = 0;
        int count = 0;
        for (StockData stock : data) {
            if (stock.getSymbol().equals("AAPL") && stock.getDate().startsWith("2022")) {
                sum += stock.getClosePrice();
                count++;
            }
        }
        double avg = sum / count;
        System.out.println("Average closing price for AAPL in 2022: " + avg);
    }
}

class StockData {
    private String symbol;
    private String date;
    private double openPrice;
    private double closePrice;
    private double highPrice;
    private double lowPrice;
    private long volume;

    public StockData(String symbol, String date, double openPrice, double closePrice, double highPrice, double lowPrice, long volume) {
        this.symbol = symbol;
        this.date = date;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
    }

    // Getters and setters omitted for brevity
}

