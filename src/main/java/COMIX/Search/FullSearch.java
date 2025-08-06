package COMIX.Search;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import COMIX.ComicGrader.Comic;

public class FullSearch implements SearchStrategy{

  // based on what matchQuery returns, it will add the comic
  // it checked for matching to a List of comics to return
  @Override
  public List<Comic> Search(List<Comic> comics, String query) {
    return comics.stream().
    filter(comic -> matchQuery(comic, query)).
    collect(Collectors.toList());
  }
  
  // this method uses Java's stream to get a stream of each property of comic,
  // then filter out any null values and then comapre them to the given query
  // it returns true or false depending on each comic and query
  // this method return true only if the entire query matches the comic property
  @Override
  public boolean matchQuery(Comic comic, String query) {
    return Stream.of(
    comic.getPublisher(), 
    comic.getSeriesTitle(), 
    comic.getPublicationDate(), 
    comic.getCreators(), 
    comic.getVolumeNumber(),
    comic.getIssueNumber(),
    comic.getCreators(),
    comic.getPrincipleCharaters(),
    comic.getDescription()).
    filter(comicProp -> comicProp != null).
    anyMatch(comicProp -> comicProp.toString().toLowerCase().equals(query.toLowerCase()));
  }
}
