package Netfreaks;

import Netfreaks.Product.Product;

public interface Netfreaks {

    String upload(Product[] products);

    void register(String name, String email, String password, String device );

    void login(String email);

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

    boolean isAClientLoggedIn();

    boolean isEmailUsed(String email);

    String getActiveProfile();

    String getActiveDevice();

    boolean isClientLoggedIn(String email);

    boolean isPasswordRight(String email, String password);

    boolean deviceNumberExceeded(String email, String device);


}
