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

            // Analyze the stock market data as needed
            // For example, calculate the average closing price for AAPL over the past year
//            double sum = 0;
//            int count = 0;
//            for (StockData stock : data) {
//                if (stock.getSymbol().equals("AAPL") && stock.getDate().startsWith("2022")) {
//                    sum += stock.getClosePrice();
//                    count++;
//                }
//            }
//            double avg = sum / count;
//            System.out.println("Average closing price for AAPL in 2022: " + avg);


            // Filter the stock market data to only show rows where the closing price is >= 180
            List<StockData> filteredData = new ArrayList<StockData>();
            for (StockData stock : data) {
                if (stock.getClosePrice() < 150) {
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
            return null;
        }

        public double getClosePrice() {
            return 0;
        }


        // Getters and setters omitted for brevity
    }


