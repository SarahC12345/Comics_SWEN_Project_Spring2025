package COMIX.ComicGrader;

import java.util.stream.Stream;

public class GradedComic extends GradeComic{
    int grade;
    public GradedComic(int grade, Comic comic){
        super(comic);
        this.grade = grade;
    }
    public double getTotalValue(){
        return super.getTotalValue() * grade;
    }
    public int getGrade(){
        return grade;
    }
    @Override
    public String toString() {
        return Stream.of(
            comic.toString(),
                getGrade()
        ).map(String::valueOf)
         .reduce((a, b) -> a + ", " + b)
         .orElse("");
    }
}
