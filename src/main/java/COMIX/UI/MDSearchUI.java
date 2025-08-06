package COMIX.UI;

public class MDSearchUI implements UISection {
        public UISection section;
        public boolean signedIn;
        public char searchStrat;
        public String searchWord;
        public MDSearchUI(boolean signedIn){
            this.signedIn = signedIn;
        }
        public void takeInput(char x){
            section = new MDSearchUI(signedIn);
            switch (searchStrat) {
                case 'P':
                    // get a list of all comics from partial search 
                    searchWord = searchWord + "";
                    break;
                case 'C':
                    // get a list of all comics from complete search
                    searchWord = searchWord + "";
                    break;
                case 'D':
                    //get a list of all comics 
                case 'b':
                    if(signedIn){
                        section = new MasterDatabaseUI(signedIn);
                    }
                    else{
                        section = new MasterDatabaseUI(signedIn);
                    }
                    break;
                case 'B':
                    if(signedIn){
                        section = new MasterDatabaseUI(signedIn);
                    }
                    else{
                        section = new MasterDatabaseUI(signedIn);
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
            System.out.println("Searching...");
        }
        public void printInfo(){
            //no info needed
        }
    
}
