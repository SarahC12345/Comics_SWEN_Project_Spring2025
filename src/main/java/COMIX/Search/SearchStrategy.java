package COMIX.Search;
import java.util.List;
import COMIX.ComicGrader.*;


public interface SearchStrategy {
  public List<Comic> Search(List<Comic> comics, String query);

  public boolean matchQuery(Comic comic, String query);
}


