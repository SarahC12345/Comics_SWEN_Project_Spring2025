package COMIX.Search.SortSearch;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import COMIX.ComicGrader.Comic;

public class SortByPublicationDate implements SortStrategy {

    private static final List<SimpleDateFormat> dateFormats = Arrays.asList(
        new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH),  // e.g., Jun 10, 2015
        new SimpleDateFormat("MMM yyyy", Locale.ENGLISH),      // e.g., Jun 2015
        new SimpleDateFormat("yyyy", Locale.ENGLISH)           // e.g., 2015
);

@Override
public List<Comic> sort(List<Comic> results) {
    results.sort((c1, c2) -> {
        Date d1 = parseDate(c1.getPublicationDate());
        Date d2 = parseDate(c2.getPublicationDate());

        if (d1 == null && d2 == null) return 0;
        if (d1 == null) return 1; // nulls go last
        if (d2 == null) return -1;

        return d1.compareTo(d2);
    });
    return results;
}

// Try parsing the date with multiple formats
private Date parseDate(String dateString) {
    for (SimpleDateFormat format : dateFormats) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            // Ignore exception, try next format
        }
    }
    return null; // Return null if none of the formats work
}
    
}