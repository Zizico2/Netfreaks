package Netfreaks.Account;

import Netfreaks.Product.Product;

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

    void addProfile(String profileName, boolean type, int ageRestriction);

    boolean hasProfile(String profileName);

    int getNProfiles();

    void selectProfile(String profileName);

    void setPlanType(PlanType type);

    PlanType getPlanType();

    String getName();

    void watch(String productName);

    void rate(Product product);

    String toString();
}
