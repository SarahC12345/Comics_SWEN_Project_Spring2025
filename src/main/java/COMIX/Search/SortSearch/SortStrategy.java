package COMIX.Search.SortSearch;
import java.util.*;

import COMIX.ComicGrader.Comic;

public interface SortStrategy{
    List<Comic> sort(List<Comic> results);
}