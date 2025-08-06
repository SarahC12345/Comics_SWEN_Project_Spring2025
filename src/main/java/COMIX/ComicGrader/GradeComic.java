package COMIX.ComicGrader;

import java.util.stream.Stream;

public abstract class GradeComic implements Comic{
    Comic comic;
    public GradeComic(Comic comic){
        this.comic = comic;
    }
    public double getTotalValue(){
        return comic.getTotalValue();
    }
    public String getPublisher(){
        return comic.getPublisher();
    }
    public String getSeriesTitle(){
        return comic.getSeriesTitle();
    }
    public String getStoryTitle(){
        return comic.getStoryTitle();
    }
    public int getVolumeNumber(){
        return comic.getVolumeNumber();
    }
    public String getIssueNumber(){
        return comic.getIssueNumber();
    }
    public String getPublicationDate(){
        return comic.getPublicationDate();
    }
    public String getCreators(){
        return comic.getCreators();
    }
    public String getPrincipleCharaters(){
        return comic.getPrincipleCharaters();
    }
    public String getDescription(){
        return comic.getDescription();
    }
}
