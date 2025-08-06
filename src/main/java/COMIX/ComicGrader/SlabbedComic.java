package COMIX.ComicGrader;

import java.util.stream.Stream;

public class SlabbedComic extends GradeComic{
    boolean slabbed;
    public SlabbedComic(Comic comic){
        super(comic);
        slabbed = true;
    }
    public double getTotalValue(){
        return super.getTotalValue() * 2;
    }
    public boolean getSlabbedValue(){
        return slabbed;
    }
    public void setSlabbedValue(boolean _slabbed){
        slabbed = _slabbed;
    }
    @Override
    public String toString() {
        return Stream.of(
            comic.toString(),
            getSlabbedValue()
        ).map(String::valueOf)
         .reduce((a, b) -> a + ", " + b)
         .orElse("");
}}
