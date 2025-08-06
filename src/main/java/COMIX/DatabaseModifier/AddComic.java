package COMIX.DatabaseModifier;

import java.io.FileWriter;
import java.io.IOException;

import COMIX.ComicGrader.Comic;

public class AddComic implements CommandInvoker{

    Comic comic;

    public AddComic(Comic comic){
        this.comic = comic;
        System.out.println(comic);
    }

    @Override
    public void execute(){
        //add the comic to the personalcomics.csv file
        try (FileWriter writer = new FileWriter("src/main/java/COMIX/data/personalcomics.csv", true)) {
            writer.append("\n");
            writer.append(comic.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo() {
        
    }
}