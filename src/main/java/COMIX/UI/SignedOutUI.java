package COMIX.UI;

public class SignedOutUI implements UISection {
        public UISection section;
        public void takeInput(char x){
            section = new SignedOutUI();
            switch (x) {
                case '1':
                    section = new MasterDatabaseUI(false);
                    break;
                case '2':
                    section = new SignedInUI();
                    break;
                case 'q':
                    section = new SignedOutUI();
                    break;
                case 'Q':
                    section = new SignedOutUI();
                    break;
                default:
                    System.out.println("Charter not recognized, Please type on of the options");
            }
        }
        public UISection getSection() {
            return section;
        }
        public void printOptions(){
            System.out.println("    1: View Master Database");
            System.out.println("    2: Sign In");
            System.out.println("    Q : Quit");
        }
        public void printInfo(){
            //no info needed
        }
    
}
