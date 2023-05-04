import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StockDataReader {
        public static void main(String[] args) {
            String csvFile = "src\\StockDataBANKBARODA_1.csv";
            List<StockData> data = new ArrayList<>();
            try {

                CSVReader reader = new CSVReader(new FileReader(csvFile));
                String[] header = reader.readNext(); // Read header row
                String[] row;
                while ((row = reader.readNext()) != null) {
                    StockData stock = new StockData(row[0], row[1], Double.parseDouble(row[2]), Double.parseDouble(row[3]),
                            Double.parseDouble(row[4]), Double.parseDouble(row[5]), Long.parseLong(row[6]));
                    data.add(stock);
                }
                reader.close();
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }

//             Analyze the stock market data as needed
             //for example Filter the stock market data to only show rows where the closing price is >= 180
            List<StockData> filteredData = new ArrayList<StockData>();
            for (StockData stock : data) {
                if (stock.getClosePrice() < 180) {
                    filteredData.add(stock);
                }
            }

            // Print the filtered data to the console
            for (StockData stock : filteredData) {
                System.out.println(stock);
            }
        }
    }

    class StockData {
        private String symbol;
        private double openPrice;
        private double closePrice;
        private double highPrice;
        private String lowPrice;
        private String volume;

        public StockData(String symbol, String date, double openPrice, double closePrice, double highPrice, double lowPrice, long volume) {
            this.symbol = symbol;
            this.openPrice = openPrice;
            this.closePrice = closePrice;
            this.highPrice = highPrice;
        }

        public String getSymbol() {
            return symbol;
        }


        public String getDate() {
            return symbol;
        }

        public double getClosePrice() {
            return 0;
        }


        public String toString() {
            return "Symbol: " + symbol + ", Date: "  + ", Open Price: " + openPrice + ", Close Price: " + closePrice + ", High Price: " + highPrice + ", Low Price: " + lowPrice + ", Volume: " + volume;
        }

    }
