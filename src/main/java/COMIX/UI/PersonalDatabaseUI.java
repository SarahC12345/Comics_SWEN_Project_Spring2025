package COMIX.UI;

import java.util.List;
import java.util.Scanner;

import COMIX.MasterDatabase;
import COMIX.ComicGrader.Comic;
import COMIX.DatabaseModifier.Command;
import COMIX.Search.GapsSearch;
import COMIX.Search.GradeComicSearch;
import COMIX.Search.IssueNumberSearch;
import COMIX.Search.RunsSearch;
import COMIX.Search.SearchStrategy;
import COMIX.PersonalDatabase;
import COMIX.Browse.BrosweStrategy;
import COMIX.Browse.BrowseAll;
import COMIX.Browse.BrowseByPage;
public class PersonalDatabaseUI implements UISection {
        public UISection section;
        public boolean signedIn;
        private SearchStrategy search;
        private BrosweStrategy strategy;
        private List<Comic> comics;
        public Command command;
        public PersonalDatabaseUI(boolean signedIn){
            this.signedIn = signedIn;
        }

        public void SetSearchStrategy(SearchStrategy searchStrategy){
            this.search = searchStrategy;
        }
        public List<Comic> Search(String query){
            List<Comic> results = search.Search(comics, query);
            System.out.println(results);
            return results;
          }

        public void setBrowseStrategy(BrosweStrategy strategy){
            this.strategy = strategy;
        }
    public void browse(){
        strategy.doBrowse(comics);
    }

        public void takeInput(char x){
            Scanner scanner = new Scanner(System.in);
            section = new MasterDatabaseUI(signedIn);
            PersonalDatabase database = new PersonalDatabase();
            String searchWord;
            switch (x) {
                case '1':
                    // search by issue number
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.SetSearchStrategy(new IssueNumberSearch());
                    database.Search(searchWord);
                    break;
                case '2':
                    // search by graded comics
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.SetSearchStrategy(new GradeComicSearch());
                    database.Search(searchWord);
                    break;
                case '3':
                    // search by comic runs
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.SetSearchStrategy(new RunsSearch());
                    database.Search(searchWord);
                    break;
                case '4':
                    // search by gap comics
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.SetSearchStrategy(new GapsSearch());
                    database.Search(searchWord);
                    break;
                case '5':
                    // browse all comics
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.setBrowseStrategy(new BrowseAll());
                    database.browse();
                    break;
                case '6':
                    // browse comics by page
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.setBrowseStrategy(new BrowseByPage(10));
                    database.browse();
                    break;
                case '7':
                    //get a list of all comics
                    List<Comic> comics = database.getFullList();
                    section = new PersonalDatabaseDisplayUI(signedIn,comics,database);
                    break;
                case 'b':
                case 'B':
                    if(signedIn){
                        section = new SignedInUI();
                    }
                    else{
                        section = new SignedOutUI();
                    }
                    break;
                default:
                    System.out.println("Charter not recognized, Please type on of the options");
                scanner.close();
            }
        }
        public UISection getSection() {
            return section;
        }
        public void printOptions(){
            System.out.println("    1: Search by Issue Number");
            System.out.println("    2: Search by Graded Comic");
            System.out.println("    3: Search by Runs of Comics");
            System.out.println("    4: Search by Gaps of Comics");
            System.out.println("    5: Browse All Comics");
            System.out.println("    6: Browse Comics by Page");
            System.out.println("    7: Display All");
            System.out.println("    B : Back");
        }
        public void printInfo(){
            System.out.println("Personal Database");
        }
    
}
