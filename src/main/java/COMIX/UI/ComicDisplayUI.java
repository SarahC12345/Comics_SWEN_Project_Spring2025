1package COMIX.UI;


import java.util.Scanner;

import COMIX.DatabaseModifier.GradeComic;
import COMIX.PersonalDatabase;
import COMIX.ComicGrader.Comic;
import COMIX.ComicGrader.SlabbedComic;
import COMIX.DatabaseModifier.AddComic;
import COMIX.DatabaseModifier.AuthenticateComic;
import COMIX.DatabaseModifier.Command;
import COMIX.DatabaseModifier.RemoveComic;
import COMIX.DatabaseModifier.SignComic;
import COMIX.DatabaseModifier.SlabbComic;
import COMIX.ComicGrader.GradedComic;
import COMIX.ComicGrader.SignedComic;;

public class ComicDisplayUI implements UISection{
    public UISection section;
        Scanner scanner = new Scanner(System.in);
        public boolean signedIn;
        public Comic comic;
        public int startID;
        public PersonalDatabase database;
        public boolean isMaster;
        public ComicDisplayUI(boolean signedIn, Comic comic,PersonalDatabase database, boolean isMaster){
            this.signedIn = signedIn;
            this.comic = comic;
            this.isMaster = isMaster;
            this.database = database;
        }

        public void takeInput(char x){
            section = this;
            switch (x) {
                case 'r':
                case 'R':
                    if(signedIn && !isMaster){
                        database.setCommand(new RemoveComic(comic));
                        database.executeCommand(comic);
                    }
                case 'e':
                case 'E':
                    if(signedIn && !isMaster){
                        //database.setCommand(new EditComic());
                        //database.executeCommand(comic);
                    }
                        break;
                case 'b':
                case 'B':
                    if(isMaster){
                        section = new MasterDatabaseUI(signedIn);
                    }
                    else{
                        section = new PersonalDatabaseUI(signedIn);
                    }
                    break;
                case 'a':
                case 'A':
                    if(signedIn){
                        database.setCommand(new AddComic(comic));
                        database.executeCommand(comic);
                    }
                case 'g':
                case 'G':
                    if(signedIn){
                        System.out.print("What is your grade?: ");
                        var input = scanner.nextInt();
                        database.setCommand(new GradeComic(comic, input));
                        database.executeCommand(comic);
                    }
                case 'i':
                case 'I':
                    if(signedIn){
                        database.setCommand(new SignComic(comic));
                        database.executeCommand(comic);
                    }
                case 's':
                case 'S':
                    if(signedIn){
                        database.setCommand(new SlabbComic(comic));
                        database.executeCommand(comic);
                    }
                case 'u':
                case 'U':
                    if(signedIn){
                        database.setCommand(new AuthenticateComic(comic));
                        database.executeCommand(comic);
                    }
                default:
                    System.out.println("Charter not recognized, Please type on of the options");
            }
        }
        public UISection getSection() {
            return section;
        }
        public void printOptions(){
            System.out.println("    B : Back");
            if(signedIn && isMaster){
                System.out.println("    A : Add Comic to Your Collection");
            }
            if(signedIn && !isMaster){
                System.out.println("    R : Remove Comic from Your Collection");
            }
            if(signedIn && !isMaster){
                System.out.println("    E : Edit Comic");
            }
            if(signedIn && !isMaster){
                System.out.println("    G : Grade Comic");
            }
            if(signedIn && !isMaster && comic instanceof GradedComic){
                System.out.println("    S : Slabb Comic");
            }
            if(signedIn && !isMaster){
                System.out.println("    I : Sign Comic");
            }
            if(signedIn && !isMaster && comic instanceof SignedComic){
                System.out.println("    U : Authenticate Comic");
            }
            if(signedIn && !isMaster){
                //return if this comic is graded or signed
            }
        }
        public void printInfo(){
            System.out.println("Title: "+comic.getStoryTitle());
            System.out.println("Series: "+comic.getSeriesTitle());
            System.out.println("Publisher : "+comic.getPublisher());
            System.out.println("Vol : "+comic.getVolumeNumber()+ " Issue: "+comic.getIssueNumber());
            System.out.println("Publication Date : "+comic.getPublicationDate());
            if(signedIn && !isMaster){
                System.out.println("Value : "+comic.getTotalValue());
            }
        }
}
