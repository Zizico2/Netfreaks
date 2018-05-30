package Netfreaks.Product;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class SeriesClass extends AbstractProductClass implements Series {

    private final int nSeasons;
    private final int nEpisodesPerSeason;

    public SeriesClass(String title, int nSeasons, int nEpisodesPerSeason, String ageRestriction, int yearOfRelease, String genre, String[] cast) {
        super(title, yearOfRelease, ageRestriction, genre, cast);
        this.nSeasons = nSeasons;
        this.nEpisodesPerSeason = nEpisodesPerSeason;
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
