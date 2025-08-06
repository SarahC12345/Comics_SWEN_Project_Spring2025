package COMIX.UI;

import java.util.List;

import COMIX.PersonalDatabase;
import COMIX.ComicGrader.Comic;

public class PersonalDatabaseDisplayUI implements UISection{
    public UISection section;
        public boolean signedIn;
        public List<Comic> comics;
        public int startID;
        public PersonalDatabase database;
        public PersonalDatabaseDisplayUI(boolean signedIn, List<Comic> comics, PersonalDatabase database){
            this.signedIn = signedIn;
            this.comics = comics;
            this.database = database;
        }
        public void takeInput(char x){
            section = this;
            switch (x) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    System.out.println(x);
                    System.out.println(comics.get(startID+Character.getNumericValue(x)));
                    section = new ComicDisplayUI(signedIn, comics.get(Character.getNumericValue(x)+startID),database,false);
                    break;
                case 'p':
                case 'P':
                    if(startID != 0){
                        startID = startID - 10;
                    }
                    break;
                case 'n':
                case 'N':
                    if(startID+10 < comics.size()){
                        startID = startID + 10;
                    }
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
            }
        }
        public UISection getSection() {
            return section;
        }
        public void printOptions(){
            if(startID != 0){
                System.out.println("    P : Previous Page"); 
            }
            for(int i =0 ; i < 10 && i < comics.size(); i++){
                System.out.println("    "+i+" : "+comics.get(i+startID).getStoryTitle()); 
            }
            if(startID+10 < comics.size()){
                System.out.println("    N : Next Page");
            }
            System.out.println("    B : Back");
        }
        public void printInfo(){
            System.out.println("Personal Database");
        }
}
