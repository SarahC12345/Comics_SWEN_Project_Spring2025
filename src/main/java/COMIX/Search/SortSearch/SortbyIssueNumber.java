package COMIX.Search.SortSearch;

import java.util.*;
import COMIX.ComicGrader.Comic;


public class SortbyIssueNumber implements SortStrategy {
    @Override
    public List<Comic> sort(List<Comic> results) {
        results.sort((c1, c2) -> {
            String issue1 = c1.getIssueNumber();
            String issue2 = c2.getIssueNumber();

            // Extract numeric and alphabetic parts
            int num1 = extractNumericPart(issue1);
            int num2 = extractNumericPart(issue2);
            
            // Compare numeric parts
            int numComparison = Integer.compare(num1, num2);
            if (numComparison != 0) {
                return numComparison;  // If numeric parts differ, return comparison result
            }

            // If numeric parts are the same, compare alphabetic parts
            String letter1 = extractLetterPart(issue1);
            String letter2 = extractLetterPart(issue2);
            return letter1.compareTo(letter2); 
        });
        return results;
    }

    // Helper method to extract the numeric part of the issue number (handles numbers and letters like "1A", "2", "10B")
    private int extractNumericPart(String issue) {
        String numericPart = issue.replaceAll("[^0-9]", "");  
        return numericPart.isEmpty() ? 0 : Integer.parseInt(numericPart); 
    }

    // Helper method to extract the alphabetic part of the issue number (handles things like "A", "B")
    private String extractLetterPart(String issue) {
        return issue.replaceAll("[^a-zA-Z]", "");  
    }
}