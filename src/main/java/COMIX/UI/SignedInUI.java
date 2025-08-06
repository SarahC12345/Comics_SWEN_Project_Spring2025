package COMIX.UI;

public class SignedInUI implements UISection {
        public UISection section;
        public void takeInput(char x){
            section = new SignedInUI();
            switch (x) {
                case '1':
                    section = new MasterDatabaseUI(true);
                    break;
                case '2':
                    section = new PersonalDatabaseUI(true);
                    break;
                case '3':
                    section = new SignedOutUI();
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
            System.out.println("    2: View Personal Database");
            System.out.println("    3: Sign Out");
            System.out.println("    Q : Quit");
        }
        public void printInfo(){
            //no info needed
        }
    
}
