package Netfreaks.Product;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public interface Product {

    String getTitle();

    int getYearOfRelease();

    int getPEGI();

    String getGenre();

    String[] getCast();

    void rate(String accountName, int rate);

    int getRate(String name);

    boolean isRatedBy(String currentProfile);

    String getMasterName();

    float getAverageRating();
}
