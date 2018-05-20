package Netfreaks;

import Netfreaks.Product.Product;

public interface Netfreaks {

    String upload(Product[] products);

    void register(String[] accountInfo);

    void login(String email, String password, String device);

    void disconnect();

    void logout();

    void membership(String planName);

    void profile(String profileName, boolean normal, int ageRestriction);

    void select(String profileName);

    void watch(String title);

    void rate(String title, int rating);

    String infoaccount();

    String searchByGenre(String genreName);

    String searchByName(String participantsName);

    String searchByRate(int rate);
}
