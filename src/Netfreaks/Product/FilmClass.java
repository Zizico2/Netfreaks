package Netfreaks.Product;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class FilmClass extends AbstractProductClass implements Film{

    private final int duration;


    public FilmClass(String title, int duration, String ageRestriction, int yearOfRelease, String genre, String[] cast) {
        super(title, yearOfRelease, ageRestriction, genre, cast);
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
