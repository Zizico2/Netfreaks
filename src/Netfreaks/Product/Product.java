package Netfreaks.Product;

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
