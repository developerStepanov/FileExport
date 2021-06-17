package stepanov;

import java.nio.file.Path;

/**
 *
 * TableBuilder interface that has the following methods.
 * Class has to implements this interface if it is necessary to extend export format
 *
 * @author Maxim Stepanov
 */
public interface TableBuilder {
    /**
     * Method to add a label to table, has to be on up side of a table
     * @param value a label that has to be applied to table
     */
    void addTableLabel(String value);
    /**
     * Method to add a header to table, has to be the first row of a table
     * @param values array of header values
     */
    void addTableHeader(String... values);
    /**
     * Method to add a row to table
     * @param values array of body table values
     */
    void addRowValues(String... values);
    /**
     * Method to add a footer to table, has to be the last row of a table
     * @param values array of footer values
     */
    void addTableFooter(String... values);
    /**
     * Method to export table to file by path
     * @param path a path where file has to be stored
     */
    void export(Path path);
}
