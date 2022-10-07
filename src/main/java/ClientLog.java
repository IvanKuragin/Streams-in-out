import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    protected List<Log> list = new ArrayList<>();
    protected File csvFile;
    protected int productNum;
    protected int amount;


    public ClientLog() {
    }

    public void log(int productNum, int amount) {
        this.productNum = productNum + 1;
        this.amount = amount;
    }

    public void exportAsCSV(File csvFile) {
        this.csvFile = csvFile;
        list.add(new Log(productNum, amount));
        ColumnPositionMappingStrategy<Log> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Log.class);
        strategy.setColumnMapping("productNum", "amount");
        String [] title1 = {"productNum,amount"};
        if (csvFile.length() == 0) {
            try (CSVWriter title = new CSVWriter(new FileWriter(csvFile, true))) {
                title.writeNext(title1);
            } catch (IOException error) {
                error.getStackTrace();
            }
        }

            try (FileWriter writer = new FileWriter(csvFile, true)) {
                StatefulBeanToCsv<Log> sbc = new StatefulBeanToCsvBuilder<Log>(writer)
                        .withMappingStrategy(strategy)
                        .build();
                sbc.write(list);
            } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException error) {
                error.printStackTrace();
            }
            list.clear();
        }
}
