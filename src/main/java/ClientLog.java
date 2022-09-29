import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    protected List <Integer> list = new ArrayList<>();
    protected File txtFile;


    public ClientLog() {}

    public void log(int productNum, int amount) {
        list.add(productNum);
        list.add(amount);
    }

    public void exportAsCSV(File txtFile) {
        this.txtFile = txtFile;
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(txtFile, true))) {
            csvWriter.writeNext(list.toString().split(","));
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        list.clear();
    }
}
