package COMIX.DatabaseModifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import COMIX.ComicGrader.Comic;

public class RemoveComic implements CommandInvoker{

    Comic comic;

    public RemoveComic(Comic comic) {
        this.comic = comic;
    }

    @Override
    public void execute(){
        // Remove the comic from the personalcomics.csv file without creating a new file
        try {
            File inputFile = new File("src/main/java/COMIX/data/personalcomics.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            StringBuilder inputBuffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
            // Check if the line contains the comic to be removed
            if (!line.contains(comic.toString())) {
                inputBuffer.append(line);
                inputBuffer.append(System.lineSeparator());
            }
            }
            reader.close();

            // Write the updated content back to the file
            FileOutputStream fileOut = new FileOutputStream(inputFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
           // return comic;

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void undo() {
    }
}