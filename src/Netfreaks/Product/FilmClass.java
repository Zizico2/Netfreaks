package Netfreaks.Product;

public class FilmClass extends AbstractProductClass implements Film{

    private int duration;


    public FilmClass(String title, int duration, String ageRestriction, int yearOfRelease, String genre, String[] cast) {
        super(title, yearOfRelease, ageRestriction, genre, cast);
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
