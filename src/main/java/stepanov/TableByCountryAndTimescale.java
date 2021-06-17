package stepanov;

import stepanov.enums.Column;
import stepanov.enums.Extension;
import stepanov.enums.Format;
import stepanov.enums.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * TableByCountryAndTimescale class that has the following methods.
 * It implements logic to create a table by each country and timescale
 *
 * @author Maxim Stepanov
 */
public class TableByCountryAndTimescale {
    private final List<Record> tableRecords;
    private TableBuilder tableBuilder;
    private Extension extension;

    private final String countryName;
    private final String timescaleName;

    /**
     * How many columns in a table
     */
    private final int COLUMNS_COUNT = 3;
    /**
     * How many total units among all vendors
     */
    private final double totalUnits;

    public TableByCountryAndTimescale(List<Record> tableRecords, String country, String timescale) {
        List<Record> preparedData= HelperService.getByCountryAndTimescale(tableRecords, country,  timescale);

        this.countryName = country;
        this.timescaleName = timescale;

        this.tableRecords = preparedData;
        this.totalUnits = preparedData.stream().map(Record::getUnits).collect(Collectors.summingDouble(Double::doubleValue));
    }

    public void sortBy(Column sortBy, Order order) {
        Comparator comparator = null;

        if(sortBy.equals(Column.Vendor) && order.equals(Order.asc)) {
            comparator = Comparator.comparing(Record::getVendor);
        } else if(sortBy.equals(Column.Vendor) && order.equals(Order.desc)) {
            comparator = Comparator.comparing(Record::getVendor).reversed();
        } else if(sortBy.equals(Column.Unit) && order.equals(Order.asc)) {
            comparator = Comparator.comparing(Record::getUnits);
        } else if(sortBy.equals(Column.Unit) && order.equals(Order.desc)) {
            comparator = Comparator.comparing(Record::getUnits).reversed();
        } else {
            throw new IllegalArgumentException("None of the argument match existing comparator object");
        }

        tableRecords.sort(comparator);
    }

    public void exportTo(Format format) {
        extension = Extension.getExtensionByFormat(format);

        if(format.equals(Format.HTML)) {
            tableBuilder = new HtmlBuilder(tableRecords.size(), COLUMNS_COUNT);
        } else if(format.equals(Format.CSV)) {
            throw new IllegalStateException("You need to implement CSV builder");
        } else if(format.equals(Format.Excel)) {
            throw new IllegalStateException("You need to implement Excel builder");
        } else {
            throw new IllegalStateException("None of the format match!");
        }
    }

    public void export() {
        if(tableBuilder == null || extension == null) {
            throw new IllegalArgumentException("Select format to export first!");
        }
        final Path storePath = getPathToStore();

        tableBuilder.addTableLabel(getTableLabel());
        tableBuilder.addTableHeader(getTableHeader());

        for(Record r : tableRecords) {
            tableBuilder.addRowValues(getTableRow(r));
        }

        tableBuilder.addTableFooter(getTableFooter());

        tableBuilder.export(storePath);
        System.out.println(String.format("Path of created file: %s", storePath));
    }

    private String getTableLabel(){
        return String.format("Table 1,PC Quarterly Market Share, the %s, %s", this.countryName, this.timescaleName);
    }

    private String[] getTableHeader() {
        String[] header = new String[] {"Vendor", "Units", "Share"};
        validateRowLength(header);
        return header;
    }

    private String[] getTableRow(Record r) {
        String[] row = new String[] {r.getVendor(), String.format("%.3f", r.getUnits()), String.format("%.1f%%", (r.getUnits()/this.totalUnits)*100)};
        validateRowLength(row);
        return row;
    }

    private String[] getTableFooter() {
        String[] footer =  new String[] {"Total", String.format("%.3f", totalUnits), "100%"};
        validateRowLength(footer);
        return footer;
    }

    private void validateRowLength(String[] row) {
        if(row.length != COLUMNS_COUNT) {
            throw new IllegalArgumentException("Column's count did not match to expected");
        }
    }

    private Path getPathToStore() {
        Path storePath = null;
        try {
            storePath = Paths.get(String.format("%s%soutput%s", new java.io.File(".").getCanonicalPath(), System.getProperty("file.separator"), extension.getExtension()));
        } catch (IOException e) {
            System.out.println("There is a problem during creating store path!");
        }

        return storePath;
    }
}
