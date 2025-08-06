package COMIX.Search.SortSearch;

import java.util.*;

import COMIX.ComicGrader.Comic;


public class SortbyVolume implements SortStrategy{
    @Override
    public List<Comic> sort(List<Comic> results){
        results.sort(Comparator.comparingInt(Comic::getVolumeNumber));
        return results;
    }
}