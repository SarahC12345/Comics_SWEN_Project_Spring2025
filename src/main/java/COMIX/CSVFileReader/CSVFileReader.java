package COMIX.CSVFileReader;
import COMIX.ComicGrader.Comic;
import COMIX.ComicGrader.UngradedComic;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class CSVFileReader {
    //takes in a csv file address and returns a list of comics
    public static List<Comic> readFile(String file){
        try {
            List<Comic> data = new ArrayList<>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            // Reading until we run out of lines
            //int x = 0;
            String line = br.readLine();
            br.readLine();
            br.readLine();
            line = br.readLine();
            while (line != null) {
                Comic comic = splitLine(line);
                data.add(comic);
                line = br.readLine();
                //x++;
            }

            // prints comics out for testing
            /*for (Comic comic : data) {
                System.out.println(comic.getIssueNumber());
            }*/
            br.close();
            return data;
        } catch (Exception e) {
            System.out.println(e);
            List<Comic> data = new ArrayList<>();
            return data;
        }
    }
    //takes a line and turns it into a comic
    public static Comic splitLine(String line){
        //takes a line and turns it into an array
        String[] array = {"","","","","","","","","","","","","",""};
        int pointer = 0;
        boolean quotes = false;
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '"'){
                quotes = !quotes;
            }
            else if(line.charAt(i) == ',' && !quotes){
                pointer++;
                array[pointer] = "";
            }
            else{
                array[pointer] += line.charAt(i);
            }
        }
        //turns that array into a comic
        int vol = 0;
        if(array[0].indexOf(" Vol. ") != -1){
            vol = (int)array[0].charAt(array[0].indexOf(" Vol. ")+6);
            array[0] = array[0].substring(0,array[0].indexOf(" Vol. ")-1);
            vol = vol - 48;
        }
        Comic comic = new UngradedComic(array[4],array[0],array[2],array[5],vol,array[1]);
        return comic;
    }
}
