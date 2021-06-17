package stepanov;

import java.util.List;
import java.util.stream.Collectors;

public class HelperService {
    public static List<Record> getByCountryAndTimescale(List<Record> records, String country, String timescale) {
        return records.stream().filter(r -> r.getCountry().equals(country) && r.getTimescale().equals(timescale)).collect(Collectors.toList());
    }
}
