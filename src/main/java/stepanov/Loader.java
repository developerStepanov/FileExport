package stepanov;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Class Loader load an input csv file to a List to easily operate with data
 *
 * @author Maxim Stepanov
 */
public class Loader {
    public static List<Record> load(File file) throws FileNotFoundException {
       return new CsvToBeanBuilder(new FileReader(file))
                .withType(Record.class).build().parse();
    }
}
