package Netfreaks.Account;

public interface Account {

    boolean NORMAL = true;

    void disconnect();

    void logout();

    String getActiveProfile();

    String getPassword();

    boolean isDeviceListFull();

    String getActiveDevice();

    void login(String device);

    boolean needToRegisterDevice(String device);

    void registerDevice(String device);

    int getNDevices();

    void addProfile(String profileName, boolean type, int ageRestriction);

    boolean hasProfile(String profileName);

    int getNProfiles();

    void changeProfileTo(String profileName);

    String getName();
}
