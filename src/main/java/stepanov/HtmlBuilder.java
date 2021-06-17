package stepanov;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * HtmlBuilder class that has the following methods.
 * Implements logic for export to html format
 *
 * @author Maxim Stepanov
 */
public class HtmlBuilder implements TableBuilder {
    private int columns;
    private final StringBuilder table = new StringBuilder();

    public static String HTML_START = "<html>";
    public static String HTML_END = "</html>";

    public static String SPAN_START = "<span style=\"font-size:14px\">";
    public static String SPAN_END = "</span>";

    public static String TABLE_START = "<table style=\"font-size:16px\" border=\"1\" width=\"500px\">";
    public static String TABLE_END = "</table>";

    public static String HEADER_START = "<th bgcolor=\"lightgrey\">";
    public static String HEADER_END = "</th>";

    public static String FOOTER_START = "<td bgcolor=\"gold\" style=\"text-align:center\">";
    public static String FOOTER_END = "</td>";

    public static String ROW_START = "<tr>";
    public static String ROW_END = "</tr>";

    public static String COLUMN_START = "<td style=\"text-align:center\">";
    public static String COLUMN_END = "</td>";

    public HtmlBuilder(int rows, int columns) {
        this.columns = columns;
        table.append(HTML_START);
        table.append(TABLE_START);
        table.append(TABLE_END);
        table.append(HTML_END);
    }

    @Override
    public void addTableLabel(String value) {
        int lastIndex = table.lastIndexOf(HTML_START);
        if (lastIndex == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(SPAN_START);
            sb.append(value);
            sb.append(SPAN_END);
            table.insert(lastIndex, sb.toString());
        }
    }

    @Override
    public void addTableHeader(String... values) {
        if (values.length != columns) {
            System.out.println("Error column length");
        } else {
            int lastIndex = table.indexOf(TABLE_START) + TABLE_START.length();
            StringBuilder sb = new StringBuilder();
            sb.append(ROW_START);
            for (String value : values) {
                sb.append(HEADER_START);
                sb.append(value);
                sb.append(HEADER_END);
            }
            sb.append(ROW_END);
            table.insert(lastIndex, sb.toString());
        }
    }

    @Override
    public void addRowValues(String... values) {
        if (values.length != columns) {
            System.out.println("Error column length");
        } else {
            int lastIndex = table.lastIndexOf(TABLE_END);
            StringBuilder sb = new StringBuilder();
            sb.append(ROW_START);
            for (String value : values) {
                sb.append(COLUMN_START);
                sb.append(value);
                sb.append(COLUMN_END);
            }
            sb.append(ROW_END);
            table.insert(lastIndex, sb.toString());
        }
    }

    @Override
    public void addTableFooter(String... values) {
        if (values.length != columns) {
            System.out.println("Error column length");
        } else {
            int lastIndex = table.indexOf(TABLE_END);
            StringBuilder sb = new StringBuilder();
            sb.append(ROW_START);
            for (String value : values) {
                sb.append(FOOTER_START);
                sb.append(value);
                sb.append(FOOTER_END);
            }
            sb.append(ROW_END);
            table.insert(lastIndex, sb.toString());
        }
    }

    @Override
    public void export(Path path) {
        try {
            Files.writeString(path, table, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("There is a problem during writing file!");
        }
    }
}
