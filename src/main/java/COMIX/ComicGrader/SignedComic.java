package COMIX.ComicGrader;

import java.util.stream.Stream;

public class SignedComic extends GradeComic{
    int signed = 0;
    public SignedComic(Comic comic){
        super(comic);
    }
    public double getTotalValue(){
        return super.getTotalValue() * 1.05;
    }
    public int getSignedValue(){
        return signed;
    }
    public void setSignedValue(int _signed){
        signed = _signed;
    }
    @Override
    public String toString() {
        return Stream.of(
                comic.toString(),
                getSignedValue()
        ).map(String::valueOf)
         .reduce((a, b) -> a + ", " + b)
         .orElse("");
    }
}
