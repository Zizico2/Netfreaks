package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;

public class StandardClass extends AbstractAccountClass implements Netfreaks.Account.Tags.Standard {


    StandardClass(String name, String email, String password) {
        super(name, email, password);
        nDevicesAllowed = 2;
        nProfilesAllowed = 5;
        profiles = new Profile[nProfilesAllowed];
    }
}
