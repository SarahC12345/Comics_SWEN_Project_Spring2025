package COMIX.Search.SortSearch;

import java.util.*;

import COMIX.ComicGrader.Comic;


public class SortbySeries implements SortStrategy{
    @Override
    public List<Comic> sort(List<Comic> results) {
        results.sort((c1, c2) -> {
            String series1 = c1.getSeriesTitle();
            String series2 = c2.getSeriesTitle();
            
            // Compare series titles alphabetically
            return series1.compareToIgnoreCase(series2);
        });
        return results;
    }
}