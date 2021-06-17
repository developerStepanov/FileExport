package stepanov;

import com.opencsv.bean.CsvBindByName;

/**
 * Class Record represents simple POJO object
 * It is a match to record(row) of a csv input file
 *
 * @author Maxim Stepanov
 */
public class Record {
    @CsvBindByName
    private String country;
    @CsvBindByName
    private String timescale;
    @CsvBindByName
    private String vendor;
    @CsvBindByName(locale = "double")
    private Double units;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTimescale(String timescale) {
        this.timescale = timescale;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public String getCountry() {
        return country;
    }

    public String getTimescale() {
        return timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public Double getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "Record{" +
                "country='" + country + '\'' +
                ", timescale='" + timescale + '\'' +
                ", vendor='" + vendor + '\'' +
                ", units=" + units +
                '}';
    }
}
