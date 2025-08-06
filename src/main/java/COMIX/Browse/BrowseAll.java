package COMIX.Browse;

import java.util.List;

import COMIX.ComicGrader.Comic;

public class BrowseAll implements BrosweStrategy{
    @Override
    public void doBrowse(List<Comic> personalDatabase) {
        System.out.println(personalDatabase);
        if(!personalDatabase.isEmpty()){
            personalDatabase.forEach(System.out::println);
        } else {
            System.out.println("You currently have no comics in your collection");
        }

    }
}