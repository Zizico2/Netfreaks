package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;
import Netfreaks.Product.Product;

import java.util.List;
import java.util.SortedMap;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public interface Account {

    boolean NORMAL = true;

    void disconnect();

    void logout();

    String getCurrentProfile();

    String getPassword();

    boolean isDeviceListFull();

    String getActiveDevice();

    void login(String device);

    boolean needToRegisterDevice(String device);

    void registerDevice(String device);

    int getNDevices();

    List<String> getDevices();

    void addProfile(String profileName, boolean type, int ageRestriction);

    boolean hasProfile(String profileName);

    int getNProfiles();

    void selectProfile(String profileName);

    void setPlanType(PlanType type);

    PlanType getPlanType();

    String getName();

    void watch(String productName);

    void rate(Product product);

    SortedMap<String,Profile> infoAccount();

    int getCurrentProfileAge();

    boolean isInHistory(String productName);
}
