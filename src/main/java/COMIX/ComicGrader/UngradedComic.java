package COMIX.ComicGrader;

import java.util.stream.Stream;

public class UngradedComic implements Comic{
    double totalValue;
    String publisher;
    String seriesTitle;
    String storyTitle;
    int volumeNumber;
    String issueNumber;
    String publicationDate;
    String creators;
    String principleCharaters;
    String description;
    public UngradedComic(double totalValue,String publisher,String seriesTitle, String storyTitle, String publicationDate, String creators, String principleCharaters, String description, int volumeNumber, String issueNumber){
        this.totalValue = totalValue;
        this.publisher = publisher;
        this.seriesTitle = seriesTitle;
        this.storyTitle = storyTitle;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
        this.creators = creators;
        this.principleCharaters = principleCharaters;
        this.description = description;
    }
    public UngradedComic(String publisher,String seriesTitle, String storyTitle, String publicationDate,int volumeNumber, String issueNumber){
        this.publisher = publisher;
        this.seriesTitle = seriesTitle;
        this.storyTitle = storyTitle;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
    }
    public double getTotalValue(){
        return totalValue;
    }
    public String getPublisher(){
        return publisher;
    }
    public String getSeriesTitle(){
        return seriesTitle;
    }
    public String getStoryTitle(){
        return storyTitle;
    }
    public int getVolumeNumber(){
        return volumeNumber;
    }
    public String getIssueNumber(){
        return issueNumber;
    }
    public String getPublicationDate(){
        return publicationDate;
    }
    public String getCreators(){
        return creators;
    }
    public String getPrincipleCharaters(){
        return principleCharaters;
    }
    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return Stream.of(
                getSeriesTitle(),
                getIssueNumber(),
                getStoryTitle(),
                getDescription(),
                getPublisher(),
                getPublicationDate(),
                getCreators(),
                getPrincipleCharaters(),
                getTotalValue(),
                getVolumeNumber()
        ).map(String::valueOf)
         .reduce((a, b) -> a + ", " + b)
         .orElse("");
}}
