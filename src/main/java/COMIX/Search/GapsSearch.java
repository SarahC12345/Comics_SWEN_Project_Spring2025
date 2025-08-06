package COMIX.Search;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import COMIX.ComicGrader.Comic;

public class GapsSearch implements SearchStrategy {

  @Override
  public List<Comic> Search(List<Comic> comics, String query) {
    List<Comic> filteredComics = comics.stream()
        .filter(comic -> matchQuery(comic, query))
        .collect(Collectors.toList());

    Map<String, List<Comic>> groupedBySeries = filteredComics.stream()
        .collect(Collectors.groupingBy(Comic::getSeriesTitle));

    Set<String> validSeries = groupedBySeries.entrySet().stream()
        .filter(entry -> hasRunWithFewGaps(entry.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());

    return filteredComics.stream()
        .filter(comic -> validSeries.contains(comic.getSeriesTitle()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean matchQuery(Comic comic, String query) {
    return Stream.of(
      comic.getPublisher(),
      comic.getSeriesTitle(),
      comic.getPublicationDate(),
      comic.getCreators(),
      comic.getVolumeNumber(),
      comic.getIssueNumber(),
      comic.getPrincipleCharaters(),
      comic.getDescription())
    .filter(Objects::nonNull)
    .anyMatch(prop -> prop.toString().toLowerCase().contains(query.toLowerCase()));
  }

  private boolean hasRunWithFewGaps(List<Comic> comics) {
    List<Integer> issues = comics.stream()
        .map(comic -> parseIssueNumber(comic.getIssueNumber()))
        .filter(n -> n >= 0)
        .distinct()
        .sorted()
        .collect(Collectors.toList());

    if (issues.size() < 10) return false; // min 10 issues to even have a ~12-issue run with 2 gaps

    for (int i = 0; i <= issues.size() - 10; i++) {
      int start = issues.get(i);
      int count = 1;
      int gaps = 0;
      for (int j = i + 1; j < issues.size(); j++) {
        int expected = start + count;
        while (issues.get(j) > expected) {
          gaps++;
          count++;
          expected = start + count;
          if (gaps > 2) break;
        }
        if (gaps > 2) break;
        count++;
        if (count >= 12) return true;
      }
    }
    return false;
  }

  private int parseIssueNumber(String issue) {
    try {
      return Integer.parseInt(issue);
    } catch (NumberFormatException e) {
      return -1;
    }
  }
}
