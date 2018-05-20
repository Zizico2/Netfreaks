package Netfreaks.Product;

public final class FilmClass extends AbstractProductClass implements Film{

    private String directorName;
    private int duration;


    public FilmClass(String title, String directorName, int duration, String ageRestriction, int yearOfRelease, String genre, String[] cast, int nCast) {
        super(title, yearOfRelease, ageRestriction, genre, cast, nCast);
        this.directorName = directorName;
        this.duration = duration;
    }

    @Override
    public String getDirector() {
        return directorName;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
