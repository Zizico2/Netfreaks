package Netfreaks;

import Netfreaks.Account.PlanType;
import Netfreaks.Product.Product;

import java.util.SortedMap;
import java.util.SortedSet;

public interface Netfreaks {

    SortedMap<String, Product> upload(Product[] products);

    void register(String name, String email, String password, String device );

    void login(String email, String device);

    void disconnect();

    void logout();

    void membership(String planName);

    void profile(String profileName, boolean normal, int ageRestriction);

    void select(String profileName);

    void watch(String title);

    void rate(String title, int rating);

    String infoaccount();

    SortedMap<String,Product> searchByGenre(String genreName);

    SortedSet<Product> searchByName(String participantsName);

    String searchByRate(int rate);

    boolean isAClientLoggedIn();

    boolean isEmailUsed(String email);

    String getActiveProfile();

    String getActiveDevice();

    boolean isClientLoggedIn(String email);

    boolean isPasswordRight(String email, String password);

    boolean deviceNumberExceeded(String email, String device);


    boolean needToRegisterDevice(String email, String device);

    void registerDevice(String email, String device);

    PlanType getActiveProfilePlan();

    boolean SameMembership(String membershipName);

    boolean isDowngradePossible(String membershipName);

    boolean isItDowngrade(String membershipName);

    boolean isSameProfile(String profileName);

    boolean profileNumberExceeded();

    boolean hasProfile(String profile);

    String getActiveAccountName();

    boolean isThereProfileSelected();

    boolean isThereAProductNamed(String productName);

    boolean isPEGICompatible(String productName);

    boolean isInRecentHistory(String productName);

    boolean isProductRated(String productName);

    boolean hasGenre(String genre);

    boolean hasDude(String name);
}
