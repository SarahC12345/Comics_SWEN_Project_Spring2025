package COMIX.Search;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import COMIX.ComicGrader.Comic;
import COMIX.ComicGrader.GradedComic;
import COMIX.ComicGrader.SlabbedComic;

public class GradeComicSearch implements SearchStrategy{

  @Override
  public List<Comic> Search(List<Comic> comics, String query) {
    return comics.stream().
    filter(comic -> comic instanceof GradedComic || comic instanceof SlabbedComic).
    collect(Collectors.toList());
  }

  @Override
  public boolean matchQuery(Comic comic, String query) {
    // return Stream.of(
    //   comic.getIssueNumber()).filter(comicProp -> comicProp != null)
    return false;
    
  }
  
}
