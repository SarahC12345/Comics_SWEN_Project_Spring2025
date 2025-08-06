package COMIX.Search;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import COMIX.ComicGrader.Comic;

public class IssueNumberSearch implements SearchStrategy{

  @Override
  public List<Comic> Search(List<Comic> comics, String query) {
    return comics.stream().
    filter(comic -> matchQuery(comic, query)).
    collect(Collectors.toList());
  }

  @Override
  public boolean matchQuery(Comic comic, String query) {
    return Stream.of(
    comic.getIssueNumber()).
    filter(comicProp -> comicProp != null).
    anyMatch(comicProp -> comicProp.toString().toLowerCase().equals(query.toLowerCase()));
  }
  
}
