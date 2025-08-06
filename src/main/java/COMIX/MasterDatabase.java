package COMIX;

import java.util.List;

import COMIX.ComicGrader.Comic;
import COMIX.ComicGrader.UngradedComic;
import COMIX.DatabaseModifier.AddComic;
import COMIX.DatabaseModifier.Command;
import COMIX.Search.FullSearch;
import COMIX.Search.PartialSearch;
import COMIX.Search.SearchStrategy;
import COMIX.Search.SortSearch.SortByPublicationDate;
import COMIX.Search.SortSearch.SortStrategy;
import COMIX.Search.SortSearch.SortbyIssueNumber;
import COMIX.Search.SortSearch.SortbySeries;
import COMIX.Search.SortSearch.SortbyTitle;
import COMIX.Search.SortSearch.SortbyVolume;
import COMIX.CSVFileReader.CSVFileReader;

public class MasterDatabase {
  String seriesTitle;
  int volumeNumber;
  String issueNumber;
  String title;
  String publisher;
  String publication; // could also use Date object
  String creators[];

  private SearchStrategy search;
  private SortStrategy sort;
  private Command command;
  private List<Comic> comics;

  public MasterDatabase() {
    comics = CSVFileReader.readFile("src/main/java/COMIX/data/comics.csv");
  } 

  public String getSeriesTitle(){
    return this.seriesTitle;
  }

  public int getVolumeNumber() {
    return this.volumeNumber;
  }

  public String getIssueNumber() {
    return this.issueNumber;
  }

  public String getTitle() {
    return this.title;
  }

  public String getPublisher() {
    return this.publisher;
  }

  public String getPublication() {
    return this.publication;
  }

  public String[] getCreators() {
    return this.creators;
  }

  public void SetSearchStrategy(SearchStrategy searchStrategy){
    this.search = searchStrategy;
  }

  public void SetSortStrategy(SortStrategy sortStrategy){
    this.sort = sortStrategy;
  }

  public void setCommand(Command command){
    this.command = command;
    System.out.println(command);
  }

  public List<Comic> Search(String query){
    List<Comic> results = search.Search(comics, query);
    for (int i = 0; i < 10; i++){
      System.out.println(results.get(i));
    }
    System.out.println(results.size());
    return results;
  }

  public List<Comic> getFullList(){
    return comics;
  }

  public List<Comic> Sort(List<Comic> results){
    results = sort.sort(results); 
    for (int i = 0; i < 10; i++){
      System.out.println(results.get(i));
    }
  }

  public void executeEditCommand(Comic comic, String oldValue, String newValue){
    command.execute();
  }

  public void executeAddCommand(Comic comic){
    command.execute();
  }

    public static void main(String[] args) {
      MasterDatabase database = new MasterDatabase();
      database.SetSearchStrategy(new PartialSearch());
      database.Search("batman");
  
      System.out.println("\nnow doing fullsearch");
      database.SetSearchStrategy(new FullSearch());
      List<Comic> searchResults = database.Search("batman");
  
      System.out.println("\nnow sorting search by IssueNumber");
      database.SetSortStrategy(new SortbyIssueNumber());
      database.Sort(searchResults);

      System.out.println("\nnow adding comic to personal database");
      UngradedComic comic = new UngradedComic("Tom Maszerowski", "Software Engineering", "Swen 262", "1/16/2025", 1, "1");
      database.setCommand(new AddComic(comic));
      database.executeAddCommand(comic);
  }
}
