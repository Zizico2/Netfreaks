package Netfreaks.Account;

import java.util.Arrays;

public interface Account {

    void disconnect();

    void logout();

    String getActiveProfile();

    String getPassword();

    boolean isDeviceListFull();

    String getActiveDevice();

    void login(String device);
}
