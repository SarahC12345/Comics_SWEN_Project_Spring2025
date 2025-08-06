package COMIX.Browse;

import java.util.List;
import java.util.Scanner;

import COMIX.ComicGrader.Comic;

public class BrowseByPage implements BrosweStrategy {
    private final int maxPageSize;

    public BrowseByPage(int maxPageSize){
        this.maxPageSize = maxPageSize;
    }

    @Override
    public void doBrowse(List<Comic> personalDatabase) {
        boolean loop = true;
        int totalPages = (int)Math.round((personalDatabase.size() / maxPageSize) + 0.5);
        int currentSpot = 0;
        int currentPage = 1;

        if(personalDatabase.isEmpty()){
            System.out.println("You currently have no comics in your collection");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Type \"exit\" to end browsing at anytime");
            
            while(loop){
                //creating page
                int pageStart = (currentPage - 1) * maxPageSize;
                int pageEnd = Math.min(pageStart + maxPageSize, personalDatabase.size()); //chooses either full next page or last coimc as page end
                for(int i = pageStart; i < pageEnd; i++){
                    System.out.println(personalDatabase.get(i));
                }

                //navigation
                System.out.println("Page:(" + currentPage + "/" + totalPages + ") Comics:(" + pageEnd + "/" + personalDatabase.size() + ")");
                System.out.println("< or >");
                String userInput = scanner.nextLine();

                //user choice on what happens next
                if(userInput.toLowerCase().equals("exit")){
                    loop = false;
                } else if (userInput.equals(">") && currentPage < totalPages) {
                    currentPage++;
                } else if (userInput.equals("<") && currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println("Invalid input or no further pages.");
                }
            }
        }
    }
}
