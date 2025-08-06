package COMIX;
import java.util.List;
import java.util.stream.Collectors;

import COMIX.Browse.BrosweStrategy;
import COMIX.Browse.BrowseAll;
import COMIX.Browse.BrowseByPage;
import COMIX.CSVFileReader.CSVFileReader;
import COMIX.CSVFileReader.PersonalCSVFileReader;
import COMIX.ComicGrader.Comic;
import COMIX.DatabaseModifier.Command;
import COMIX.Search.GapsSearch;
import COMIX.Search.GradeComicSearch;
import COMIX.Search.IssueNumberSearch;
import COMIX.Search.PartialSearch;
import COMIX.Search.RunsSearch;
import COMIX.Search.SearchStrategy;

public class PersonalDatabase {
    private final String personalDataFile = "src/main/java/COMIX/data/personalcomics.csv";
    private final List<Comic> personalComics;
    private BrosweStrategy strategy;
    private SearchStrategy search;
    private Command command;

    private int gradedValue;
    private Boolean signed;

    public PersonalDatabase(){
        personalComics = PersonalCSVFileReader.readFile(personalDataFile);
        System.out.println(personalComics.toString());
    }

     public void setCommand(Command command){
        this.command = command;
    }
    public void executeEditCommand(Comic comic, String oldValue, String newValue){
        command.execute();
      }
    
    public void executeCommand(Comic comic){
        command.execute();
    }
    
    public void executeGradeCommand(Comic comic, int grade){
        command.execute();
    }


    public int getGradedValue() {
        return this.gradedValue;
    }

    public Boolean getSignedValue() {
        return this.signed;
    }

    public List<Comic> getCollection(){
        return personalComics;
    }

    public void setBrowseStrategy(BrosweStrategy strategy){
        this.strategy = strategy;
    }
    public List<Comic> getFullList(){
        return personalComics;
    }
    public void browse(){
        strategy.doBrowse(personalComics);
    }

    public void SetSearchStrategy(SearchStrategy searchStrategy){
        this.search = searchStrategy;
    } 

    public List<Comic> Search(String query){
        List<Comic> results = search.Search(personalComics, query);
        // for (int i = 0; i < 10; i++){
        //   System.out.println(results.get(i));
        // }
        //System.out.println(results.stream().map//(Comic::getIssueNumber).collect(Collectors.toList()));
        System.out.println(results);
        return results;
      }

  public static void main(String[] args) {
    PersonalDatabase database = new PersonalDatabase();
      database.setBrowseStrategy(new BrowseByPage(10));
      database.browse();

      System.out.println();

      database.setBrowseStrategy(new BrowseAll());
      database.browse();

      System.out.println();

    database.SetSearchStrategy(new GradeComicSearch());
    database.Search("graded");

    database.SetSearchStrategy(new IssueNumberSearch());
    database.Search("3");

    database.SetSearchStrategy(new RunsSearch());
    database.Search("batman");

    database.SetSearchStrategy(new GapsSearch());
    database.Search("amazing");
  }
}
