package COMIX.ComicGrader;

import java.util.stream.Stream;

public interface Comic {
    public double getTotalValue();
    public String getPublisher();
    public String getSeriesTitle();
    public String getStoryTitle();
    public int getVolumeNumber();
    public String getIssueNumber();
    public String getPublicationDate();
    public String getCreators();
    public String getPrincipleCharaters();
    public String getDescription();

    @Override
    public String toString();
}
