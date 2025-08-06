package COMIX.ComicGrader;

import java.util.stream.Stream;

public class AuthenticatedComic extends GradeComic{
    boolean authenticated;
    public AuthenticatedComic(Comic comic){
        super(comic);
        authenticated = true;
    }

    public double getTotalValue(){
        return super.getTotalValue() * 1.2;
    }

    public boolean getAuthenticatedValue() {
        return authenticated;
    }
    
    public void setAuthenticatedValue(boolean _authenticated){
        authenticated = _authenticated;
    }
    @Override
    public String toString() {
        return Stream.of(
                comic.toString(),
                getAuthenticatedValue()
        ).map(String::valueOf)
         .reduce((a, b) -> a + ", " + b)
         .orElse("");
    }
}
