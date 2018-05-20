package Netfreaks.Product;

public class SeriesClass extends AbstractProductClass implements Series {

    private String creatorName;
    private int nSeasons;
    private int nEpisodesPerSeason;

    public SeriesClass(String title, String creatorName, int nSeasons, int nEpisodesPerSeason, String ageRestriction, int yearOfRelease, String genre, String[] cast, int nCast) {
        super(title, yearOfRelease, ageRestriction, genre, cast, nCast);
        this.creatorName = creatorName;
        this.nSeasons = nSeasons;
        this.nEpisodesPerSeason = nEpisodesPerSeason;
    }

    @Override
    public String getCreatorName() {
        return creatorName;
    }

    @Override
    public int getNSeasons() {
        return nSeasons;
    }

    @Override
    public int getNEpisodesPerSeason() {
        return nEpisodesPerSeason;
    }


}
