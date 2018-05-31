package Netfreaks;

import Netfreaks.Account.Account;
import Netfreaks.Account.PlanType;
import Netfreaks.Product.Product;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public interface Netfreaks {

    int MAX_RATE = 5;

    /**
     *
     *
     * @param products
     * @return
     */
    SortedMap<String, Product> upload(Product[] products);

    /**
     *
     *
     * @param name
     * @param email
     * @param password
     * @param device
     */
    void register(String name, String email, String password, String device );

    /**
     *
     *
     * @param email
     * @param device
     */
    void login(String email, String device);

    /**
     *
     *
     */
    void disconnect();

    /**
     *
     *
     */
    void logout();

    /**
     *
     *
     * @param planName
     */
    void membership(String planName);

    /**
     *
     *
     * @param profileName
     * @param normal
     * @param ageRestriction
     */
    void profile(String profileName, boolean normal, int ageRestriction);

    /**
     *
     *
     * @param profileName
     */
    void select(String profileName);

    /**
     *
     * @param title
     */
    void watch(String title);

    /**
     *
     *
     * @param title
     * @param rating
     */
    void rate(String title, int rating);

    /**
     *
     *
     * @return
     */
    Account infoaccount();

    /**
     *
     *
     * @param genreName
     * @return
     */
    SortedMap<String,Product> searchByGenre(String genreName);

    /**
     *
     *
     * @param participantsName
     * @return
     */
    SortedSet<Product> searchByName(String participantsName);

    /**
     *
     *
     * @param rate
     * @return
     */
    List<SortedSet<Product>> searchByRate(int rate);

    /**
     *
     *
     * @return
     */
    boolean isAClientLoggedIn();

    /**
     *
     *
     * @param email
     * @return
     */
    boolean isEmailUsed(String email);

    /**
     *
     *
     * @return
     */
    String getActiveProfile();

    /**
     *
     *
     * @return
     */
    String getActiveDevice();

    /**
     *
     *
     * @param email
     * @return
     */
    boolean isClientLoggedIn(String email);

    /**
     *
     *
     * @param email
     * @param password
     * @return
     */
    boolean isPasswordRight(String email, String password);

    /**
     *
     *
     * @param email
     * @param device
     * @return
     */
    boolean deviceNumberExceeded(String email, String device);

    /**
     *
     *
     * @param email
     * @param device
     * @return
     */
    boolean needToRegisterDevice(String email, String device);

    /**
     *
     *
     * @param email
     * @param device
     */
    void registerDevice(String email, String device);

    /**
     *
     *
     * @return
     */
    PlanType getActiveProfilePlan();

    /**
     *
     *
     * @param membershipName
     * @return
     */
    boolean SameMembership(String membershipName);

    /**
     *
     *
     * @param membershipName
     * @return
     */
    boolean isDowngradePossible(String membershipName);

    /**
     *
     *
     * @param membershipName
     * @return
     */
    boolean isItDowngrade(String membershipName);

    /**
     *
     *
     * @param profileName
     * @return
     */
    boolean isSameProfile(String profileName);

    /**
     *
     *
     * @return
     */
    boolean profileNumberExceeded();

    /**
     *
     *
     * @param profile
     * @return
     */
    boolean hasProfile(String profile);

    /**
     *
     *
     * @return
     */
    String getActiveAccountName();

    /**
     *
     *
     * @return
     */
    boolean isThereProfileSelected();

    /**
     *
     *
     * @param productName
     * @return
     */
    boolean isThereAProductNamed(String productName);

    /**
     *
     *
     * @param productName
     * @return
     */
    boolean isPEGICompatible(String productName);

    /**
     *
     *
     * @param productName
     * @return
     */
    boolean isInRecentHistory(String productName);

    /**
     *
     *
     * @param productName
     * @return
     */
    boolean isProductRated(String productName);

    /**
     *
     *
     * @param genre
     * @return
     */
    boolean hasGenre(String genre);

    /**
     *
     *
     * @param name
     * @return
     */
    boolean hasDude(String name);

    /**
     *
     *
     * @param rate
     * @return
     */
    boolean hasShowsWithRateHigherThan(int rate);
}
