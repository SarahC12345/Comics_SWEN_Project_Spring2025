package COMIX.UI;

import java.util.List;
import java.util.Scanner;

import COMIX.MasterDatabase;
import COMIX.ComicGrader.Comic;
import COMIX.ComicGrader.UngradedComic;
import COMIX.DatabaseModifier.AddComic;
import COMIX.Search.FullSearch;
import COMIX.Search.PartialSearch;
import COMIX.Search.SortSearch.SortbyIssueNumber;

public class ComixUI {
    public static void main(String[] args) {
        char input = 1;
        UISection section = new SignedOutUI();
        Scanner scanner = new Scanner(System.in);
        while (input != 'Q' && input !='q'){
            printLogo();
            section.printInfo();
            System.out.println("\033[0;1m" + "Options :" + "\033[22m");
            section.printOptions();
            System.out.print("Enter Option: ");
            input = scanner.nextLine().charAt(0);
            section.takeInput(input);
            section = section.getSection();
        }
        scanner.close();
        
    }
    public static void printLogo(){
        System.out.println("\r\n" + //
                        "                                 /////|\r\n" + //
                        "                                ///// |\r\n" + //
                        "                               |~~~|  |\r\n" + //
                        "                               |===|  |\r\n" + //
                        "                               |C  |  |\r\n" + //
                        "██████████████████████████████ " + "| O |  |\r\n" + //
                        "█─▄▄▄─█─▄▄─█▄─▀█▀─▄█▄─▄█▄─▀─▄█ " + "|  M| /\r\n" + //
                        "█─███▀█─██─██─█▄█─███─███▀─▀██ " + "|===|/\r\n" +//
                        "▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▀▄▄▄▀▄▄▄▀▄▄█▄▄▀ " + "'---'");
    }
    
}
