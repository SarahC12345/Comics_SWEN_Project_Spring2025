package COMIX.Search;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import COMIX.ComicGrader.Comic;

public class RunsSearch implements SearchStrategy {

  @Override
  public List<Comic> Search(List<Comic> comics, String query) {
    List<Comic> filteredComics = comics.stream()
        .filter(comic -> matchQuery(comic, query))
        .collect(Collectors.toList());

    Map<String, List<Comic>> groupedBySeries = filteredComics.stream()
        .collect(Collectors.groupingBy(Comic::getSeriesTitle));

    Set<String> validSeries = groupedBySeries.entrySet().stream()
        .filter(entry -> hasValidRun(entry.getValue()))
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

  private boolean hasValidRun(List<Comic> comics) {
    List<Integer> issues = comics.stream()
        .map(comic -> parseIssueNumber(comic.getIssueNumber()))
        .filter(n -> n >= 0)
        .distinct()
        .sorted()
        .collect(Collectors.toList());

    if (issues.size() < 12) return false;

    int streak = 1;
    for (int i = 1; i < issues.size(); i++) {
      if (issues.get(i) == issues.get(i - 1) + 1) {
        streak++;
        if (streak >= 12) return true;
      } else {
        streak = 1;
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
