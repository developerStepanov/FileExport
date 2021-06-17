package stepanov.enums;

/**
 * Enum Extension is using to choose suffix to exported file
 *
 * @author Maxim Stepanov
 */
public enum Extension {
    html(".html"),
    csv(".csv"),
    xlsx(".xlsx");

    private final String extension;

    Extension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static Extension getExtensionByFormat(Format format) {
        if(format.equals(Format.HTML)) {
            return Extension.html;
        } else if(format.equals(Format.CSV)) {
            return Extension.csv;
        } else if(format.equals(Format.Excel)) {
            return Extension.xlsx;
        } else {
            throw new IllegalArgumentException("Format did not match any of extension format");
        }
    }
}
