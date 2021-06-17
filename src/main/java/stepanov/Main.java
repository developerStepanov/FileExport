package stepanov;

import stepanov.enums.Column;
import stepanov.enums.Format;
import stepanov.enums.Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String args []) {
        List<Record> records = null;
        String resourceUrl = Main.class.getResource(System.getProperty("file.separator")).getPath();
        String url =  String.format("%s%sdata.csv", resourceUrl, System.getProperty("file.separator"));
        File fileCSV = new File(url);

        try {
            records = Loader.load(fileCSV);
        } catch (FileNotFoundException e) {
            try {
                throw new IllegalArgumentException(String.format("Cannot read stored csv file, file not found in path: %s!", fileCSV.getCanonicalPath()));
            } catch(IOException ioException) {
                System.out.println("Error in reading path object of stored csv file!");
            }
        }

        TableByCountryAndTimescale table = new TableByCountryAndTimescale(records, "Slovakia", "2010 Q4");
        table.sortBy(Column.Vendor, Order.asc);
        table.exportTo(Format.Excel);
        table.export();
    }
}
