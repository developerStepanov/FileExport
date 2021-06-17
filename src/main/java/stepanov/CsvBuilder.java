package stepanov;

import java.nio.file.Path;

/**
 *
 * CsvBuilder class that has the following methods.
 * It has to implements logic for export to csv format
 *
 * @author Maxim Stepanov
 */
public class CsvBuilder implements TableBuilder {
    @Override
    public void addTableLabel(String value) {

    }

    @Override
    public void addTableHeader(String... values) {

    }

    @Override
    public void addRowValues(String... values) {

    }

    @Override
    public void addTableFooter(String... values) {

    }

    @Override
    public void export(Path path) {

    }
}
