package COMIX.UI;

import java.util.List;
import java.util.Scanner;

import COMIX.MasterDatabase;
import COMIX.ComicGrader.Comic;
import COMIX.DatabaseModifier.Command;
import COMIX.Search.FullSearch;
import COMIX.Search.PartialSearch;
import COMIX.Search.SearchStrategy;
import COMIX.Search.SortSearch.SortByPublicationDate;
import COMIX.Search.SortSearch.SortbyIssueNumber;
import COMIX.Search.SortSearch.SortbyTitle;
import COMIX.Search.SortSearch.SortbyVolume;
import COMIX.Search.SortSearch.SortStrategy;

public class MasterDatabaseUI implements UISection {
    private SearchStrategy search;
    private SortStrategy sort;
    private List<Comic> comics;

        public void SetSearchStrategy(SearchStrategy searchStrategy){
            this.search = searchStrategy;
        }
        
        public void SetSortStrategy(SortStrategy sortStrategy){
            this.sort = sortStrategy;
        }
        
        public UISection section;
        public boolean signedIn;
        public MasterDatabaseUI(boolean signedIn){
            this.signedIn = signedIn;
        }
        public void takeInput(char x){
            Scanner scanner = new Scanner(System.in);
            section = new MasterDatabaseUI(signedIn);
            MasterDatabase database = new MasterDatabase();
            String searchWord;
            switch (x) {
                case '1':
                    // get a list of all comics from partial search 
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.SetSearchStrategy(new PartialSearch());
                    database.Search(searchWord);
                    promptSort( scanner,database,comics);
                    break;
                case '2':
                    // get a list of all comics from complete search
                    System.out.print("Search:");
                    searchWord = scanner.nextLine();
                    database.SetSearchStrategy(new FullSearch());
                    database.Search(searchWord);
                    promptSort( scanner,database,comics);

                    break;
                case '3':
                    //get a list of all comics
                    List<Comic> comics = database.getFullList();
                    section = new MasterDatabaseDisplayUI(signedIn,comics);
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
            System.out.println("    1: Search by Partial");
            System.out.println("    2: Search by Full Name");
            System.out.println("    3: Display All");
            System.out.println("    B : Back");
        }
        public void printInfo(){
            System.out.println("Master Database");
        }

        public void Sort(List<Comic> results){
            results = sort.sort(results); 
            for (int i = 0; i < 10; i++){
              System.out.println(results.get(i));
            }
          }

        public List<Comic> Search(String query){
            List<Comic> results = search.Search(comics, query);
            for (int i = 0; i < 10; i++){
              System.out.println(results.get(i));
            }
            System.out.println(results.size());
            return results;
          }
          private void promptSort(Scanner scanner, MasterDatabase database, List<Comic> results) {
    System.out.println("\nHow would you like to sort the results?");
    System.out.println("    1: By Series Title");
    System.out.println("    2: By Volume");
    System.out.println("    3: By Issue Number");
    System.out.println("    4: By Publication Date");
    System.out.print("Enter choice: ");

    String sortChoice = scanner.nextLine();

    switch (sortChoice) {
        case "1":
            database.SetSortStrategy(new SortbyTitle()); 
            break;
        case "2":
            database.SetSortStrategy(new SortbyVolume());
            break;
        case "3":
            database.SetSortStrategy(new SortbyIssueNumber());
            break;
        case "4":
            database.SetSortStrategy(new SortByPublicationDate());
            break;
        default:
            System.out.println("Invalid sort option. Displaying unsorted results.");
    }

    results = database.Sort(results);
    for (Comic comic : results) {
        System.out.println(comic);
    }

    System.out.println("\nTotal results: " + results.size());
}
}
