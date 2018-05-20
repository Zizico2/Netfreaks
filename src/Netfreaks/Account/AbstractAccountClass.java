package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;

public class AbstractAccountClass implements Account {

    private String name;
    private String email;
    private String password;
    Profile[] profiles;
    int nDevicesAllowed;
    int nProfilesAllowed;

    AbstractAccountClass(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getNDevicesAllowed() {
        return nDevicesAllowed;
    }

    @Override
    public int getNProfilesAllowed() {
        return nProfilesAllowed;
    }
}
