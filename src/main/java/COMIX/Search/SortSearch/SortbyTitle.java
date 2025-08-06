package COMIX.Search.SortSearch;

import java.util.*;

import COMIX.ComicGrader.Comic;


public class SortbyTitle implements SortStrategy{
    @Override
    public List<Comic> sort(List<Comic> results) {
        results.sort((c1, c2) -> {
            String title1 = c1.getStoryTitle();
            String title2 = c2.getStoryTitle();
            
            // Compare full titles alphabetically
            return title1.compareToIgnoreCase(title2);
        });
        return results;
    }
}